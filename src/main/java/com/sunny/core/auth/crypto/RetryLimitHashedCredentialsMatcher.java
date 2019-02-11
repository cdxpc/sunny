package com.sunny.core.auth.crypto;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Value;

import com.sunny.core.auth.token.CaptchaUsernamePasswordToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	@Value("${sunny.user.password.maxRetryCount}")
	private String maxRetryCount; // 密码输入错误最大次数限制
	@Value("${sunny.user.password.interval}")
	private String interval; // 密码输入错误次数超过最大限制时，下次输入有效时的最短间隔时间（毫秒）
	
	private Cache<String, AtomicInteger> loginRecordCount;
	private Cache<String, Long> retryTime;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		this.loginRecordCount = cacheManager.getCache("loginRecordCount");
		this.retryTime = cacheManager.getCache("retryTime");
	}
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		CaptchaUsernamePasswordToken cupToken = (CaptchaUsernamePasswordToken) token;
		String username = cupToken.getUsername();
		// 从缓存中获取用户尝试登录的次数
		AtomicInteger retryCount = loginRecordCount.get(username);
		// 如果之前没有登录过，则创建一个登录记录缓存
		if(retryCount == null) {
			retryCount = new AtomicInteger(0);
			loginRecordCount.put(username, retryCount);
		}
		// 比较当前尝试的次数是否超过最大受限登录次数
		if(retryCount.incrementAndGet() >= NumberUtils.toInt(maxRetryCount)) {
			Long retryTimeBegin = Long.valueOf(System.currentTimeMillis());
			Long retry = retryTime.get(username);
			// 如果超过了受限次数，则比较受限时间是否已过，如果还在受限时间范围内，限制登录
			if(retryTimeBegin - retry < NumberUtils.toLong(interval)) {
				throw new ExcessiveAttemptsException();
			}
			loginRecordCount.put(username, new AtomicInteger(2));
		}
		boolean match = super.doCredentialsMatch(token, info);
		if(log.isDebugEnabled()) {
			log.debug("登录匹配结果：" + match);
		}
		if(match) {
			loginRecordCount.remove(username);
			retryTime.remove(username);
		} else {
			retryTime.put(username, Long.valueOf(System.currentTimeMillis()));
		}
		return match;
	}
}

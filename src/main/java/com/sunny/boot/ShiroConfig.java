package com.sunny.boot;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sunny.core.auth.crypto.RetryLimitHashedCredentialsMatcher;
import com.sunny.core.filter.CaptchaFilter;
import com.sunny.module.login.realm.SimpleLoginRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	// Session超时时间，单位为毫秒（默认30分钟）
	@Value("${sunny.shiro.session.timeout}")
	private int timeout;

	// 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
	@Value("${sunny.shiro.session.validationInterval}")
	private int validationInterval;

	// 设置Cookie的域名
	@Value("${sunny.shiro.cookie.domain}")
	private String domain;

	// 设置cookie的有效访问路径
	@Value("${sunny.shiro.cookie.path}")
	private String path;

	// 设置HttpOnly属性
	@Value("${sunny.shiro.cookie.httpOnly}")
	private boolean httpOnly;

	// 设置Cookie的过期时间，秒为单位
	@Value("${sunny.shiro.cookie.maxAge}")
	private int maxAge;

	// 登录地址
	@Value("${sunny.shiro.login.loginUrl}")
	private String loginUrl;

	// 权限认证失败地址
	@Value("${sunny.shiro.login.unauthorizedUrl}")
	private String unauthorizedUrl;
	
	@Autowired
	private CaptchaFilter captchaFilter;
	
	/**
	 * 缓存管理器 使用Ehcache实现
	 */
	@Bean
	public EhCacheManager ehCacheManager() {
		EhCacheManager ecm = new EhCacheManager();
		ecm.setCacheManagerConfigFile("classpath:cache/ehcache-shiro.xml");
		return ecm;
	}

	/**
	 * 缓存管理器
	 * 
	 * @return
	 */
	@Bean
	public RetryLimitHashedCredentialsMatcher credentialsMatcher() {
		RetryLimitHashedCredentialsMatcher credentialsMatcher = new RetryLimitHashedCredentialsMatcher(
				ehCacheManager());
		credentialsMatcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
		credentialsMatcher.setHashIterations(3);
		credentialsMatcher.setStoredCredentialsHexEncoded(true); // 默认是true,可以不设置
		return credentialsMatcher;
	}

	/**
	 * 自定义Realm
	 */
	@Bean
	public SimpleLoginRealm loginRealm() {
		SimpleLoginRealm realm = new SimpleLoginRealm();
		realm.setCredentialsMatcher(credentialsMatcher());
		realm.setCachingEnabled(true);
		return realm;
	}

//	/**
//	 * 自定义sessionDAO会话
//	 */
//	@Bean
//	public OnlineSessionDAO sessionDAO() {
//		OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
//		return sessionDAO;
//	}
//
//	/**
//	 * 自定义sessionFactory会话
//	 */
//	@Bean
//	public OnlineSessionFactory sessionFactory() {
//		OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
//		return sessionFactory;
//	}

	/**
	 * 自定义sessionFactory调度器
	 */
//	@Bean
//	public SpringSessionValidationScheduler sessionValidationScheduler() {
//		SpringSessionValidationScheduler sessionValidationScheduler = new SpringSessionValidationScheduler();
//		// 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
//		sessionValidationScheduler.setSessionValidationInterval(validationInterval * 60 * 1000);
//		return sessionValidationScheduler;
//	}

	/**
	 * 会话管理器
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager manager = new DefaultWebSessionManager();
		// 加入缓存管理器
		manager.setCacheManager(ehCacheManager());
		// 删除过期的session
		manager.setDeleteInvalidSessions(true);
		// 设置全局session超时时间
		manager.setGlobalSessionTimeout(timeout * 60 * 1000);
		// 去掉 JSESSIONID
		manager.setSessionIdUrlRewritingEnabled(false);
		// 定义要使用的无效的Session定时调度器
//		manager.setSessionValidationScheduler(sessionValidationScheduler());
		// 是否定时检查session
		manager.setSessionValidationSchedulerEnabled(true);
		// 自定义SessionDao
//		manager.setSessionDAO(sessionDAO());
		// 自定义sessionFactory
//		manager.setSessionFactory(sessionFactory());
		return manager;
	}

	/**
	 * 安全管理器
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 设置realm.
		securityManager.setRealm(loginRealm());
		// 记住我
		securityManager.setRememberMeManager(rememberMeManager());
		// 注入缓存管理器;
		securityManager.setCacheManager(ehCacheManager());
		// session管理器
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	/**
	 * Shiro过滤器配置
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 身份认证失败，则跳转到登录页面的配置
		shiroFilterFactoryBean.setLoginUrl(loginUrl);
		// 权限认证失败，则跳转到指定页面
		shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);
		// Shiro连接约束配置，即过滤链的定义
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 对静态资源设置匿名访问
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/sunny.png", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
//		filterChainDefinitionMap.put("/docs/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/plugins/**", "anon");
		filterChainDefinitionMap.put("/profile/**", "anon");
		filterChainDefinitionMap.put("/sunny/**", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		// 获取验证码
		filterChainDefinitionMap.put("/captcha", "anon");
		// 退出 logout地址，shiro去清除session
		filterChainDefinitionMap.put("/logout", "logout");
		// 不需要拦截的访问
		filterChainDefinitionMap.put("/login", "anon,captcha");

		Map<String, Filter> filters = new LinkedHashMap<>();
//		filters.put("onlineSession", onlineSessionFilter());
//		filters.put("syncOnlineSession", syncOnlineSessionFilter());
		filters.put("captcha", captchaFilter);
		shiroFilterFactoryBean.setFilters(filters);

		// 所有请求需要认证
		filterChainDefinitionMap.put("/**", "user");
		// 系统请求记录当前会话
//		filterChainDefinitionMap.put("/main", "onlineSession,syncOnlineSession");
//		filterChainDefinitionMap.put("/system/**", "onlineSession,syncOnlineSession");
//		filterChainDefinitionMap.put("/monitor/**", "onlineSession,syncOnlineSession");
//		filterChainDefinitionMap.put("/tool/**", "onlineSession,syncOnlineSession");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		return shiroFilterFactoryBean;
	}

//	/**
//	 * 	自定义在线用户处理过滤器
//	 */
//	@Bean
//	public OnlineSessionFilter onlineSessionFilter() {
//		OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
//		onlineSessionFilter.setLoginUrl(loginUrl);
//		return onlineSessionFilter;
//	}
//
//	/**
//	 * 	自定义在线用户同步过滤器
//	 */
//	@Bean
//	public SyncOnlineSessionFilter syncOnlineSessionFilter() {
//		SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
//		return syncOnlineSessionFilter;
//	}

	/**
	 * cookie 属性设置
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setHttpOnly(httpOnly);
		cookie.setMaxAge(maxAge * 24 * 60 * 60); // 记住我cookie缓存时间长,单位为：天
		return cookie;
	}

	/**
	 * 记住我
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		cookieRememberMeManager.setCipherKey(Base64.decode("pMvTx48hLt+/C8D+cM5vR=="));
		return cookieRememberMeManager;
	}

	/**
	 * 开启Shiro代理
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
		proxyCreator.setProxyTargetClass(true);
		return proxyCreator;
	}

	/**
	 * 开启Shiro注解通知器
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager());
		return advisor;
	}
	
	/**
	 * thymeleaf模板引擎和shiro框架的整合
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

}

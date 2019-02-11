package com.sunny.boot;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码 boot配置
 * @author tlbank
 *
 */
@Configuration
public class CaptchaConfig {
	
	/**
	 * 文字验证码
	 * @return
	 */
	@Bean(name = "charKaptcha")
	public DefaultKaptcha charKaptcha() {
		String[] custChar = {"28","kaptchaCode","35","5"};
		return getKaptcha(new Properties(), custChar);
	}
	
	/**
	 * 算术验证码
	 * @return
	 */
	@Bean(name = "mathKaptcha")
	public DefaultKaptcha mathKaptcha() {
		String[] custMath = {"38","kaptchaCodeMath","5","6"};
		Properties properties = new Properties();
		properties.setProperty("kaptcha.textproducer.impl", "com.sunny.core.auth.captcha.MathTextCreator");
		properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
		properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.ShadowGimpy");
		return getKaptcha(properties, custMath);
	}
	
	/**
	 * 图片验证码
	 * @return
	 */
	@Bean(name = "imageKaptcha")
	public DefaultKaptcha imageKaptcha() {
		// TODO 图片验证码的实现...
		return null;
	}
	
	private DefaultKaptcha getKaptcha(Properties properties, String[] customs) {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		for(int i = 0; i < customs.length; i++) {
			properties.setProperty("kaptcha.textproducer.font.size", customs[0]);
			properties.setProperty("kaptcha.session.key", customs[1]);
			properties.setProperty("kaptcha.textproducer.char.spac", customs[2]);
			properties.setProperty("kaptcha.textproducer.char.length", customs[3]);
		}
		properties.setProperty("kaptcha.border", "yes");
		properties.setProperty("kaptcha.border.color", "105,179,90");
		properties.setProperty("kaptcha.textproducer.font.color", "blue");
		properties.setProperty("kaptcha.image.width", "160");
		properties.setProperty("kaptcha.image.height", "60");
		properties.setProperty("kaptcha.textproducer.font.names", "Arial,Courier");
		properties.setProperty("kaptcha.noise.color", "white");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}

}

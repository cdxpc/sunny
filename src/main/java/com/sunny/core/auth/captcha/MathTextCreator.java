package com.sunny.core.auth.captcha;

import java.util.Random;
import com.google.code.kaptcha.text.impl.DefaultTextCreator;

/**
 * 算术验证码生成器
 *
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月13日
 * @since 1.0.0v
 */
public class MathTextCreator extends DefaultTextCreator {

	private static final String[] CNUMBERS = "0,1,2,3,4,5,6,7,8,9,10".split(",");

	@Override
	public String getText() {
		int result;
		Random random = new Random();
		int x = random.nextInt(10);
		int y = random.nextInt(10);
		StringBuilder suChinese = new StringBuilder();
		int randomNum = (int) Math.round(Math.random() * 2);
		if (randomNum == 0) {
			result = x * y;
			suChinese.append(CNUMBERS[x]);
			suChinese.append("*");
			suChinese.append(CNUMBERS[y]);
		} else if (randomNum == 1) {
			if (!(x == 0) && y % x == 0) {
				result = y / x;
				suChinese.append(CNUMBERS[y]);
				suChinese.append("/");
				suChinese.append(CNUMBERS[x]);
			} else {
				result = x + y;
				suChinese.append(CNUMBERS[x]);
				suChinese.append("+");
				suChinese.append(CNUMBERS[y]);
			}
		} else if (randomNum == 2) {
			if (x >= y) {
				result = x - y;
				suChinese.append(CNUMBERS[x]);
				suChinese.append("-");
				suChinese.append(CNUMBERS[y]);
			} else {
				result = y - x;
				suChinese.append(CNUMBERS[y]);
				suChinese.append("-");
				suChinese.append(CNUMBERS[x]);
			}
		} else {
			result = x + y;
			suChinese.append(CNUMBERS[x]);
			suChinese.append("+");
			suChinese.append(CNUMBERS[y]);
		}
		suChinese.append("=?@").append(result);
		return suChinese.toString();
	}

}

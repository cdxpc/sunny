package com.sunny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * 该类是为了支持使用打成jar包的方式进行部署<br/>
 * 	该启动类<br/>
 * 		如果是放在最外层的包下，不需要指定扫包路径，默认是扫描本包及其所有的子包<br/>
 * 		如果不是放在最外层包下，则需要指定扫包路径，否则会出现其他需要扫描的包而扫描不到的情况<br/>
 * 
 * @author cdxpc <cdxpc2018@163.com>, <br/>
 * 		   kevin.chen <crsfyc-9@163.com>
 * @date 2019年2月11日
 * @since 1.0.0v
 */
@SpringBootApplication // (scanBasePackages = {"com.sunny"})
@Slf4j
public class SpringBootStarter {
	
	public static void main(String[] args) {
		long beginTime = System.currentTimeMillis();
		SpringApplication.run(SpringBootStarter.class, args);
		long endTime = System.currentTimeMillis();
		log.info("启动成功，耗时：" + (endTime - beginTime) + "毫秒");
	}
}

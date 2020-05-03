package com.quickly.devploment.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @ClassName YmlValueConfig
 * @Description
 * @Author LiDengJin
 * @Date 2020/2/22 18:27
 * @Version V-1.0
 **/
@Configuration
@Log4j2
public class YmlValueConfig {
	@Value("${app.name}")
	private String appName;

	@PostConstruct
	public void init(){
		log.warn("this is app name : {}", appName);
	}
}

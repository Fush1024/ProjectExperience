package com.quickly.devploment.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AppPropertiesConfig
 * @Description
 * @Author LiDengJin
 * @Date 2020/2/22 18:40
 * @Version V-1.0
 **/
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
@Component
public class AppPropertiesConfig {
	String name;

	@Getter
	@Setter
	@ToString
	static class AppProperties {
		String name;

	}
}

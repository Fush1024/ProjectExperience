package com.quickly.devploment.comfig;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ConfigOfDruidMonitor
 * @Description
 * @Author LiDengJin
 * @Date 2020/4/18 15:39
 * @Version V-1.0
 **/
@Component
public class ConfigOfDruidMonitor {
	/**
	 * 配置druid管理后台的servlet
	 *
	 * @return
	 */
	@Bean
	public ServletRegistrationBean statViewSerlvet() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String, Object> initParameters = new HashMap<>();
		initParameters.put("loginUsername", "admin");
		initParameters.put("loginPassword", "123456");
		bean.setInitParameters(initParameters);
		return bean;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
		filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));

		Map<String, Object> initParams = new HashMap<>();
		initParams.put("exclusions", "*.js,*.css,/druid/*");
		filterRegistrationBean.setInitParameters(initParams);
		return filterRegistrationBean;
	}
}

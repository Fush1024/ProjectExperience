package com.quickly.devploment;

import com.quickly.devploment.config.AppPropertiesConfig;
import com.quickly.devploment.pojo.UserPojo;
import lombok.extern.log4j.Log4j2;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.quickly.devploment"})
@MapperScan("com.quickly.devploment.mapper")
@Log4j2
public class DevplomentApplication implements InitializingBean {


	@Autowired
	private AppPropertiesConfig appPropertiesConfig;

	private List<UserPojo> userPojoList = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(DevplomentApplication.class, args);
	}

	@Autowired
	private DataSource dataSource;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.warn("the app name is : {}", appPropertiesConfig.getName());
//		for (int i = 0; i < 10000; i++) {
//			userPojoList.add(new UserPojo());
//		}
		System.out.println(dataSource);

	}
}

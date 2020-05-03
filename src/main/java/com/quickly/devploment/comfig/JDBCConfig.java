package com.quickly.devploment.comfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.jdbc.Driver;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @ClassName JDBCConfig
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/30 10:10
 * @Version V-1.0
 **/
//@Configuration
public class JDBCConfig {

//	@Bean
	public DataSource getDataSourdce(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("xiaoxingxing");
		dataSource.setUrl("jdbc:mysql://120.78.140.235:3306/stu");
		try {
			dataSource.setDriver(new Driver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}



}

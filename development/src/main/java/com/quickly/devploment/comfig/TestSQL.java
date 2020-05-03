package com.quickly.devploment.comfig;

import com.quickly.devploment.pojo.StudentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * @ClassName TestSQL
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/30 10:33
 * @Version V-1.0
 **/
@Component
public class TestSQL {

	@Autowired
	public DataSource dataSource;


	public List<StudentPojo> getStudent(Integer pageNum, Integer pageSize) {
		try (Connection connection = dataSource.getConnection();) {
			String selectStudent = "select * from student limit " + pageNum + "," + pageSize;
			Statement statement = connection.createStatement();
			boolean execute = statement.execute(selectStudent);
			ResultSet resultSet = statement.getResultSet();

			while (resultSet.next()) {
				String name = resultSet.getString("sname");
				System.out.println(name);
			}
			return null;
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		return null;

	}
}

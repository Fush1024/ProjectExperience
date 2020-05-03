package com.quickly.devploment.mapper;

import com.quickly.devploment.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

/**
 * @ClassName StudentMapper
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/26 11:10
 * @Version V-1.0
 **/
public interface StudentMapper {
	List<StudentPojo> getAllStudentPojo(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

	void geuAllStudentPojoByStream(@Param("pageNum") Integer pageNum,
			@Param("pageSize") Integer pageSize, ResultHandler<StudentPojo> handler);
}

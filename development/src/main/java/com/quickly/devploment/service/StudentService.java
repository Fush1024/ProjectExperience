package com.quickly.devploment.service;

import com.quickly.devploment.pojo.StudentPojo;

import java.util.List;

/**
 * @ClassName StudentService
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/26 11:09
 * @Version V-1.0
 **/
public interface StudentService {
	List<StudentPojo> geuAllStudentPojo(Integer pageNum, Integer pageSize);
	List<StudentPojo> geuAllStudentPojoByStream(Integer pageNum, Integer pageSize);
}

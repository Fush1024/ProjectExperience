package com.quickly.devploment.controller;

import com.quickly.devploment.pojo.StudentPojo;
import com.quickly.devploment.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName UserController
 * @Description
 * @Author LiDengJin
 * @Date 2019/9/30 11:11
 * @Version V-1.0
 **/
@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentService studentService;

	/**
	 *  获取用户名称
	 * @param
	 * @return
	 */
	@RequestMapping("/getAllstudent")
	@ResponseBody
	public List<StudentPojo> getAllStudent(Integer pageNum,Integer pageSize){
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 500 : pageSize;
		return studentService.geuAllStudentPojo(pageNum,pageSize);
	}


	@RequestMapping("/getAllstudentByStream")
	@ResponseBody
	public List<StudentPojo> getAllStudentByStream(Integer pageNum,Integer pageSize){
		pageNum = pageNum == null ? 1 : pageNum;
		pageSize = pageSize == null ? 500 : pageSize;
		return studentService.geuAllStudentPojoByStream(pageNum,pageSize);
	}

}

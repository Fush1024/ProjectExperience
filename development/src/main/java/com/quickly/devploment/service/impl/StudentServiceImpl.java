package com.quickly.devploment.service.impl;

import com.quickly.devploment.init.MyApplicationContext;
import com.quickly.devploment.mapper.StudentMapper;
import com.quickly.devploment.pojo.StudentPojo;
import com.quickly.devploment.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName StudentServiceImpl
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/26 11:10
 * @Version V-1.0
 **/

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

//	@Autowired
//	private StudentMapper studentMapper;

	@Override
	public List<StudentPojo> geuAllStudentPojo(Integer pageNum, Integer pageSize) {
		Long startTime = System.currentTimeMillis();
		StudentMapper studentMapper = MyApplicationContext.getBean("studentMapper");
		List<StudentPojo> allStudentPojo = studentMapper.getAllStudentPojo(pageNum, pageSize);
		Runtime run = Runtime.getRuntime();
		log.warn("JVM最大的内存量, {}" , run.maxMemory());
		log.warn("JVM的空闲内容量, {}" , run.freeMemory());
		log.warn("JVM的总内存, {}" , run.totalMemory());

		log.error("耗费时间 times = {} s",(System.currentTimeMillis() - startTime)/1000 );

		return allStudentPojo;
	}

	@Override
	public List<StudentPojo> geuAllStudentPojoByStream(Integer pageNum, Integer pageSize) {
		Long startTime = System.currentTimeMillis();
		List<StudentPojo> allStudentPojo = new ArrayList<>();
		StudentMapper studentMapper = MyApplicationContext.getBean("studentMapper");
		studentMapper.geuAllStudentPojoByStream(pageNum, pageSize, (h)->{
			allStudentPojo.add(h.getResultObject());
		});
		Runtime run = Runtime.getRuntime();
		log.warn("JVM最大的内存量, {}" , run.maxMemory());
		log.warn("JVM的空闲内容量, {}" , run.freeMemory());
		log.warn("JVM的总内存, {}" , run.totalMemory());
		System.out.println("This are water");
		log.error("耗费时间 times = {} s",(System.currentTimeMillis() - startTime)/1000 );
		return allStudentPojo;	}
}

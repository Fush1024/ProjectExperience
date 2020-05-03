package com.quickly.devploment;

import com.quickly.devploment.comfig.TestSQL;
import com.quickly.devploment.google.GoogleAuthenticator;
import com.quickly.devploment.pojo.StudentPojo;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AnnotationAppStart
 * @Description
 * @Author LiDengJin
 * @Date 2019/10/17 10:32
 * @Version V-1.0
 **/
public class AnnotationAppStart {
	@Test
	public void testAnnotation() {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(
				"com.quickly.devploment.comfig");
		TestSQL testSQL = annotationConfigApplicationContext.getBean(TestSQL.class);
		List<StudentPojo> student = testSQL.getStudent(1, 10);
	}

	@Test
	public void testMap(){
		Map<String,String> map = new HashMap<>();
		map = null;
		if(map.isEmpty()){
			System.out.println("1");
		}else {
			System.out.println("22");
		}
	}

	@Test
	public void testGoogleAuthenticator(){
		String secret = GoogleAuthenticator.generateSecretKey();
		String qrcode = GoogleAuthenticator.getQRBarcodeURL("Java技术栈", "120.78.140.235", secret);
		System.out.println("二维码地址:" + qrcode);
		System.out.println("密钥:" + secret);

	}
}

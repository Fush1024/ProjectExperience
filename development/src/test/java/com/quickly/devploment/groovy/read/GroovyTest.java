package com.quickly.devploment.groovy.read;

import com.quickly.devploment.groovy.repay.FormulaEntity;
import com.quickly.devploment.groovy.repay.GroovyContext;
import com.quickly.devploment.groovy.repay.RepaymentMethodFormulaInterface;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/7/21 5:49 下午
 * @Version 1.0
 */
@Slf4j
public class GroovyTest {
	public static String filePath = "/Users/lidengjin/study/sourceofgithub/mygithub/ProjectExperience/development/src/test/resources/groovy/groovy_class.txt";
//	public static String filePath = "/Users/lidengjin/study/sourceofgithub/mygithub/ProjectExperience/development/src/test/resources/groovy/groovy_user.txt";

	public static String invokeMethod = "readLineToList";

	public static String loadClass = "com.quickly.devploment.groovy.pojo.GroovyUserData";

	public static void main(String[] args) {
		GroovyObject groovyObject = null;
		try {
			groovyObject = (GroovyObject) GroovyClassLoader.class.getClassLoader().loadClass(loadClass).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Object[] array = new Object[]{filePath};
		List<String> readLineToList = (List<String>) groovyObject.invokeMethod(invokeMethod, array);

		//		readLineToList.stream().forEach(line -> {
		//			if (StringUtils.startsWith(line, "--")) {
		//				return;
		//			}
		//			UserGroovy userGroovy = JSONObject.parseObject(line, UserGroovy.class);
		//			System.out.println(userGroovy.toString());
		//		});
		StringBuffer stringBuffer = new StringBuffer();
		readLineToList.stream().forEach(line -> {
			// 注意换行 不然没法转换。
			stringBuffer.append(line + "\n");
		});
		String script = stringBuffer.toString();
//		String script = "package com.quickly.devploment.groovy.repayclass FirstBaseRepaymentMethod extends BaseRepaymentMethodFormula{    @Override    String sayName() {        return \"first name\"    }}";
		FormulaEntity formulaEntity = new FormulaEntity();
		formulaEntity.setVersion(3);
		formulaEntity.setFormulaNo("3213123");
		formulaEntity.setGroovyScript(script);
		RepaymentMethodFormulaInterface instance = GroovyContext
				.getInstance(formulaEntity, RepaymentMethodFormulaInterface.class);
		log.info("say hello {} ", instance.sayHello());
		log.info("groovy context {}", GroovyContext.FORMULA_INSTANCE_MAP);
	}
}

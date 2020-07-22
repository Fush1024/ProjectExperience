package com.quickly.devploment.groovy.repay;

import java.util.Arrays;
import java.util.List;

/**
 * @Author lidengjin
 * @Date 2020/7/22 9:13 上午
 * @Version 1.0
 */
public abstract class BaseRepaymentMethodFormula  implements RepaymentMethodFormulaInterface{
	@Override
	public List<String> sayHello() {
		String name = sayName();
		return Arrays.asList("base hello", "super hello", name);
	}
}

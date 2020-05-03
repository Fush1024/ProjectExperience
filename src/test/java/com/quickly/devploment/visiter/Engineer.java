package com.quickly.devploment.visiter;

import java.util.Random;

/**
 * @ClassName A
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/5 16:19
 * @Version V-1.0
 **/
// 工程师
public class Engineer extends Staff {

	public Engineer(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	// 工程师一年的代码数量
	public int getCodeLines() {
		return new Random().nextInt(10 * 10000);
	}
}

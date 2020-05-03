package com.quickly.devploment.visiter;

import java.util.Random;

/**
 * @ClassName A
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/5 16:20
 * @Version V-1.0
 **/
// 经理
public class Manager extends Staff {

	public Manager(String name) {
		super(name);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	// 一年做的产品数量
	public int getProducts() {
		return new Random().nextInt(10);
	}
}

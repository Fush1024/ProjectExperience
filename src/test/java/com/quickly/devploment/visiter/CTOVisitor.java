package com.quickly.devploment.visiter;

/**
 * @ClassName a
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/5 16:22
 * @Version V-1.0
 **/
public class CTOVisitor implements Visitor {
	@Override
	public void visit(Engineer engineer) {
		System.out.println("工程师: " + engineer.name + ", 代码行数: " + engineer.getCodeLines());
	}

	@Override
	public void visit(Manager manager) {
		System.out.println("经理: " + manager.name + ", 产品数量: " + manager.getProducts());
	}
}

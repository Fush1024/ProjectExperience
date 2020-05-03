package com.quickly.devploment.visiter;

/**
 * @ClassName Visitor
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/5 16:18
 * @Version V-1.0
 **/
public interface Visitor {
	// 访问工程师类型
	void visit(Engineer engineer);

	// 访问经理类型
	void visit(Manager manager);
}

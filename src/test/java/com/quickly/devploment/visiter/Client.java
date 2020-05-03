package com.quickly.devploment.visiter;

/**
 * @ClassName A
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/5 16:22
 * @Version V-1.0
 **/
public class Client {

	public static void main(String[] args) {
		// 构建报表
		BusinessReport report = new BusinessReport();
		System.out.println("=========== CEO看报表 ===========");
		report.showReport(new CEOVisitor());
		System.out.println("=========== CTO看报表 ===========");
		report.showReport(new CTOVisitor());
	}
}

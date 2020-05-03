package com.quickly.devploment.visiter;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName A
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/5 16:20
 * @Version V-1.0
 **/
// 员工业务报表类
public class BusinessReport {

	private List<Staff> mStaffs = new LinkedList<>();

	public BusinessReport() {
		mStaffs.add(new Manager("经理-A"));
		mStaffs.add(new Engineer("工程师-A"));
		mStaffs.add(new Engineer("工程师-B"));
		mStaffs.add(new Engineer("工程师-C"));
		mStaffs.add(new Manager("经理-B"));
		mStaffs.add(new Engineer("工程师-D"));
	}

	/**
	 * 为访问者展示报表
	 * @param visitor 公司高层，如CEO、CTO
	 */
	public void showReport(Visitor visitor) {
		for (Staff staff : mStaffs) {
			staff.accept(visitor);
		}
	}
}

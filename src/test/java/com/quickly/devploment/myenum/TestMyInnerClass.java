package com.quickly.devploment.myenum;

/**
 * @ClassName ClassMyaateo
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/27 11:15
 * @Version V-1.0
 **/
public class TestMyInnerClass {
	public static void main(String[] args) {
		System.out.println(11);
	}

	class Bean1 {
		public int I = 0;
	}

	public static class Bean2 {
		public int J = 0;
	}
}

class Bean {
	public class Bean3 {
		public int k = 0;
	}
}

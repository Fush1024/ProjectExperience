package com.quickly.devploment.myenum;

/**
 * @ClassName EnumTest
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/27 10:57
 * @Version V-1.0
 **/
public class LightTest {

	// 1. 定义枚举类型


	public enum Light {


		RED(1), GREEN(3),

		YELLOW(2);

		private int nCode;


		private Light(int _nCode) {

			this.nCode = _nCode;

		}


		@Override
		public String toString() {

			return String.valueOf(this.nCode);

		}


	}

	public static void main(String[] args) {
		System.out.println("11");
	}
}


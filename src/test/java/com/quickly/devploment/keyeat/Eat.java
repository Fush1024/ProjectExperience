package com.quickly.devploment.keyeat;

import java.util.Scanner;

/**
 * @ClassName Eat
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/20 15:44
 * @Version V-1.0
 **/
public class Eat {
	public static void main(String[] args) {
//		order();
		stringFormat();
		}

	private static void stringFormat() {
		String s = "撒谎的%s";
		String replace = "哈哈";
		String format = String.format(s, replace);
		System.out.println(format);
	}


	private static void order() {

		Scanner sc = new Scanner(System.in);
		System.out.println("欢迎来到小媛媛的餐厅");
		do {
			String string = sc.nextLine();
			if (string.equals(" ")) {
				break;
			}
			int s = Integer.valueOf(string);
			if (s < 3) {
				System.out.println("人数 "+s+"  请在 A 区 就餐");
			}else  {
				System.out.println("人数 "+s+"  请在 B 区 就餐");
			}
		} while (true);


	}
}

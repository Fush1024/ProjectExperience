package com.quickly.devploment.answer;

import com.quickly.devploment.pojo.UserPojo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @ClassName Answer
 * @Description
 * @Author LiDengJin
 * @Date 2019/11/27 15:01
 * @Version V-1.0
 **/
@Slf4j
public class Answer {


	// 默认此次减免的总金额 默认是 0
	private static BigDecimal deductionMoney = BigDecimal.ZERO;
	// 默认初始化的时候 已经减免的金额 0
	private static BigDecimal deratePenaltyInterest = BigDecimal.ZERO;
	// 回购的时候罚息 50
	private static BigDecimal repurchaseMoney = new BigDecimal(50);

	public static void main(String[] args) {
		//		testIntValueError();
		//		testThreadAlive();
		//		testLoveHeart();
		//		findMaxStr("yyabrdabjcabreg");
		//		testJavaPyramid();
		//		testMermoryOut();

		//		for (int i = 10; i <= 50; i += 10) {
		//			testMyCalcuate(i);
		//		}

//		testBigInteger();
		testRemoveList();
	}

	private static void testRemoveList() {
		ArrayList<String> arrayList = new ArrayList();
		arrayList.add("new String");
//		ArrayList<String> arrayList1 = new ArrayList();
//		arrayList1.add("11");
//		List<String> collect = arrayList1.stream().filter(s -> s.equals("222")).collect(Collectors.toList());
//		arrayList.removeAll(collect);
		arrayList.add("123");
		arrayList.add("13");
		arrayList.add("112");
		arrayList.remove(2);
	}


	private static void testBigInteger() {
		BigInteger bigInteger = BigInteger.probablePrime(10, new Random());
		System.out.println(bigInteger);
	}


	private static void testMyCalcuate(int i) {

		log.error("债权 回购罚息 {} ,已减罚息 {} 本次共减免 {} ", repurchaseMoney, deratePenaltyInterest, i);
		deductionMoney = new BigDecimal(i);
		deductionMoney = deductionMoney.add(deratePenaltyInterest);
		if (deductionMoney.subtract(repurchaseMoney).compareTo(BigDecimal.ZERO) <= 0) {
			// 此时需要去 记账 只有回购的罚息 此时只记工商即可
			log.info("工商 回购本 {} ,已减罚息 {}， 本次减免 {} ", repurchaseMoney, deratePenaltyInterest,
					deductionMoney.subtract(deratePenaltyInterest));
			deratePenaltyInterest = deductionMoney;
		}
		if (deductionMoney.subtract(repurchaseMoney).compareTo(BigDecimal.ZERO) > 0) {
			// 此时需要去 记账 只有回购的罚息 此时只记工商即可
			log.info("工商 回购本 {} ,已减罚息 {}， 本次减免 {} ", repurchaseMoney, deratePenaltyInterest,
					repurchaseMoney.subtract(deratePenaltyInterest));

			log.info("融通 本次减免自增 {} ", deductionMoney.subtract(repurchaseMoney));
			deratePenaltyInterest = repurchaseMoney;

		}

	}

	/**
	 * 内存泄露
	 */
	private static void testMermoryOut() {
		Vector v = new Vector(10);
		for (int i = 1; i < 100; i++) {
			Object o = new Object();
			v.add(o);
			o = null;
		}
	}

	/**
	 * java 金字塔
	 */
	private static void testJavaPyramid() {
		int lay = 7;
		for (int i = 1; i <= lay; i++) {//输出7层
			if (i == lay) {//最后一行输出全部数据
				for (int m = 0; m < 2 * lay - 1; m++) {
					System.out.print("*");
				}
			} else {
				for (int j = 0; j < lay - i; j++) {//内循环 输出 lay-i 个不换行空格
					System.out.print(" ");
				}
				//接着输出星星
				for (int k = 1; k <= 2 * i - 1; k++) {
					//掏空，除两头外其他用空格替换
					if (k == 1 || k == 2 * i - 1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				//一行结束换行
				System.out.println("\n");
			}
		}
	}

	/**
	 * 爱心公式
	 * <p>
	 * （x²+y²-1）³-x²*y³=0
	 */
	private static void testLoveHeart() {
		//		for (int i = 0; i < 100000; i++) {
		//			for (int j = 0; j < i; j++) {
		//				if ((i * i + j * j - 1) * (i * i + j * j - 1) * (i * i + j * j - 1) - i * i * j * j * j == 0){
		//					System.out.println("*");
		//				}
		//			}
		//		}

		for (float y = (float) 1.5; y > -1.5; y -= 0.1) {
			for (float x = (float) -1.5; x < 1.5; x += 0.05) {
				float a = x * x + y * y - 1;
				if ((a * a * a - x * x * y * y * y) <= 0.0) {
					System.out.print("^");
				} else
					System.out.print(" ");
			}
			System.out.println();
		}


	}

	private static void testThreadAlive() {
		int i = Thread.activeCount();
		System.out.println(i);

		for (int j = 0; j < 10; j++) {
			new Thread().start();
		}
		int i1 = Thread.activeCount();
		System.out.println(i1);

	}

	/**
	 * 思路:"abdab" 拆分情况：1.ab abd abda abdab 2.bd bda bdab 3 da dab 4 ab 5 b
	 */
	public static String findMaxStr(String str) {
		String reg;// 最大字符串
		String left;// 剩余字符串
		int k = 0;// 计数器
		int len = 0;// 最大字符串的长度
		String result = null;// 最终结果
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < str.length() - i; j++) {
				if (k < (j + 1)) {
					// 将字符串拆分成若干个子串
					reg = new String(str.substring(k, j + 1));
					left = new String(str.substring(j + 1));

					if (left.indexOf(reg) != -1 && reg.length() != 1) {

						if (reg.length() > len) {
							result = reg;
							len = reg.length();
						}
					}
				}
			}
			k++;
		}
		return result;

	}

	private static void testIntValueError() {
		Scanner in = new Scanner(System.in);
		String s = in.next();
		String[] arr = new String[s.length()];
		for (int i = 0; i < s.length(); i++) {
			arr[i] = s.substring(i, i + 1);
		}
		for (int i = 0; i < arr.length; i++) {
			switch (arr[i]) {
				case "0":
					arr[i] = "0000";
					break;
				case "1":
					arr[i] = "0001";
					break;
				case "2":
					arr[i] = "0010";
					break;
				case "3":
					arr[i] = "0011";
					break;

				case "4":
					arr[i] = "0100";
					break;

				case "5":
					arr[i] = "0101";
					break;

				case "6":
					arr[i] = "0110";
					break;

				case "7":
					arr[i] = "0111";
					break;

				case "8":
					arr[i] = "1000";
					break;
				case "9":
					arr[i] = "1001";
					break;

				case "A":
					arr[i] = "1010";
					break;

				case "B":
					arr[i] = "1011";
					break;

				case "C":
					arr[i] = "1100";
					break;

				case "D":
					arr[i] = "1101";
					break;

				case "E":
					arr[i] = "1110";
					break;

				case "F":
					arr[i] = "1111";
					break;
			}
		}
		String er = "";
		double num = 0;
		for (int i = 0; i < arr.length; i++) {
			er = er + arr[i];
		}
		String[] arr1 = new String[er.length()];
		int[] arr2 = new int[er.length()];
		for (int i = 0; i < er.length(); i++) {
			// 这里肯定报错 因为 长度不一样 所以会报错
			arr1[i] = s.substring(i, i + 1);
			arr2[i] = Integer.valueOf(arr1[i]).intValue();//在此处发生错误
			num = num + Math.pow((double) arr2[i], i);
		}
		System.out.print((int) num);
	}


	@Test
	public void testUserPojo(){
		UserPojo userPojo = new UserPojo();
		userPojo.setId(1);
		userPojo.setPassword("123");
		userPojo.setUsername("32");
		System.out.println(userPojo);

		UserPojo userPojo1 = userPojo;
		System.out.println(userPojo1);

		userPojo.setUsername("123456");
		System.out.println(userPojo1);

		userPojo1.setPassword("asd");
		System.out.println(userPojo);


	}

	@Test
	public void testBigDecial(){
		BigDecimal bigDecimal = new BigDecimal("10");
		System.out.println(bigDecimal.multiply(new BigDecimal("-1")));
	}




	@Test
	public void testJvm(){
		// 在堆中  创建这个对象 。
		UserPojo userPojo  = new UserPojo();

		//
		userPojo.setId(123);
		userPojo.getId();
	}

		class ListNode{
			public int val;
			ListNode next = null;

			public ListNode(int val){
				this.val = val;
			}


		//利用栈的思想
		public  boolean checkPalindrom(ListNode node){
			if(node == null)
				return false;
			Stack<ListNode> stack = new Stack<>();
			ListNode fast = node;
			ListNode slow = node;
			while (fast != null && fast.next != null){
				stack.push(slow);
				slow = slow.next;
				fast = fast.next.next;
			}
			//如果为奇数个
			if(fast != null)
				slow = slow.next;
			while (!stack.isEmpty()){
				if(stack.pop().val != slow.val)
					return false;
				slow = slow.next;
			}

			return true;
		}

		//利用链表反转的思想
		public  boolean checkPalindrom_(ListNode node){
			if(node == null)
				return false;
			ListNode fast = node;
			ListNode slow = node;
			while (fast != null && fast.next != null){
				slow = slow.next;
				fast = fast.next.next;
			}

			while (fast != null)
				slow = slow.next;
			ListNode p = slow.next;
			ListNode q = slow.next.next;
			slow.next = null;
			while(p != null){
				p.next = slow;
				slow = p;
				p = q;
				if(q.next != null)
					q = q.next;
			}
			while (slow != null){
				if(slow.val != node.val)
					return false;
				slow = slow.next;
				node = node.next;
			}

			return true;
		}

	}
	@Test
	public void testLink(){

		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(2);
		ListNode node6 = new ListNode(1);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = null;
		System.out.println(new ListNode(10).checkPalindrom(node1));
		System.out.println(new ListNode(10).checkPalindrom(node1));
	}

	@Test
	public void testInteger(){
		Integer integer = Integer.getInteger("nettyrpc.default.thread.nums", 16);
		System.out.println(integer);

	}

	@Test
	public void testMath(){
		double sqrt = Math.sqrt(0);

		System.out.println(Math.sqrt(0));
	}

	@Test
	public void testArray(){
		int list[] = {1,3,4,5};
		try {
			System.out.println("Sum of given array is " + sum(list));
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array Index is Out Of Bounds");
		}
		catch (IndexOutOfBoundsException except) {
			System.out.println("Index is Out Of Bounds");
		}
	}

	public static int sum(int arr[]) {
		int result = 0;
		int i;
		for (i = 0; i <= arr.length; i++) {
			result = result + arr[i];
		}
		return result;
	}

	@Test
	public void test(){
		UserPojo userPojo = new UserPojo();
		UserPojo userPojo1 ;
		UserPojo userPojo2 = new UserPojo();
		userPojo1 = userPojo2;


	}

	@Test
	public void testBigdec(){
		MyMoney myMoney = new MyMoney();
		myMoney.setBigDecimal(BigDecimal.ZERO.add(myMoney.getBigDecimal()));
		System.out.println(myMoney.toString());
	}

	class MyMoney{
		private BigDecimal bigDecimal;

		public MyMoney() {
		}

		public BigDecimal getBigDecimal() {
			return bigDecimal;
		}

		public void setBigDecimal(BigDecimal bigDecimal) {
			this.bigDecimal = bigDecimal;
		}

		public MyMoney(BigDecimal bigDecimal) {
			this.bigDecimal = bigDecimal;
		}

		@Override
		public String toString() {
			return "MyMoney{" + "bigDecimal=" + bigDecimal + '}';
		}
	}

	@Test
	public void testBigDecimal(){
		int amount = 100698;
		BigDecimal bigDecimal = new BigDecimal(amount);
		System.out.println(bigDecimal);
		// commmit
		BigDecimal divide = bigDecimal.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
		System.out.println(divide);
	}
}

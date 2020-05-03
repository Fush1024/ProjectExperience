package com.quickly.devploment.supers;

/**
 * @ClassName F
 * @Description
 * @Author LiDengJin
 * @Date 2019/12/27 10:54
 * @Version V-1.0
 **/


import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

class Test {

	public static void main(String[] args) {
		(new Test()).act();


	}

	public void act() {
		Vector<First> vs = new Vector<First>();
		//      vs.add(new Base());//错误，First容器只能装First及其子类，不能装First的超类，
		vs.add(new Second());

		List<? super Second> vSuper = new Vector<First>();//可以容纳本类及子类，但无法容纳超类
		//      vSuper.add(new Base());错误vSuper只是Vector<First>的引用，First的容器只能装First及其子类，不能装它的超类
//		      vSuper.add(new First());// 错误
		vSuper.add(new Second()); //正确
		vSuper.add(new Third()); //正确

		checkSuper(new Vector<First>());//可以传递超类、本类参数，但无法传递子类参数
		checkSuper(new Vector<Second>());
//		      checkSuper(new Vector<Third>());//错误

		List<? extends Second> vExtends = new Vector<Second>();//不能容纳任何子类、本类、超类
		//      vExtends.add(new First());错误
//		      vExtends.add(new Second());//错误
//		      vExtends.add(new Third());//错误
		vExtends.add(null);//正确，但是没用

		//      checkExtends(new Vector<First>());//错误，无法传递超类参数
		checkExtends(new Vector<Second>());//能够传递本类及子类参数
		checkExtends(new Vector<Third>());

		//List<? super T> 当容器用时，能容纳T本身及T的子类，但无法容纳T的超类。用来向函数传递参数时，只能传递T及T的超类
		//List<? extends T> 当容器用时，只能容纳null，没什么用处。用来向函数传递参数时，只能传递T及T的子类，不能传递T的超类

		Second second = new Second();
		second.setName("qiao");
		second.setId("11");
		second.setKind("11");



		final Function<Second, First> firstBaseFunction =  t -> {
			return (First) t;
		};

		final Function<First, Base> firstBaseFunction1 =  t -> {
			return (Base) t;
		};

		final Base apply1 = firstBaseFunction1.compose(firstBaseFunction).apply(second);
		System.out.println(apply1);

		//second extends first
		final Function<Second, Second> firstBaseFunction2 =  t -> {
			return t;
		};

		final Base apply2 = firstBaseFunction1.compose(firstBaseFunction2).apply(second);
		System.out.println(apply2);

		//second extends first
		final Function<Base, First> firstBaseFunction3 =  t -> {
			return (First) t;
		};

		final Base apply3 = firstBaseFunction1.compose(firstBaseFunction3).apply(second);
		System.out.println(apply3);




	}


	public void checkSuper(List<? super Second> a) {

	}

	public void checkExtends(List<? extends Second> a) {

	}

	@Data
	@ToString
	public class Base{
		@Override
		public String toString() {
			return "Base{" +
					"id='" + id + '\'' +
					'}';
		}

		String id;


	}
	@Data
	public class First extends Base{
		String name;

		@Override
		public String toString() {
			return "First{" +
					"id='" + id + '\'' +
					", name='" + name + '\'' +
					'}';
		}
	}
	@Data
	public class Second extends First {
		@Override
		public String toString() {
			return "Second{" +
					"id='" + id + '\'' +
					", name='" + name + '\'' +
					", kind='" + kind + '\'' +
					'}';
		}

		String kind;
	}
	@Data
	public class Third extends Second {
		String color;

		@Override
		public String toString() {
			return "Third{" +
					"id='" + id + '\'' +
					", name='" + name + '\'' +
					", kind='" + kind + '\'' +
					", color='" + color + '\'' +
					'}';
		}
	}
}


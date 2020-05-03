package com.quickly.devploment.visiter;

/**
 * @ClassName Square
 * @Description
 * @Author LiDengJin
 * @Date 2020/1/9 21:03
 * @Version V-1.0
 **/
public class Square extends Rectangle {
	private long length;

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	@Override
	public long getHeight() {
		return getLength();
	}

	@Override
	public void setHeight(long height) {
		setLength(height);
	}

	@Override
	public long getWeight() {
		return getLength();
	}

	@Override
	public void setWeight(long weight) {
		setLength(weight);
	}


	public static void main(String[] args) {
//		Rectangle rectangle = new Rectangle();
//		rectangle.setHeight(16);
//		rectangle.setWeight(16);
//		resize(rectangle);

		Square square = new Square();
		square.setLength(10);
		resize(square);
	}
	public static void resize(Rectangle rectangle) {
		while (rectangle.getWeight()>=rectangle.getHeight()){
			rectangle.setHeight(rectangle.getHeight()+1);
			System.out.println(" weight "+ rectangle.getWeight() + "   height" + rectangle.getHeight() );
		}
		System.out.println("结束");
	}
}

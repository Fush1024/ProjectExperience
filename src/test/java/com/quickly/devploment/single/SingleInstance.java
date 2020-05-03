package com.quickly.devploment.single;

/**
 * @ClassName SingleInstance
 * @Description
 * @Author LiDengJin
 * @Date 2020/4/3 18:17
 * @Version V-1.0
 **/
public class SingleInstance {
	private  static SingleObject singleObject;

	public static SingleObject getSingleObject(){
		if(singleObject == null) {
			synchronized (SingleInstance.class){
				if(singleObject == null){
					singleObject = new SingleObject("name", "port");
				}
			}
		}
		return singleObject;
	}


	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(()->{
				SingleObject singleObject = SingleInstance.getSingleObject();
				if(singleObject.getPort() == null) {
					System.out.println("ThreadName : " + Thread.currentThread().getName() + " _" + singleObject);
				}
			}).start();
		}
	}
}
 class SingleObject {
	private String name;
	private String port;


	public SingleObject() {

	}

	public SingleObject(String name, String port) {
		this.name = name;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "SingleObject{" + "name='" + name + '\'' + ", port='" + port + '\'' + '}';
	}
}




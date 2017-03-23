package com.designmodel;

//代理模式
public class ProxyDemo {
	public static void main(String[] args) {
		Image image = new ProxyImage("test_10mb.jpg");

		// 图像将从磁盘加载
		image.display();
		System.out.println("");
		// 图像将无法从磁盘加载
		image.display();
	}
}

// 接口
interface Image {
	void display();
}

// 实体类
class RealImage implements Image {

	private String fileName;

	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk(fileName);
	}

	@Override
	public void display() {
		System.out.println("Displaying " + fileName);
	}

	private void loadFromDisk(String fileName) {
		System.out.println("Loading " + fileName);
	}
}

// 代理类
class ProxyImage implements Image {

	private RealImage realImage;
	private String fileName;

	public ProxyImage(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public void display() {
		if (realImage == null) {
			realImage = new RealImage(fileName);
		}
		realImage.display();
	}
}

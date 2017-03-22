package com.designmodel;

//桥接模式
public class BridgeDemo {
	public static void main(String[] args) {

		AbstractRoad speedWay = new SpeedWay();
		speedWay.aCar = new Car();
		speedWay.run();

		AbstractRoad street = new Street();
		street.aCar = new Bus();
		street.run();
	}

}

abstract class AbstractRoad {
	// 与AbstractCar产生耦合
	AbstractCar aCar;

	void run() {
	};
}

abstract class AbstractCar {
	void run() {
	};
}

class Street extends AbstractRoad {
	@Override
	void run() {
		// TODO Auto-generated method stub
		aCar.run();
		System.out.println("在市区街道行驶");
	}
}

class SpeedWay extends AbstractRoad {
	@Override
	void run() {
		// TODO Auto-generated method stub
		aCar.run();
		System.out.println("在高速公路行驶");
	}
}

class Car extends AbstractCar {
	@Override
	void run() {
		// TODO Auto-generated method stub
		System.out.print("小汽车");
	}
}

class Bus extends AbstractCar {
	@Override
	void run() {
		// TODO Auto-generated method stub
		System.out.print("公交车");
	}
}

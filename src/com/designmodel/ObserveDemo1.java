package com.designmodel;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 */
class House extends Observable{
	private String houseName;
	private double price;
	public House(String h,double d){
		this.houseName=h;
		this.price=d;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double d){
		this.price=d;
		super.setChanged();  //设置变化点
		super.notifyObservers(d); //通知观察者价格变为d
	}
	public void setHouseName(String s){
		this.houseName=s;
		super.setChanged();  //设置变化点
		super.notifyObservers(s); //通知观察者房屋名字变为d
	}
	public String toString(){
		return this.houseName+"房子价格为："+getPrice();
	}
}

class HousePriceObserver implements Observer{
	private String name;
	private Double price;
	public HousePriceObserver(String n){
		this.name=n;
	}
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Double){
			this.price=((Double)arg).doubleValue();
			System.out.println(this.name+"观察到的价格更改为："+this.price+" "+o);
		}
		if(arg instanceof String){
			System.out.println(this.name+"观察到的房屋名字更改为："+(String)arg+" "+o);
		}
	}	
}

public class ObserveDemo1 {

	public static void main(String[] args) {
		House h1=new House("h1",10000);
		House h2=new House("h2",50000);
		
		HousePriceObserver hpo1=new HousePriceObserver("li");
		HousePriceObserver hpo2=new HousePriceObserver("ye");
		HousePriceObserver hpo3=new HousePriceObserver("aa");
		h1.addObserver(hpo1);
		h1.addObserver(hpo2);
		h1.addObserver(hpo3);
		
		h2.addObserver(hpo2);
		
		h1.setPrice(8000);
		h2.setPrice(5000);
		System.out.println(h1);
		System.out.println(h2);
		
		h1.setHouseName("h3");
	}

}

package com.designmodel;

import java.util.Observable;
import java.util.Observer;

class House extends Observable{
	private double price;
	public House(double d){
		this.price=d;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double d){
		super.setChanged();
		super.notifyObservers(d);
		this.price=price;
	}
	public String toString(){
		return "房子价格为："+this.price;
	}
}

class HousePriceObserver implements Observer{
	private String name;
	public HousePriceObserver(String n){
		this.name=n;
	}
	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Double){
			System.out.println(this.name+"观察到的价格更改为："+((Double)arg).doubleValue());
		}
	}	
}

public class ObserveDemo1 {

	public static void main(String[] args) {
		House h=new House(10000);
		House h2=new House(50000);
		
		HousePriceObserver hpo1=new HousePriceObserver("li");
		HousePriceObserver hpo2=new HousePriceObserver("ye");
		HousePriceObserver hpo3=new HousePriceObserver("aa");
		h.addObserver(hpo1);
		h.addObserver(hpo2);
		h.addObserver(hpo3);
		
		h2.addObserver(hpo2);
		
		h.setPrice(8000);
		h2.setPrice(5000);
	}

}

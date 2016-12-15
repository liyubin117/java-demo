package com.basic;


enum Season{
	SPRING,
	SUMMER,
	AUTUMN,
	WINTER,
}

class TestSeason{
	public static void judgeSeason(Season s){
		switch(s){
		case SPRING:
			System.out.println(s+" is warm");
			break;
		case SUMMER:
			System.out.println(s+" is hot");
			break;
		case AUTUMN:
			System.out.println(s+" is cool");
		case WINTER:
			System.out.println(s+" is cold");
			break;
		default:
			System.out.println("no such season");
		}
	}
}

public class TestEnum {

	public static void main(String[] args) {
		System.out.println(Season.SPRING);
		
		for(Season s:Season.values()){
			System.out.println(s);
		}
		
		for(Season s:Season.values()){
			TestSeason.judgeSeason(s);
		}
		
//		TestSeason.judgeSeason(Season.a);
	}

}

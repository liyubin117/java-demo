package com.collectionmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HashMapDemo {
	public static void main(String[] args) {
		Random rnd = new Random();
		Map<Integer, Integer> countMap = new HashMap<>();
		
		for(int i=0; i<1000; i++){
		    int num = rnd.nextInt(4);
		    Integer count = countMap.get(num);
		    if(count==null){
		        countMap.put(num, 1);
		    }else{
		        countMap.put(num, count+1);
		    }
		}

		for(Map.Entry<Integer, Integer> kv : countMap.entrySet()){
		    System.out.println(kv.getKey()+","+kv.getValue());
		}
		
		//构造方式
		//public HashMap(Map<? extends K, ? extends V> m)
		Map<Number, Number> map = new HashMap<>(countMap);
		for(Map.Entry<Number, Number> kv : map.entrySet()){
		    System.out.println(kv.getKey()+","+kv.getValue());
		}
	}
}

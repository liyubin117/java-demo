package com.collectionmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
		
		System.out.println(countMap.containsKey(10));
		System.out.println(countMap.containsValue(100));
		countMap.remove(3);

		for(Map.Entry<Integer, Integer> kv : countMap.entrySet()){
		    System.out.println(kv.getKey()+","+kv.getValue());
		}
		
		//构造方式
		//public HashMap(Map<? extends K, ? extends V> m)
		System.out.println("----构造方法--------");
		Map<Number, Number> map = new HashMap<>(countMap);
		for(Map.Entry<Number, Number> kv : map.entrySet()){
		    System.out.println(kv.getKey()+","+kv.getValue());
		}
		
		System.out.println("-----Map.Entry-----");
		Set<Map.Entry<Number, Number>> entrys=map.entrySet();
		for(Map.Entry<Number, Number> entry:entrys){
			if(entry.getKey().equals(1)){
				continue;
			}
		    System.out.println(entry.getKey()+","+entry.getValue());
		}
		//keySet、values、entrySet返回视图
		map.values().clear();
		System.out.print("clear后：");
		for(Map.Entry<Number, Number> kv : map.entrySet()){
		    System.out.println(kv.getKey()+","+kv.getValue());
		}
		
	}
}

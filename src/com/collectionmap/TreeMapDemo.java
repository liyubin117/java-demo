package com.collectionmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TreeMapDemo {
	
	public static void main(String[] args) {
		Map<String, String> map  = new TreeMap<>();
		map.put("a", "abstract");
		map.put("c", "call");
		map.put("b", "basic");
		map.put("T", "tree");
		for(Entry<String,String> kv : map.entrySet()){
		    System.out.print(kv.getKey()+"="+kv.getValue()+" ");
		}
		System.out.println();
		
		//不区分大小写
		Map<String, String> map2  = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		map2.putAll(map);
		for(Entry<String,String> kv : map2.entrySet()){
		    System.out.print(kv.getKey()+"="+kv.getValue()+" ");
		}
		System.out.println();
		
		//使用自定义Comparator，逆序输出
		//Collections.reverseOrder() 逆序比较器
		//Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER) 逆序不区分大小写比较器
		Map<String, String> map3  = new TreeMap<>(
				new Comparator<String>(){
					@Override
					public int compare(String o1, String o2) {
						return o2.compareTo(o1);
					}
				}
		);
		map3.putAll(map);
		for(Entry<String,String> kv : map3.entrySet()){
		    System.out.print(kv.getKey()+"="+kv.getValue()+" ");
		}
		System.out.println();
		
		//比较日期
		Map<String, Integer> map4  = new TreeMap<>(new Comparator<String>() {
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    
		    @Override
		    public int compare(String o1, String o2) {
		        try {
		            return sdf.parse(o1).compareTo(sdf.parse(o2));
		        } catch (ParseException e) {
		            e.printStackTrace();
		            return 0;
		        }
		    }
		});
		
	}

}

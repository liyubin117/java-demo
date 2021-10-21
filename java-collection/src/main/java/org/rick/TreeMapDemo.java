package org.rick;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class TreeMapDemo {
	//打印映射
	public static void printMap(Map<? extends Object,? extends Object> map){
		for(Entry<? extends Object, ? extends Object> kv : map.entrySet()){
		    System.out.print(kv.getKey()+"="+kv.getValue()+" ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Map<String, String> map  = new TreeMap<>();
		map.put("a", "abstract");
		map.put("c", "call");
		map.put("b", "basic");
		map.put("T", "tree");
		Map<String, String> hashMap  = new HashMap<>(map);
		System.out.print("hash map："); TreeMapDemo.printMap(hashMap);
		System.out.print("tree map："); TreeMapDemo.printMap(map);
		
		//不区分大小写
		Map<String, String> map2  = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
		map2.putAll(map);
		TreeMapDemo.printMap(map2);

		
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
		TreeMapDemo.printMap(map3);
		
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
		map4.put("2016-7-3", 100);
		map4.put("2016-7-10", 120);
		map4.put("2016-8-1", 90);
		TreeMapDemo.printMap(map4);
		
		//Navigatable接口
		NavigableMap<String, String> navigatableMap  = new TreeMap<>(map);
		System.out.println(navigatableMap.firstEntry());
		System.out.println(navigatableMap.lastEntry());
		System.out.println(navigatableMap.floorEntry("d"));
		System.out.println(navigatableMap.ceilingEntry("d"));
		System.out.println(navigatableMap.descendingMap()
		        .subMap("d", false, "a", true));
		System.out.println(navigatableMap.pollLastEntry());
		TreeMapDemo.printMap(navigatableMap);
	}

}

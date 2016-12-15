package com.lyb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestListMap {
	public static void main(String[] args){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("liyubin", 1);
		map.put("liyubin", 2);
		map.put("liyubin", 2);
		map.put("li", String.valueOf(5));
		System.out.println(map.get("liyubin"));
		System.out.println(map.get("li"));
		System.out.println("map:"+map.toString());
		
		Map<String,Object> map2=new HashMap<String,Object>();
		map2.put("liyubin", 3);
		map2.put("li", String.valueOf(5));
		System.out.println("map2:"+map2.toString());
		
		System.out.println("---------------------------");
		
		
		List<String> list=new ArrayList<String>();
		list.add("liyubin");
		list.add("haha");
		list.add("ha");
		list.add("ha");
		for(String s:list){
			System.out.println(s);
		}
		list.remove(1);
		for(String s:list){
			System.out.println(s);
		}
		
		System.out.println("---------------------------");
		
		List<Map<String,Object>> lm=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		Map<String,Object> base=new HashMap<String,Object>();
		lm.add(map);
		base.put("liyubin", 2);
		for(Map<String,Object> m:lm){
			if(base.get("liyubin").equals(m.get("liyubin"))){
				result.add(m);
				result.add(base);
			}
		}
		System.out.println(result.size());
		for(Map<String,Object> m:result){
			System.out.println(m.toString());
		}
	}

}

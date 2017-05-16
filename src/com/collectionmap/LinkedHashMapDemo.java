package com.collectionmap;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LinkedHashMapDemo {

	public static void main(String[] args) {
		//默认情况下按插入有序。
		Map<String,Integer> seqMap = new LinkedHashMap<>();
		seqMap.put("c", 100);
		seqMap.put("d", 200);
		seqMap.put("a", 500);
		seqMap.put("d", 300);
		TreeMapDemo.printMap(seqMap);
		
		//按访问有序（每次访问都会将该键值对移到末尾），典型应用是LRU缓存，前提是需要重写removeEldestEntry
		Map<String,Integer> accessMap = new LinkedHashMap<>(16, 0.75f, true);
		accessMap.put("c", 100);
		accessMap.put("d", 200);
		accessMap.put("a", 500);
		accessMap.get("c");
		accessMap.put("d", 300);
		TreeMapDemo.printMap(accessMap);
		
		LRUCache<String,Object> cache = new LRUCache<>(3);
		cache.put("a", "abstract");
		cache.put("b", "basic");
		cache.put("c", "call");
		cache.get("a");
		cache.put("d", "call");
		System.out.println(cache);
	}
}

//LRU缓存的简单实现
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int maxEntries;
    
    public LRUCache(int maxEntries){
        super(16, 0.75f, true);
        this.maxEntries = maxEntries;
    }
    
    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > maxEntries;
    }
}    

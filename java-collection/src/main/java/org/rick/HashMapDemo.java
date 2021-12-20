package org.rick;

import org.junit.Test;
import scala.Tuple2;

import java.util.*;

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
		//jdk1.7:HashMap<Number,Number>(countMap)
		//jdk1.8:HashMap<>(countMap)
		Map<Number, Number> map = new HashMap<Number,Number>(countMap);
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

	@Test
	public void testConcurrentModifyException() {
		Map<String, String> map = new HashMap<>();
        map.put("a", "one");
        map.put("b", "123");
        map.forEach((k,v) -> {
            if (k.equals("a")) {
                map.remove("a");
            }
        });
        System.out.println(map);
	}

	@Test
	public void testMap() {
		List<Tuple2<Integer, Integer>> runningJobKey = new ArrayList<>();
		runningJobKey.add(new Tuple2<>(1, 2));
		runningJobKey.add(new Tuple2<>(3, 4));
		runningJobKey.add(new Tuple2<>(5, 6));

		Map<Tuple2<Integer, Integer>, Integer> reportedJobs = new HashMap<>();
		reportedJobs.put(new Tuple2<>(3, 4), 100);
		reportedJobs.put(new Tuple2<>(5, 6), 100);
		reportedJobs.put(new Tuple2<>(7, 8), 100);

		List<Tuple2<Integer, Integer>> removeTasks = new ArrayList<>();

		reportedJobs.forEach((k,v) -> {
			if (!runningJobKey.contains(k)) {
				removeTasks.add(k);
			}
		});
		removeTasks.forEach(reportedJobs::remove);
		System.out.println(removeTasks);
		System.out.println(reportedJobs);
	}
}

//class Tuple2<T, A> {
//    T k;
//    A v;
//    public Tuple2(T k, A v) {
//        this.k = k;
//        this.v = v;
//    }
//}
//package com.threads;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Random;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.ConcurrentSkipListMap;
//import java.util.concurrent.CopyOnWriteArrayList;
////并发容器
//public class ConcurrentCollection {
//
//	public static void main(String[] args) throws InterruptedException {
//		/**
//		 * CopyOnWriteArrayList
//		 */
//		//写时拷贝List，支持容器被修改时，并发迭代读取
//		final List<String> list=new CopyOnWriteArrayList<>();
//		//同步容器报ConcurrentModificationException异常
////		final List<String> list=Collections.synchronizedList(new ArrayList<String>());
//
//		SCIterator.startIteratorThread(list);
//		SCIterator.startModifyThread(list);
//
////		JDK1.8前CopyOnWriteArrayList的迭代器不支持修改操作，也不支持一些依赖迭代器修改方法的操作，比如Collections的sort方法
//		CopyOnWriteArrayList<String> list1 = new CopyOnWriteArrayList<>();
//	    list1.add("c");
//	    list1.add("a");
//	    list1.add("b");
//	    try{
//		    Collections.sort(list1);
//	    }catch(UnsupportedOperationException e){
//	    	System.out.println("UnsupportedOperationException catched!");
//	    }
//	    System.out.println(list1);
//
//	    /**
//	     * ConcurrentHashMap
//	     */
//	    //HashMap非线程安全，jdk1.7下CPU会达到100%，死循环。因为1.7里HashMap的table[i]后是一个链表，并发更新极可能形成环，出现死循环。三种情况：无法停止、ArrayIndexOutOfBoundsException、执行完成
//	    //jdk1.8是用红黑树实现的，不太可能有这个问题（还未验证）
//	    unsafeConcurrentUpdate();
//	    //ConcurrentHashMap支持迭代中修改容器。而HashMap遇到这种情况会直接抛出ConcurrentModificationException异常
//	    ConcurrentHashMapIteratorDemo.test();
//
//	    Object[] o=new Object[16];
//	    System.out.println(o.length);
//	    System.out.println("a".hashCode()&15);
//	    System.out.println("b".hashCode()&15);
//	    System.out.println("c".hashCode()&15);
//	    System.out.println("g".hashCode()&15);
//
//	    /**
//	     * ConcurrentSkipListMap
//	     */
//	    Map<String, String> map = new ConcurrentSkipListMap<>(
//	            Collections.reverseOrder());
//	    map.put("a", "abstract");
//	    map.put("c", "call");
//	    map.put("b", "basic");
//	    System.out.println(map.toString());
//
//	}
//
//
//
//	public static void unsafeConcurrentUpdate() {
////		线程安全：
////		final Map<Integer, Integer> map = new ConcurrentHashMap<>();
////		非线程安全
//	    final Map<Integer, Integer> map = new HashMap<>();
//	    for (int i = 0; i < 10; i++) {
//	        Thread t = new Thread() {
//	            Random rnd = new Random();
//
//	            @Override
//	            public void run() {
//	                for (int i = 0; i < 100; i++) {
//	                    map.put(rnd.nextInt(), 1);
//	                }
//	            }
//	        };
//	        t.start();
//	    }
//	}
//}
//
////t1启动后，创建迭代器，但在迭代输出每个元素前，先睡眠1秒钟，主线程启动t1后，先睡眠一下，确保t1先运行，然后给map增加了一个元素
//class ConcurrentHashMapIteratorDemo {
//	final static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
//
//    public static void test() {
//        map.put("b", "basic");
//        map.put("a", "abstract");
//        System.out.println(map.size());
//        Thread t1 = new Thread() {
//            @Override
//            public void run() {
//                for (Entry<String, String> entry : map.entrySet()) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                    }
//                    System.out.println(entry.getKey() + "," + entry.getValue());
//                }
//            }
//        };
//        t1.start();
//        // 确保线程t1启动
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//        }
////        map.put("c", "call");
//        map.put("g", "call");
//
//        //得弄懂为啥g一开始在被遍历过的部分，而c不在。目前还没有。。。
//        System.out.println(map.size());
//        System.out.println("a:"+(hash("a")&15));
//        System.out.println("b:"+(hash("b")&15));
//        System.out.println("c:"+(hash("c")&15));
//        System.out.println("g:"+(hash("g")&15));
//
//
//    }
//
//    public static int hash(Object k) {
//        int h = hashSeed;
//
//        if ((0 != h) && (k instanceof String)) {
//            return sun.misc.Hashing.stringHash32((String) k);
//        }
//
//        h ^= k.hashCode();
//
//        // Spread bits to regularize both segment and index locations,
//        // using variant of single-word Wang/Jenkins hash.
//        h += (h <<  15) ^ 0xffffcd7d;
//        h ^= (h >>> 10);
//        h += (h <<   3);
//        h ^= (h >>>  6);
//        h += (h <<   2) + (h << 14);
//        return h ^ (h >>> 16);
//    }
//
//    private static transient final int hashSeed = randomHashSeed(map);
//
//    private static int randomHashSeed(ConcurrentHashMap instance) {
//        if (sun.misc.VM.isBooted() && Holder.ALTERNATIVE_HASHING) {
//            return sun.misc.Hashing.randomHashSeed(instance);
//        }
//
//        return 0;
//    }
//
//    private static class Holder {
//        static final boolean ALTERNATIVE_HASHING;
//
//        static {
//            String altThreshold = java.security.AccessController.doPrivileged(
//                new sun.security.action.GetPropertyAction(
//                    "jdk.map.althashing.threshold"));
//
//            int threshold;
//            try {
//                threshold = (null != altThreshold)
//                        ? Integer.parseInt(altThreshold)
//                        : Integer.MAX_VALUE;
//
//                if (threshold == -1) {
//                    threshold = Integer.MAX_VALUE;
//                }
//
//                if (threshold < 0) {
//                    throw new IllegalArgumentException("value must be positive integer.");
//                }
//            } catch(IllegalArgumentException failed) {
//                throw new Error("Illegal value for 'jdk.map.althashing.threshold'", failed);
//            }
//            ALTERNATIVE_HASHING = threshold <= (1 << 30);
//        }
//    }
//}

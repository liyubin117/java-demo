package com.memoryctl;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ReferenceDemo {

	public static void main(String[] args) throws InterruptedException {
		
		Runtime run=Runtime.getRuntime();
		System.out.println("free memory:"+run.freeMemory());
		
		//强引用，被强引用的对象将不会被GC回收，即使内存不够报异常也不会。
		String s=new String("strong reference");
		//报OutOfMemoryError异常
//		final int max=10240000;
//		String[] ss=new String[max];
//		for(int i=0;i<max;i++){
//			ss[i]=new String("String:"+i);
//		}
		
		//使用WeakHashMap弱引用映射，键是弱引用，不论插入多少数据，都不会报内存不足异常。用来作为内存敏感的高速缓存
		//因为GC线程只要在它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存
		final int max=1024000;
//		ReferenceQueue rq=new ReferenceQueue();
//		WeakReference<String> wr=new WeakReference<>("weak reference",rq);
		WeakHashMap<Integer,String> map=new WeakHashMap<>();
		for(int i=0;i<max;i++){
			map.put(i, "ha");
//			System.gc();
		}
		System.out.println(map);
		
		
		System.out.println("free memory:"+run.freeMemory());
		
		//弱引用。当内存不够时自动被GC回收
		WeakReference<String> wr=new WeakReference<>("weak reference");
		System.out.println(wr.get());
		
		//虚引用，相当于没有引用，任何时候都可能被GC回收。必须与ReferenceQueue合用
		ReferenceQueue queue = new ReferenceQueue ();
		PhantomReference[] pr = new PhantomReference[100];
		for(int i=0;i<100;i++){
			pr[i]=new PhantomReference("phantom reference "+i, queue);
			System.out.println("pr["+i+"]:"+pr[i].get());
			System.out.println("queue:"+queue.poll());
			Thread.sleep(500);
		}
		
//		Thread.sleep(1000);
		


	}

}

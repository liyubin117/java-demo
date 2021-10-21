package org.rick;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

import model.Pair;

public class Atomic {

	public static void main(String[] args) {
		//AtomicInteger
		Counter counter=new Counter(10000);
		counter.oper();
		
		//AtomicReference
		Pair p = new Pair(100, 200);
        AtomicReference<Pair> pairRef = new AtomicReference<>(p);
        pairRef.compareAndSet(p, new Pair(200, 200));
        System.out.println(pairRef.get().getFirst());
        
        //AtomicIntegerArray
        int[] arr = { 1, 2, 3, 4 };
        AtomicIntegerArray atomicArr = new AtomicIntegerArray(arr);
        atomicArr.compareAndSet(1, 2, 100);
        System.out.println(atomicArr.get(1));
        System.out.println(arr[1]);
        
        //FieldUpdater
        FieldUpdaterDemo.DemoObject obj = new FieldUpdaterDemo.DemoObject();
        obj.compareAndSetNum(0, 100);
        obj.compareAndSetRef(null, new String("hello"));
        System.out.println(obj.getNum());
        System.out.println(obj.getRef());
        
        //AtomicStampedReference，解决ABA问题，只有值和时间戳都与预期相同时再更新
        Pair pair = new Pair(100, 200);
        int stamp = 1;
        AtomicStampedReference<Pair> stampedRef = new AtomicStampedReference<Pair>(pair, stamp);
        int newStamp = 2;
        stampedRef.compareAndSet(pair, new Pair(200, 200), stamp, newStamp);
        System.out.println(pair);
	}

}

class Counter{
	private static AtomicInteger i=new AtomicInteger(0);
	private int max;
	
	public Counter(int max){
		this.max=max;
	}
	
	static class Count extends Thread{
		@Override
		public void run(){
			//类似于++i
			i.incrementAndGet();
		}
	}
	
	public void oper(){
		Thread[] s=new Thread[max];
		for(int num=0;num<max;num++){
			s[num]=new Count();
			s[num].start();
		}
		for(int num=0;num<max;num++){
			try {
				s[num].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(i.get());
	}
	
}

//FieldUpdater方便以原子方式更新对象中的字段，字段不需要声明为原子变量，FieldUpdater是基于反射机制实现的
class FieldUpdaterDemo {
    static class DemoObject {
    	//声明volatile变量
        private volatile int num;
        private volatile Object ref;

        private static final AtomicIntegerFieldUpdater<DemoObject> numUpdater
            = AtomicIntegerFieldUpdater.newUpdater(DemoObject.class, "num");
        private static final AtomicReferenceFieldUpdater<DemoObject, Object>
            refUpdater = AtomicReferenceFieldUpdater.newUpdater(
                    DemoObject.class, Object.class, "ref");

        public boolean compareAndSetNum(int expect, int update) {
            return numUpdater.compareAndSet(this, expect, update);
        }

        public int getNum() {
            return num;
        }

        public Object compareAndSetRef(Object expect, Object update) {
            return refUpdater.compareAndSet(this, expect, update);
        }

        public Object getRef() {
            return ref;
        }
    }
}
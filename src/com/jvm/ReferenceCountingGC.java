package com.jvm;

/**
 * 若GC采用引用计数算法（一个对象对应一个引用计数器，若被引用 则加1，引用失效则减1，若为0则不可被使用，应该被GC）
 * 无法解决循环引用问题
 * 所以HotSpot虚拟机采用引用可达算法，本例中的GC可以正常回收对象
 */
public class ReferenceCountingGC {
	public Object instance;
	private static final int _1MB=1024*1024;
	private byte[] bigSize=new byte[2*_1MB];

	public static void main(String[] args) {
		//objA和objB相互引用
		ReferenceCountingGC objA=new ReferenceCountingGC();
		ReferenceCountingGC objB=new ReferenceCountingGC();
		objA.instance=objB;
		objB.instance=objA;
		
		objA=null;
		objB=null;
		
		//发生GC
		System.gc();
	}

}

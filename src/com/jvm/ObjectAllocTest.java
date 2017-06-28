package com.jvm;

import org.junit.Before;
import org.junit.Test;

//对象分配规则
public class ObjectAllocTest {
	private static final int _1MB=1024*1024;

	/**
	 * MinorGC：发生在新生代GC里。
	 * 若MinorGC后仍无足够空间容纳新对象，且之前其他对象无法放入一个Survivor，将通过分配担保机制将之提前放入老年代里，这样新对象才能进来
	 * 内存分配：对象优先进新生代的Eden
	 */
//	-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	@Test
	public void testMinorGC(){
		byte[] alloc1,alloc2,alloc3,alloc4;
		alloc1=new byte[2*_1MB];
		alloc2=new byte[2*_1MB];
		alloc3=new byte[2*_1MB];
		alloc4=new byte[4*_1MB];
	}
	
	@Test
	/**
	 * 大对象直接进老年代
	 */
//	-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728 -XX:+UseSerialGC
	public void testBigObject() {
		byte[] alloc;
		alloc=new byte[4*_1MB];
	}
	
	/**
	 * 长期存活的对象直接进老年代
	 */
//  -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution -XX:+UseSerialGC
	@Test
	public void testLongLive(){
		byte[] alloc1,alloc2,alloc3;
		alloc1=new byte[_1MB/4];
		//什么时候进入老年代取决于XX:MaxTenuringThreshold设置
		alloc2=new byte[4*_1MB];
		alloc3=new byte[4*_1MB];
		alloc3=null;
		alloc3=new byte[4*_1MB];
	}

}

package org.rick.jvm;

/**
 * MinorGC：发生在新生代GC里。
 * 若MinorGC后仍无足够空间容纳新对象，且之前其他对象无法放入一个Survivor，将通过分配担保机制将之提前放入老年代里，这样新对象才能进来
 * 内存分配：对象优先进新生代的Eden
 */
public class MinorGC {
	private static final int _1MB=1024*1024;
	
	//vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
	public static void main(String[] args) {
		byte[] alloc1,alloc2,alloc3,alloc4;
		alloc1=new byte[2*_1MB];
		alloc2=new byte[2*_1MB];
		alloc3=new byte[2*_1MB];
		alloc4=new byte[4*_1MB];

	}
}

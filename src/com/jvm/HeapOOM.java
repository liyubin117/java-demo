package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出OutOfMemoryExceptiion
 *
 */
public class HeapOOM {
	static class OOMObject{
		String s="liyubin";
	}
	//vm参数：-verbose:gc -Xms10M -Xmx10M -Xmn5M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:+HeapDumpOnOutOfMemoryError
	public static void main(String[] args) {
		List<OOMObject> list=new ArrayList<>();
		while(true){
			list.add(new OOMObject());
		}
	}

}

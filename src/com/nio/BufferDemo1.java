package com.nio;

import java.nio.Buffer;
import java.nio.CharBuffer;

public class BufferDemo1 {

	public static void main(String[] args) {
		//初始化CharBuffer对象
		CharBuffer buff=CharBuffer.allocate(8);
		System.out.println("初始状态：");
		System.out.println("mark:"+buff.mark());
		System.out.println("position:"+buff.position());
		System.out.println("limit:"+buff.limit());
		System.out.println("capacity:"+buff.capacity());
		
		//添加元素
		buff.put('a');
		buff.put('b');
		buff.put('c');
		buff.put(7, 'd');
		System.out.println("加入三个元素后：");
		System.out.println("mark:"+buff.mark());
		System.out.println("position:"+buff.position());
		System.out.println("limit:"+buff.limit());
		System.out.println("capacity:"+buff.capacity());
		
		//调用flip，准备读
		buff.flip();
		System.out.println("调用flip后：");
		System.out.println("mark:"+buff.mark());
		System.out.println("position:"+buff.position());
		System.out.println("limit:"+buff.limit());
		System.out.println("capacity:"+buff.capacity());
		System.out.println("第3个元素是:"+buff.get(2));
		
		//调用clear，准备写
		buff.clear();
		System.out.println("调用clear后：");
		System.out.println("mark:"+buff.mark());
		System.out.println("position:"+buff.position());
		System.out.println("limit:"+buff.limit());
		System.out.println("capacity:"+buff.capacity());
	}

}

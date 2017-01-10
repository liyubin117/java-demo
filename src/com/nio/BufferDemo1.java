package com.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;
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
		
		//打印buff中的内容的两种方法
		//第一种不适用于ByteBuffer
		System.out.println(buff);
		buff.position(0);
		char[] charArr=new char[buff.remaining()];
		buff.get(charArr);
		System.out.println(new String(charArr));
		
		//wrap方法的使用
		ByteBuffer bbuf=ByteBuffer.allocate(10);
		ByteBuffer newBbuf=ByteBuffer.allocate(10);
		/*此时会因大于长度限制而报错
		bbuf.put("Hello ByteBuffer,i am liyubin!!!".getBytes());*/
		String data="Hello ByteBuffer,i am liyubin!!!";
		newBbuf=ByteBuffer.wrap(data.getBytes());
		/*注意此时Buffer的limit、position仍是0，不可用flip()
		newBbuf.flip();*/
		byte[] bytes=new byte[newBbuf.remaining()];
		newBbuf.get(bytes);
		System.out.println(new String(bytes));
		System.out.println(new String(newBbuf.toString().getBytes()));
		System.out.println(data);
		
	}

}

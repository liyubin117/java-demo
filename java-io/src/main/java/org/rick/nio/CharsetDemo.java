package org.rick.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetDemo {

	public static void main(String[] args) throws CharacterCodingException {
		Charset UTF8charset=Charset.forName("GBK");
		//编码
		CharsetEncoder encoder=UTF8charset.newEncoder();
		String data="我是李宇彬";
		CharBuffer cbuf=CharBuffer.wrap(data);
		ByteBuffer bbuf=encoder.encode(cbuf);
		byte[] bytes=new byte[bbuf.remaining()];
		//bbuf.flip();
		bbuf.get(bytes);
		System.out.println("不解码，直接输出："+new String(bytes));
		bbuf.position(0);
		//解码
		CharsetDecoder decoder=UTF8charset.newDecoder();
		CharBuffer s=decoder.decode(bbuf);
		System.out.println("解码后："+s);
	}

}

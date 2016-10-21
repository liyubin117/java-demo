package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
	public static void main(String[] args) throws FileNotFoundException{
		FileChannel inChannel=null;
		FileChannel outChannel=null;
		try{
			File f=new File("D:/eclipse_luna/workspace_luna/JavaTest/file/HelloWorld.java");
			File f2=new File("D:/eclipse_luna/workspace_luna/JavaTest/file/HelloWorld2.java");
			
			inChannel=new FileInputStream(f).getChannel();
			MappedByteBuffer buffer=inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length() );
			
			Charset charset=Charset.forName("GBK");
			outChannel =new FileOutputStream(f2).getChannel();
			outChannel.write(buffer);
			
			buffer.clear();
			CharsetDecoder decoder=charset.newDecoder();
			CharBuffer charBuffer=decoder.decode(buffer);
			System.out.println(charBuffer);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			
		}
		
	}
}

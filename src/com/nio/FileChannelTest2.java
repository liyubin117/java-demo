package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest2 {
	public static void main(String[] args) throws FileNotFoundException{
		FileChannel inChannel=null;
		String newData=null;
		try{
			File f=new File("file/HelloWorld.java");
			
			inChannel=new RandomAccessFile(f,"rw").getChannel();
//			ByteBuffer bb=ByteBuffer.allocate(1024);
//			int buffer=inChannel.read(bb);
			MappedByteBuffer bb=inChannel.map(FileChannel.MapMode.READ_WRITE, 0, f.length() );
			
			//buffer.flip();
			Charset charset=Charset.forName("UTF8");
			CharsetDecoder decoder=charset.newDecoder();
			CharBuffer charBuffer=decoder.decode(bb);
			System.out.println(charBuffer);
			
//			bb.mark();
//			bb.clear();
//			bb.reset();
//			
//			bb.put("haha".getBytes(),0,"haha".length());
			
			newData="\nNew String to write to file..." + System.currentTimeMillis();
			bb.clear();
			bb.put(newData.getBytes());
			bb.flip();
			while(bb.hasRemaining()) {
			    inChannel.write(bb);
			}


		}catch(IOException e){
			e.printStackTrace();
		}finally{
			
		}
		
	}
}

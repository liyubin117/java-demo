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
import java.util.Date;

public class FileChannelTest2 {
	public static void main(String[] args) throws FileNotFoundException{
		FileChannel inChannel=null;
		String newData=null;
		try{
			//直接将所有数据映射进缓冲
			File f=new File("file/HelloWorld.java");
			inChannel=new RandomAccessFile(f,"rw").getChannel();
			MappedByteBuffer bb=inChannel.map(FileChannel.MapMode.READ_WRITE, 0, f.length() );
			//读取
			Charset charset=Charset.forName("UTF8");
			CharsetDecoder decoder=charset.newDecoder();
			CharBuffer charBuffer=decoder.decode(bb);
			System.out.println(charBuffer);
			//写入
			bb.clear();
			bb.put("HELLO HAHAHAHA!".getBytes());
			bb.flip();
			inChannel.write(bb);
			
//			bb.mark();
//			bb.clear();
//			bb.reset();
//			
//			bb.put("haha".getBytes(),0,"haha".length());
			
			ByteBuffer bb2=ByteBuffer.allocate(1024);
			Date date=new Date(System.currentTimeMillis());
			newData="\nNew String to write to file..." + date.toString();
			bb2.clear();
			bb2.put(newData.getBytes());
			
			while(bb2.hasRemaining()) {
				bb2.flip();
			    inChannel.write(bb2);
			}


		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				inChannel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

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
		FileInputStream is=null;
		FileOutputStream os=null;
		FileChannel inChannel=null;
		FileChannel outChannel=null;

		try{
			File f=new File("file/HelloWorld.java");
			File f2=new File("file/HelloWorld2.java");
			is=new FileInputStream(f);
			os=new FileOutputStream(f2,true);
			
			inChannel=is.getChannel();
			MappedByteBuffer buffer=inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length() );
			
			Charset charset=Charset.forName("UTF8");
			outChannel =os.getChannel();
			buffer.clear();
			outChannel.write(buffer);
			
			buffer.flip();
			CharsetDecoder decoder=charset.newDecoder();
			CharBuffer charBuffer=decoder.decode(buffer);
			System.out.println(charBuffer);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				inChannel.close();
				outChannel.close();
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

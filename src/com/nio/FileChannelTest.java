package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
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
			outChannel=os.getChannel();

//			MappedByteBuffer buffer=inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length() );
//			outChannel =os.getChannel();
//			buffer.clear();
//			outChannel.write(buffer);

            ByteBuffer buffer = ByteBuffer.allocate(10);
            int length=-1;
            while((length=inChannel.read(buffer))!=-1){
                //第一次切换，切到读模式
                buffer.flip();
                int outlength=0;
                while((outlength=outChannel.write(buffer))!=0){
                    System.out.println("写入字节数:" + outlength);
                }
                //第二次切换，切到写模式
                buffer.clear();
            }
            //强制刷新到磁盘
            outChannel.force(true);

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

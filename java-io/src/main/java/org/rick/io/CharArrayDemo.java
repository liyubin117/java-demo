package org.rick.io;

import java.io.*;

public class CharArrayDemo {

	public static void main(String[] args) {
		File f=new File("file"+File.separator+"Cac.java");
		Reader reader = null;
		Writer writer = null;
		try {
			reader=new InputStreamReader(new FileInputStream(f),"UTF-8");
			writer=new CharArrayWriter();
			char[] chars=new char[1024];
			int readed=0;
			while((readed=reader.read(chars))!=-1){
				writer.write(chars, 0, readed);
			}
			System.out.println(writer.toString());
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

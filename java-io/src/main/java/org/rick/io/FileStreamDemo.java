package org.rick.io;

import java.io.*;

public class FileStreamDemo {

	public static void main(String[] args) throws IOException {
		File f=new File("file"+File.separator+"test.file");
		OutputStream os=new FileOutputStream(f,true);
		Writer w=new OutputStreamWriter(os);
		String s="hello StreamRW\n";
		w.write(s);
		w.close();
		
		InputStream is=new FileInputStream(f);
		Reader r=new InputStreamReader(is);
		char[] c=new char[1024];
		int i=r.read(c);
		System.out.println(new String(c));
		r.close();
	}

}

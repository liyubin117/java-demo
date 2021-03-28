package com.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

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

package com.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class SystemIO {

	public static void main(String[] args) throws IOException {
		OutputStream os=System.out;
		byte[] bs="Hello SystemIO\n".getBytes();
		os.write(bs);
		
		InputStream is=System.in;
		byte[] bs2=new byte[8];
		/*Scanner scanner=new Scanner(System.in);
		String str=scanner.nextLine();*/
		is.read(bs2);
		os.write(bs2);
		
		String str="Hello\n";
		try{
			os.write(Integer.parseInt(str));
		}catch(Exception e){
			System.err.println(e);
		}
	}

}

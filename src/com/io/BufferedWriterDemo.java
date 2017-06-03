package com.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import com.pubtest.Emp;

public class BufferedWriterDemo {

	public static void main(String[] args) {
		List<Emp> le=Arrays.asList(
				new Emp("李",26,80000),
				new Emp("叶",24,5000)
				);
		File f=new File("file"+File.separator+"Emp.txt");
		BufferedWriter writer = null;
		try {
			writer=new BufferedWriter(new FileWriter(f));
			for(Emp e:le){
				writer.write(e.toString());
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}

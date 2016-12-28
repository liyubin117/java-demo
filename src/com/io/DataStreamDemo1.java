package com.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class DataStreamDemo1 {

	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		Properties p=System.getProperties();
		System.out.println(p.getProperty("file.encoding"));
		
		File f=new File(/*"file"+File.separator+*/"c:/"+"testDataStream.file");
		DataOutputStream dos=new DataOutputStream(new FileOutputStream(f));
		String[] name=new String[]{"衬衣","手套","毛巾"};
		float[] price=new float[]{98.8f,30.3f,50.5f};
		int[] nums=new int[]{3,2,1};
		/*dos.write("品种".getBytes("UTF-8"));
		dos.writeChar('\t');
		dos.write("价格".getBytes("UTF-8"));
		dos.writeChar('\t');
		dos.write("数量".getBytes("UTF-8"));
		dos.writeChar('\n');*/
		for(int i=0;i<name.length;i++){
			dos.write(name[i].getBytes("UTF-8"));
			dos.writeChar('\t');
			dos.writeFloat(price[i]);
			dos.writeChar('\t');
			dos.writeInt(nums[i]);
			dos.writeChar('\n');
		}
		dos.close();
		
		//不知道为什么有乱码
		DataInputStream dis=new DataInputStream(new FileInputStream(f));
		int len=0;
		char[] temp;
		char ca=0;
		String name2=null;
		float price2=0.0f;
		int nums2=0;
		try{
			while(/*(ca=dis.readChar())!=-1*/true){
				temp=new char[500];
				len=0;

				while((ca=dis.readChar())!='\t'){
					temp[len]=ca;
					len++;
				}
				name2=new String(temp,0,len);
				//dis.readChar(); //读取\t，此处不用读，因为前一步判断是否等于'\t'并跳出循环说明已经读出'\t'了
				price2=dis.readFloat();
				dis.readChar(); //读取\t
				nums2=dis.readInt();
				dis.readChar(); //读取\n
				System.out.printf("品种：%s，价格：%5.2f，数量：%d\n", name2,price2,nums2);
			}
		}catch(Exception e){
			dis.close();
		}
		
		
	}

}

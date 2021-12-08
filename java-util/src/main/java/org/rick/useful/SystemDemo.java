package org.rick.useful;

import java.util.UUID;

public class SystemDemo {

	public static void main(String[] args) {
		//取得当前系统的所有属性
		System.getProperties().list(System.out);
		
		System.out.println("当前字符集："+System.getProperty("file.encoding")
		+"\n当前目录："+System.getProperty("user.dir")
		);
		
		System.out.print("换行符："+System.getProperty("line.separator"));

		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
        System.out.println(System.nanoTime());

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
	}

}

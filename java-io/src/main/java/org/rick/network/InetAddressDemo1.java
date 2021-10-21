package org.rick.network;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressDemo1 {
	public static void main(String[] args) throws IOException{
		InetAddress ia=InetAddress.getByName("www.baidu.com");
		System.out.println(ia.getCanonicalHostName()+" "+ia.getHostAddress()+" "+ia.getHostName());
		System.out.println(ia.isReachable(1000));
		
		ia=InetAddress.getLocalHost();
		System.out.println(ia.getHostAddress()+" "+ia.getHostName());
		
		byte[] bytes={0,0,0,0};
		ia=InetAddress.getByAddress(bytes);
		System.out.println(ia.getHostAddress()+" "+ia.getHostName());
		
		ia=InetAddress.getByAddress("lipc",bytes);
		System.out.println(ia.getHostAddress()+" "+ia.getHostName());
		
		
		InetAddress[] ias=InetAddress.getAllByName("localhost");
		System.out.println("--------------");
		for(InetAddress i:ias){
			System.out.println(i.getHostAddress()+" "+i.getHostName());
		}
		System.out.println("--------------");

	}
}

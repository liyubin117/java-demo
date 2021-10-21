package org.rick;

public class RuntimeDemo {
	public static void main(String[] args){
		Runtime run=Runtime.getRuntime();
		System.out.println("最大内存量："+run.maxMemory());
		System.out.println("空闲内存量："+run.freeMemory());
		long l=run.freeMemory();
		
		String s="hello liyubin "+"a"+"b"+"c"+"d"+"1"+1+"welcome"+"ha";
		for(int i=0;i<10000000;i++){
			s+=i;
		}
		System.out.println("空闲内存量："+run.freeMemory());
		long l2=run.freeMemory();
		System.out.println("使用内存量："+(l-l2));

		run.gc();
		System.out.println("gc后空闲内存量："+run.freeMemory());
		long l3=run.freeMemory();
		
		StringBuffer buf=new StringBuffer("hello liyubin "); 
		buf.append("a").append("b").append("c").append("d")
		.append("1").append(1).
		append("welcome").append("ha");
		
		for(int i=0;i<10000000;i++){
			buf.append(i);
		}
		System.out.println("空闲内存量："+run.freeMemory());
		long l4=run.freeMemory();
		System.out.println("使用内存量："+(l3-l4));		

	
	}
}

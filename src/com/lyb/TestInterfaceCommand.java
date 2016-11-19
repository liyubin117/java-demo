package com.lyb;

/*用接口实现传入代码块的类似功能，体现了命令模式的设计思想*/
interface Command{
	void process(int[] target);
}

class PrintCommand implements Command{

	@Override
	public void process(int[] target) {
		for(int i=1;i<=target.length;i++){
			System.out.println("第"+i+"个参数是："+target[i-1]);
		}
	}
	
}

class SumCommand implements Command{

	@Override
	public void process(int[] target) {
		int sum=0;
		for(int a:target){
			sum+=a;
		}
		System.out.println("参数之和为："+sum);
	}
	
}

class ProcessArray{
	public static void processArray(int[] target,Command cmd){
		cmd.process(target);
	}
}



public class TestInterfaceCommand {
	public static void main(String[] args){
		int[] target={1,10,-1,500};
		ProcessArray.processArray(target, new PrintCommand());
		ProcessArray.processArray(target, new SumCommand());

	}

}

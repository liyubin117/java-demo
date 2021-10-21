package org.rick.jvm;

public class FinalizeEscapeGC {
	public static FinalizeEscapeGC SAVE_HOOK=null;
	public void isAlive(){
		System.out.println("i am alive :)");
	}
	@Override
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("FinalizeEscapeGC finalize method executed!");
		SAVE_HOOK=this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK=new FinalizeEscapeGC();
		
		//第一次回收，成功活下来
		SAVE_HOOK=null;
		System.gc();
		//finalize方法优先级很低，等待0.5秒方便其执行
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("i am dead :(");
		}
		
		//第二次回收，因为finalize方法已被执行过，不再执行，从而无法被引用，只能被回收
		SAVE_HOOK=null;
		System.gc();
		Thread.sleep(500);
		if(SAVE_HOOK!=null){
			SAVE_HOOK.isAlive();
		}else{
			System.out.println("i am dead :(");
		}
	}

}

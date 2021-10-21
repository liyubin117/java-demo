package org.rick;

import java.io.IOException;

public class RuntimeExec {
	public static void main(String[] args){
		Runtime run=Runtime.getRuntime();

		Process p=null;
		
		try {
			p=run.exec("notepad.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			Thread.sleep(3000);
		}catch(Exception e){
			e.printStackTrace();
		}
		p.destroy();
	}
}

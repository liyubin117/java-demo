package com.useful.international;

import java.util.ListResourceBundle;

public class Messageclass_zh_CN extends ListResourceBundle{
	private final Object[][] data={
			{"info","你好"},
			{"comma","，"},
			{"name","李宇彬"}
	};
	
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return data;
	}
	

}

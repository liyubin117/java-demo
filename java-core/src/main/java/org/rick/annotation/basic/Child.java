package org.rick.annotation.basic;

@Description(author = "liyubin", desc = "Child class annotation")
public class Child extends Person {

	@Override
	@Description(author = "liyubin", desc = "Child function annotation")
	public String name() {
		return super.name();
	}

	@Override
	public int age() {
		return super.age();
	}

	@Override
	public void sing() {
		super.sing();
	}

}

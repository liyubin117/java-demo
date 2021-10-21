package model;

public class Person implements Cloneable{
	
	private String name;
	private transient String addr;
	private int age;
	
	public Person(String name){
		this.name=name;
	}
	public Person(){
		
	}
	public Person(String name,String addr,int age){
		this.name=name;
		this.addr=addr;
		this.age=age;
	}
	public String toString(){
		return "Person name:"+this.name+" addr:"+this.addr+" age："+this.age;
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		//return super.clone();
		return new Person(this.name,this.addr,this.age);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
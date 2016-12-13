package com.useful;

class Name{
	private String firstName;
	private String lastName;
	public Name(String first,String last){
		this.firstName=first;
		this.lastName=last;
	}
	@Override
	public boolean equals(Object o){
		if(this==o){
			return true;
		}
		if(!(o instanceof Name)){
			return false;
		}
		Name name=(Name)o;
		if(this.firstName.equals(name.firstName)&&this.lastName.equals(name.lastName)){
			return true;
		}else{
			return false;
		}
	}
}

public class EqualsDemo1 {

	public static void main(String[] args) {
		Name n1=new Name("Mick","Jordan");
		Name n2=new Name("Mick","Jordan");
		Name n3=new Name("Jack","Lee");
		System.out.println(n1.equals(n2));
		System.out.println(n1.equals(n3));
	}

}

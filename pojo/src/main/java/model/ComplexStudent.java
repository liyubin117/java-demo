package model;

import java.util.Map;

//复杂对象
public class ComplexStudent {
	String name;
	int age;
	Map<String, Double> scores;
	ContactInfo contactInfo;

	public ComplexStudent() {
		super();
	}

	public ComplexStudent(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Map<String, Double> getScores() {
		return scores;
	}

	public void setScores(Map<String, Double> scores) {
		this.scores = scores;
	}

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	@Override
	public String toString() {
		return "ComplexStudent [name=" + name + ", age=" + age + ", scores=" + scores + ", contactInfo=" + contactInfo
				+ "]";
	}
}
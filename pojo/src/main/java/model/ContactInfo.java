package model;

public class ContactInfo {

	String phone;
	String address;
	String email;

	public ContactInfo(String phone, String address, String email) {
		super();
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContactInfo [phone=" + phone + ", address=" + address + ", email=" + email + "]";
	}

	public ContactInfo() {
		super();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

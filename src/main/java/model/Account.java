package model;

public class Account {
	private String mail;
	private String password;
	private int role;
	private String name;
	private String address;
	private String phone;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String mail, String password, int role, String name, String address, String phone) {
		super();
		this.mail = mail;
		this.password = password;
		this.role = role;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Account [mail=" + mail + ", password=" + password + ", role=" + role + ", name=" + name + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	
	
}

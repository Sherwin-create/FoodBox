package com.sherwin.foodbox.model;

public class Jwt {
	String username;
	String password;
	public Jwt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Jwt(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Jwt [username=" + username + ", password=" + password + "]";
	}
	
	
}

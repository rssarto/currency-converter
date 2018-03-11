package com.converter.model;

public class LoginUser {
	
    private String userName;
    private String password;
    
    public LoginUser() {}

    public LoginUser(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }	

}

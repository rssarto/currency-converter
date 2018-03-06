package com.converter.model;

public class Currency {
	
	private String initials;
	private String description;
	
	public Currency() {}
	
	public Currency(String initials, String description) {
		super();
		this.initials = initials;
		this.description = description;
	}
	
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

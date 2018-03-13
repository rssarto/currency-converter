package com.converter.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="USERS", schema="public")
public class User {
	
	@Id
	@GeneratedValue(generator="user_id_seq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="user_id_seq", sequenceName="user_id_seq", allocationSize=1)
	private long id;
	
	@NotNull(message="The user name is mandatory")
	@Column(unique=true, name="user_name")
	private String userName;
	
	@NotNull(message="The password is mandatory")
	private String password;
	
	@NotNull(message="The email is mandatory")
	@Column(unique=true)
	private String email;
	
	@NotNull(message="The street is mandatory")
	private String street;
	
	@NotNull(message="The zip code is mandatory")
	@Column(name="zip_code")
	private String zipCode;
	
	@NotNull(message="The city is mandatory")
	private String city;
	
	@NotNull(message="The country is mandatory")
	private String country;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "Brazil/East")
	@Column(name="birth_date")
	private Date birthDate;
	
	@Basic(fetch=FetchType.LAZY)
	@OneToMany(mappedBy="user")
	private List<Quotation> quotations;
	
	public User() {}

	public User(String userName, String password, String email, String street, String zipCode, String city,
			String country) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}

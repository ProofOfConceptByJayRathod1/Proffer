package com.proffer.endpoints.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="seller")
public class Seller {
	
	@Id
	private String  email;
	private String houseName;
	private String address;
	private String contact;
	private String password;
	
	public Seller() {
		super();
	}

	public Seller(String email, String houseName, String address, String contact, String password) {
		super();
		this.email = email;
		this.houseName = houseName;
		this.address = address;
		this.contact = contact;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
                                                                             
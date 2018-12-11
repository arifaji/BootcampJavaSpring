package com.bootcamp.mavenapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name="customernumber")
	private int customerNumber;
	@Column (name="firstname")
	private String firstName;
	@Column (name="lastname")
	private String lastName;
	@Column (name="birthdate")
	private Date birthDate;
	@Column (name="username")
	private String username;
	@Column (name="password")
	private String password;
	@Column (name="phonetype")
	private String phoneType;
	@Column (name="phonenumber")
	private String phoneNumber;
	
	public Customer () {}
	public Customer (String firstName, String lastName, Date birthDate, String username, String password, String phoneType, String phoneNumber, int customerNumber ) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.username = username;
		this.password = password;	
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
		this.customerNumber = customerNumber;
	}
	public int getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
	public String getPhoneType() {
		return phoneType;
	}
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}

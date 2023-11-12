package com.employees;

import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;

public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;
	private UUID id;
	private String name, surname, username, email, department;
	private HashSet<String> phoneNumbers;

	public Employee(String name, String surname, String username, String email, String department,
			String phoneNumber) {
		id = UUID.randomUUID();
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.department = department;
		this.phoneNumbers = new HashSet<>();
		this.phoneNumbers.add(phoneNumber);
	}

	public void addPhoneNumber(String phoneNumber) throws Exception {
		if (phoneNumbers.contains(phoneNumber))
			throw new Exception("Phone number already present");
		phoneNumbers.add(phoneNumber);
	}

	public void deletePhoneNumber(String phoneNumber) throws Exception {
		if (!phoneNumbers.contains(phoneNumber))
			throw new Exception("Phone number not present");
		phoneNumbers.remove(phoneNumber);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public HashSet<String> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(HashSet<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", department="
				+ department + ", phoneNumbers=" + phoneNumbers + "]";
	}

}

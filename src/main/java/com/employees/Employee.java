package com.employees;

import java.util.HashSet;
import java.util.UUID;

public class Employee {
	
	private UUID id;
	private String name, username, email, department;
	private HashSet <String> phoneNumbers;
	
	public Employee(String name, String username, String email, String department, String [] phoneNumbers) {
		id = UUID.randomUUID();
		this.name = name;
		this.username = username;
		this.email = email;
		this.department = department;
		this.phoneNumbers = new HashSet <> ();
		for (String phoneNumber : phoneNumbers)
			this.phoneNumbers.add(phoneNumber);
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDepartment(String department) {
		this.department = department;
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

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getDepartment() {
		return department;
	}

	public HashSet<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	
}

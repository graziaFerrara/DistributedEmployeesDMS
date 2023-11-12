package com.employees.ws;

import database.Database;

/**
 * This service allows employees to manage and update the content of the
 * employee database.
 * 
 * @author graziaferrara
 *
 */
public class EmployeeService {

	private static Database database = new Database();
	
	public String insertEmployee(String username, String name, String surname, String email, String department,
			String phoneNumbers) {
		try {
			database.insertEmployee(username, name, surname, email, department, phoneNumbers);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Employee " + username + " correctly inserted";
	}
	
	public String removeEmployeeByUsername(String username) {
		try {
			database.removeEmployeeByUsername(username);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Employee " + username + " correctly deleted";
	}
	
	public String updateEmployeeByUsername(String username, String fieldToUpdate, String newValue) {
		try {
			database.updateEmployeeByUsername(username, fieldToUpdate, newValue);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Employee " + username + " correctly updated";
	}

}

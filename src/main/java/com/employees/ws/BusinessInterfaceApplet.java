package com.employees.ws;

import java.util.HashSet;
import java.util.UUID;

import com.employees.Employee;

import database.Database;

/**
 * Allows users to search for employees based on various criteria.
 * @author graziaferrara
 *
 */
public class BusinessInterfaceApplet {
	
	private static Database database = new Database();
	
	public String searchEmployeeById(String id) {
		Employee empl;
		try {
			empl = database.searchEmployeeById(UUID.fromString(id));
		} catch (Exception e) {
			return e.getMessage();
		}
		return empl.toString();
	}
	
	public String searchEmployeeByUsername(String username) {
		Employee empl;
		try {
			empl = database.searchEmployeeByUsername(username);
		} catch (Exception e) {
			return e.getMessage();
		}
		return empl.toString();
	}
	
	public String searchEmployeesByEmail(String email) {
		HashSet <Employee> empl;
		try {
			empl = database.searchEmployeesByEmail(email);
		} catch (Exception e) {
			return e.getMessage();
		}
		return empl.toString();
	}
	
	public String searchEmployeesByDepartment(String detpartment) {
		HashSet <Employee> empl;
		try {
			empl = database.searchEmployeesByDepartment(detpartment);
		} catch (Exception e) {
			return e.getMessage();
		}
		return empl.toString();
	}
	
	public String searchEmployeesByPhoneNumber(String phoneNumber) {
		HashSet <Employee> empl;
		try {
			empl = database.searchEmployeesByPhoneNumber(phoneNumber);
		} catch (Exception e) {
			return e.getMessage();
		}
		return empl.toString();
	}

}


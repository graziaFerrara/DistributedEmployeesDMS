package database;

import com.employees.*;
import java.util.*;
import java.util.Map.Entry;

public class Database {
	
	private static HashMap <UUID, Employee> database;
	private static HashMap <String, UUID> usernameIDCorrespondance;
	
	public Database() {
		database = new HashMap <> ();
		usernameIDCorrespondance = new HashMap <> ();
	}
	
	// Search operations
	
	public Employee searchEmployeeById(UUID id) throws Exception {
		Employee employee = database.get(id);
		if (employee == null) throw new Exception("Employee not found");
		return employee;
	}
	
	public Employee searchEmployeeByUsername(String username) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
		 throw new Exception("Employee not found");
		return database.get(id);
	}
	
	public Employee searchEmployeeByEmail(String email) throws Exception {
		for (Entry <UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getEmail().equals(email))
				return entry.getValue();
		throw new Exception("Employee not found");
	}
	
	public HashSet <Employee> searchEmployeesByDepartment(String department) throws Exception {
		HashSet <Employee> foundEmployees = new HashSet <> ();
		for (Entry <UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getDepartment().equals(department))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size()<1) throw new Exception("Employee not found");
		return foundEmployees;
	}
	
	public Employee searchEmployeesByPhoneNumber(String phoneNumber) throws Exception {
		for (Entry <UUID, Employee> entry : database.entrySet())
			if(entry.getValue().getPhoneNumbers().contains(phoneNumber))
				return entry.getValue();
		throw new Exception("Employee not found");
	}
	
	// Add, remove, update operations
	
	public boolean addEmployee(String username, String name, String email, String department, String [] phoneNumbers) throws Exception {
		if (usernameIDCorrespondance.containsKey(username))
			throw new Exception("Username already present");
		Employee newEmployee = new Employee(username, name, email, department, phoneNumbers);
		database.put(newEmployee.getId(), newEmployee);
		usernameIDCorrespondance.put(username, newEmployee.getId());
		return true;
	}

	public boolean removeEmployeeByUsername(String username) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		database.remove(id);
		usernameIDCorrespondance.remove(username);
		return true;
	}
	
	public boolean updateEmployeeByUsername(String username, String fieldToUpdate, String newValue) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return updateField(id,fieldToUpdate,newValue);
	}
	
	private boolean updateField(UUID id, String fieldToUpdate, String newValue) {
		switch(fieldToUpdate.toLowerCase().strip()) {
		case "username":
			updateUsername(id,newValue);
			break;
		case "name":
			updateName(id, newValue);
			break;
		case "email":
			updateEmail(id, newValue);
			break;
		case "department":
			updateDepartment(id, newValue);
			break;
		default:
			return false;
		}
		return true;
	}

	private void updateDepartment(UUID id, String newValue) {
		database.get(id).setDepartment(newValue);
	}

	private void updateEmail(UUID id, String newValue) {
		database.get(id).setEmail(newValue);
	}

	private void updateName(UUID id, String newValue) {
		database.get(id).setName(newValue);
	}

	private void updateUsername(UUID id, String newValue) {
		database.get(id).setUsername(newValue);
	}
}

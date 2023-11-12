package database;

import com.employees.*;
import java.util.*;
import java.util.Map.Entry;

public class Database {

	private static HashMap<UUID, Employee> database = new HashMap<>();
	private static HashMap<String, UUID> usernameIDCorrespondance = new HashMap<>();

	// Search operations

	public Employee searchEmployeeById(UUID id) throws Exception {
		Employee employee = database.get(id);
		if (employee == null)
			throw new Exception("Employee not found");
		return employee;
	}

	public Employee searchEmployeeByUsername(String username) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return database.get(id);
	}

	public HashSet<Employee> searchEmployeesByName(String name) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getName().equals(name))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	public HashSet<Employee> searchEmployeesBySurname(String surname) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getSurname().equals(surname))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	public HashSet<Employee> searchEmployeesByEmail(String email) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getEmail().equals(email))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	public HashSet<Employee> searchEmployeesByDepartment(String department) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getDepartment().equals(department))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	public HashSet<Employee> searchEmployeesByPhoneNumber(String phoneNumber) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getPhoneNumbers().contains(phoneNumber))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	// Add, remove, update operations

	public boolean insertEmployee(String username, String name, String surname, String email, String department,
			String phoneNumbers) throws Exception {
		if (usernameIDCorrespondance.containsKey(username))
			throw new Exception("Username already present");
		Employee newEmployee = new Employee(name, surname, username, email, department, phoneNumbers);
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
		return updateField(id, fieldToUpdate, newValue);
	}

	private boolean updateField(UUID id, String fieldToUpdate, String newValue) throws Exception {
		switch (fieldToUpdate.toLowerCase().strip()) {
		case "username":
			updateUsername(id, newValue);
			break;
		case "name":
			updateName(id, newValue);
			break;
		case "surname":
			updateSurname(id, newValue);
			break;
		case "email":
			updateEmail(id, newValue);
			break;
		case "department":
			updateDepartment(id, newValue);
			break;
		default:
			throw new Exception("Not valid field");
		}
		return true;
	}

	private void updateSurname(UUID id, String newValue) {
		database.get(id).setSurname(newValue);
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

	private void updateUsername(UUID id, String newValue) throws Exception {
		if (usernameIDCorrespondance.containsKey(newValue)) throw new Exception("Username already in use");
		database.get(id).setUsername(newValue);
	}
}

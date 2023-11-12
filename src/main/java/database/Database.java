package database;

import com.employees.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Representation of the employee's database.
 * @author graziaferrara
 *
 */
public class Database {

	private static HashMap<UUID, Employee> database = new HashMap<>();
	private static HashMap<String, UUID> usernameIDCorrespondance = new HashMap<>();

	// Search operations

	/**
	 * Method to search all the employees.
	 * @return
	 */
	public Collection<Employee> searchAllEmployees() {
		return (Collection<Employee>) database.values();
	}

	/**
	 * Method to search a employee given the id.
	 * @param idString
	 * @return
	 * @throws Exception
	 */
	public Employee searchEmployeeById(String idString) throws Exception {
		UUID id = UUID.fromString(idString);
		Employee employee = database.get(id);
		if (employee == null)
			throw new Exception("Employee not found");
		return employee;
	}

	/**
	 * Method to search a employee given the username.
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public Employee searchEmployeeByUsername(String username) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return database.get(id);
	}

	/**
	 * Method to search all the employees with the same name.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public HashSet<Employee> searchEmployeesByName(String name) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getName().equals(name))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	/**
	 * Method to search all the employees with the same surname.
	 * @param surname
	 * @return
	 * @throws Exception
	 */
	public HashSet<Employee> searchEmployeesBySurname(String surname) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getSurname().equals(surname))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	/**
	 * Method to search all the employees with the same email.
	 * @param email
	 * @return
	 * @throws Exception
	 */
	public HashSet<Employee> searchEmployeesByEmail(String email) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getEmail().equals(email))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	/**
	 * Method to search all the employees with the same department.
	 * @param department
	 * @return
	 * @throws Exception
	 */
	public HashSet<Employee> searchEmployeesByDepartment(String department) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getDepartment().equals(department))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	/**
	 * Method to search all the employees with the same phone number.
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public HashSet<Employee> searchEmployeesByPhoneNumber(String phoneNumber) throws Exception {
		HashSet<Employee> foundEmployees = new HashSet<>();
		for (Entry<UUID, Employee> entry : database.entrySet())
			if (entry.getValue().getPhoneNumbers().contains(phoneNumber))
				foundEmployees.add(entry.getValue());
		if (foundEmployees.size() < 1)
			throw new Exception("No employee found");
		return foundEmployees;
	}

	// insert, remove, update operations

	/**
	 * Method to insert a employee.
	 * @param username
	 * @param name
	 * @param surname
	 * @param email
	 * @param department
	 * @param phoneNumbers
	 * @return
	 * @throws Exception
	 */
	public boolean insertEmployee(String username, String name, String surname, String email, String department,
			String phoneNumbers) throws Exception {
		if (usernameIDCorrespondance.containsKey(username))
			throw new Exception("Username already present");
		Employee newEmployee = new Employee(name, surname, username, email, department, phoneNumbers);
		database.put(newEmployee.getId(), newEmployee);
		usernameIDCorrespondance.put(username, newEmployee.getId());
		return true;
	}

	/**
	 * Method to remove a employee given the username.
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean removeEmployeeByUsername(String username) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		database.remove(id);
		usernameIDCorrespondance.remove(username);
		return true;
	}

	/**
	 * Method to remove a employee given the id.
	 * @param idString
	 * @return
	 * @throws Exception
	 */
	public boolean removeEmployeeById(String idString) throws Exception {
		UUID id = UUID.fromString(idString);
		if (!database.containsKey(id))
			throw new Exception("Employee not found");
		Employee empl = database.get(id);
		database.remove(id);
		usernameIDCorrespondance.remove(empl.getUsername());
		return true;
	}

	/**
	 * Method to update a employee given the username.
	 * @param username
	 * @param fieldToUpdate
	 * @param newValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmployeeByUsername(String username, String fieldToUpdate, String newValue) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return updateField(id, fieldToUpdate, newValue);
	}

	/**
	 * Method to update a employee given the id.
	 * @param idString
	 * @param fieldToUpdate
	 * @param newValue
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmployeeById(String idString, String fieldToUpdate, String newValue) throws Exception {
		UUID id = UUID.fromString(idString);
		if (!database.containsKey(id))
			throw new Exception("Employee not found");
		return updateField(id, fieldToUpdate, newValue);
	}

	/**
	 * Method to insert the phone number of a employee given the username.
	 * @param username
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean insertPhoneNumberByUsername(String username, String phoneNumber) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return insertPhoneNumber(id, phoneNumber);
	}

	/**
	 * Method to insert the phone number of a employee given the id.
	 * @param idString
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean insertPhoneNumberById(String idString, String phoneNumber) throws Exception {
		UUID id = UUID.fromString(idString);
		if (!database.containsKey(id))
			throw new Exception("Employee not found");
		return insertPhoneNumber(id, phoneNumber);
	}
	
	/**
	 * Method to update the phone number of a employee given the username.
	 * @param username
	 * @param oldPhoneNumber
	 * @param newPhoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean updatePhoneNumberByUsername(String username, String oldPhoneNumber, String newPhoneNumber) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return updatePhoneNumber(id, oldPhoneNumber, newPhoneNumber);
	}

	/**
	 * Method to update the phone number of a employee given the id.
	 * @param idString
	 * @param oldPhoneNumber
	 * @param newPhoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean updatePhoneNumberById(String idString, String oldPhoneNumber, String newPhoneNumber) throws Exception {
		UUID id = UUID.fromString(idString);
		if (!database.containsKey(id))
			throw new Exception("Employee not found");
		return updatePhoneNumber(id, oldPhoneNumber, newPhoneNumber);
	}

	/**
	 * Method to remove the phone number of a employee given the username.
	 * @param username
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean removePhoneNumberByUsername(String username, String phoneNumber) throws Exception {
		UUID id = usernameIDCorrespondance.get(username);
		if (id == null)
			throw new Exception("Employee not found");
		return removePhoneNumber(id, phoneNumber);
	}

	/**
	 * Method to remove the phone number of a employee given the id.
	 * @param idString
	 * @param phoneNumber
	 * @return
	 * @throws Exception
	 */
	public boolean removePhoneNumberById(String idString, String phoneNumber) throws Exception {
		UUID id = UUID.fromString(idString);
		if (!database.containsKey(id))
			throw new Exception("Employee not found");
		return removePhoneNumber(id, phoneNumber);
	}

	private boolean insertPhoneNumber(UUID id, String phoneNumber) throws Exception {
		return database.get(id).insertPhoneNumber(phoneNumber);
	}
	
	private boolean updatePhoneNumber(UUID id, String oldPhoneNumber, String newPhoneNumber) throws Exception {
		return database.get(id).updatePhoneNumber(oldPhoneNumber, newPhoneNumber);
	}

	private boolean removePhoneNumber(UUID id, String phoneNumber) throws Exception {
		return database.get(id).removePhoneNumber(phoneNumber);
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
		if (usernameIDCorrespondance.containsKey(newValue))
			throw new Exception("Username already in use");
		database.get(id).setUsername(newValue);
	}

}

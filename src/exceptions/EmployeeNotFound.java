package exceptions;

import organization.Employee;

public class EmployeeNotFound extends Exception {
	public EmployeeNotFound() {
		super("Error Finding Employee.class... Employee Not Found...");
	}
	
	public EmployeeNotFound(String message) {
		super(message);
	}
}

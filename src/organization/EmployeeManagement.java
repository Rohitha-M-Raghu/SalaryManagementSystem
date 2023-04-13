package organization;

import java.util.List;

import exceptions.EmployeeNotFound;

public interface EmployeeManagement {
	public void addEmployee(String name, float salary);
	public void assignSubordinate(String managerName, String subordinateName) throws EmployeeNotFound;
	public void displayEmployeeDetails(String employeeName);
	public boolean displaySubordinates(List<String> subordinates);
	public void  displaySubordinates(Employee emp);
	public void findemployees(Employee emp);
	public void displayAllEmployees();
	public boolean hasSubordinate(Employee manager, Employee subordinate);
}

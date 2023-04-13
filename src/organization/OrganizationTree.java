package organization;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import exceptions.EmployeeNotFound;

public class OrganizationTree {
	private String topEmployee;
	private Map<String, Employee> employee; 
	
	public OrganizationTree() {
		employee = new HashMap<>();
		topEmployee = "noname";
	}
	
	public void addEmployee(String name, float salary) {
		employee.put(name, new Employee(name, salary));
		if(topEmployee.equalsIgnoreCase("noname")) {
			setTopEmployee(name);
		}
	}
	
	public void addSubordinate(String managerName, String subordinateName) throws EmployeeNotFound {
		Employee manager = employee.get(managerName);
		Employee subordinate = employee.get(subordinateName);
		try {
			if(manager != null && subordinate != null) {
				manager.addSubordinate(subordinate);
				subordinate.setManagerName(managerName);
				if(topEmployee.equalsIgnoreCase(subordinateName)) {
					setTopEmployee(managerName);
				}
			}
		}
		catch (Exception e) {
			throw new EmployeeNotFound("Error Occured while Assigning Manager...");
		}
	}
	
	public String getTopEmployee() {
		return topEmployee;
	}

	public void setTopEmployee(String topEmployee) {
		this.topEmployee = topEmployee;
	}
	
	public void displayEmployeeDetails(String employeeName) {
		Employee employeeDetails = employee.get(employeeName);
		System.out.println("Employee Details");
		System.out.println("---------------------");
		System.out.println("Employee ID: " + employeeDetails.getId());
		System.out.println("Employee Name: " + employeeDetails.getName());
		System.out.println("Employee Salary: " + employeeDetails.getSalary());
		this.displaySubordinates(employeeDetails.getSubordinateNames());
		
	}
	
	public boolean displaySubordinates(List<String> subordinates) {
		System.out.println("Subordinates: ");
		if(subordinates.isEmpty()) {
			System.out.println("None");
			return false;
		}
		int i = 0;
		for(String name: subordinates) {
			System.out.println(++i + ". " + name);
		}
		return true;
	}

	public Employee getEmployee(String employeeName) {
		return employee.get(employeeName);
	}
	
	public void  displaySubordinates(Employee emp) {
		List<String> subordinateList = emp.getSubordinateNames();
		for (int i = 0; i < subordinateList.size(); i++) {
			System.out.print(" " + subordinateList.get(i));
			if(i < subordinateList.size() - 1) {
				System.out.print(",");
			}
		}
	}
	
	public void findemployees(Employee emp) {
		System.out.print("\n" + emp.getName() + "-" );
		this.displaySubordinates(emp);
		if(emp.getSubordinates().isEmpty()) {
			System.out.print(" none");
			return;
		}
		for(Employee e: emp.getSubordinates()) {
			findemployees(e);
		}
	}
	public void displayAllEmployees() {
		System.out.println("Employee Tree");
		System.out.println("-----------------");
		Employee emp = employee.get(topEmployee);
		this.findemployees(emp);
	}
	
	public float sumOfsubordinateSalary(Employee emp) {
		if(emp.getSubordinates().isEmpty()) {
			return 0;
		}
		float salary = 0;
		for(Employee e: emp.getSubordinates()) {
			salary += e.getSalary();
			salary += sumOfsubordinateSalary(e);
		}
		return salary;
	}
	
	public int totalSubordinates(Employee emp) {
		if(emp.getSubordinates().isEmpty()) {
			return 0;
		}
		int total = 0;
		total += emp.getSubordinates().size();
		for(Employee e: emp.getSubordinates()) {
			total += totalSubordinates(e);
		}
		return total;
	}
	
	public float salaryCalculator(Employee emp) {
		return sumOfsubordinateSalary(emp)/totalSubordinates(emp);
	}
	
	public void salaryFinder(Employee emp) {
		System.out.println("Employee Name: " + emp.getName());
		System.out.println("Employee Salary: " + emp.getSalary());
		//System.out.println("Total Subordinate Salary: " + this.sumOfsubordinateSalary(emp));
		//System.out.println("Total Subordinates: " + this.totalSubordinates(emp));
		System.out.println("Expected Salary: " + this.salaryCalculator(emp));
		if(isUnderPaid(emp)) {
			System.out.println("UnderPaid");
		}
	}
	
	public boolean isUnderPaid(Employee emp) {
		if(this.salaryCalculator(emp) > emp.getSalary()) {
			return true;
		}
		return false;
	}
	
	public void displayUnderpaidEmployee() {
		System.out.println("Under Paid Employees");
		System.out.println("----------------------");
		employee.forEach((employeeName, emp) ->{
			if(isUnderPaid(emp)) {
				System.out.println(employeeName);
			}
		});
		
	}
	
	public void displaySalaryDetails() {
		System.out.println("Salary Details");
		System.out.println("-------------------");
		employee.forEach((employeeName, emp)-> {
			System.out.println(employeeName + "\t\t" + emp.getSalary());
		});
	}
}

package organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Employee {
	private static int noOfEmployees = 0;
	private int id;
	private String name;
	private float salary;
	private List<Employee> subordinates;
	private String managerName;
	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Employee(String name, float salary) {
		noOfEmployees ++;
		id = noOfEmployees;
		this.name = name;
		this.salary = salary;
		this.managerName = "none";
		this.subordinates = new ArrayList<>();
	}

	public static int getNoOfEmployees() {
		return noOfEmployees;
	}

	public static void setNoOfEmployees(int noOfEmployees) {
		Employee.noOfEmployees = noOfEmployees;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public List<Employee> getSubordinates() {
		return subordinates;
	}
	
	public void addSubordinate(Employee subordinate) {
		subordinates.add(subordinate);
	}
	
	public List<String> getSubordinateNames(){
		List<String> subordinateNames = new ArrayList<>();
		for(Employee e: subordinates) {
			subordinateNames.add(e.getName());
		}
		return subordinateNames;
	}
	
	
}

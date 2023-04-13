package ui;


import java.util.Scanner;

import exceptions.EmployeeNotFound;
import organization.Employee;
import organization.OrganizationTree;

public class MainMenu {
	private static OrganizationTree organizationTree = new OrganizationTree();
	private static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		int choice = 0;
		while (choice!= 12) {
			displayMenu();
			System.out.print("Enter your choice: ");
			try {
				choice = scanner.nextInt();
			}
			catch (Exception e) {
				System.out.println("Wrong choice... Try Again...");
			}
			
			try {
				switch (choice) {
				case 1:
					addEmployee();
					break;
				case 2:
					displayEmployeeData();
					break;
				case 3:
					displaySubordinatesData();
					break;
				case 4:
					assignManager();
					break;
				case 5:
					displayManager();
					break;
				case 6:
					displayTopEmployee();
					break;
				case 7:
					displayEmployeeTree();
					break;
				case 8:
					findSalary();
					break;
				case 9:
					organizationTree.displaySalaryDetails();
					break;
				case 10:
					organizationTree.displayUnderpaidEmployee();
					break;
				case 11:
					findSubordinate();
					break;
				case 12:
					System.out.println("Exiting Application...");
					break;
				default:
					System.out.println("Wrong choice... Try Again...");
					break;
				}
			}
			catch(EmployeeNotFound e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static void displayMenu() {
		System.out.println("\nMENU");
		System.out.println("======================");
		System.out.println("1. Add Employee");
		System.out.println("2. Display Employee details");
		System.out.println("3. Display Subordinates");
		System.out.println("4. Assign Manager");
		System.out.println("5. Display Manager");
		System.out.println("6. Display Top Employee");
		System.out.println("7. Display Employee Tree");
		System.out.println("8. FInd salary");
		System.out.println("9. Salary Details");
		System.out.println("10. List of Underpaid Employees");
		System.out.println("11. Has Subordinate");
		System.out.println("12. Exit");
	}
	
	public static void addEmployee() {
		System.out.print("Enter employee Name: ");
		String name = scanner.next();
		if(name.equalsIgnoreCase("cancel")) {
			System.out.println("Canceled...");
			return;
		}
		System.out.print("Enter salary: ");
		float salary = scanner.nextFloat();
		organizationTree.addEmployee(name, salary);
		System.out.println("Employee Data Added Successfully...");
	}
	
	public static void displayEmployeeData() throws EmployeeNotFound {
		System.out.print("Enter Employee name: ");
		String name = scanner.next();
		try {
			organizationTree.displayEmployeeDetails(name);
		}
		catch (NullPointerException e) {
			throw new EmployeeNotFound();
		}
	}
	
	public static void displaySubordinatesData() throws EmployeeNotFound {
		System.out.print("Enter Manager Name: ");
		String name = scanner.next();
		try {
			if(!organizationTree.displaySubordinates(organizationTree.getEmployee(name).getSubordinateNames())) {
				System.out.println("Not a Manager...");
			}
		}
		catch (NullPointerException e) {
			throw new EmployeeNotFound();
		}
	}
	
	public static void assignManager() throws EmployeeNotFound {
		System.out.print("Enter Manager Name: ");
		String managerName = scanner.next();
		System.out.print("Enter Subordinate Name: ");
		String subordinateName = scanner.next();
		organizationTree.assignSubordinate(managerName, subordinateName);
	}
	
	public static void displayManager() throws EmployeeNotFound {
		System.out.print("Enter Employee Name: ");
		String name = scanner.next();
		if(name.equalsIgnoreCase(organizationTree.getTopEmployee())) {
			System.out.println("Top Employee... No managers...");
		}
		else {
			try {
				System.out.println(organizationTree.getEmployee(name).getManagerName());
			}
			catch (NullPointerException e) {
				throw new EmployeeNotFound("Manager Not Found...");
			}
		}
	}
	
	
	public static void displayTopEmployee() {
		System.out.println("Top Employee: " + organizationTree.getTopEmployee());
	}
	
	public static void displayEmployeeTree() {
		organizationTree.displayAllEmployees();
	}
	
	public static void findSalary() {
		System.out.println("Salary Details");
		System.out.println("----------------");
		System.out.print("Enter Employee Name: ");
		String name = scanner.next();
		organizationTree.salaryFinder(organizationTree.getEmployee(name));
	}
	
	public static void findSubordinate() {
		System.out.println("Enter Employee Name: ");
		String employeeName = scanner.next();
		System.out.println("Enter Manager Name: ");
		String managerName = scanner.next();
		Employee subordinate = organizationTree.getEmployee(employeeName);
		Employee manager = organizationTree.getEmployee(managerName);
		System.out.println(organizationTree.hasSubordinate(manager, subordinate));
	}
}

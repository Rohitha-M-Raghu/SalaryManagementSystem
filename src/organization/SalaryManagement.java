package organization;

public interface SalaryManagement {
	public float sumOfsubordinateSalary(Employee emp);
	public int totalSubordinates(Employee emp);
	public float salaryCalculator(Employee emp);
	public void salaryFinder(Employee emp);
	public boolean isUnderPaid(Employee emp);
	public void displayUnderpaidEmployee();
	public void displaySalaryDetails();
}

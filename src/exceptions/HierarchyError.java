package exceptions;

public class HierarchyError extends Exception{
	public HierarchyError(String message) {
		super(message);
	}
	
	public HierarchyError() {
		super("Error Occured in Organization Tree");
	}
}

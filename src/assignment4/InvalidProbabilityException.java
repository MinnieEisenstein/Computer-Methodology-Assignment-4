package assignment4;

public class InvalidProbabilityException extends Exception {
	public InvalidProbabilityException() {		
		super("Probability is invalid. Must be between 0 and 1");
	}
}

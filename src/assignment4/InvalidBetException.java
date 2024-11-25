package assignment4;

public class InvalidBetException extends Exception{
	public InvalidBetException() {		
		super("Invalid Bet. Must be in range");
	}
}

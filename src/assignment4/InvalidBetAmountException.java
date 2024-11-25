package assignment4;

public class InvalidBetAmountException extends Exception{
	public InvalidBetAmountException() {		
		super("Bet amount is invalid");
	}
}

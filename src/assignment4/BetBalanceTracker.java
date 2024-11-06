package assignment4;

public class BetBalanceTracker {
	private double minBalance;
	private IRandomValueGenerator rand;
	private double balance;
	
	//constructor 
	public BetBalanceTracker(double minBalance, IRandomValueGenerator rand){
		
	}
	
	//a simple getter for the current balance
	public double getCurrentBalance() {
		
		return 0;
		
	}
	
	//returns a boolean to indicate if the current balance is above the minimum 
	//balance by at least amnt, in which case a bet for amnt or less can be placed, 
	//or not, in which case the bet cannot be placed
	
	public boolean canBet(double amnt) {
		return true;
	}
	
	//manually increases the balance, rejects negative numbers
	public void addMoney(double amnt) {
		
	}
	
	public void betOnANumber(double amnt, int min, int max, int selectedNumber) {
		
	}
	
	public void betOnProbability(double amnt, double p) {
		
	}
	
	
	
	
	
	
	


}

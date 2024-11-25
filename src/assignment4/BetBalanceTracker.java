package assignment4;

public class BetBalanceTracker {
	private double minBalance;
	private IRandomValueGenerator rand;
	private double balance;
	
	//constructor 
	public BetBalanceTracker(double minBalance, IRandomValueGenerator rand){
		this.minBalance =minBalance;
		this.rand= rand;
		balance=0;
	}
	
	//a simple getter for the current balance
	public double getCurrentBalance() {
		
		return balance;
		
	}
	
	//returns a boolean to indicate if the current balance is above the minimum 
	//balance by at least amnt, in which case a bet for amnt or less can be placed, 
	//or not, in which case the bet cannot be placed
	
	public boolean canBet(double amnt) {
		return (balance - amnt >= minBalance);
	}
	
	//manually increases the balance, rejects negative numbers
	public void addMoney(double amnt) throws InvalidAmountException {
		if(amnt < 0) {throw new InvalidAmountException();}
		
		balance +=amnt;
	}
	
	public double betOnANumber(double amnt, int min, int max, int selectedNumber) throws InvalidBetException,  InvalidBetAmountException {
		if (rand.getRandomNum(min, max)== selectedNumber) {
			//bet is won!!
			//add the money won to balance
			//(range-1) * amnt betted
			double moneyWon = (max-min +1) *amnt;
			//this.addMoney(moneyWon);
			return moneyWon;
		}
		else {
			return 0- amnt;
		}
		
	}
	
	//for reference: (p^-1 -1) * amount
	public double betOnProbability(double amnt, double p) throws InvalidProbabilityException, InvalidBetAmountException{
		if(rand.getTrueWithProbability(p)) {
			double moneyWon = ((p/1)-1) *amnt;
			return moneyWon;
		}
		else {
			return 0-amnt;
		}
	}

	

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	


}

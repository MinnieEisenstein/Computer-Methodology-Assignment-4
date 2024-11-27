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
	
	public double betOnANumber(double amnt, int min, int max, int selectedNumber) throws InvalidBetException, InvalidBetAmountException, InvalidAmountException {
	    if (selectedNumber < min || selectedNumber > max) {
	        throw new InvalidBetException();
	    }

	    if (!canBet(amnt)) {  // Check if the bet amount is valid
	        throw new InvalidBetAmountException();
	    }

	    int randomNum = rand.getRandomNum(min, max); // Generate a random number

	    if (randomNum == selectedNumber) {
	        // Bet won!
	        double moneyWon = (max - min + 1 - 1) * amnt;  // Calculate winnings
	        balance += moneyWon;  // Update balance on win
	        return moneyWon;  // Return the winnings amount
	    } else {
	        // Bet lost!
	        balance -= amnt;  // Deduct the bet amount from balance (loss case)
	        return -amnt;  // Return the negative bet amount to indicate loss
	    }
	}
	
	//for reference: (p^-1 -1) * amount
	public double betOnProbability(double amnt, double p) throws InvalidProbabilityException, InvalidBetAmountException, InvalidAmountException{
		
		if(p > 1 || p < 0) {
			throw new InvalidProbabilityException();
		}
		
		if(!canBet(amnt)) {//throw exception if bet amnt is too much and will cause balance below minbalance
			throw new InvalidBetAmountException();
		}
		
		
		if(rand.getTrueWithProbability(p)) {
			double moneyWon =((1 / p) - 1) * amnt;
			moneyWon = roundToTwoDecimalPlaces(moneyWon); 
			 balance += moneyWon; 
			return moneyWon;
		}
		else {
			balance -= amnt;
			return -amnt;
		}
	}

	

	public void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}
	
	public double roundToTwoDecimalPlaces(double value) {
	    return Math.round(value * 100.0) / 100.0;
	}

}

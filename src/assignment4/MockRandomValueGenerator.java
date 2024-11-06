package assignment4;

import java.util.Random;

public class MockRandomValueGenerator implements IRandomValueGenerator{
	private int randomNum;
	private boolean probabilityResult;
	
	@Override
	public int getRandomNum(int min, int max) {
		Random rand = new Random(max);
		randomNum=rand.nextInt()-min;
		return randomNum;
		
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		// returns true or false based on the Probability
		double randomDecimal =this.getRandomNum(1, 100)/100;
		
		if (randomDecimal > p) {//if the random dec is less than the probability dec, return true
			return true;
		}
		return false;
	}
	
	
	public setRandomNum
	
}

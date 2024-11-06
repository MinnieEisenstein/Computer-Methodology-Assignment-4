package assignment4;

import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator{

	@Override
	public int getRandomNum(int min, int max) {
		Random rand = new Random(max);
		return rand.nextInt()-min;
		
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

}

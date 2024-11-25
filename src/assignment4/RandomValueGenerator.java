package assignment4;

import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator{


	
	//constructor that does nothing but construct a Random Value Generator Object
	public RandomValueGenerator() {
		
	}
	
	@Override
	public int getRandomNum(int min, int max) {
		Random rand = new Random();//create a new random
		return rand.nextInt(max - min + 1) + min;//get random number between the min and max
		
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		
		//make sure p is between 0 and 1
		 if (p < 0 || p > 1) {
	            throw new IllegalArgumentException("Probability must be between 0 to 1");
	        }
		// Generate a random number between 1 and 100
        int randomInt = this.getRandomNum(1, 100);

        // Convert it to a decimal by dividing by 100
        double randomDecimal = randomInt / 100.0;

        // Check if the random decimal is less than or equal to p
        if (randomDecimal <= p) {
            return true; 
        }
        return false; 
	}

}

package assignment4;

import java.util.Random;

public class MockRandomValueGenerator implements IRandomValueGenerator{
	private int randomNum;
	private boolean probabilityResult;
	
	@Override
	public int getRandomNum(int min, int max) {
		return 5;//will always return 5
		
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		 return false;//will always return false
	}
	

	
}

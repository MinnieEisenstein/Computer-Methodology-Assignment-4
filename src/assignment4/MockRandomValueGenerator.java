package assignment4;

import java.util.Random;

public class MockRandomValueGenerator implements IRandomValueGenerator{
	private int randomNum;
	private boolean probabilityResult;
	
	@Override
	public int getRandomNum(int min, int max) {
		return randomNum;//will always return set randomNum
		
	}

	@Override
	public boolean getTrueWithProbability(double p) {
		 return probabilityResult;//will always return same boolean taht is set
	}
	
	public void setRandomNum(int num) {
		this.randomNum= num;
	}

	public void setProbabilityResult(boolean result) {
		this.probabilityResult= result;
	}
}

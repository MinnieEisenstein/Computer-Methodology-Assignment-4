package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BetBalanceTracker_TEST {
@Test
	
	RandomValueGenerator rand1;
	RandomValueGenerator rand2;
	MockRandomValueGenerator mockRand;;

	BetBalanceTracker tracker1;
	BetBalanceTracker tracker2;
	BetBalanceTracker mockTracker;
	
	@BeforeEach //sets up 3 bet balance tracker objects, 1 using a mock random value generator
	void setUp() throws Exception {
		RandomValueGenerator rand1 = new RandomValueGenerator();
		RandomValueGenerator rand2 = new RandomValueGenerator();
		MockRandomValueGenerator mockRand = new MockRandomValueGenerator();
		
		BetBalanceTracker tracker1 = new BetBalanceTracker(0, rand1);
		BetBalanceTracker tracker2 = new BetBalanceTracker(-10, rand2);
		BetBalanceTracker mockTracker = new BetBalanceTracker(-5, mockRand);		
	}
	
	//before each is not doing what it should...

	//for reference: assertEquals(methods.Sum(array), 3);
	@Test
	
	 void getCurrentBalanceReturnsMinBalanceIfNothingIsAdded() {
		
		assertEquals(tracker1.getCurrentBalance(), 0);
		assertEquals(tracker2.getCurrentBalance(), -10);
		assertEquals(mockTracker.getCurrentBalance(), -5);
		
	}
	
	
	
	

}

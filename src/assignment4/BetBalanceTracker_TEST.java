package assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BetBalanceTracker_TEST {

	
	RandomValueGenerator rand1;
	RandomValueGenerator rand2;
	MockRandomValueGenerator mockRand;;

	BetBalanceTracker tracker1;
	BetBalanceTracker tracker2;
	BetBalanceTracker mockTracker;
	
	
	@BeforeEach //sets up 3 bet balance tracker objects, 1 using a mock random value generator
	void setUp()  {
	    rand1 = new RandomValueGenerator();
	    rand2 = new RandomValueGenerator();
	    mockRand = new MockRandomValueGenerator();

	    tracker1 = new BetBalanceTracker(0, rand1);
	    tracker2 = new BetBalanceTracker(-10, rand2);
	    mockTracker = new BetBalanceTracker(-5, mockRand);
	}

	@Test
	//testing the balance methods
	
	 void testGetCurrentBalanceReturnsZeroIfNothingIsAdded() {
		
		assertEquals(tracker1.getCurrentBalance(), 0);
		assertEquals(tracker2.getCurrentBalance(), 0);
		assertEquals(mockTracker.getCurrentBalance(), 0);
		
	}
	
	@Test
	void testConstructorSetsBalanceCorrectly() {
		// Verify the initial balance is set correctly
	    assertEquals(0, tracker1.getCurrentBalance());
	    assertEquals(-10, tracker2.getCurrentBalance());
	    assertEquals(-5, mockTracker.getCurrentBalance());
	}
	
	@Test
	void testGetCurrentBalanceWorksWithValidInput() throws InvalidAmountException {

	    // Add money and verify the balance updates
	    tracker1.addMoney(50);
	    assertEquals(50, tracker1.getCurrentBalance(), "After adding 50, balance  should be 50");
	    tracker1.addMoney(30);
	    assertEquals(80, tracker1.getCurrentBalance(), "After adding 30, balance  should be 80");

	    tracker2.addMoney(20);
	    assertEquals(10, tracker2.getCurrentBalance(), "After adding 20 balance should be 10");
	    tracker2.addMoney(30);
	    assertEquals(40, tracker2.getCurrentBalance(), "After adding 30 balance should be 40");
	    
	    mockTracker.addMoney(15);
	    assertEquals(10, mockTracker.getCurrentBalance(), "After adding 15, balance for mockTracker should be 10");
	}
	
	@Test
	void testGetCurrentBalanceWorksWithInvalidInput() throws InvalidAmountException {
		// try to add a negative amount and make sure exception is thrown
		// For tracker1
	    assertThrows(InvalidAmountException.class, () -> {
	        tracker1.addMoney(-10);  // Try to add a negative amount
	    }, "InvalidAmountException should be thrown for tracker1 when trying to add -10");

	    // For tracker2
	    assertThrows(InvalidAmountException.class, () -> {
	        tracker2.addMoney(-10);  // Try to add a negative amount
	    }, "InvalidAmountException should be thrown for tracker2 when trying to add -10");

	    // For mockTracker
	    assertThrows(InvalidAmountException.class, () -> {
	        mockTracker.addMoney(-10);  // Try to add a negative amount
	    }, "InvalidAmountException should be thrown for mockTracker when trying to add -10");
	}
	
	
	@Test
	void testAddMoneyIncreasesBalanceCorrectly() throws InvalidAmountException {
	    // Test adding a positive amount to tracker1
	    tracker1.addMoney(50);
	    assertEquals(50, tracker1.getCurrentBalance(), "Balance should be 50 after adding 50 to tracker1");
	    tracker1.addMoney(50);
	    assertEquals(100, tracker1.getCurrentBalance(), "Balance should be 100 after adding 50 to tracker1");
	    tracker1.addMoney(50);
	    tracker1.addMoney(50);
	    tracker1.addMoney(50);
	    assertEquals(250, tracker1.getCurrentBalance(), "Balance should be 250 after adding 50 3 times to tracker1");

	    // Test adding a positive amount to tracker2 (with initial negative balance)
	    tracker2.addMoney(20);
	    assertEquals(10, tracker2.getCurrentBalance(), "Balance should be 10 after adding 20 to tracker2");

	    // Test adding a positive amount to mockTracker
	    mockTracker.addMoney(15);
	    assertEquals(10, mockTracker.getCurrentBalance(), "Balance should be 10 after adding 15 to mockTracker");
	}

	@Test
	void testAddMoneyRejectsNegativeValues() throws InvalidAmountException {
	    // Try to add a negative amount to tracker1
	    assertThrows(InvalidAmountException.class, () -> {
	        tracker1.addMoney(-10);  // Try to add a negative amount
	    }, "InvalidAmountException should be thrown for tracker1 when attempting to add -10");

	    // Try to add a negative amount to tracker2
	    assertThrows(InvalidAmountException.class, () -> {
	        tracker2.addMoney(-30);  // Try to add a negative amount
	    }, "InvalidAmountException should be thrown for tracker2 when attempting to add -30");

	    // Try to add a negative amount to mockTracker
	    assertThrows(InvalidAmountException.class, () -> {
	        mockTracker.addMoney(-5);  // Try to add a negative amount
	    }, "InvalidAmountException should be thrown for mockTracker when attempting to add -5");
	}

	@Test
	void testAddMoneyWithZeroInput() throws InvalidAmountException {
	    // Test adding 0 to tracker1
	    tracker1.addMoney(0);
	    assertEquals(0, tracker1.getCurrentBalance(), "Balance should stay 0 after adding 0 to tracker1");

	    // Test adding 0 to tracker2
	    tracker2.addMoney(0);
	    assertEquals(-10, tracker2.getCurrentBalance(), "Balance should stay -10 after adding 0 to tracker2");

	    // Test adding 0 to mockTracker
	    mockTracker.addMoney(0);
	    assertEquals(-5, mockTracker.getCurrentBalance(), "Balance should stay -5 after adding 0 to mockTracker");
	}

	
	//for reference public boolean canBet(double amnt) 
	
	@Test
	void testCanBetWhenBalanceIsEnough() throws InvalidAmountException {
	    tracker1.addMoney(50); // Balance is 50
	    assertTrue(tracker1.canBet(25), "Tracker1 should allow a bet of 25, when balance is 50 and minBalance is 0");
	    tracker2.addMoney(10);// Balance is 10
	    assertTrue(tracker2.canBet(9));
	    mockTracker.addMoney(100);
	    assertTrue(mockTracker.canBet(55));
	}

	@Test
	void testCanBetWhenAmountEqualsBalance() throws InvalidAmountException {
		tracker1.addMoney(50); // Balance is 50
	    assertTrue(tracker1.canBet(50), "Tracker1 should allow a bet of 50, when balance is 50 and minBalance is 0");
	    tracker2.addMoney(10);// Balance is 10
	    assertTrue(tracker2.canBet(10));
	    mockTracker.addMoney(100);
	    assertTrue(mockTracker.canBet(100));
	}
	@Test
	void testCanBetWhenAmountWontExceedMinBalance() throws InvalidAmountException {
		tracker1.addMoney(50); // Balance is 50, min balance is 0
	    assertTrue(tracker1.canBet(50), "Tracker1 should allow a bet of 50, when balance is 50 and minBalance is 0");
	    tracker2.addMoney(10);// // Balance is 10, min balance is -10
	    assertTrue(tracker2.canBet(15));
	    mockTracker.addMoney(100);// Balance is 100, min balance is -5
	    assertTrue(mockTracker.canBet(103));
	}
	@Test
	void testCantBetWhenAmountExceedsMinBalance() throws InvalidAmountException {
		tracker1.addMoney(50); // Balance is 50, min balance is 0
	    assertFalse(tracker1.canBet(51), "Tracker1 wont allow a bet of 51, when balance is 50 and minBalance is 0");
	    tracker2.addMoney(10);// // Balance is 10, min balance is -10
	    assertFalse(tracker2.canBet(30),"Tracker2 wont allow a bet of 30, when balance is 10 and minBalance is -10");
	    mockTracker.addMoney(100);// Balance is 100, min balance is -5
	    assertFalse(mockTracker.canBet(108),"mocktracker wont allow a bet of 108, when balance is 100 and minBalance is -5");
	}
	
	
	
	//tests for bet on a number
	
	@Test
	void testBetOnANumberReturnsCorrectBalanceWhenWon() throws InvalidBetException, InvalidBetAmountException, InvalidAmountException {
	    // Simulate a win with mockTracker using mockRand
	    mockRand.setRandomNum(5); // Set the random number generator to 5
	    mockTracker.addMoney(50); // add money to balance
	    double winAmountMock = mockTracker.betOnANumber(50, 1, 10, 5); // Bet on 5

	    // how much money is added if it's a win = (range - 1) * betAmount
	    // Initial balance for mockTracker is 0, so after the win, the balance should be: ((10-1+1)-1) *50 = 450
	    assertEquals(450, winAmountMock, "The amount won should be (range - 1) * betAmount");
	    assertEquals(450, mockTracker.getCurrentBalance(), "Balance for mockTracker should be updated to 450 after winning bet on 5 between 1 and 10, 50 $");

	    //using real random number generators
	    tracker1.addMoney(50);//balance is 50 now    
	    double winAmountTracker1 = tracker1.betOnANumber(50, 1, 10, 5); // Bet on number 5
	    
	    // Check if the balance changes after the bet
	    assertNotEquals(50, tracker1.getCurrentBalance(), "Balance for tracker1 did not remain the same after placing a bet");
	    
	    if(rand1.getRandomNum(1, 10) == 5) {
	        // Simulate a win with a matching number
	        assertEquals(450, winAmountTracker1, "The amount won should be (range - 1) * betAmount for tracker1");
	        assertEquals(500, tracker1.getCurrentBalance(), "Balance for tracker1 should be updated to 500 after winning bet on 5");
	    }
	    
	}
	
	@Test
	void testBetOnANumberReturnsCorrectBalanceWhenLost() throws InvalidBetException, InvalidBetAmountException, InvalidAmountException {
	    // Simulate a loss with mockTracker using mockRand
	    mockRand.setRandomNum(5); // Set the random number generator to 5
	    mockTracker.addMoney(50); // add money to balance
	    mockTracker.betOnANumber(50, 1, 10, 3); // Bet on 3 (incorrect, should lose)

	    // how much money is lost if it's a loss = -betAmount
	    // Initial balance for mockTracker is 0, so after the loss, the balance should be: 0 - 50 = -50
	    assertEquals(-50, mockTracker.getCurrentBalance(), "Balance for mockTracker should be updated to -50 after losing bet on 3 between 1 and 10, 50 $");

	    //using real random number generators
	    tracker1.addMoney(50);//balance is 50 now    
	    double winAmountTracker1 = tracker1.betOnANumber(50, 1, 10, 3); // Bet on number 3 (incorrect, should lose)
	    
	    // Simulate a loss with a non-matching number
	    assertEquals(-50, winAmountTracker1, "The amount lost should be the negative bet amount for tracker1");
	    assertEquals(0, tracker1.getCurrentBalance(), "Balance for tracker1 should be updated to 0 after losing bet on 3");
	}
	
	@Test
	void testBetOnANumberCalculatesWinAmountCorrectly() throws InvalidBetException, InvalidBetAmountException, InvalidAmountException {
	    // Simulate a win with mockTracker using mockRand
	    mockRand.setRandomNum(5); // Set the random number generator to 5
	    mockTracker.addMoney(50); // add money to balance
	    double winAmountMock = mockTracker.betOnANumber(50, 1, 10, 5); // Bet on 5

	    // how much money is added if it's a win = (range - 1) * betAmount
	    // Initial balance for mockTracker is 0, so after the win, the balance should be: ((10-1+1)-1) *50 = 450
	    assertEquals(450, winAmountMock, "The amount won should be (range - 1) * betAmount");
	    assertEquals(450, mockTracker.getCurrentBalance(), "Balance for mockTracker should be updated to 450 after winning bet on 5 between 1 and 10, 50 $");

	    // using real random number generators for tracker1
	    tracker1.addMoney(50);//balance is 50 now    
	    double winAmountTracker1 = tracker1.betOnANumber(50, 1, 10, 5); // Bet on number 5
	    
	    // Assert the correct calculation of the winning amount
	    assertEquals(450, winAmountTracker1, "The amount won should be (range - 1) * betAmount for tracker1");
	}
	
	@Test
	void testBetOnANumberCalculatesLossAmountCorrectly() throws InvalidBetException, InvalidBetAmountException, InvalidAmountException {
	    // Simulate a loss with mockTracker using mockRand
	    mockRand.setRandomNum(5); // Set the random number generator to 5
	    mockTracker.addMoney(50); // add money to balance
	    double winAmountMock = mockTracker.betOnANumber(50, 1, 10, 3); // Bet on 3 (incorrect, should lose)

	    // how much money is lost if it's a loss = -betAmount
	    // Initial balance for mockTracker is 0, so after the loss, the balance should be: 0 - 50 = -50
	    assertEquals(-50, winAmountMock, "The amount lost should be the negative bet amount");
	    assertEquals(-50, mockTracker.getCurrentBalance(), "Balance for mockTracker should be updated to -50 after losing bet on 3 between 1 and 10, 50 $");

	    // using real random number generators for tracker1
	    tracker1.addMoney(50);//balance is 50 now    
	    double winAmountTracker1 = tracker1.betOnANumber(50, 1, 10, 3); // Bet on number 3 (incorrect, should lose)
	    
	    // Assert the correct calculation of the losing amount
	    assertEquals(-50, winAmountTracker1, "The amount lost should be the negative bet amount for tracker1");
	    assertEquals(0, tracker1.getCurrentBalance(), "Balance for tracker1 should be updated to 0 after losing bet on 3");
	}
	
	@Test
	void testRejectInvalidBetOutOfRange() throws InvalidAmountException {
	    // Test for mockTracker
	    mockRand.setRandomNum(5); // Set the mock random number to 5
	    mockTracker.addMoney(50); // Add money to balance
	    assertThrows(InvalidBetException.class, () -> {
	        mockTracker.betOnANumber(50, 1, 10, 15); // Invalid bet on number 15 (out of range)
	    }, "InvalidBetException should be thrown for mockTracker when the bet number is out of range");

	    // Test for tracker1 
	    tracker1.addMoney(50); // Add money to balance
	    assertThrows(InvalidBetException.class, () -> {
	        tracker1.betOnANumber(50, 1, 10, 15); // Invalid bet on number 15 
	    }, "InvalidBetException should be thrown for tracker1 when the bet number is out of range");

	    // Test for tracker2 
	    tracker2.addMoney(50); // Add money to balance
	    assertThrows(InvalidBetException.class, () -> {
	        tracker2.betOnANumber(50, 1, 10, 15); // Invalid bet on number 15 )
	    }, "InvalidBetException should be thrown for tracker2 when the bet number is out of range");
	}
	
	
	
	@Test
	void testRejectBetIfCanBetReturnsFalseForAllTrackers() throws InvalidAmountException {
	    // Add money to all three trackers to set up the test 

	    // Adding 100 to tracker1, tracker2, and mockTracker
	    tracker1.addMoney(100); 
	    tracker2.addMoney(100);
	    mockTracker.addMoney(100); 

	    // Test: Try placing a bet of 200, which exceeds the balance and should trigger the exception

	    // tracker1 - Invalid bet on number 5 (should be rejected)
	    assertThrows(InvalidBetAmountException.class, () -> {
	        tracker1.betOnANumber(200, 1, 10, 5);
	    }, "InvalidBetAmountException should be thrown for tracker1 when the bet exceeds the balance and would cause the balance to be below minBalance");

	    // mockTracker - Invalid bet on number 5 (should be rejected)
	    assertThrows(InvalidBetAmountException.class, () -> {
	        mockTracker.betOnANumber(200, 1, 10, 5);
	    }, "InvalidBetAmountException should be thrown for mockTracker when the bet exceeds the balance and would cause the balance to be below minBalance");

	    // tracker2 - Invalid bet on number 5 (should be rejected)
	    assertThrows(InvalidBetAmountException.class, () -> {
	        tracker2.betOnANumber(200, 1, 10, 5);
	    }, "InvalidBetAmountException should be thrown for tracker2 when the bet exceeds the balance and would cause the balance to be below minBalance");

	    // Verify the balance remains unchanged as the bet was rejected for all trackers
	    assertEquals(100, tracker1.getCurrentBalance(), "Balance for tracker1 should stay the same after rejecting bet");
	    assertEquals(100, mockTracker.getCurrentBalance(), "Balance for mockTracker should stay the same after rejecting bet");
	    assertEquals(100, tracker2.getCurrentBalance(), "Balance for tracker2 should stay the same after rejecting bet");
	}
	
	//testing the betOnProbability method
	
	@Test
	void testBetOnProbabilityWithZeroProbability() throws InvalidProbabilityException, InvalidBetAmountException, InvalidAmountException {
	    // tracker1: Add balance and bet
	    tracker1.addMoney(100);  // balance is 100
	    mockRand.setProbabilityResult(false);  //p = 0.0, it will always be a loss
	    double result1 = tracker1.betOnProbability(50, 0.0);  // betting 50, p = 0.0
	    assertEquals(-50, result1, "Tracker1: Should always lose with p = 0.0");
	    assertEquals(50, tracker1.getCurrentBalance(), "Tracker1: Balance should decrease by 50 after a loss");

	    // tracker2
	    tracker2.addMoney(100);  // balance is 100
	    mockRand.setProbabilityResult(false);  // p = 0.0, it will always be a loss
	    double result2 = tracker2.betOnProbability(50, 0.0);  // betting 50, p = 0.0
	    assertEquals(-50, result2, "Tracker2: Should always lose with p = 0.0");
	    assertEquals(50, tracker2.getCurrentBalance(), "Tracker2: Balance should decrease by 50 after a loss");

	    // mockTracker
	    mockTracker.addMoney(100);  // balance is 100
	    mockRand.setProbabilityResult(false);  // p = 0.0, it will always be a loss
	    double resultMock = mockTracker.betOnProbability(50, 0.0);  // betting 50, p = 0.0
	    assertEquals(-50, resultMock, "MockTracker: Should always lose with p = 0.0");
	    assertEquals(50, mockTracker.getCurrentBalance(), "MockTracker: Balance should decrease by 50 after a loss");
	}
	
	//for reference: (p^-1 -1) * amount
	@Test
	void testBetOnProbabilityWith100PercentProbability() throws InvalidProbabilityException, InvalidBetAmountException, InvalidAmountException {
		//will always keep balance the same because ((1/1)-1) * amount will always be 0
	    // tracker1: Add balance and bet
	    tracker1.addMoney(100);  // balance is 100
	    double result1 = tracker1.betOnProbability(50, 1.0);  // betting 50, p = 1.0 
	    assertEquals(0, result1, "Tracker1: Winnings should be 0 with p = 1.0");
	    assertEquals(100, tracker1.getCurrentBalance(), "Tracker1: Balance should not change after a win with 100% probability");

	    // tracker2: Add balance and bet
	    tracker2.addMoney(100);  // balance is 100
	    double result2 = tracker2.betOnProbability(50, 1.0);  // betting 50, p = 1.0
	    assertEquals(0, result2, "Tracker2: Winnings should be 0 with p = 1.0");
	    assertEquals(100, tracker2.getCurrentBalance(), "Tracker2: Balance should not change after a win with 100% probability");

	    // mockTracker: Add balance and bet
	    mockTracker.addMoney(100);  // balance is 100
	    double resultMock = mockTracker.betOnProbability(50, 1.0);  // betting 50, p = 1.0
	    assertEquals(0, resultMock, "MockTracker: Winnings should be 0 with p = 1.0");
	    assertEquals(100, mockTracker.getCurrentBalance(), "MockTracker: Balance should not change after a win with 100% probability");
	}
	
	@Test
	void testBetOnProbabilityWithMidpointProbability() throws InvalidProbabilityException, InvalidBetAmountException, InvalidAmountException {
	    //tracker 1
	    tracker1.addMoney(100);  // balance is 100
	    boolean result1 = rand1.getTrueWithProbability(0.3);  // p = 0.3
	    
	    double resultTracker1 = tracker1.betOnProbability(50, 0.3);  // Bet on 30% probability, betting 50 $

	    
	    if (result1) {//returns true
	        // (1 / 0.3) * 50 = 166.67
	        assertEquals(166.67, resultTracker1, "Tracker1:166.67 should be added to balance after a win on betting 50 $ for .3 probability");
	        assertEquals(266.67, tracker1.getCurrentBalance(), "Tracker1: Balance should increase to 266.67 after a win");
	    } else {
	        // Loss equals the bet amount: -50
	        assertEquals(-50, resultTracker1, "Tracker1: Loss should equal the bet amount when the probability fails");
	        assertEquals(50, tracker1.getCurrentBalance(), "Tracker1: Balance should decrease by 50 after a loss");
	    }

	   //tracker 2
	    tracker2.addMoney(100);  //balance is 100
	    boolean result2 = rand2.getTrueWithProbability(0.3);  // Simulate random outcome with p = 0.3
	    
	    double resultTracker2 = tracker2.betOnProbability(50, 0.3);  // Bet on 30% probability

	    
	    if (result2) {//returns true
	        // (1 / 0.3) * 50 = 166.67
	        assertEquals(166.67, resultTracker2, "Tracker2: 166.67 should be added to balance after a win on betting 50 $ for .3 probability");
	        assertEquals(266.67, tracker2.getCurrentBalance(), "Tracker2: Balance should increase to 266.67 after a win");
	    } else {
	        // Loss equals the bet amount: -50
	        assertEquals(-50, resultTracker2, "Tracker2: Loss should equal the bet amount when the probability fails");
	        assertEquals(50, tracker2.getCurrentBalance(), "Tracker2: Balance should decrease by 50 after a loss");
	    }
	}
	
	@Test
	void testMockTrackerBetOnProbability() throws InvalidProbabilityException, InvalidBetAmountException, InvalidAmountException {
	    // using the mockrandom probility generator to simulate a win
	    mockRand.setProbabilityResult(true);  
	    mockTracker.addMoney(100);  // balance is 100
	    double winAmountMock = mockTracker.betOnProbability(50, 0.3);  // Bet on 30% probability

	    //(1 / 0.3) * 50  = 166.67
	    assertEquals(166.67, winAmountMock, "MockTracker: 166.67 should be added to balance after a win on betting 50 $ for .3 probability)");
	    assertEquals(266.67, mockTracker.getCurrentBalance(), "MockTracker: Balance should increase to 266.67 after a win");

	    //using the mockrandom probility generator to simulate a loss
	    mockRand.setProbabilityResult(false);  
	    double lossAmountMock = mockTracker.betOnProbability(50, 0.3);  // Bet on 30% probability

	    // Loss equals the bet amount: but negative
	    assertEquals(-50, lossAmountMock, "MockTracker: Loss should equal the bet amount when the probability fails");
	    assertEquals(216.67, mockTracker.getCurrentBalance(), "MockTracker: Balance should decrease by 50 after a loss");
	}
	
	@Test
	void testBetOnProbabilityThrowsExceptionForInvalidProbabilityBelowZero() {
	    assertThrows(InvalidProbabilityException.class, () -> {
	        tracker1.betOnProbability(50, -0.1);  // Invalid probability (less than 0)
	    }, "InvalidBetException should be thrown for negative probability");
	}
	@Test
	void testBetOnProbabilityThrowsExceptionForInvalidProbabilityAboveOne() {
	    assertThrows(InvalidBetException.class, () -> {
	        tracker1.betOnProbability(50, 1.1);  // Invalid probability 
	    }, "InvalidBetException should be thrown for probability greater than 1");
	}
	
	@Test
	void testBetOnProbabilityRejectedIfBalanceWouldGoBelowMinBalance() throws InvalidBetException, InvalidBetAmountException, InvalidProbabilityException, InvalidAmountException {
	    tracker2.addMoney(20);  // balance is 20

	    // Try betting 50, which would make it go under minBalance if lost
	    assertThrows(InvalidBetAmountException.class, () -> {
	        tracker2.betOnProbability(50, 0.3);  //bet 50
	    }, "InvalidBetAmountException should be thrown when the bet would cause the balance to go below minBalance");

	    // Check that the balance did not change (should stay 20)
	    assertEquals(20, tracker2.getCurrentBalance(), "The balance should not change after rejecting the bet");
	}
}



package candyCrisis;

/* Class: SymmetryMatch.java
 * Programmer: Jennifer Sunahara
 * Date: 10/3/2018
 * Description: A helper class for the heuristicManager class. SymmetryMatch has two static methods 
 * getSymmetryMatch1(String board) and getSymmetryMatch2(String board) which both return a heuristic
 * integer value of a given board state (given to the functions as a string).
 * BoardStates.
 * 
 */

public class SymmetryMatch {
	
	// Static function for the heuristicManager class, used to get an integer value of the heuristic of a given 
	// boardState (string start), in string format. From max heuristic of 5, reduces the heuristic value by 1 
	// for each matching top-bottom row pairs. 
	// Returns an integer
	public static int getSymmetryMatch1(String board)
	{
		int value = 5;//max value is complete mismatch
		
		for(int i=0; i<5; i++)
		{
			if(board.charAt(i)==board.charAt(i+10)) {value--;} //for each match decrement
		}
		
		return value;	
	}

	// Static function for the heuristicManager class, used to get an integer value of the heuristic of a given 
	// boardState (string start), in string format. From max heuristic of 5, reduce the heuristic value by 1 
	// for each matching top-bottom row pairs, following a specific order (left to center, then right to center)
	// Returns an integer
	public static int getSymmetryMatch2(String board)
	{
		int value = 5;//max value is complete mismatch
		
		//starting from the left, stopping at the center
		for(int i=0; i<3; i++)
		{
			if(board.charAt(i)==board.charAt(i+10)) {value--;} //for each match decrement
		}
		//starting from the right, stopping at the center
		for(int i=4; i>1; i--)
		{
			if(board.charAt(i)==board.charAt(i+10)) {value--;} //for each match decrement
		}
		
		return value;		
	}
	
	//This main is here to demonstrate how to use the functions getSymmetryMatch1 and getSymmetryMatch2
	/*
	public static void main( String[] args ) 
	{
		String test_0= "1234512345abcde";//0 symmmetric match
		String test_1= "12345123451bcde";//1 symmmetric match
		String test_2= "123451234512cde";//2 symmmetric match
		String test_3= "1234512345123de";//3 symmmetric match
		String test_4= "12345123451234e";//4 symmmetric match
		String test_5= "123451234512345";//5 symmmetric match
		
		System.out.println("SymmetryMatch1 of test_0: " + getSymmetryMatch1(test_0));
		System.out.println("SymmetryMatch1 of test_1: " + getSymmetryMatch1(test_1));
		System.out.println("SymmetryMatch1 of test_2: " + getSymmetryMatch1(test_2));
		System.out.println("SymmetryMatch1 of test_3: " + getSymmetryMatch1(test_3));
		System.out.println("SymmetryMatch1 of test_4: " + getSymmetryMatch1(test_4));
		System.out.println("SymmetryMatch1 of test_5: " + getSymmetryMatch1(test_5));
		System.out.println();
		System.out.println("SymmetryMatch2 of test_0: " + getSymmetryMatch2(test_0));
		System.out.println("SymmetryMatch2 of test_1: " + getSymmetryMatch2(test_1));
		System.out.println("SymmetryMatch2 of test_2: " + getSymmetryMatch2(test_2));
		System.out.println("SymmetryMatch2 of test_3: " + getSymmetryMatch2(test_3));
		System.out.println("SymmetryMatch2 of test_4: " + getSymmetryMatch2(test_4));
		System.out.println("SymmetryMatch2 of test_5: " + getSymmetryMatch2(test_5) + " (normal for it to be negative, though this case shouldn't happen.)" );
	}*/
}

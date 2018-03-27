/* Class: heuristic.java
 * Programmer: Eric Davies
 * Date: 15/3/2018
 * Description: A static class that contains the evaluateHepuristic function and all its helper functions.
 */
package candyCrisis;

public class heuristicManager {

	/* Function: evaluateHeuristics
	 * Paramaters:
	 * @String node: the board state to evaluate
	 * Returns: An integer which is the heurisitic evaluation
	 * Description: A function that evaluates the heuristic cost of a current state and returns if it is a good state to be in. Uses
	 * 	other function to assess the cost and is just a holder for easy interfacing with the code.
	 * 
	 */
	public static int evaluateHeuristic(String node)
	{
		int h = 3*(SymmetryMatch.getSymmetryMatch1(node)) + 2*(SymmetryMatch.getSymmetryMatch2(node));
	    return h;
	}
}

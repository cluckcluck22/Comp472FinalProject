/* Class: heuristic.java
 * Programmer: Eric Davies
 * Date: 15/3/2018
 * Description: A static class that contains the evaluateHepuristic function and all its helper functions.
 */
package candyCrisis;

public class heuristicManager {

	public static int evaluateHeuristic(String node)
	{
		int h = (SymmetryMatch.getSymmetryMatch1(node)) + 2*(SymmetryMatch.getSymmetryMatch2(node));
	    return h;
	}
}

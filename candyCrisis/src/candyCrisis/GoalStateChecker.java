/* Class: GoalStateChecker.java
 * Date: 26/3/2018
 * Description: A class containing the static methods to check if the manual and automatic solvers of the puzzle are in a goal state.
 * 
 */
package candyCrisis;

public class GoalStateChecker {

	/* Function isGoalState()
	 * Return: true or false depending on if the game state is in a goal state
	 * Description: A funcion that checks if the games top and bottom row are matching, the only goal requirement for the game.
	 * 
	 */
	public static boolean isGoalState() {
		int numCol = BoardStateHandler.GAMESTATE[0].length;
		int lastRowIndex = BoardStateHandler.GAMESTATE.length - 1;
		
		for(int col=0; col<numCol; col++) {
			if(BoardStateHandler.GAMESTATE[0][col]!=BoardStateHandler.GAMESTATE[lastRowIndex][col])
				return false;
		}
		
		return true;
	}
	
	/* Function: isGoalStateAi
	 * Return: true or false depending on if the game state is in a goal state
	 * Description: A function that checks if the games top and bottom row are matching, the only goal requirement for the game.
	 * 
	 */
	public static boolean isGoalStateAi(String state) {
		
		for(int i=0; i<5; i++){
			if(state.charAt(i)!=state.charAt(i+10))
				return false;
		}
		
		return true;
	
	}
	
}

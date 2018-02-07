package candyCrisis;

public class GoalStateChecker {

	public static boolean isGoalState(char[][] state) {
		int numCol = state[0].length;
		int lastRowIndex = state.length - 1;
		
		for(int col=0; col<numCol; col++) {
			if(state[0][col]!=state[lastRowIndex][col])
				return false;
		}
		
		return true;
	}
	
}

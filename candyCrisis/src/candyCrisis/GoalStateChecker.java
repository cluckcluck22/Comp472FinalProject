package candyCrisis;

public class GoalStateChecker {

	public static boolean isGoalState() {
		int numCol = BoardStateHandler.GAMESTATE[0].length;
		int lastRowIndex = BoardStateHandler.GAMESTATE.length - 1;
		
		for(int col=0; col<numCol; col++) {
			if(BoardStateHandler.GAMESTATE[0][col]!=BoardStateHandler.GAMESTATE[lastRowIndex][col])
				return false;
		}
		
		return true;
	}
	
	public static boolean isGoalStateAi(String state) {
		
		for(int i=0; i<5; i++){
			if(state.charAt(i)!=state.charAt(i+10))
				return false;
		}
		
		return true;
	
	}
	
}

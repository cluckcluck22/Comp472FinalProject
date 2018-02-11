package candyCrisis;

public class LegalActionChecker {

	//returns true or false based on action of empty space
	//action is 'U', 'D', 'L', or 'R'
	public static boolean isLegalAction(char action) {
		
		char[][] currentState = BoardStateHandler.GAMESTATE;
		
		int emptySpaceRow=0, emptySpaceCol = 0;
		
		//find location of empty space
		int numRows = currentState.length;
		int numCol = currentState[0].length;
		for(int i=0; i<numRows;i++) {
			for(int j=0; j<numCol; j++) {
				if(currentState[i][j]=='e') {
					emptySpaceRow = i;
					emptySpaceCol = j;
					break;
				}
			}
		}
		
		
		//if on first row
		if(emptySpaceRow==0 && action=='U') return false;
		//if on last row
		if(emptySpaceRow==currentState.length - 1 && action=='D') return false;
		//if on first column
		if(emptySpaceCol==0 && action=='L') return false;
		//if on last column
		if(emptySpaceCol==currentState[0].length - 1 && action=='R') return false;		
		
		
		return true;
	}
	
}

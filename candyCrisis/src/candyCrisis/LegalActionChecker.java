package candyCrisis;

public class LegalActionChecker {

	//returns true or false based on action of empty space
	//action is 'U', 'D', 'L', or 'R'
	public static boolean isLegalAction(char action) {
		
		char[][] currentState = BoardStateHandler.GAMESTATE;
		
		int emptySpaceRow=0, emptySpaceCol = 0;
		
		//find location of empty space
		int numRows = 3;
		int numCol = 5;
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
		if(emptySpaceRow==3 - 1 && action=='D') return false;
		//if on first column
		if(emptySpaceCol==0 && action=='L') return false;
		//if on last column
		if(emptySpaceCol==5 - 1 && action=='R') return false;		
		
		
		return true;
	}
	
}

package candyCrisis;

public class LegalActionChecker {

	//returns true or false based on action of empty space
	public static boolean isLegalAction(char[][] gameState, char action) {
		
		int emptySpaceRow=0, emptySpaceCol = 0;
		
		//find location of empty space
		int numRows = gameState.length;
		int numCol = gameState[0].length;
		for(int i=0; i<numRows;i++) {
			for(int j=0; j<numCol; j++) {
				if(gameState[i][j]=='e') {
					emptySpaceRow = i;
					emptySpaceCol = j;
					break;
				}
			}
		}
		
		
		//if on first row
		if(emptySpaceRow==0 && action=='U') return false;
		//if on last row
		if(emptySpaceRow==gameState.length - 1 && action=='D') return false;
		//if on first column
		if(emptySpaceCol==0 && action=='L') return false;
		//if on last column
		if(emptySpaceCol==gameState[0].length - 1 && action=='R') return false;		
		
		
		return true;
	}
	
}

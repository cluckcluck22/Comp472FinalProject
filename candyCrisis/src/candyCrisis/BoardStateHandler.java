package candyCrisis;
/* Class: BoardStateHandler.java
 * Programmer: Jennifer Sunahara
 * Date: 8/2/2018
 * Description: A class that holds information about the boardstate, as well as the methods to modify it. 
 * keeps the game history, number of moves done so far, and time. After every move, the boardStateHandler
 * will make a call to the GoalStateChecker to check if the board is in a goal state.
 * 
 * TODO: Test cases
 */

import candyCrisis.FileHandler.FileHandler;

import java.util.ArrayList;

public class BoardStateHandler {

	public static char[][] GAMESTATE;
	private FileHandler filer;
	
	private int empty;
	private int numRow = 3;//hard-coded, because it will always be this size
	private int numCol = 5;//hard-coded, because it will always be this size
	private String pathHistory;
	private long totalTime;
	private long startTime;
	private int numMoves;
	
	public BoardStateHandler() 
	{
		GAMESTATE = new char[numRow][numCol];	
		empty = 0;//default empty location is 0, which is equivalent to A
		numMoves = 0;
		pathHistory = "";
		totalTime=0;
		filer = new FileHandler();
	}
	
	public boolean hasNextBoard()
	{
		return filer.hasNextBoard();
	}
	
	//Method to setup the initial GAMESTATE, using a call to the FileHandler. This also sets int empty to the correct location.
	public void begin()
	{		
		GAMESTATE = filer.getNextBoard();// .getStartBoard();
		boolean foundEmpty=false;
		empty = 0;
		pathHistory = "";
		numMoves=0;
		
		for(int row=0; row<numRow; row++) 
		{
			for(int col=0; col<numCol; col++) 
			{
				if(GAMESTATE[row][col]=='e') //so long as we have not found empty, increment empty
				{
					foundEmpty=true;
				}
				else if(foundEmpty==false){empty++;}
			}
		}
		startTime= System.nanoTime(); // System.currentTimeMillis() is less accurate but faster
	}
	
	//Method to modify the GAMESTATE by moving the empty cell to the left. No checking for validity of the move.
	public void moveLeft() 
	{
		int	row = empty/numCol;
		int col = empty%numCol;
		
		//swap 'e' with the char to the left of it
		char temp = GAMESTATE[row][col-1];
		GAMESTATE[row][col]=temp;
		GAMESTATE[row][col-1] = 'e';
		empty--; //update empty 
		pathHistory= pathHistory.concat(String.valueOf((char)(empty+65))); //update pathHistory based on move made
		numMoves++;
		
	}
	
	//Method to modify the GAMESTATE by moving the empty cell to the right. No checking for validity of the move.
	public void moveRight() 
	{
		int	row = empty/numCol;
		int col = empty%numCol;
		
		//swap 'e' with the char to the right of it
		char temp = GAMESTATE[row][col+1];
		GAMESTATE[row][col]=temp;
		GAMESTATE[row][col+1] = 'e';
		empty++; //update empty 
		pathHistory= pathHistory.concat(String.valueOf((char)(empty+65))); //update pathHistory based on move made
		numMoves++;
	}
	
	//Method to modify the GAMESTATE by moving the empty cell up. No checking for validity of the move.
	public void moveUp() 
	{
		int	row = empty/numCol;
		int col = empty%numCol;
		
		//swap 'e' with the char above it
		char temp = GAMESTATE[row-1][col];
		GAMESTATE[row][col]=temp;
		GAMESTATE[row-1][col] = 'e';
		empty = empty-numCol; //update empty 
		pathHistory= pathHistory.concat(String.valueOf((char)(empty+65))); //update pathHistory based on move made
		numMoves++;
	}
	
	//Method to modify the GAMESTATE by moving the empty cell down. No checking for validity of the move.
	public void moveDown() 
	{
		int	row = empty/numCol;
		int col = empty%numCol;
		
		//swap 'e' with the char below it
		char temp = GAMESTATE[row+1][col];
		GAMESTATE[row][col]=temp;
		GAMESTATE[row+1][col] = 'e';
		empty = empty+numCol; //update empty 
		pathHistory= pathHistory.concat(String.valueOf((char)(empty+65))); //update pathHistory based on move made
		numMoves++;
	}
		
	//Method to return a String of GAMESTATE, likely used by ManualInput handler to eventually print to screen.
	//Input: None
	//Output: String of the currentBoardState
	public String currentBoardToString() 
	{
		String currentBoard = "";
		for(int row=0; row<numRow; row++) 
		{
	        for(int col=0; col<numCol; col++) 
	        {
	        	currentBoard.concat(String.valueOf(GAMESTATE[row][col]));
	        }
	        System.out.println();
		}
		return currentBoard;
	}
	
	//OPTIONAL Method to print a String of GAMESTATE, possibly used by ManualInput handler to eventually print to screen.
	public void printCurrentBoard() 
	{
		for(int row=0; row<numRow; row++) 
		{
	        for(int col=0; col<numCol; col++) 
	        {
	        	System.out.print(GAMESTATE[row][col] + " ");
	        }
	        System.out.println();
		}	
			
	}
		
	//Method to check if GAMESTATE is in a goal state, using a call to GoalStateChecker. If so, the function will
	//create an object of type Result, based on the path history and total time, to be saved to file by the FileHandler
	public void checkGoalState()
	{
		if(GoalStateChecker.isGoalState()) 
		{
			long endTime = System.nanoTime();
			
			totalTime = endTime-startTime;
			
			Result r = new Result (pathHistory, totalTime,"",0);
			
			filer.saveBoardResult(r);
			//FileHandler.saveBoardResult(r); // NOTE: I think we are only passing a single result at the end.
		}
	}
}

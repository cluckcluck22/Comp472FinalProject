package candyCrisis.FileHandler;

import java.util.ArrayList;

public class FileHandler
{
	private static final int MAX_ROW = 3;
	private static final int MAX_COLUMN = 15;
	static char[][] results = new char[MAX_ROW][MAX_COLUMN];
	
	
	//Static method for BoardStateHandler() to get starting board from a txt file.
	//Input: None
	//Output: 2D array of character. 
	public static char[][] getStartBoard()
	{	
		//Generate dummy 2D array for testing.
		results = getDummyArray();
		
		return results;	
	}
	
	//Static method for BoardStateHandler() to store the moves of a finished board.
	//Input: Arraylist moveHistory, Arraylist totalTime
	//Output: A text file that will store the array of moves, total completed time
	public static void saveBoardResult(ArrayList<String> moveHistory, ArrayList<Integer> totalTime)
	{
		int boardCount = 0;
		String boardName = "Board" + boardCount;
		boardCount++;
		
		System.out.println("Board history saved to " + boardName + ".txt");
	}
	
	//Method for testing purpose.
	private static char[][] getDummyArray()
	{
		//Dummy response (for testing)
		String[] inputList = {"rebwrbbbrrrbrbw", "brbwwrrbbbbrerr", "rrbbrwbrrrwbebb"};
		
		for (int i = 0; i < MAX_ROW; i++)
		{
			for (int j = 0; j < MAX_COLUMN; j++)
			{
				results[i][j] = inputList[i].charAt(j);
				
				System.out.print(results[i][j]);
			}
			
			System.out.println("");
		}
			
		return results;	
	}

	public static void main(String[] args)
	{
		ArrayList<String> moveHistory = new ArrayList<String>();
		ArrayList<Integer> totalTime = new ArrayList<Integer>();
		
		saveBoardResult(moveHistory, totalTime);
		
		getStartBoard();
	}

}

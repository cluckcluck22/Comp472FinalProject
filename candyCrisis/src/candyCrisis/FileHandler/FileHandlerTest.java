package candyCrisis.FileHandler;

import java.util.ArrayList;
import java.util.List;


/*
 * Note: Test 3 - Test getting new boards - NOT WORKING!!
 * Problem: boardsList in FileHandler is only 
 */
public class FileHandlerTest
{
	public static void main(String[] args)
	{
		//1. Test saving board history
		/*ArrayList<Result> boardHistoryAL = new ArrayList<Result>();
		boardHistoryAL.add(new Result("GHMNOJ", 6));
		boardHistoryAL.add(new Result("NIDEJONIHCBG", 28));
		boardHistoryAL.add(new Result("HGBAFGLMNOJ", 8));
		
		Result boardHistory = new Result("GHMNOJ", 6);*/
	
		//saveBoardResultAL(boardHistoryAL);
		//saveBoardResult(boardHistory);
		
		//2. Test finding empty tile position
		/*int[] emptyTileIndexTest = new int[3];
		emptyTileIndexTest = getEmptyTileIndex();
		
		for(int i = 0; i < emptyTileIndexTest.length; i++)
		{
			System.out.println("Empty at Row: " + i + " and Column: " + emptyTileIndexTest[i]);
		}*/
		
		
		//3. Test getting new boards
		FileHandler obj1 = new FileHandler();
		/*List<char[][]> boardsListTest = new ArrayList<char[][]>();
		boardsListTest = obj1.getBoardsList();*/
			
		/*for(int i = 0; i < boardsListTest.size(); i++)
		{
			char[][] testBoard = boardsListTest.get(i);
			
			System.out.println("Board size:" + boardsListTest.size());
			System.out.println("Testing board " + i);
			
			for(int m = 0; m < testBoard.length; m++)
			{
				for(int n = 0; n < testBoard[i].length; n++)
				{
					System.out.print(testBoard[m][n]);				
				}
				
				System.out.println();
			}			
		}*/
	}
}
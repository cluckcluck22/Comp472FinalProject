package candyCrisis.FileHandler;

import java.util.ArrayList;
import java.util.List;
import candyCrisis.Result;

/*
 * Note: Test 3 - Test getting new boards - NOT WORKING!!
 * Problem: boardsList in FileHandler is only 
 */
public class FileHandlerTest
{
	public static void manualGameTest()
	{
		FileHandler obj = new FileHandler();
		
		//1. Get single board using hasNextBoard() and getNextBoard()		
		while(obj.hasNextBoard())
		{
			System.out.println("Has next board? -> " + obj.hasNextBoard());
			
			char[][] testBoard = obj.getNextBoard();
			
			for(int m = 0; m < testBoard.length; m++)
			{
				for(int n = 0; n < testBoard[m].length; n++)
				{
					System.out.print(testBoard[m][n]);				
				}
				
				System.out.println();
			}		
		}
		
		System.out.println("Has next board? -> " + obj.hasNextBoard());
		
	}
	
	public static void autoGameTest()
	{
		FileHandler obj = new FileHandler();
		
		//1. Get single board using hasNextBoard() and getNextBoard()		
		while(obj.hasNextBoardString())
		{
			System.out.println("Has next board? -> " + obj.hasNextBoardString());
			
			String testBoardString = obj.getNextBoardString();

			System.out.println(testBoardString);				
		}
		
		System.out.println("Has next board? -> " + obj.hasNextBoardString());
		
	}
	
	public static void main(String[] args)
	{
		//manualGameTest();
		//autoGameTest();
	}
}
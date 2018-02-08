package candyCrisis.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* Class: FileHandler.java
 * Programmer: Jesse Tsang
 * Date: 5/2/2018
 * Description: This class will read the txt files that contain starting board information.
 * 	Then it will encode the board into a 2D multidimensional array and pass it to BoardStateHandler().
 * 	After a game is finish, this class will also handle the given state history and encode it to a txt file.
 */
public class FileHandler
{
	private static final int MAX_ROW = 3;
	private static final int MAX_COLUMN = 15;
	static char[][] results = new char[MAX_ROW][MAX_COLUMN];
	
	
	private static final String ABS_PATH = "src/Resources/Sample_Data.txt";
	
	
	//Static method for BoardStateHandler() to get starting board from a txt file.
	//Input: None
	//Output: 2D array of character. 
	public static char[][] getStartBoard()
	{
		//Get NIO Path
		Path path = Paths.get(ABS_PATH);
	
		List<String> resultList = new ArrayList<>();
			
		try (Stream<String> stream = Files.lines(path))
		{
			//Convert the stream to an array list.
			//Each line would be 1 array.
			resultList = stream
							.collect(Collectors.toList());
			
			int counter = 0; //Counter to skip spaces
					
			for (int i = 0; i < MAX_ROW; i++)
			{
				for (int j = 0; j < MAX_COLUMN; j++)
				{
					results[i][j] = resultList.get(i).charAt(counter);
									
					//System.out.println("J is: " + j + "... and char is " + resultList.get(i).charAt(counter));
					//System.out.print(resultList.get(i).charAt(counter));
					
					counter = counter + 2;
				}
				
				counter = 0; //Reset for each line
				
				System.out.println("");
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
				
		//Generate dummy 2D array for testing.
		//results = getDummyArray();
		
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
		
		//saveBoardResult(moveHistory, totalTime);
		
		getStartBoard();
	}

}

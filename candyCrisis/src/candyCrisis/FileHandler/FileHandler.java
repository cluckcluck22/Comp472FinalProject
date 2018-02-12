package candyCrisis.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import candyCrisis.Result;

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
	private static final int NUM_OF_BOARD_PER_FILE = 3;
	static char[][] results = new char[MAX_ROW][MAX_COLUMN];
	
	private static final String ABS_PATH_READ = "src/Resources/Sample_Data.txt";
	private static String pathWrite = "src/Resources/";
	int outputCounter = 0;
	
	private static int[] emptyTileIndex;
	
	//Static method for BoardStateHandler() to get starting board from a txt file.
	//Input: None
	//Output: 2D array of character. 
	public static char[][] getStartBoard()
	{
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_READ);
	
		List<String> resultList = new ArrayList<>();
		emptyTileIndex = new int[NUM_OF_BOARD_PER_FILE];
			
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
					
					char isEmpty = results[i][j];
									
					if(isEmpty == 'e')
					{
						emptyTileIndex[i] = j;
						//System.out.println("Found Empty: " + isEmpty + " at row " + i + " and column " + emptyTileIndex.get(i) + ".");
					}

					counter = counter + 2;
				}
				
				counter = 0; //Reset for each line
			}	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return results;	
	}
	
	public static void saveBoardResult(Result result)
	{
		int boardCount = 0;
		String boardName = "Board" + boardCount;
		
		//Get NIO Path
		Path path = Paths.get(pathWrite + "Output_" + boardName + ".txt");
		
		byte[] pathHistoryBA = (result.getPathHistory() + System.lineSeparator()).getBytes();
		byte[] totalTimeBA = (String.valueOf(result.getTotalTime()) + System.lineSeparator()).getBytes();
		
		try
		{
			Files.write(path, pathHistoryBA, StandardOpenOption.APPEND);
			Files.write(path, totalTimeBA, StandardOpenOption.APPEND);	
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	//Static method for BoardStateHandler() to store the moves of a finished board.
	//Input: Arraylist moveHistory, Arraylist totalTime
	//Output: A text file that will store the array of moves, total completed time
	public static void saveBoardResultAL(ArrayList<Result> boardHistory)
	{
		int boardCount = 0;
		String boardName = "Board" + boardCount;
			
		try
		{
			//Get NIO Path
			Path path = Paths.get(pathWrite + "Output_" + boardName + ".txt");
			
			for (Result r : boardHistory)
			{
				byte[] pathHistoryBA = (r.getPathHistory() + System.lineSeparator()).getBytes();
				byte[] totalTimeBA = (String.valueOf(r.getTotalTime()) + System.lineSeparator()).getBytes();
						
				Files.write(path, pathHistoryBA, StandardOpenOption.APPEND);
				Files.write(path, totalTimeBA, StandardOpenOption.APPEND);		
			}		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Board history saved to " + boardName + ".txt");
	}
	
	//Return a map of empty tile index <row, column> position.
	public static int[] getEmptyTileIndex()
	{
		return emptyTileIndex;
	}

	public static void main(String[] args)
	{
		ArrayList<Result> boardHistoryAL = new ArrayList<Result>();
		boardHistoryAL.add(new Result("GHMNOJ", 6));
		boardHistoryAL.add(new Result("NIDEJONIHCBG", 28));
		boardHistoryAL.add(new Result("HGBAFGLMNOJ", 8));
		
		Result boardHistory = new Result("GHMNOJ", 6);
	
		//saveBoardResultAL(boardHistoryAL);
		//saveBoardResult(boardHistory);
		
		getStartBoard();
		
		int[] emptyTileIndexTest = new int[3];
		emptyTileIndexTest = getEmptyTileIndex();
		
		for(int i = 0; i < emptyTileIndexTest.length; i++)
		{
			System.out.println("Empty at Row: " + i + " and Column: " + emptyTileIndexTest[i]);
		}	
	}
}
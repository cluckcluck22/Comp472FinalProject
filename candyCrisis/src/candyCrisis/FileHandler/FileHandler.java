package candyCrisis.FileHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
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
	static char[][] results = new char[MAX_ROW][MAX_COLUMN];
	
	private static final String ABS_PATH_READ = "src/Resources/Sample_Data.txt";
	private static String pathWrite = "src/Resources/";
	int outputCounter = 0;
	
	//Static method for BoardStateHandler() to get starting board from a txt file.
	//Input: None
	//Output: 2D array of character. 
	public static char[][] getStartBoard()
	{
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_READ);
	
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
	
	//Static method for BoardStateHandler() to store the moves of a finished board.
	//Input: Arraylist moveHistory, Arraylist totalTime
	//Output: A text file that will store the array of moves, total completed time
	public static void saveBoardResult(ArrayList<Result> boardHistory)
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
		ArrayList<Result> boardHistory = new ArrayList<Result>();
		boardHistory.add(new Result("GHMNOJ", 6));
		boardHistory.add(new Result("NIDEJONIHCBG", 28));
		boardHistory.add(new Result("HGBAFGLMNOJ", 8));
	
		saveBoardResult(boardHistory);
		
		//getStartBoard();
	}
}
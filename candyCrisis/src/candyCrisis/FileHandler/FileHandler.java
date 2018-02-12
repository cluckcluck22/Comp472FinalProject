package candyCrisis.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import candyCrisis.Result;

/* Class: FileHandler.java
 * Programmer: Tsang Chi Kit (ID: 25692636)
 * Date: 5/2/2018
 * Description: This class will read the txt files that contain starting board information.
 * 	Then it will encode the board into a 2D multidimensional array and pass it to BoardStateHandler().
 * 	After a game is finish, this class will also handle the given state history and encode it to a txt file.
 */
public class FileHandler
{
	private final static String ABS_PATH_READ = "src/Resources/Sample_Data.txt";
	private final static String ABS_PATH_WRITE = "src/Resources/";
	
	private final static int MAX_ROW = 3;
	private final static int MAX_COLUMN = 5;
	private final static int NUM_OF_BOARD_PER_FILE = 3;
	
	private char[][] board;
	private List<char[][]> boardsList; //Arraylist of 3X5 boards.
	
	private int boardCount;
	private int[] emptyTileIndex;
	
	private boolean hasNextBoard;
	
	//Constructor
	public FileHandler()
	{
		this.boardCount = 0;
		this.boardsList = new ArrayList<char[][]>();
		this.emptyTileIndex = new int[NUM_OF_BOARD_PER_FILE];
		
		hasNextBoard = true;
		
		getStartBoard();
	}
	
	//Static method for BoardStateHandler() to get starting board from a txt file.
	//Input: None
	//Output: ArrayList of char[][]. 
	private List<char[][]> getStartBoard()
	{
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_READ);
			
		//Read txt file
		try (Stream<String> stream = Files.lines(path))
		{
			List<String> resultList = new ArrayList<>();
			
			//Convert the stream to an array list.
			//Each line would be 1 char[][].
			resultList = stream
							.collect(Collectors.toList());
			
			int counter = 0; //Counter to skip spaces
			
			//Each row in the text file = 1 char[][]
			for(int i = 0; i < resultList.size(); i++)
			{			
				//System.out.println("Single board - getStartBoard() - " + i  + " started!");
				char[][] board = new char[MAX_ROW][MAX_COLUMN];
				
				for(int m = 0; m < MAX_ROW; m++)
				{
					for(int n = 0; n < MAX_COLUMN; n++)
					{
						board[m][n] = resultList.get(i).charAt(counter);
						
						//Check if the tile is an empty tile
						char isEmpty = board[m][n];
						
						//If yes, write it to emptyTileIndex array
						if(isEmpty == 'e')
						{
							emptyTileIndex[i] = n;
							//System.out.println("Found Empty: " + isEmpty + " at row " + i + " and column " + emptyTileIndex.get(i) + ".");
						}
						
						//This is used to skip spaces in text file.
						counter = counter + 2;
						
						//System.out.print(board[m][n]);
					}
					
					//System.out.println();
				}
				
				//System.out.println("Single board - getStartBoard() - " + i + " finished!" );
				
				//Add the char[][] to the arraylist of boards
				boardsList.add(board);
										
				counter = 0; //Reset for each line
			}	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	
		return boardsList;	
	}

	public void saveBoardResult(Result result)
	{
		String boardName = "Board" + boardCount;
		
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_WRITE + "Output_" + boardName + ".txt");
		
		byte[] pathHistoryBA = (result.getPathHistory() + System.lineSeparator()).getBytes();
		byte[] totalTimeBA = (String.valueOf(result.getTotalTime()) + System.lineSeparator()).getBytes();
		
		try
		{
			Files.write(path, pathHistoryBA, StandardOpenOption.APPEND);
			Files.write(path, totalTimeBA, StandardOpenOption.APPEND);	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public char[][] getNextBoard()
	{
		Iterator<char[][]> itr = boardsList.iterator();
		
		if(itr.hasNext())
		{
			board = itr.next();
			
			/*System.out.println("Testing board - getNextBoard()");
			
			for(int m = 0; m < board.length; m++)
			{
				for(int n = 0; n < board[m].length; n++)
				{
					System.out.print(board[m][n]);				
				}
				
				System.out.println();
			}*/			
			
			itr.remove();
			hasNextBoard = itr.hasNext();			
		}
		else
		{
			hasNextBoard = false;
			
			board = null;
		}

		return board;
	}
	
	//Return an array of empty tile position ordered by their board number.
	public int[] getEmptyTileIndex()
	{
		return emptyTileIndex;
	}
	
	//Return list of char[][] (ArrayList)
	public List<char[][]> getBoardsList()
	{		
		return boardsList;
	}

	public boolean hasNextBoard()
	{
		return hasNextBoard;
	}
	
	
	
	
}
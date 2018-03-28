package candyCrisis.FileHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import candyCrisis.Result;

/*  Class Title: FileHandler.java
 *  Author: Tsang Chi Kit (ID: 25692636)
 *  Date: 5/2/2018
 *  Description: This class will read the txt files that contain starting board information.
 * 	Then it will encode the board into a 2D multidimensional array and pass it to BoardStateHandler().
 * 	After a game is finish, this class will also handle the given state history and encode it to a txt file.
 */
public class FileHandler
{
	private final static String ABS_PATH_READ = "src/Resources/Sample_Challenge03.txt";
	private final static String ABS_PATH_WRITE = "src/Resources/";
	
	private final static int MAX_ROW = 3;
	private final static int MAX_COLUMN = 5;
	private int NUM_OF_BOARD_PER_FILE;
	
	HashMap<Integer, Result> outputResult = new HashMap<Integer, Result>();	
	private char[][] board;					//To store a 3X5 board in 2D array form.
	private List<char[][]> boardsList; 		//Arraylist of 3X5 boards.
	
	private String boardString;				//To store a board in string form (for Dijkstra class).
	private List<String> boardsStringList;	//To store an array of strings(boardString).
	
	private int boardCount;
	private int[] emptyTileIndex;			//To store the position of the empty tile
	
	private boolean hasNextBoard;			//A boolean that check if there exist another board in the text file
	private boolean hasNextBoardString;		//A boolean that check if there exist another board string in the text file
	
	//Constructor
	public FileHandler()
	{
		this.boardCount = 0;
		this.boardsList = new ArrayList<char[][]>();
		this.boardString = "";
		this.boardsStringList = new ArrayList<String>();
		
		hasNextBoard = true;
		hasNextBoardString = true;
		
		getStartBoard();
	}
	
	//Method to import boards into an arraylist.
	//Input: None.
	//Output: None. 
	private void getStartBoard()
	{
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_READ);
			
		//Read txt file
		try (Stream<String> stream = Files.lines(path))
		{
			List<String> resultList = new ArrayList<>();
			
			//Convert the stream to an array list.
			//Each line would be 1 char[][].
			resultList = stream.collect(Collectors.toList());
			
			int counter = 0; //Counter to skip spaces
			
			//Each row in the text file = 1 char[][] (1 row = 1 board)
			for(int i = 0; i < resultList.size(); i++)
			{			
				board = new char[MAX_ROW][MAX_COLUMN];
				boardString = "";
				
				NUM_OF_BOARD_PER_FILE = resultList.size();
				emptyTileIndex = new int[NUM_OF_BOARD_PER_FILE];
				
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
							//System.out.println("Found Empty: " + isEmpty + " at row " + i + " and column " + emptyTileIndex[i] + ".");
						}
						
						//Append the char to the boardString
						boardString = boardString + board[m][n]; 
						
						//This is used to skip spaces in text file.
						counter = counter + 2;
					}		
				}
								
				//Add the char[][] to the arraylist of boards
				boardsList.add(board);
				
				//Add the boardString (board in String form) into boardStringList
				boardsStringList.add(boardString);
										
				counter = 0; //Reset for each line
			}	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		};	
	}

	//Method for store board history (path history and total game time) into a text file.
	//Input: Result data structure (String str, int i)
	//Output: A Output_Board.txt file in /Resources folder.
	public synchronized void saveBoardResult(Result result)
	{
		outputResult.put(result.index,result);
		/*
		String boardName = "Board" + boardCount;
		
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_WRITE + "Output_" + boardName + ".txt");
		byte[] pathHistoryStart = (result.startState + System.lineSeparator()).getBytes();
		byte[] pathHistoryBA = (result.getPathHistory() + System.lineSeparator()).getBytes();
		float resultMilleSec = (result.getTotalTime()/1000000);
		byte[] totalTimeBA = (String.valueOf(resultMilleSec + "ms") + System.lineSeparator()).getBytes();
		
		try
		{
			if(!Files.exists(path))
			{
				Files.createFile(path);
			}
			Files.write(path, pathHistoryStart, StandardOpenOption.APPEND);
			Files.write(path, pathHistoryBA, StandardOpenOption.APPEND);
			Files.write(path, totalTimeBA, StandardOpenOption.APPEND);	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		*/
		//boardCount++;
	}
	
	public  void saveBoardResultFinal()
	{
		
		String boardName = "Board" + boardCount;
		
		String outputFileName = "Output_" + boardName + ".txt";
		
		if(ABS_PATH_READ.indexOf("input1.txt")!=-1) {outputFileName="output1.txt";}
		else if(ABS_PATH_READ.indexOf("input2.txt")!=-1){outputFileName="output2.txt";}
		else if(ABS_PATH_READ.indexOf("input3.txt")!=-1){outputFileName="output3.txt";}
		else if(ABS_PATH_READ.indexOf("input4.txt")!=-1){outputFileName="output4.txt";}
		
		//Get NIO Path
		Path path = Paths.get(ABS_PATH_WRITE + outputFileName);
		int moves = 0;
		for(int i = 0; i < outputResult.size();i++)
		{
			Result result = outputResult.get(i);
			moves += result.getPathHistory().length();
		byte[] pathHistoryBA = (result.getPathHistory() + System.lineSeparator()).getBytes();
		int resultMilleSec = (int) (result.getTotalTime()/1000000);
		byte[] totalTimeBA = (String.valueOf(resultMilleSec + "ms") + System.lineSeparator()).getBytes();
		
		try
		{
			if(!Files.exists(path))
			{
				Files.createFile(path);
			}
			Files.write(path, pathHistoryBA, StandardOpenOption.APPEND);
			Files.write(path, totalTimeBA, StandardOpenOption.APPEND);	
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		}
		byte[] pathHistoryBA = (String.valueOf(Integer.toString(moves))).getBytes();
		try {
			Files.write(path, pathHistoryBA, StandardOpenOption.APPEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Method for BoardStateHandler class to retreive a char[][] (a board state).
	//Input: None
	//Output: char[][] (size = 3 rows 5 columns)
	public char[][] getNextBoard()
	{
		Iterator<char[][]> itr = boardsList.iterator();
		
		if(itr.hasNext())
		{
			board = itr.next();
					
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
	
	//Method for BoardStateHandler class to retreive a char[][] (a board state).
	//Input: None
	//Output: String (a board in string form, size = 15)
	public String getNextBoardString()
	{
		Iterator<String> itr = boardsStringList.iterator();
		String result = "";
		
		if(itr.hasNext())
		{
			result = itr.next();
					
			itr.remove();
			hasNextBoardString = itr.hasNext();			
		}
		else
		{
			hasNextBoardString = false;
			
			boardString = null;
		}

		return result;
	}	
	
	//Method for BoardStateHandler class to retreive all the empty tiles positions.
	//Input: None
	//Output: int[] where index = board#, index_value = empty tile position (0 - 14) 
	public int[] getEmptyTileIndex()
	{
		return emptyTileIndex;
	}
	
	//Getter method to retreive the arraylist that contains all the boards that are extracted from txt file.
	//Input: None
	//Output: List<char[][]> 
	public List<char[][]> getBoardsList()
	{		
		return boardsList;
	}
	
	//Getter method to retreive the arraylist that contains all the boards that are extracted from txt file.
	//Input: None
	//Output: List<char[][]> 
	public List<String> getBoardsStringList()
	{		
		return boardsStringList;
	}	

	//Boolean method to tell if there is board available, use in conjunction with getNextBoard(). 
	//This method will check the iterator of boardsList is empty or not.
	//Input: None
	//Output: Boolean value of hasNextBoard() 
	public boolean hasNextBoard()
	{
		return hasNextBoard;
	}
	
	//Boolean method to tell if there is a board string available, use in conjunction with getNextBoardString(). 
	//This method will check the iterator of boardsStringList is empty or not.
	//Input: None
	//Output: Boolean value of hasNextBoardString() 
	public boolean hasNextBoardString()
	{
		return hasNextBoardString;
	}	
}
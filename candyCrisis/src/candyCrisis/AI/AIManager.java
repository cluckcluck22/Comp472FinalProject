package candyCrisis.AI;

import java.util.List;

import candyCrisis.Dijkstra;
import candyCrisis.Result;
import candyCrisis.FileHandler.FileHandler;

public class AIManager
{
	private Dijkstra solver;
	private FileHandler fileHandler;
	
	private long startTime, totalTime;
	
	//Constructor
	public AIManager()
	{
		this.solver = new Dijkstra();
		this.fileHandler = new FileHandler();
		
		//Start counting total time for solving a puzzle.
		this.startTime = System.nanoTime();
		
		startAI();
	}
	
	//This method will ...
	//1. Check if there is a board string available in the FileHandler object.
	//2. While there is a board string 
	//3. 	Pass the string to the Dijkstra object and invoke runDijkstra(boardString).
	private void startAI()
	{
		boolean hasNextBoardString = false;
		String boardString = "";
		
		//Check if there is a board available ...
		hasNextBoardString = fileHandler.hasNextBoard();
				
		//While available ... do
		while(hasNextBoardString)
		{
			boardString = fileHandler.getNextBoardString();
			String resultPath = getResultString(solver.runDijkstra(boardString));
			
			long endTime = System.nanoTime();
			
			totalTime = startTime - endTime;
			
			Result result = new Result(resultPath, totalTime); 
			
			fileHandler.saveBoardResult(result);
		}		
	}

	private String getResultString(List<String> result)
	{
		String resultString = "";
		
		for (String s : result)
		{
			resultString = resultString + s;
		}
		
		return resultString;
	}
}

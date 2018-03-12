package candyCrisis.AI;

import java.util.List;

import candyCrisis.Dijkstra;
import candyCrisis.Result;
import candyCrisis.FileHandler.FileHandler;

public class AIManager
{
	private Dijkstra solver;
	private FileHandler fileHandler;
	
	private long startBoardTime, totalBoardTime, totalGameTime;
	
	//Constructor
	public AIManager()
	{
		this.solver = new Dijkstra();
		this.fileHandler = new FileHandler();
		
		//Start counting total time for solving a puzzle.
		this.startBoardTime = System.nanoTime();
		
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
		hasNextBoardString = fileHandler.hasNextBoardString();
				
		//While available ... do
		//1. Start timer
		//2. Get board string
		//3. Pass board string to Dijkstra.runDijkstra()
		//4. Get List<String> as resultPath
		//5. Stop timer
		//6. Pass resultPath to FileHandler
		//7. Repeat 
		while(hasNextBoardString)
		{
			startBoardTime = System.nanoTime();					
			
			boardString = fileHandler.getNextBoardString();
			
			String resultPath = getResultString(solver.runDijkstra(boardString));
			
			//String resultPath = "GHMNOJ";							//For testing only!!
			
			long endTime = System.nanoTime();
			
			totalBoardTime = endTime - startBoardTime;
			totalGameTime = totalGameTime + totalBoardTime;
			
			Result result = new Result(resultPath, totalBoardTime); 
			
			fileHandler.saveBoardResult(result);
			
			//Restart process
			hasNextBoardString = fileHandler.hasNextBoardString();
			System.out.println("run Done");
		}
		
		System.out.println("All boards solved!! Total time is: " + (float)(totalGameTime/1000000) + "ms");
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
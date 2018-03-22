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
		//Start counting total time for solving a puzzle.
		this.startBoardTime = System.nanoTime();
		
		this.solver = new Dijkstra();
		this.fileHandler = new FileHandler();
		
		
		
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
			System.out.println("\niterations");
			System.out.println(solver.iterations);			
			long endTime = System.nanoTime();
			
			totalBoardTime = endTime - startBoardTime;
			totalGameTime = totalGameTime + totalBoardTime;
			
			Result result = new Result(resultPath, totalBoardTime,"",0); 
			
			fileHandler.saveBoardResult(result);
			
			//Restart process
			hasNextBoardString = fileHandler.hasNextBoardString();
			System.out.println("run Done");
		}
		
		System.out.println("All boards solved!! Total time is: " + (float)(totalGameTime/1000000) + "ms");
	}

	/* Function: getResultsString
	 * Date: 12/3/2018
	 * Description: A function that takes in the state list from the Dijkstra Algorithm and finds the path the empty state takes through the board state
	 */
	private String getResultString(List<String> result)
	{
		String resultString = "";
		
		for (String s : result)
		{
			char[] arr =  s.toCharArray();
			for(int i = 0; i < 15; i ++)
			{
				//On finding the e element
				if(arr[i] == 'e')
				{
					resultString += (char)('A' + (i));
				}
			}
		}
		System.out.print(resultString);
		return resultString;
	}
}
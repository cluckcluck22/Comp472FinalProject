package Threads;

import java.util.ArrayList;
import java.util.List;

import candyCrisis.Dijkstra;
import candyCrisis.Result;
import candyCrisis.FileHandler.FileHandler;

public class thread implements Runnable {
	String resultString;
	String board;
	int index;
	long startBoardTime;
	long endTime;
	long totalBoardTime;
	public Result result;
	FileHandler fileHandler;
	
	public Result getResult()
	{
		return result;
	}
	
	public thread(String board,long startBoardTime,FileHandler fileHandler,int index)
	{
		this.board = board;
		this.startBoardTime = startBoardTime;
		this.fileHandler = fileHandler;
		this.index = index;
	}
	
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
    public void run() {
    	Dijkstra solver = new Dijkstra();
    	resultString = getResultString(solver.runDijkstra(board));
    	endTime = System.nanoTime();
    	totalBoardTime = endTime - startBoardTime;
    	result = new Result(resultString, totalBoardTime,board,index); 
    	fileHandler.saveBoardResult(result);
    }
    public static void main(String args[]) throws InterruptedException {
    	List<Thread> myThreads = new ArrayList<Thread>();
        long startBoardTime = System.nanoTime();
		
		FileHandler  fileHandler = new FileHandler();
    	
		boolean hasNextBoardString = false;
		String boardString = "";		
		hasNextBoardString = fileHandler.hasNextBoardString();
		int  x = 0;
		while(hasNextBoardString)
		{
			startBoardTime = System.nanoTime();					
			myThreads.add(new Thread(new thread(fileHandler.getNextBoardString(),startBoardTime,fileHandler,x)));
			x++;
			//Restart process
			hasNextBoardString = fileHandler.hasNextBoardString();
		}
		System.out.println("Threads stored");
		for(int i = 0;i < myThreads.size();i++)
		{
			myThreads.get(i).start();
		}
		System.out.println("Threads Started");
			for(int i = 0;i< myThreads.size();i++)
			{
				myThreads.get(i).join();
			}
			System.out.println("Writing out");
			fileHandler.saveBoardResultFinal();
			System.out.println("Done Everything");
			long endTime = System.nanoTime();
			System.out.println(((endTime - startBoardTime)/ 1000000) + "ms");
    }

}

package candyCrisis;

/* Class: GenerateSuccessor.java
 * Programmer: Jennifer Sunahara
 * Date: 10/3/2018
 * Description: A helper class for the Dijkstra class. GenerateSuccessor has the static method getSuccessors(String start)
 * which returns a list of strings representing valid successsors of the string start. Here the strings represent
 * BoardStates.
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class GenerateSuccessor {
	
	//Static function for the Dijkstra class, used to get a list of the valid successors of a given boardState (string start), in string format
	//Returns a list of strings
	public static List<String> getSuccessors(String start)
	{
		List<String> successors = new ArrayList<String>();
		
		int empty = start.indexOf('e');
		
		String top ="";
		String bot = "";
		String left = "";
		String right = "";
		
		if( empty > 5 ) // if e isn't in the top row
		{
			top = getTop(start, empty);
			successors.add(top);
		}
		if(	empty < 10 ) // if e isn't in the bot row
		{
			 bot = getBot(start, empty);
			 successors.add(bot);
		}
		if( empty%5 != 0 ) // if e isn't in the left col 
		{
			left = getLeft(start, empty);
			successors.add(left);
		}
		if( empty%5 != 4 ) // if e isn't in the right col 
		{
			right = getRight(start, empty);
			successors.add(right);
		}
				
		return successors;//only valid successor states (in string format) are returned
	}
	
	// Helper method for getSuccessors that returns the successor string if the up action is taken (ie: if the empty tile is moved upwards)
	private static String getTop(String start, int empty) 
	{
		char temp = start.charAt(empty-5);		
		String top = start.substring(0,empty-5) + 'e' + start.substring(empty-4,empty) + temp + start.substring(empty+1);//note: substring(0,empty) doesn't include empty
		return top;
	}
	
	// Helper method for getSuccessors that returns the successor string if the down action is taken (ie: if the empty tile is moved downwards)
	private static String getBot(String start, int empty) 
	{
		char temp = start.charAt(empty+5);		
		String bot = start.substring(0,empty) + temp + start.substring(empty+1,empty+5) + 'e' + start.substring(empty+6);
		return bot;
	}
	
	// Helper method for getSuccessors that returns the successor string if the left action is taken (ie: if the empty tile is moved left)
	private static String getLeft(String start, int empty) 
	{
		char temp = start.charAt(empty-1);
		String left = "";
		if(empty==14) //special case to avoid going out of bounds
		{
			left = start.substring(0,empty-1) + 'e' +temp;
		}
		else
		{
			left = start.substring(0,empty-1) + 'e' + temp + start.substring(empty+1);
		}
		
		return left;		
	}
	
	// Helper method for getSuccessors that returns the successor string if the right action is taken (ie: if the empty tile is moved right)
	private static String getRight(String start, int empty) 
	{
		char temp = start.charAt(empty+1);
		String right = start.substring(0,empty) +  temp + 'e' + start.substring(empty+2);
				
		return right;		
	}
	
	//This main is here to demonstrate how to use the function getSuccessors
	/*public static void main( String[] args ) 
	{
		String start="0123456e8901234";

		List<String> suc= GenerateSuccessor.getSuccessors(start);
		
		System.out.println(start.substring(0, 5));
		System.out.println(start.substring(5, 10));
		System.out.println(start.substring(10));
		System.out.println();
		
		for (String s : suc) {
			System.out.println(s.substring(0, 5));
			System.out.println(s.substring(5, 10));
			System.out.println(s.substring(10));
			System.out.println();
		}
	}*/

}

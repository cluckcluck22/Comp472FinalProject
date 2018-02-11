/* Class: ManualInputHandler.java
 * Programmer: Eric Davies
 * Date: 5/2/2018
 * Description: A class that handles will play the board manually if launched from this class. Checks if
 * 	the players input is of a valid range and will send the corresponding input key to the input interface
 * 	as needed.
 * 
 * TODO
 * 1)Remove the TODO's scattered throughout the code.
 * 2)Update code to handle end of game when that is developed.
 * 
 */

package candyCrisis;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ManualInputHandler {
	
	public static Map<String,Character> mapA;
	static
    {
		mapA = new HashMap<String,Character>();
		mapA.put("up", 'U');
		mapA.put("down", 'D');
		mapA.put("left", 'L');
		mapA.put("right", 'R');
		mapA.put("display", 'X');
    }
	
	public static void main( String[] args ) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        System.out.println( "Welcome to the CandyCrisis manual input method. The objective of the game is to "
        		+ "have the pieces of the game be symetrical over the middle row. To modify the following puzzle:");
        //TODO Display the puzzles current state to the user
        System.out.println("Please use the following commands:");
        System.out.println("left");
        System.out.println("right");
        System.out.println("down");
        System.out.println("up");
        System.out.println("display");
        System.out.println("exit");
        
        Scanner reader = new Scanner(System.in);
        System.out.print("Please enter a command: ");
        String line = reader.nextLine();
        while(!line.contentEquals("exit"))
        {
        		//Check for valid char
        		if(mapA.containsKey(line))
        		{
        			System.out.println("Valid move "+line+ " received");
        			InputInterface.updateBoard(mapA.get(line));
        		}
        		else
        		{
        			System.out.println("Invalid input "+line+" please enter a valid input");
        		}
        	System.out.print("Please enter a command: ");
            line = reader.nextLine();
        }
        reader.close();
        System.exit( 0 ); //success
    }

}

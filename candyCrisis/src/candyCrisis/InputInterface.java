/* Class: InputInterface.java
 * Programmer: Eric Davies
 * Date: 5/2/2018
 * Description: A class that severs as a standard interface from the player or the AI input to the board.
 * 	This class will also check if the move is a valid board move from the player as well, something that
 * 	will be omitted from the AI interaction.
 * 
 * TODO:
 * 1)Remove all TODO's from the code once applicable
 * 2)Write up test cases for this class
 * 3)Handle end game once applicable.
 * 4)Write more todo's when I'm off the train.
 * 
 */
package candyCrisis;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InputInterface {
	public static BoardStateHandler boardObj = new BoardStateHandler();
	static
	{
		boardObj.begin();
	}
	public static Map<Character,Character> invert;
	static
	{
		invert = new HashMap<Character,Character>();
		invert.put('U','D');
		invert.put('D', 'U');
		invert.put('L', 'R');
		invert.put('R','L');
	}
	public static Map<Character,Method> mapA;
	static
    {
		try {
		mapA = new HashMap<Character,Method>();
		mapA.put('U', BoardStateHandler.class.getDeclaredMethod("moveUp"));
		mapA.put('D', BoardStateHandler.class.getDeclaredMethod("moveDown"));
		mapA.put('L', BoardStateHandler.class.getDeclaredMethod("moveLeft"));
		mapA.put('R', BoardStateHandler.class.getDeclaredMethod("moveRight"));
		mapA.put('X', BoardStateHandler.class.getDeclaredMethod("printCurrentBoard"));
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static boolean updateBoard(char item) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		if(item == 'X')
		{
			(mapA.get(item)).invoke(boardObj);
			return true;
		}
		else
		{
		//TODO check if input valid, fail and return false otherwise
		if(LegalActionChecker.isLegalAction(item))
		//if(true)
		{
			(mapA.get(item)).invoke(boardObj);
			boardObj.printCurrentBoard();
			return true;
		}
		else
		{
			return false;
		}	
		}
	}
}

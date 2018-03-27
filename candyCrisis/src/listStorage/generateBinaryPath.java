package listStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class generateBinaryPath 
{
	private Map uniqueVal = new HashMap<String,Integer>();
	
	//This method put every char in a board to the HashMap (if not already exists)
	//Key = character ; Value = convert (counter)
	public generateBinaryPath(String board)
	{
		int convert = 0;
		char[] val = board.toCharArray();
		
		for(int i = 0; i < val.length; i ++)
		{
			if(!uniqueVal.containsKey(val[i]))
			{
				uniqueVal.put(val[i], convert);
				convert ++;
			}
		}
	}
	
	//Convert the incoming String to a list of 3-digit binary character. The binary is based on the order of the constructor String
	//Todo: 
	//	1. What does this do?
	//	2. Why some char have the same binary (aka why only take last 3-digit)? 
	public List<Character> getBinaryArray(String path)
	{
		char[] val = path.toCharArray();
		List<Character> newVal = new ArrayList<Character>();
		
		for(int  i = 0; i < val.length;i++)
		{
			//TODO: Make this generate to the correct precision so it is less
			char[] tmp = Integer.toBinaryString(0x10000 | (int) uniqueVal.get(val[i])).substring(14).toCharArray();
			
			/************************ - Test codes
			int mapValue = (int) uniqueVal.get(val[i]);
			String binString = Integer.toBinaryString(0x10000 | (int) uniqueVal.get(val[i]));
			String binSubString = binString.substring(14);
			System.out.println("Char: " + mapValue);
			System.out.println("Binary String: " + binString);
			System.out.println("Binary SubString: " + binSubString);
			*************************/
			
			for(int x = 0; x < tmp.length; x++)
			{
				newVal.add(tmp[x]);
			}
		}
		
		return newVal;
	}
	
	public static void main(String[] args)
	{
		generateBinaryPath testBoard = new generateBinaryPath("AFKLMHIDEJINOJEDIHGLMHCDI");
		
		String testPath = "AFKLMHIDEJINOJEDIHGLMHCDI";
		testBoard.getBinaryArray(testPath);
	}
}
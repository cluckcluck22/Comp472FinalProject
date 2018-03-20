package listStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class generateBinaryPath {
	private Map uniqueVal = new HashMap<String,Integer>();
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
	
	public  List<Character> getBinaryArray(String path)
	{
		char[] val = path.toCharArray();
		List<Character> newVal = new ArrayList<Character>();
		for(int  i = 0; i < val.length;i++)
		{
			//TODO: Make this generate to the correct precision so it is less
			char[] tmp = Integer.toBinaryString(0x10000 | (int) uniqueVal.get(val[i])).substring(14).toCharArray();
			for(int x = 0; x < tmp.length; x++)
			{
				newVal.add(tmp[x]);
			}
		}
		return newVal;
	}
}

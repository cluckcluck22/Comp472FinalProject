/* Class ListInsertTest.java
 * Programmer: Eric Davies
 * Date: 22/3/2018
 * Description: A class that Eric used to experiment with a binary list insertion.
 * 
 */

package ListSorter;

import java.util.ArrayList;
import java.util.List;

public class ListInsertTest {
	
	public static List<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) {
		for(int i = 0; i < 50; i ++)
		{
			list.add(i);
		}
		list.remove(0);
		
		insertVal(list,0,list.size(),50);
		
		insertVal(list,0,list.size(),8);
		insertVal(list,0,list.size(),9);
		removeVal(list,0,list.size(),13);
		System.out.println(list);

	}
	
	public static void insertVal(List<Integer> list, int min, int max, int newVal)
	{
		int index = (max-min)/2 + min;
		int tmpVal = list.get(index);
		if(tmpVal == newVal)
		{
			list.add(index, newVal);
			return;
		}
		else if( index == min)
		{
			if(tmpVal > newVal)
			{
				list.add(index,newVal);
			}
			else
			{
				list.add(index+1, newVal);
			}
			
			return;
		}
		else
		{
			if(newVal < tmpVal)
			{
				insertVal(list,min, index, newVal);
			}
			else
			{
				insertVal(list,index,max,newVal);
			}
		}
	}
	public static void removeVal(List<Integer> list, int min, int max, int newVal)
	{
		int index = (max-min)/2 + min;
		int tmpVal = list.get(index);
		if(tmpVal == newVal)
		{
			list.remove(index);
			return;
		}
		else
		{
			if(newVal < tmpVal)
			{
				removeVal(list,min, index, newVal);
			}
			else
			{
				removeVal(list,index,max,newVal);
			}
		}
	}

}

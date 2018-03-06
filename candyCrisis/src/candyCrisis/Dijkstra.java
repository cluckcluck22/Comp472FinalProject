/* Class: Dijkstra
 * Programmer: Eric Davies
 * Date: 5/3/2018
 * Description: A class that stores the dijkstra function to search through the puzzle search space
 * TODO:
 * Remove all suggestion comments
 * Solve all TODO's in the code block
 */

package candyCrisis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dijkstra {
	List<Node> openList,closedList;
	Map<String, String> referenceTable = new HashMap<String, String>();
	
	public int testing = 10;
	public int iterations = 0;
	public boolean debug = true;
	
	public Dijkstra()
	{

	}
	
	
	public List<String> runDikstra(String startNode)
	{
		openList.add(new Node(startNode,0,0));
		
		while(openList.size() > 0 && iterations < testing)
		{
			iterations ++;
			Node current = openList.remove(0);
			if(false)		//if(isFinalState(current.name) == true)
			{
				//Generate Path
				return getPath(current.name,startNode);
				//Return Path
			}
			if(debug)
			{
				debugger();
			}
			
			String[] connected = null;
			for(int i = 0; i < connected.length; i++)
			{
				String element  = connected[i];
				
				if(containsName(openList,element))
				{
					Node openElement = getElementWithName(openList,element).get();
					if(openElement.cost > current.cost + 1)
					{
						openElement.cost = current.cost + 1;
						updateReferenceTable(element,current.name);
						//update reference table
					}
				}
				else if(containsName(closedList,element))
				{
					Node closedElement = getElementWithName(closedList,element).get();
					if(closedElement.cost > current.cost + 1 )
					{
						deleteElementWithName(closedList,element);
						sortedAdd(openList,element,current.cost + 1,0);
						updateReferenceTable(element,current.name);
						//update reference table
					}
				}
				else
				{
					//push to open list
					sortedAdd(openList,element,current.cost + 1, 0);
					updateReferenceTable(element,current.name);
					//update reference table
					
				}
				//check if closed contains
				//check if open contains
				//else push to open
			}
		}
		
		//TODO remove this once complete or update so it is reached when no goal reached
		return null;
	}
	
	public boolean containsName(final List<Node> list, final String name){
	    return list.stream().filter(o -> o.name.equals(name)).findFirst().isPresent();
	}
	public Optional<Node> getElementWithName(final List<Node> list, final String name){
	    return list.stream().filter(o -> o.name.equals(name)).findFirst();
	}
	public void deleteElementWithName(final List<Node> list, final String name)
	{
		list.remove(list.stream().filter(o -> o.name.equals(name)).findFirst().get());
	}
	
	/* Function: sortedAdd
	 * Programmer: Eric Davies
	 * Date: 5/3/2018
	 * Description: A function that adds elements into a sorted list in order to maintain an increasing order
	 */
	public void sortedAdd(List<Node> list, String name,int cost,int heuristic)
	{
		for(int i = 0; i < list.size(); i ++)
		{
			if(list.get(i).cost> cost)
			{
				list.add(i,new Node(name,cost,heuristic));
			}
		}
	}
	
	private void debugger()
	{
		System.out.println("Debugger Called");
		System.out.println("Open List Contents");
		printList(openList);
		System.out.println("Closed List Contents");
		printList(closedList);
	}
	private void printList(List<Node> list)
	{
		String output = "";
		for(int i = 0; i< list.size();i++)
		{
			Node tmp = list.get(i);
			output += "(" + tmp.name +":" + tmp.cost + ":" + tmp.heuristicCost + ")";
		}
		System.out.println(output);
	}
	
	
	public void updateReferenceTable(String child,String parent)
	{
		referenceTable.put(child, parent);
	}
	public List<String> getPath(String goal,String start)
	{
		List<String> path = null;
		while(true)
		{
			String item =referenceTable.get(goal);
			path.add(item);
			if(item.equals(start))
			{
				return path;
			}
		}
	}

}

/* Class: Dijkstra
 * Programmer: Eric Davies
 * Date: 5/3/2018
 * Description: A class that stores the dijkstra function to search through the puzzle search space
 * TODO:
 * Solve all TODO's in the code block
 */

package candyCrisis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Dijkstra {
	
	List<Node> openList,closedList;
	Map<String, String> referenceTable;
	
	/*************Testing Data*******************/
	public int testing = 10000;
	public int iterations = 0;
	public boolean debug = false;
	/*************Testing Data End**************/
	
	
	/* Constructor: Dijkstra
	 * Paramaters: None
	 * Description: A default constructor that does nothing
	 */
	public Dijkstra()
	{
		
	}
	
	/* Function: runDikstra
	 * Parameters:
	 * 	@String startNode: A 15 character single line representation of the board state
	 * Returns:
	 * 	@ List<String>: A list of 15 character board states that is the path from the start to the goal.
	 * Description: A function that runs the Dijkstra algorithm until all paths lead to no goal or the shortest path to a goal node is reached.
	 */
	public List<String> runDijkstra(String startNode)
	{
		iterations = 0;		//TODO remove iteration as this is for loops testing purposes and now holds no true use
		
		//Set and reset list for every call
		openList = new ArrayList<Node>();
		closedList = new ArrayList<Node>();
		referenceTable = new HashMap<String, String>();
		//*********************************
		
		
		openList.add(new Node(startNode,0,0));
		updateReferenceTable(startNode,startNode);
		
		while(openList.size() > 0 && iterations < testing)
		{
			iterations ++;
			Node current = openList.remove(0);
			
			//check if goal state found
			if(GoalStateChecker.isGoalStateAi(current.name))
			{
				System.out.println("Goal State Reached");
				return getPath(current.name,startNode);
			}
			
			if(debug)
			{
				debugger();
			}
			
			//Get all successors to the current state, will be between two and four
			List<String> connected = GenerateSuccessor.getSuccessors(current.name);
			
			//loop through connections
			for(int i = 0; i < connected.size(); i++)
			{
				String element  = connected.get(i);
				
				//check if node already found and on open list
				if(containsName(openList,element))
				{
					Node openElement = getElementWithName(openList,element).get();
					//check if current path cost is less than path cost of node on open list
					if(openElement.cost > current.cost + 1)
					{
						//update path cost
						openElement.cost = current.cost + 1;
						//update reference table
						updateReferenceTable(element,current.name);
						
					}
				}
				//check if node already been processed and on closed list
				else if(containsName(closedList,element))
				{
					Node closedElement = getElementWithName(closedList,element).get();
					//check if cost to node on closed list is greater than the current path cost
					if(closedElement.cost > current.cost + 1 )
					{
						//remove from closed and reinsert to open with new path cost
						deleteElementWithName(closedList,element);
						sortedAdd(openList,element,current.cost + 1,0);
						//update reference table
						updateReferenceTable(element,current.name);
					}
				}
				else
				{
					//push to open list as it has not already been seen
					sortedAdd(openList,element,current.cost + 1, 0);
					updateReferenceTable(element,current.name);
					//update reference table
					
				}
			}
			sortedAdd(closedList,current);
		}
		
		//TODO remove this once complete or update as it is reached when no goal reached
		return new ArrayList<String>();
	}
	
	/* Function: containsName
	 * Params:
	 * 	@ final List<Node> list: list to check for containing name
	 * 	@ final String name: name to check if in list
	 * Returns:
	 * 	@ boolean: state of the list containing the name in the node name field
	 * Description: A function that filters the node list for elements that contain the passed name. Filters for the first node and checks if there was
	 * 	any to confirm existence
	 */
	public boolean containsName(final List<Node> list, final String name){
	    return list.stream().filter(o -> o.name.equals(name)).findFirst().isPresent();
	}
	
	/* Function getElementWithName
	 * Params:
	 * 	@ final List<Node> list: list to get element from
	 * 	@ final String name: name of the node to get from the list
	 * Returns:
	 * 	@ Optional<Node>: returns the node if it is possible to get. Run only after using containsName to confirm existence.
	 * Description: A function that gets the first node with the name tag on the passed list. Optional since the method to fetch can find nothing.
	 * 	Should only be run after running containsName to confirm existence of such an element
	 */
	public Optional<Node> getElementWithName(final List<Node> list, final String name){
	    return list.stream().filter(o -> o.name.equals(name)).findFirst();
	}
	
	/* Function deleteElementWithName
	 * Params:
	 * 	@ final List<Node> list: the list to delete the element from
	 * 	@ final String name: the name element of the node to delete from
	 * Description: A function that finds the element with the name and deletes it
	 * 
	 */
	public void deleteElementWithName(final List<Node> list, final String name)
	{
		list.remove(list.stream().filter(o -> o.name.equals(name)).findFirst().get());
	}
	
	/* Function: sortedAdd
	 * Params:
	 * 	@ List<Node> list: list to add a new node to
	 * 	@ String name: name of the new node
	 *  @ String cost: actual cost to get to the current node
	 *  @ String heuristic: estimated cost to get to the new node
	 * Description: A function that inserts nodes into a sorted list in the first position from the left that is greater than the passed cost
	 * 	value. This maintains the sorted lists order and allows the smallest cost node to be easily found
	 */
	public void sortedAdd(List<Node> list, String name,int cost,int heuristic)
	{
		for(int i = 0; i < list.size(); i ++)
		{
			if(list.get(i).cost> cost)
			{
				list.add(i,new Node(name,cost,heuristic));
				return;
			}
		}
		list.add(new Node(name,cost,heuristic));
	}
	
	/* Function sortedAdd
	 * Params:
	 * 	@ List<Node> list: list to add a node to
	 * 	@ Node: node to add into the sorted list
	 * Description: A function that inserts an existing node into a sorted list at the first position from the left that the node is greater than.
	 * 	This allows for the list to stay in a sorted state
	 * 
	 */
	public void sortedAdd(List<Node> list, Node current)
	{
		for(int i = 0; i < list.size(); i ++)
		{
			if(list.get(i).cost> current.cost)
			{
				list.add(i,current);
				return;
			}
		}
		list.add(current);
	}	
	
	
	/* Function: debugger
	 * Description: A function that prints out the contents of the closed and open list. Useful for the testing of the Dijkstra looping is
	 * 	functioning properly
	 */
	private void debugger()
	{
		System.out.println("Debugger Called");
		System.out.println("Open List Contents");
		printList(openList);
		System.out.println("Closed List Contents");
		printList(closedList);
	}
	
	/* Function: printList
	 * Params:
	 * 	@ List<Node> list: list to print
	 * Description: A function that loops over a node list and outputs a human readable list to the console instead.
	 */
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
	
	/* Function: updateReferenceTable
	 * Params:
	 * 	@ String child: name of the child node entry to update
	 * 	@ String parent: name of the parent node to be set as the value in the referenceTable
	 * Description: A function that inserts/updates the entry with the key child and value parent
	 */
	public void updateReferenceTable(String child,String parent)
	{
		referenceTable.put(child, parent);
	}
	
	/* Function getPath
	 * Params:
	 * 	@ String goal: the end node to start getting the path from
	 * 	@ String start: the start node that signifies the start of the path
	 * Returns:
	 * 	@ List<String>: A list of every state from the end state to the start state
	 * Description: A function that loops until it has fetched the complete path from the reference table
	 */
	public List<String> getPath(String goal,String start)
	{
		List<String> path = new ArrayList<String>();
		String item = goal;
		path.add(0,goal);
		while(true)
		{
			item =referenceTable.get(item);
			path.add(0,item);
			if(item.equals(start))
			{
				return path;
			}
		}
	}

}

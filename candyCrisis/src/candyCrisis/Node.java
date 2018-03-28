/* Class: Node.java
 * Programmer: Eric Davies
 * Date: 6/3/2018
 * Description: A class that stores the name, cost and heuristic cost into an easily referencible object for the Dijkstra algorithm.
 */

package candyCrisis;

public class Node {
	public String name;
	public int cost;
	public int heuristicCost;
	
	public Node parent;
	
	public Node()
	{
		name = "";
		cost = 0;
		heuristicCost = 0;
	}
	public Node(String passedName, int passedCost, int passedHeuristic,Node parent)
	{
		name = passedName;
		cost = passedCost;
		heuristicCost = passedHeuristic;
		this.parent = parent;
	}

	public Node(String passedName, int passedCost, int passedHeuristic) {
		name = passedName;
		cost = passedCost;
		heuristicCost = passedHeuristic;
		this.parent = this;
	}
	public int getCost()
	{
		return cost + heuristicCost;
	}
}

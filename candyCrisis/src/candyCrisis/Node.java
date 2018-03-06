package candyCrisis;

public class Node {
	public String name;
	public int cost;
	public int heuristicCost;
	
	public Node()
	{
		name = "";
		cost = 0;
		heuristicCost = 0;
	}
	public Node(String passedName, int passedCost, int passedHeuristic)
	{
		name = passedName;
		cost = passedCost;
		heuristicCost = passedHeuristic;
	}

}

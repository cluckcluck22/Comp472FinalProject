package listStorage;

import java.util.ArrayList;
import java.util.List;

import candyCrisis.Node;

public class listManagers {
	binaryPathTree openListExistence = new binaryPathTree();
	binaryPathTree closedListExistence = new binaryPathTree();
	List<Node> openListOrder = new ArrayList<Node>();
	generateBinaryPath generator;
	private int openListSize = 0;
	
	public listManagers(String board)
	{
		generator = new generateBinaryPath(board);
	}
	
	public Node getNodeFromOpenListExistence(String element)
	{
		List<Character> baseRepresent = generator.getBinaryArray(element);
		return openListExistence.getNode(baseRepresent, 0);
	}
	
	public Node getNodeFromClosedListExistence(String element)
	{
		List<Character> baseRepresent = generator.getBinaryArray(element);
		return closedListExistence.getNode(baseRepresent, 0);
	}
	
	
	public void updateOpenlist(String element,int cost,String parent)
	{
		Node move = null;
		for(int i = 0; i < openListSize; i++)
		{
			if(openListOrder.get(i).name.equals(element))
			{
				move = openListOrder.get(i);
				move.parent = parent;
				break;
			}
		}
		move.cost = cost;
		boolean added = false;
		for(int i = 0; i < openListSize; i++)
		{
			if(move.getCost() < openListOrder.get(i).getCost())
			{
				openListOrder.add(i,move);
				added = true;
				break;
			}
		}
		if(!added)
		{
			openListOrder.add(move);
		}
		openListSize++;

	}
	
	public void updateOpenlist(Node element,int cost,String parent)
	{
		element.parent = parent;
		for(int i = 0; i < openListSize; i++)
		{
			if(openListOrder.get(i).name.equals(element.name))
			{
				openListOrder.remove(i);
			}
		}
		element.cost = cost;
		boolean added = false;
		for(int i = 0; i < openListSize; i++)
		{
			if(element.getCost() < openListOrder.get(i).getCost())
			{
				openListOrder.add(i,element);
				added = true;
				break;
			}
		}
		if(!added)
		{
			openListOrder.add(element);
		}
		openListSize++;

	}
	
	public void insertOpenList(String element, int cost, int heuristic,String parent)
	{
		
		Node insert = new Node(element,cost,heuristic,parent);
		insertVal(openListOrder,0,openListSize,insert);
		/*
		boolean  added = false;
		for(int i = 0 ;i < openListSize; i++)
		{
			if(insert.getCost() < openListOrder.get(i).getCost())
			{
				openListOrder.add(i,insert);
				added = true;
				break;
			}
		}
		
		if(!added)
		{
			openListOrder.add(insert);
		}
		*/
		openListSize ++;
		this.openListExistence.insertValue(insert, generator.getBinaryArray(element), 0);
	}
	
	public void insertVal(List<Node> list, int min, int max, Node newVal)
	{
		int index = (max-min)/2 + min;
		if(max ==0)
		{
			list.add(0,newVal);
			return;
		}
		Node tmpVal = list.get(index);
		if(tmpVal.getCost() == newVal.getCost())
		{
			list.add(index, newVal);
			return;
		}
		else if( index == min)
		{
			if(tmpVal.getCost() > newVal.getCost())
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
			if(newVal.getCost() < tmpVal.getCost())
			{
				insertVal(list,min, index, newVal);
				return;
			}
			else
			{
				insertVal(list,index,max,newVal);
				return;
			}
		}
	}
	
	public boolean openListHasNext()
	{
		return openListSize > 0;
	}
	
	public void insertClosedList(String element, int cost, int heuristic,String parent)
	{
		Node insert = new Node(element, cost, heuristic,parent);
		this.closedListExistence.insertValue(insert, generator.getBinaryArray(element), 0);
	}
	public void insertClosedList(Node insert)
	{
		this.closedListExistence.insertValue(insert, generator.getBinaryArray(insert.name), 0);
	}
	
	public void closedListRemove(String element)
	{
		this.closedListExistence.removeNode(generator.getBinaryArray(element), 0);
	}
	
	
	public Node openListGetFirst()
	{
		if(openListSize > 0)
		{
			Node tmp = openListOrder.get(0);
			openListOrder.remove(0);
			this.openListExistence.removeNode(generator.getBinaryArray(tmp.name), 0);
			openListSize --;
			return tmp;
		}
		else
		{
			return null;
		}
	}
	
}

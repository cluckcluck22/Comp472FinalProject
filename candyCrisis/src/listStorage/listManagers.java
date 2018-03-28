package listStorage;

import java.util.ArrayList;
import java.util.List;

import candyCrisis.Node;

public class listManagers 
{
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
	
	
	public void updateOpenlist(String element,int cost,Node parent)
	{
		Node move = this.getNodeFromOpenListExistence(element);
		move.parent = parent;
		//removeVal(openListOrder,0,openListSize,move);
		
		for(int i = 0; i < openListSize; i++)
		{
			if(openListOrder.get(i).name.equals(element))
			{
				openListOrder.remove(i);
				break;
			}
		}
		
		openListSize--;
		move.cost = cost;
		insertVal(openListOrder,0,openListSize,move);
		/*
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
		}*/
		
		openListSize++;
	}
	
	public void updateOpenlist(Node element,int cost,Node parent)
	{
		element.parent = parent;
		//removeVal(openListOrder,0,openListSize,element);
		
		for(int i = 0; i < openListSize; i++)
		{
			if(openListOrder.get(i).name.equals(element.name))
			{
				openListOrder.remove(i);
				break;
			}
		}
		
		openListSize--;
		element.cost = cost;
		insertVal(openListOrder,0,openListSize,element);
		/*
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
		}*/
		
		openListSize++;

	}
	
	public void insertOpenList(String element, int cost, int heuristic)
	{
		
		Node insert = new Node(element,cost,heuristic);
		insertVal(openListOrder,0,openListSize,insert);
		openListSize ++;
		this.openListExistence.insertValue(insert, generator.getBinaryArray(element), 0);
	}
	public void insertOpenList(String element, int cost, int heuristic, Node Parent)
	{
		
		Node insert = new Node(element,cost,heuristic,Parent);
		insertVal(openListOrder,0,openListSize,insert);
		openListSize ++;
		this.openListExistence.insertValue(insert, generator.getBinaryArray(element), 0);
	}
	
	public void removeVal(List<Node> list, int min, int max, Node oldVal)
	{
		int index = (max-min)/2 + min;
		Node tmpVal = list.get(index);
		
		if(tmpVal.name.equals(oldVal.name))
		{
			list.remove(index);
			return;
		}
		else if(tmpVal.name.equals(list.get(index + 1).name))
		{
			list.remove(index + 1);
		}
		else
		{
			if(oldVal.getCost() < tmpVal.getCost())
			{
				removeVal(list,min, index, oldVal);
			}
			else
			{
				removeVal(list,index,max,oldVal);
			}
		}
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
	
	public void insertClosedList(String element, int cost, int heuristic,Node parent)
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
package listStorage;

import java.util.List;

import candyCrisis.Node;

public class binaryPathTree {
	
	binaryPathTree zero = null;
	binaryPathTree one = null;
	Node current = null;
	
	public binaryPathTree()
	{
		
	}
	
	public void insertValue(Node val, List<Character> path, int index)
	{
		if(path.size() == index)
		{
			current = val;
		}
		else
		{
			if(path.get(index).equals('0'))
			{
				if(zero == null)
				{
					zero = new binaryPathTree();
				}
				zero.insertValue(val, path, index+1);
			}
			else
			{
				if(one == null)
				{
					one = new binaryPathTree();
				}
				one.insertValue(val, path, index+1);
			}
		}
	}
	
	public Node getNode(List<Character> path, int index)
	{
		if(path.size() == index)
		{
			return current;
		}
		else
		{
			if(path.get(index).equals('0'))
			{
				if(zero == null)
				{
					return null;
				}
				else
				{
					return zero.getNode(path, index + 1);
				}
			}
			else
			{
				if(one == null)
				{
					return null;
				}
				else
				{
					return one.getNode(path, index+ 1);
				}
			}
		}
	}
	
	public boolean getNodeExistence(List<Character> path, int index)
	{
		if(path.size() == index)
		{
			return current != null;
		}
		else
		{
			if(path.get(index).equals('0'))
			{
				if(zero == null)
				{
					return false;
				}
				else
				{
					return zero.getNodeExistence(path, index + 1);
				}
			}
			else
			{
				if(one == null)
				{
					return false;
				}
				else
				{
					return one.getNodeExistence(path, index+ 1);
				}
			}
		}
	}
	public void removeNode(List<Character> path, int index)
	{
		if(path.size() == index)
		{
			current = null;
		}
		else
		{
			if(path.get(index).equals('0'))
			{
				if(zero != null)
				{
					zero.removeNode(path, index + 1);
				}
			}
			else
			{
				if(one != null)
				{
					one.removeNode(path, index+ 1);
				}
			}
		}
	}


}

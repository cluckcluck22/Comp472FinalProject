package listStorage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import candyCrisis.Node;

public class binaryPathTreeTest {

	@Test
	public void test() {
		generateBinaryPath generator = new generateBinaryPath("rebwrbbbrrrbrbw");
		binaryPathTree tree = new binaryPathTree();
		Node passedNode = new Node("rebwrbbbrrrbrbw", 1, 0);
		List<Character> baseRepresent = generator.getBinaryArray(passedNode.name);
		List<Character> baseRepresent2 = generator.getBinaryArray("rebwrbbbrrrbrbb");
		
		tree.insertValue(passedNode, baseRepresent, 0);
		System.out.print(tree.getNodeExistence(baseRepresent, 0));
		tree.removeNode(baseRepresent, 0);
		System.out.print(tree.getNodeExistence(baseRepresent, 0));
		System.out.print(tree.getNodeExistence(baseRepresent2, 0));
	}
	
	@Test
	public void testSorting()
	{
		
	}
}

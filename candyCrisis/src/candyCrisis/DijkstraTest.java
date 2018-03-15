package candyCrisis;

import static org.junit.Assert.*;

import org.junit.Test;

public class DijkstraTest {

	
	/* test
	 * Description: A test to get the average time after five runs of a difficult to run puzzle
	 * 
	 */
	@Test
	public void test() {
		long startTime = System.nanoTime();
		long totalGameTime = 0;
		int iterations = 5;
		for(int i = 0; i < iterations; i ++)
		{
			Dijkstra obj = new Dijkstra();
			obj.runDijkstra("brbwwrrbbbbrerr");
			System.out.println(obj.iterations);
		}
		long endTime = System.nanoTime();

		long duration = (endTime - startTime); 
		
		System.out.println((duration/ 1000000 / iterations) + "ms");
	}

}

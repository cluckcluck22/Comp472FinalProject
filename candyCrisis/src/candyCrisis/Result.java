/**
 * 
 */
package candyCrisis;

/**
 * @author Jesse Tsang
 * This is a data structure to store a game's path history and total time.
 */
public class Result
{
	public String startState;
	private String pathHistory;
	private float totalTime;
	public int index;
	
	public Result(String pathHistory, float totalTime, String startState, int index)
	{
		this.pathHistory = pathHistory;
		this.totalTime = totalTime;
		this.startState = startState;
		this.index = index;
	}

	public String getPathHistory()
	{
		return pathHistory;
	}

	public void setPathHistory(String pathHistory)
	{
		this.pathHistory = pathHistory;
	}

	public float getTotalTime()
	{
		return totalTime;
	}

	public void setTotalTime(float totalTime)
	{
		this.totalTime = totalTime;
	}
}

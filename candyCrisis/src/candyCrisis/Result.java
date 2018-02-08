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
	private String pathHistory;
	private float totalTime;
	
	public Result(String pathHistory, float totalTime)
	{
		this.pathHistory = pathHistory;
		this.totalTime = totalTime;
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

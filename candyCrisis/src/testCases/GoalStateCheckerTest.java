package testCases;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import candyCrisis.GoalStateChecker;
import candyCrisis.InputInterface;

public class GoalStateCheckerTest {

	@Test
	public void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		InputInterface.updateBoard('D');
		InputInterface.updateBoard('R');
		InputInterface.updateBoard('D');
		InputInterface.updateBoard('R');
		InputInterface.updateBoard('U');
		assertEquals(true,GoalStateChecker.isGoalState());
	}

}

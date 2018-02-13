package testCases;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import candyCrisis.InputInterface;
import candyCrisis.LegalActionChecker;

public class LegalActionCheckerTest {

	@Test
	public void testUp() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
		InputInterface.updateBoard('R');	//causes the board to be loaded due to static obj on the interface, any action will cause this
		assertEquals(true,LegalActionChecker.isLegalAction('U'));
		InputInterface.updateBoard('U');
		InputInterface.updateBoard('U');
		assertEquals(false,LegalActionChecker.isLegalAction('U'));
	}
	@Test
	public void testLeft() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
		InputInterface.updateBoard('L');	//causes the board to be loaded due to static obj on the interface, any action will cause this
		assertEquals(true,LegalActionChecker.isLegalAction('L'));
		InputInterface.updateBoard('L');
		InputInterface.updateBoard('L');
		InputInterface.updateBoard('L');
		assertEquals(false,LegalActionChecker.isLegalAction('L'));
	}
	@Test
	public void testDown() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
		InputInterface.updateBoard('R');	//causes the board to be loaded due to static obj on the interface, any action will cause this
		assertEquals(true,LegalActionChecker.isLegalAction('D'));
		InputInterface.updateBoard('D');
		InputInterface.updateBoard('D');
		assertEquals(false,LegalActionChecker.isLegalAction('D'));
	}
	@Test
	public void testRight() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
	
		InputInterface.updateBoard('R');	//causes the board to be loaded due to static obj on the interface, any action will cause this
		assertEquals(true,LegalActionChecker.isLegalAction('R'));
		InputInterface.updateBoard('R');
		InputInterface.updateBoard('R');
		InputInterface.updateBoard('R');
		assertEquals(false,LegalActionChecker.isLegalAction('R'));
	}

}

package GUI;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestController {
	GuiLogin guiLoginObj = new GuiLogin();
	Controller cntObj = new Controller();
	boolean test;

	@Test
	public void testCheck() {
		test = cntObj.check("vlad");
		assertEquals(true,test);
	}
	@Test
	public void testCheck2(){
		test = cntObj.check("Andrei");
		assertEquals(false,test);
	}

}

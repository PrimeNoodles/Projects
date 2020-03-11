package edu.cuny.brooklyn.project;

import static org.junit.Assert.*;
import edu.cuny.brooklyn.project.puzzler.*;
import org.junit.Assert;
import org.junit.*;
import edu.cuny.brooklyn.project.treasure.*;
/**
 * Unit test for simple App.
 */
 // TODO: HAD TO MAKE SIZE PUBLIC TO CHECK TESTING
 // SHOULD USE ACCESSOR FUNCTIONS INSTEAD
public class AppTest {

	/**
	 * Rigourous Test :-)
	 */
	@Test
	public void testApp() {
		assertTrue(true);
	}

	@Test
	public void testCircleTreasure() {
		CircleTreasure circletreasure = new CircleTreasure(5);
		// Assert.assertNotNull(circletreasure);
		// Why assert that it is not null?
		Assert.assertEquals(circletreasure.size, 5);
		Assert.assertTrue(circletreasure.isTreasureCell(1, 1));

	}

	@Test
	public void testRectangleTreasure() {
		RectangleTreasure rectangletreasure = new RectangleTreasure(5);
		// Assert.assertNotNull(rectangletreasure);
		// Why assert that it is not null?
		Assert.assertEquals(rectangletreasure.size, 5);
		Assert.assertTrue(rectangletreasure.isTreasureCell(1, 1));

	}

	@Test
	public void testTriangleTreasure() {
		TriangleTreasure triangletreasure = new TriangleTreasure(5);
		// Assert.assertNotNull(triangletreasure);
		// Why assertNotNull?
		Assert.assertEquals(triangletreasure.size, 5);
		Assert.assertTrue(triangletreasure.isTreasureCell(1, 1));

	}

	@Test
	public void testPresRiddle() {
		PresRiddlePuzzler presRiddle = new PresRiddlePuzzler();
		Assert.assertEquals(presRiddle.presQ.length, 45);			// 45 presidents
		Assert.assertEquals(presRiddle.presQ[0], "Washington");	//1-1 = 0
		Assert.assertEquals(presRiddle.presQ[44], "Trump");		// 45-1 = 44

	}


	@Test
	public void testSizeRiddle() {
		SizeRiddlePuzzler sizeRiddle = new SizeRiddlePuzzler(0.1);
		// the 2 arrays must be the same size since they depend on eachother
		Assert.assertEquals(sizeRiddle.obby.length, sizeRiddle.obbyLength.length);		// arrays of same length
		Assert.assertEquals(sizeRiddle.obby[3], "a professional basket ball court");	// message of 3rd array
		Assert.assertEquals(sizeRiddle.obbyLength[3], 1228, (1228 * 0.1));				// error margin (the delta) of 1228 * 0.1 = 122.8
		Assert.assertEquals(sizeRiddle.obby[7], "a credit card");						// message of 7th array
		Assert.assertEquals(sizeRiddle.obbyLength[7], 3.375, (3.375 * 0.1));			// error margin (the delta) of 3.375 * 0.1 = 0.3375
	}


	@Test
	public void testExpntRiddle() {
		ExpntMathPuzzler expntPuzzler = new ExpntMathPuzzler();
		double ans = Math.pow(expntPuzzler.base, expntPuzzler.exp);
		double correctObjAns = expntPuzzler.correctAns;
		Assert.assertEquals(ans, correctObjAns, 0);
		// the base raised to the exponent should be the correct ans
	}

}

package edu.cuny.brooklyn.project.puzzler;

import java.util.Random;

public class PuzzlerSettings {
	public final static int UNSUPPORTED_PUZZLER = -1;

	// Math puzzles are the 100s
	public final static int MATH_PUZZLER = 100;
	public final static int MATH_PUZZLER_SQRT = 101;
	public final static int MATH_PUZZLER_EXPNT = 102;
	// riddles are the 200s
	public final static int RIDDLE_PUZZLER = 200;
	public final static int RIDDLE_PUZZLER_PRES = 201;
	public final static int RIDDLE_PUZZLER_SIZE = 202;

	// array to hold puzzle types int values
	private static int[] puzzlerTypes = {
		MATH_PUZZLER_SQRT, MATH_PUZZLER_EXPNT, RIDDLE_PUZZLER_SIZE, RIDDLE_PUZZLER_PRES
	};

	private static Random rng = new Random();

	public static int getRandomPuzzlerType() {
		int typeIndex = rng.nextInt(puzzlerTypes.length);
		return puzzlerTypes[typeIndex];
	}
}

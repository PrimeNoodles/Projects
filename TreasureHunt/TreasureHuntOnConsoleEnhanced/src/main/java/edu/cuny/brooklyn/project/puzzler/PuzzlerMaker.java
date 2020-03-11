package edu.cuny.brooklyn.project.puzzler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PuzzlerMaker {
	private final static Logger LOGGER = LoggerFactory.getLogger(PuzzlerMaker.class);

	public Puzzler make() {
		int type = PuzzlerSettings.getRandomPuzzlerType();
		LOGGER.debug("Puzzler type = " + type);
		Puzzler puzzler;
		switch(type) {
		// sqrt puzzle
		case PuzzlerSettings.MATH_PUZZLER_SQRT:
			puzzler = new SqrtMathPuzzler(5, 15, 0.05);
			LOGGER.debug("Made a square root puzzler: message = " + puzzler.getMessage() + " and answer = " + puzzler.getAnswer());
			break;

		// exp puzzle
		case PuzzlerSettings.MATH_PUZZLER_EXPNT:
			puzzler = new ExpntMathPuzzler();
			LOGGER.debug("Made a math puzzler: message = " + puzzler.getMessage() + " and answer = " + puzzler.getAnswer());
			break;

		// pres puzzler
		case PuzzlerSettings.RIDDLE_PUZZLER_PRES:
			puzzler = new PresRiddlePuzzler();
			LOGGER.debug("Made a presidential riddle puzzler: message = " + puzzler.getMessage() + " and answer = " + puzzler.getAnswer());
			break;

			// size puzzle
		case PuzzlerSettings.RIDDLE_PUZZLER_SIZE:
			puzzler = new SizeRiddlePuzzler(0.1);
			LOGGER.debug("Made a size riddle puzzler: message = " + puzzler.getMessage() + " and answer = " + puzzler.getAnswer());
			break;
		default:
			LOGGER.error("Unsupported puzzler type = " + type);
			puzzler = null;
			throw new RuntimeException("Selected a non-existing puzzler type. This should never happen.");
		}
		return puzzler;
	}
}

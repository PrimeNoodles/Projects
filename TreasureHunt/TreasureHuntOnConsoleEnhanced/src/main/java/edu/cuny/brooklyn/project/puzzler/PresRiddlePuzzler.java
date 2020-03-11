package edu.cuny.brooklyn.project.puzzler;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PresRiddlePuzzler extends RiddlePuzzler {
	private final static Logger LOGGER = LoggerFactory.getLogger(PresRiddlePuzzler.class);
	private int answerValue;
	public String[] presQ;

	// Cleaveland ran for president in 2 different terms
	// Also differentiated between the different fathers & sons that ran,
	// and presidents with the same last name
	public PresRiddlePuzzler() {
		presQ = new String[]{"Washington", "J. Adams, Sr.", "Jefferson", "Madison", "Monroe", "J. Q. Adams, Jr.", "Jackson", "Buren", "Harrison", "Tyler", "Polk", "Taylor", "Fillmore", "Pierce", "Buchanan", "Lincoln", "A. Johnson", "Grant", "Hayes", "Garfield", "Arthur", "Cleveland (1)", "Harrison", "Cleveland (2)", "McKinley", "T. Roosevelt", "Taft", "Wilson", "Harding", "Coolidge", "Hoover", "F. D. Roosevelt", "Truman", "Eisenhower", "Kennedy", "L. B. Johnson", "Nixon", "Ford", "Carter", "Reagan", "G. H.W. Bush, Sr.", "Clinton", "G. W. Bush, Jr.", "Obama", "Trump"};

		Random rng = new Random();

		// Creates a random integer which is then assigned to the string array presQ. The true answer is the rng integer + 1
		// next int makes values from 0 to 45 (exclusive)
		// added one so the answ can range from 1 to 46 (user will enter no.s from 1 - 46)
		answerValue = rng.nextInt(45) + 1;

		// I believe these are used in the RiddlePuzzler Class or the Puzzler Class
		// -1 to move left 1 index. Ex. Washington is ansVal 1. So hes at 1-1 = 0, loc. 0
		String message = "What number president is " + presQ[answerValue - 1] + "?" ;
		String answer = Integer.toString(answerValue);
		setMessage(message);
		setAnswer(answer);

		//cheat code
		// System.out.println("answer is " + (answerValue));
	}

	public boolean isCorrect(String enteredAnswer) {
		int entered = Integer.parseInt(enteredAnswer);
		// changed to minus 1, so for ex pres Washington is 1-1 which is index 0
		if(entered == answerValue) {
			LOGGER.debug("Correct answer");
			return true;
		}
		else {
			LOGGER.debug("Incorrect answer");
			return false;
		}
	}
}

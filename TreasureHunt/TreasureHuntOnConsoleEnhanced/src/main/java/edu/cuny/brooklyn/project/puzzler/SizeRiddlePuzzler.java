package edu.cuny.brooklyn.project.puzzler;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SizeRiddlePuzzler extends RiddlePuzzler {
	private final static Logger LOGGER = LoggerFactory.getLogger(SizeRiddlePuzzler.class);
	private double toleratedAnswerRelativeError;
	private double answerValue;
	private Random rng;
	private int number;
	private final int lim = 10;		// max obj in the arr
//	public double variedTARE[];
	public String[] obby;
	public double obbyLength[];


	public SizeRiddlePuzzler(double inputError) {
		// the message displayed for each object type
		obby = new String[lim];
		obby[0] = "a football field";
		obby[1] = "the average car length";
		obby[2] = "a rectangular tissue box";
		obby[3] = "a professional basket ball court";
		obby[4] = "a piece of printer paper";
		obby[5] = "the average dvd disc";
		obby[6] = "the average index card";
		obby[7] = "a credit card";
		obby[8] = "a quarter";
		obby[9] = "any dollar bill";

		obbyLength = new double[lim];
		obbyLength[0] = 3600;
		obbyLength[1] = 183;
		obbyLength[2] = 4.5;
		obbyLength[3] = 1228;
		obbyLength[4] = 8.25;
		obbyLength[5] = 4.7;
		obbyLength[6] = 5;
		obbyLength[7] = 3.375;
		obbyLength[8] = 0.955;
		obbyLength[9] = 6.14;

		// the amount of tolerated answer relative error of each object type
/*		variedTARE = new double[lim];
		variedTARE[0] = 100;
		variedTARE[1] = 7;
		variedTARE[2] = 0.51;
		variedTARE[3] = 100;
		variedTARE[4] = 0.25;
		variedTARE[5] = 0.3;
		variedTARE[6] = 1;
		variedTARE[7] = 0.125;
		variedTARE[8] = 0.1;
		variedTARE[9] = 0.15;
*/


		// the array is manually sorted in order,
		// obby[0], obbyLength[0], variedTARE[0] all belongs together, etc

		rng = new Random();
		number = rng.nextInt(10);

		// I believe these are used in the RiddlePuzzler Class or the Puzzler Class
		String message = "How long is " + obby[number] + " in inches ?";
		answerValue = obbyLength[number];					// correct ans
		String answer = Double.toString(answerValue);		// correct ans to a string
		toleratedAnswerRelativeError = answerValue * inputError; 	// value of tolerated answer relative error
		setMessage(message);
		setAnswer(answer);

		//cheat code
		// System.out.println("answer is " + obbyLength[number]);
		// System.out.println("tolerable answer relative error is " + toleratedAnswerRelativeError);
	}

	public boolean isCorrect(String enteredAnswer) {
		double entered = Double.parseDouble(enteredAnswer);
		// if absolute value of (your answer - exact length) < relative error, then true
		if (Math.abs((entered - answerValue)) < toleratedAnswerRelativeError) {
		LOGGER.debug("Correct answer");
			return true;
		} else {
			LOGGER.debug("Incorrect answer");
			return false;
		}
	}
}

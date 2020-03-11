package edu.cuny.brooklyn.project.puzzler;
import java.util.Random;
import java.lang.Math;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpntMathPuzzler extends MathPuzzler {
    private final static Logger LOGGER = LoggerFactory.getLogger(ExpntMathPuzzler.class);
    String message;
    String answer;
    public int base;
    public int exp;
    public double correctAns;

    public ExpntMathPuzzler()
    {
        Random rng = new Random();
        base = rng.nextInt(9) + 2;
        exp = rng.nextInt(1) + 2;

        message = "(" + base + ") raised to the power of (" + exp + ") = ?";
        correctAns = Math.pow(base, exp);
        answer = Double.toString(correctAns);
        setMessage(message);
        setAnswer(answer);

        // prints ans for debugging
       // System.out.println("Ans: " + answer);
    }

    public boolean isCorrect(String enteredAnswer) {
        double entered = Double.parseDouble(enteredAnswer);
        if (entered == correctAns) {
            LOGGER.debug("Correct answer");
            return true;
        } else {
            LOGGER.debug("Incorrect answer");
            return false;
        }
    }

}

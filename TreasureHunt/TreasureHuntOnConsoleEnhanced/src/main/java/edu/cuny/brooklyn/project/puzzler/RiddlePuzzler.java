package edu.cuny.brooklyn.project.puzzler;

public abstract class RiddlePuzzler extends Puzzler {
	public RiddlePuzzler() {
		this(null, null);
	}
	
	public RiddlePuzzler(String message, String answer) {
		super(message, answer, PuzzlerSettings.RIDDLE_PUZZLER);
	}
}

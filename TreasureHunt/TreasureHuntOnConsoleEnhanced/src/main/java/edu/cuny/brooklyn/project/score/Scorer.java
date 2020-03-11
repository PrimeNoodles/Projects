package edu.cuny.brooklyn.project.score;

import edu.cuny.brooklyn.project.GameSettings;

public class Scorer {

	private int totalScore;
	private int roundScore;
	private int level;
	
	public Scorer() {
		totalScore = 0;
		roundScore = 0;
		level=1;
	}
	
	public int getTotalScore() {
		return totalScore;
	}

	public int getRoundScore() {
		return roundScore;
	}

	public void updateScore(int attempts) {
		roundScore =  GameSettings.MAX_SCORE - (attempts - 1) * GameSettings.SCORE_PENALTY;
		totalScore += roundScore;
		level++;
	}

	public int getLevel() {
		return level;
	}

}

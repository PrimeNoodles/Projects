package edu.cuny.brooklyn.project.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.frame.Coordinates;
import edu.cuny.brooklyn.project.frame.GameFrame;
import edu.cuny.brooklyn.project.frame.TreasureClue;
import edu.cuny.brooklyn.project.puzzler.Puzzler;
import edu.cuny.brooklyn.project.puzzler.PuzzlerMaker;
import edu.cuny.brooklyn.project.score.Scorer;
import edu.cuny.brooklyn.project.treasure.TreasureGenerator;

public class GameController {
	private final static Logger LOGGER = LoggerFactory.getLogger(GameController.class);
	
	GameFrame gameFrame;
	Scorer scorer = new Scorer();
	PuzzlerMaker puzzlerMaker = new PuzzlerMaker();
	Scanner scan = new Scanner(System.in);
	
	public GameController() {
		gameFrame = new GameFrame();
		scorer = new Scorer();
		puzzlerMaker = new PuzzlerMaker();
	}
	
	public void runTheGame() {
		TreasureGenerator treasureSelector = new TreasureGenerator();
		
		boolean toExit = true;
		
		do {
			gameFrame.getTreasureField().placeTreasure(treasureSelector.generate());
			int attempts = solvePuzzler();
			
			String clue = TreasureClue.getClue(gameFrame.getTreasureField().getTreasureXLeft(),
					gameFrame.getTreasureField().getTreasureYTop(),
					gameFrame.getTreasureField().getTreasureBoundingBoxWidth(),
					gameFrame.getTreasureField().getTreasureBoundingBoxLength(),
					attempts);
			attempts = locateTreasure(clue);
			
			scorer.updateScore(attempts);
			LOGGER.debug("RoundScore = " + scorer.getRoundScore() + " TotalScore = " + scorer.getTotalScore());
			
			toExit = delcareEndOfRun();
		} while (!toExit);
	}
	
	private int solvePuzzler() {
		int attempts = 0;
		String answer = "";
		Puzzler puzzler = puzzlerMaker.make();
		do {
			gameFrame.getIoWindow().paintPuzzler(puzzler.getMessage());
			gameFrame.updateDisplayIoWindow();
			gameFrame.display();
			answer = gameFrame.getIoWindow().answerPuzzler();
			attempts ++;
		} while (!puzzler.isCorrect(answer));
		
		LOGGER.debug("User solved the puzzler in " + attempts + " steps.");

		return attempts;
	}
	
	private int locateTreasure(String clue) {
		int attempts = 0;
		Coordinates coordinates;
		do {
			gameFrame.getIoWindow().paintClue(clue);
			gameFrame.updateDisplayIoWindow();
			gameFrame.display();
			coordinates = gameFrame.getIoWindow().locateTreasure();
			attempts ++;
		} while (!gameFrame.getTreasureField().foundTreasure(coordinates.getX(), coordinates.getY()));
		
		LOGGER.debug("User located the treasure in" + attempts + " attempts.");
		return attempts;
	}
	
	private boolean askToContinue() {
		boolean toContinue=true;
		boolean tryAgain=true;
		String answer;
		answer=scan.next();
		
		while(tryAgain) {
			if (answer.equalsIgnoreCase("y")) { 
				toContinue = false;
				tryAgain=false;
			}
			else if (answer.equalsIgnoreCase("n")) {
				toContinue = true;
				tryAgain=false;
			}
			else {
				System.out.println("Incorrect Entry");
				System.out.println("Enter y or n");
				answer=scan.next();
				tryAgain = true;
			}
		}
		return toContinue;
	}
	
	private boolean delcareEndOfRun() {
		// TODO: allow users to enter y or n. If yes, return false
		gameFrame.getTreasureField().showTreasure(); // paint the treasure
		gameFrame.updateDisplayTreasureField();
		gameFrame.getIoWindow().paintCongratulatoryMessage();
		gameFrame.updateDisplayIoWindow();
		gameFrame.display();
		
		boolean toContinue = askToContinue();
		
		return toContinue;
	}
}

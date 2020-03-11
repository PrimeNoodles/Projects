/**
 * The start-up code for the 1st class project of a sequence 5 projects in CISC 3120 
 * Sections MW2 and MW8 CUNY Brooklyn College. The project should result a simple 
 * text-based game application. 
 * 
 * Spring 2018 
 */

package edu.cuny.brooklyn.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.controller.GameController;

public class TreasureHuntConsoleApp {
	private final static Logger LOGGER = LoggerFactory.getLogger(TreasureHuntConsoleApp.class);
	
	public static void main(String[] args) {
		LOGGER.info("TreasureHuntConsoleApp started.");
		
		GameController controller = new GameController();
		controller.runTheGame();
		
		LOGGER.info("TreasureHuntConsoleApp exits.");
	}
}
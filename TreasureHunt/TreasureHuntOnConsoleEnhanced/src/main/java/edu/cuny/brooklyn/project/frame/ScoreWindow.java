package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;

public class ScoreWindow {
	private int width;
	private int height;
	private char[][] window;

	public ScoreWindow() {
		height = GameSettings.DEFAULT_SCORE_WINDOW_HEIGHT;
		width = GameSettings.DEFAULT_SCORE_WINDOW_WIDTH;
		window = new char[width][height];
	}
	
	public char getCharAt(int x, int y) {
		return window[y][x];
	}


	public int getHeight() {
		return height;
	}	
	

	public int getWidth() {
		return width;
	}

}

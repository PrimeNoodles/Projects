package edu.cuny.brooklyn.project.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameFrame {
	private final static Logger LOGGER = LoggerFactory.getLogger(GameFrame.class);

	private ScoreWindow scoreWindow;
	private int xPosScoreWindow;
	private int yPosScoreWindow;

	private TreasureField treasureField;
	private int xPosTreasureField;
	private int yPosTreasureField;

	private GameIOWindow ioWindow;
	private int xPosIoWindow;
	private int yPosIoWindow;

	private int displayBufferWidth;
	private int displayBufferHeight;
	private char[][] displayBuffer;

	public GameFrame() {
		scoreWindow = new ScoreWindow();
		treasureField = new TreasureField();
		ioWindow = new GameIOWindow();

		computeDisplayHeight();
		computeDisplayWidth();
		LOGGER.debug("displayBufferHeight = " + displayBufferHeight + " displayBufferWidth = " + displayBufferWidth);
		computeComponentPositions();

		displayBuffer = new char[displayBufferHeight][displayBufferWidth];
		fillDisplayBuffer(false);
	}

	public void display() {
		for (int i = 0; i < displayBuffer.length; i++) {
			for (int j = 0; j < displayBuffer[i].length; j++) {
				System.out.print(displayBuffer[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	

	public GameIOWindow getIoWindow() {
		return ioWindow;
	}

	public TreasureField getTreasureField() {
		return treasureField;
	}

	public void updateDisplayIoWindow() {
		for (int i=0; i<ioWindow.getHeight(); i++) {
			int y = yPosIoWindow + i;
			for (int j=0; j<ioWindow.getWidth(); j++) {
				int x = xPosIoWindow + j;
				displayBuffer[y][x] = ioWindow.getChar(j, i);
			}
		}
	}


	public void updateDisplayTreasureField() {
		for (int i=0; i<treasureField.getFieldHeight(); i++) {
			int y = yPosTreasureField + i;
			for (int j=0; j<treasureField.getFieldWidth(); j++) {
				int x = xPosTreasureField + j;
				displayBuffer[y][x] = treasureField.getCharAt(j, i);
			}
		}
	}

	private void blankDisplayBuffer() {
		for (int i = 0; i < displayBuffer.length; i++) {
			for (int j = 0; j < displayBuffer[i].length; j++) {
				displayBuffer[i][j] = ' ';
			}
		}
	}

	private void computeDisplayHeight() {
		displayBufferHeight = scoreWindow.getHeight() + 2 
				+ treasureField.getFieldHeight() + 2 + ioWindow.getHeight()	+ 2;
	}

	private void computeDisplayWidth() {
		displayBufferWidth = Math.max(scoreWindow.getWidth(),
				Math.max(treasureField.getFieldWidth(), ioWindow.getWidth())) + 2;
	}

	private void computeComponentPositions() {
		xPosScoreWindow = (displayBufferWidth - scoreWindow.getWidth()) / 2;
		yPosScoreWindow = 1;
		
		xPosTreasureField = (displayBufferWidth - treasureField.getFieldWidth()) / 2;
		yPosTreasureField = yPosScoreWindow + scoreWindow.getHeight() + 2;
		
		xPosIoWindow = (displayBufferWidth - ioWindow.getWidth()) / 2;
		yPosIoWindow = yPosTreasureField + treasureField.getFieldHeight() + 2;
		
		LOGGER.debug(String.format(
				"(xPosScoreWindow, yPosScoreWindow, xPosTreasureField, yPosTreasureField, xPosIoWindow, yPosIoWindow) "
						+ " = (%d, %d, %d, %d, %d, %d)",
				xPosScoreWindow, yPosScoreWindow, xPosTreasureField, yPosTreasureField, xPosIoWindow, yPosIoWindow));
	}

	private void fillDisplayBuffer(boolean showTreasure) {
		blankDisplayBuffer();

		fillAllBorders();
	}

	private void fillAllBorders() {
		int xLeft, yTop, xRight, yBottom;
		
		xLeft = xPosScoreWindow - 1;
		xRight = xLeft + scoreWindow.getWidth() + 1;
		yTop = yPosScoreWindow - 1;
		yBottom = yTop + scoreWindow.getHeight() + 1;
		LOGGER.debug(String.format("ScoreWindow(xLeft, xRight, yTop, yBottom) = (%d, %d, %d, %d)", xLeft, xRight,
				yTop, yBottom));
		fillBorder(xLeft, yTop, xRight, yBottom);
		
		
		xLeft = xPosTreasureField - 1;
		xRight = xLeft + treasureField.getFieldWidth() + 1;
		yTop = yPosTreasureField - 1;
		yBottom = yTop + treasureField.getFieldHeight() + 1;
		LOGGER.debug(String.format("TreasureField(xLeft, xRight, yTop, yBottom) = (%d, %d, %d, %d)", xLeft, xRight,
				yTop, yBottom));
		fillBorder(xLeft, yTop, xRight, yBottom);

		xLeft = xPosIoWindow - 1;
		xRight = xLeft + ioWindow.getWidth() + 1;
		yTop = yPosIoWindow - 1;
		yBottom = yTop + ioWindow.getHeight() + 1;
		LOGGER.debug(String.format("IOWindow(xLeft, xRight, yTop, yBottom) = (%d, %d, %d, %d)", xLeft, xRight, yTop,
				yBottom));
		fillBorder(xLeft, yTop, xRight, yBottom);
	}

	private void fillBorder(int xLeft, int yTop, int xRight, int yBottom) {
		// See https://en.wikipedia.org/wiki/Box-drawing_character
		LOGGER.debug(String.format("(xLeft, yTop, xRight, yBottom) = (%d, %d, %d, %d)", xLeft, yTop, xRight, yBottom));
		LOGGER.debug(String.format("displayBuffer: height=%d, width=%d", displayBufferHeight, displayBufferWidth));
		for (int i = xLeft; i <= xRight; i++) {
			displayBuffer[yTop][i] = '\u2500';
			displayBuffer[yBottom][i] = '\u2500';
		}
		for (int i = yTop; i <= yBottom; i++) {
			displayBuffer[i][xLeft] = '\u2502';
			displayBuffer[i][xRight] = '\u2502';
		}
		displayBuffer[yTop][xLeft] = '\u250c';
		displayBuffer[yTop][xRight] = '\u2510';
		displayBuffer[yBottom][xLeft] = '\u2514';
		displayBuffer[yBottom][xRight] = '\u2518';
	}

}

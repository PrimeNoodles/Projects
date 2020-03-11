package edu.cuny.brooklyn.project.frame;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.treasure.Treasure;

public class TreasureField {
	private final static Logger LOGGER = LoggerFactory.getLogger(TreasureField.class);
	
	private int fieldWidth;
	private int fieldHeight;
	private char[][] field;
	
	private Treasure treasure;
	private int xLeft; /* position of the top left corder of the treasure bounding box */ 
	private int yTop;  /* position of the top left corner of the treasure bounding box */ 
	
	private Random rng;
	
	public TreasureField() {
		fieldWidth = GameSettings.FIELD_WIDTH;
		fieldHeight = GameSettings.FIELD_HEIGHT;
		field = new char[fieldHeight][fieldWidth];
		
		clear();	
		
		treasure = null;
		rng = new Random();
	}
	

	public boolean foundTreasure(int x, int y) {
		if (x >= fieldWidth || x < 0 || y >= fieldHeight || y < 0) {
			LOGGER.debug("User enter a location out of bound, not a treasure obviously.");
			return false;
		}
		
		LOGGER.debug("User's guess (x, y) = (" + x + ", " + y + ")");
		int xRight = xLeft + treasure.getBoundingBoxWidth();
		int yBottom = yTop + treasure.getBoundingBoxLength();
		LOGGER.debug("Treasure is bounded at (xLeft, yTop) -- (xRight, yBottom) = "
				+ "(" + xLeft + ", " + yTop + ") -- " 
				+ "(" + xRight + ", " + yBottom + ")");
		int xOffset = x - xLeft;
		int yOffset = y - yTop;
		LOGGER.debug("Treasure cell at (x, y) = (" + x + "," + y + ")");
		return treasure.isTreasureCell(xOffset, yOffset);
	}



	public char getCharAt(int x, int y) {
		return field[y][x];
	}
	
	public int getFieldWidth() {
		return fieldWidth;
	}
	
	public int getFieldHeight() {
		return fieldHeight;
	}
	
	public int getTreasureXLeft() {
		return xLeft;
	}

	public int getTreasureYTop() {
		return yTop;
	}
	
	public int getTreasureBoundingBoxLength() {
		return treasure.getBoundingBoxLength();
	}
	
	public int getTreasureBoundingBoxWidth() {
		return treasure.getBoundingBoxWidth();
	}
	
	public void placeTreasure(Treasure treasure) {
		this.treasure = treasure;
		int availableWidth = fieldWidth - treasure.getBoundingBoxLength();
		int availableHeight = fieldHeight - treasure.getBoundingBoxWidth();
		//Position of the treasure is randomly generated
		xLeft = rng.nextInt(availableWidth);
		yTop = rng.nextInt(availableHeight);
		int xRight = xLeft + treasure.getBoundingBoxWidth();
		int yBottom = yTop + treasure.getBoundingBoxLength();
		LOGGER.debug("Treasure is placed and bounded at (xLeft, yTop) -- (xRight, yBottom) = "
				+ "(" + xLeft + ", " + yTop + ") -- " 
				+ "(" + xRight + ", " + yBottom + ")");
	}


	public void showTreasure() {
		clear();
		int xPos = getTreasureXLeft();
		int yPos = getTreasureYTop();
		for (int i=0; i<treasure.getBoundingBoxLength(); i++) { // y
			for (int j=0; j<treasure.getBoundingBoxWidth(); j++) { // x
				if (treasure.isTreasureCell(j, i)) {
					int value = treasure.getValueAt(j, i);
					char c = '+';
					if (value >=0 && value <= 9) {
						c = (Integer.toString(value)).charAt(0);
					} else if (value < 0) {
						c = '-';
					}
					LOGGER.debug("Field Size (height, width) = (" + fieldHeight + ", " + fieldWidth + ")");
					LOGGER.debug("Treasure Position (xLeft, yTop) = (" + xLeft + ", " + yTop + ")");
					LOGGER.debug("Treasure Boundingbox Size (width, length) = " + "(" + treasure.getBoundingBoxWidth() + ", " + treasure.getBoundingBoxLength() + ")");
					LOGGER.debug("Treasure Cell at Field (x, y) = (" + (xLeft + j) + ", " + (yTop + i) + ")");
					field[yPos + i][xPos + j] = c;
				}
			}
		}
	}

	private void clear() {
		for (int i=0; i<field.length; i++) {
			for (int j=0; j<field[i].length; j++) {
				field[i][j] = GameSettings.FIELD_EMPTY;
			}
		}
	}

}

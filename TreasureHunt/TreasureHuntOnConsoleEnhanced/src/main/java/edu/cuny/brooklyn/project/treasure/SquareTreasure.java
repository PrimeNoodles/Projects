package edu.cuny.brooklyn.project.treasure;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.score.Scorer;

public class SquareTreasure extends Treasure implements TreasureValueSetter {
	private int[][] treasure;
	private int size;
	private Scorer levelScore = new Scorer();
	
	public SquareTreasure(int size, TreasureValueSetter setter) {
		this.size = size;
		treasure = new int[size][size];
		for (int i=0; i<treasure.length; i++) {
			for (int j=0; j<treasure[i].length; j++) {
				int cellValue = setter.getCellValue();
				treasure[i][j] = cellValue;
				setTotalValue(getTotalValue() + cellValue);
			}
		}		
	}
	
	public SquareTreasure(int size) {
		this(size, () -> GameSettings.DEFAULT_TREASURE_VALUE);
	}

	@Override
	public int getValueAt(int x, int y) {
		return treasure[y][x];
	}

	@Override
	public int getBoundingBoxWidth() {
		return size;
	}

	@Override
	public int getBoundingBoxLength() {
		return size;
	}

	@Override
	public boolean isTreasureCell(int x, int y) {
		return treasure[y][x] > 0;
	}

	@Override
	//Makes the cell value the value of the level the user is on
	public int getCellValue() {
		return levelScore.getLevel();
	}
}

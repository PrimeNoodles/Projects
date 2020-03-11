package edu.cuny.brooklyn.project.treasure;

public abstract class Treasure {
	/*
	 * Treasure is bounded in a box
	 * +---------------------+------------------>X
	 * |333312111111111113444|     ^
	 * |        2222         |    length
	 * |          2          |     V
	 * +---------------------+------
	 * |                     |
	 * |<-----width--------->|
	 * |
	 * |
	 * V Y
	 */
	private int totalValue;
	
	public abstract int getBoundingBoxWidth();

	public abstract int getBoundingBoxLength();

	public abstract int getValueAt(int x, int y);
	
	public abstract boolean isTreasureCell(int x, int y);
	
	public int getTotalValue() {
		return totalValue;
	}

	void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
}

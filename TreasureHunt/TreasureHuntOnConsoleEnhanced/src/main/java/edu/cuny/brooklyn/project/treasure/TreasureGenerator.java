package edu.cuny.brooklyn.project.treasure;

import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;

import edu.cuny.brooklyn.project.GameSettings;

public class TreasureGenerator {
	public Treasure generate() {
		Random rng = new Random();
		int shape = rng.nextInt(4);
		int size = rng.nextInt(12);
		size += 2; //generates a random number between 2 and 14 for the size
		switch (shape) {
		case 0:
			return new SquareTreasure(size);
		case 1:
			return new CircleTreasure(size);
		case 2:
			return new RectangleTreasure(size);
		case 3:
			return new TriangleTreasure(size);
		}
		//Default case
		return new SquareTreasure(GameSettings.DEFAULT_TREASURE_SIZE);
	}
}

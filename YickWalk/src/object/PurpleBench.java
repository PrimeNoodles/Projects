package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class PurpleBench extends ObjectManager{
	public PurpleBench() {
		name = "Bench";
		
		//Collision area
		//Use collisionAreaX instead of collisionArea.x because this is the default x value position
		collisionAreaX = 2;
		collisionAreaY = 28;
		collisionArea.width = 58;
		collisionArea.height = 30;

		
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/BenchPurple.png"));
		}catch(IOException e) {
			System.out.println("Object Bench Error");
			e.printStackTrace();
		}
	}
}

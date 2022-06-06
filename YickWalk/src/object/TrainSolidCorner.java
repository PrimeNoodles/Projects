package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class TrainSolidCorner extends ObjectManager{
	public TrainSolidCorner() {
		name = "TrainSolidCorner";
		
		//Collision area
		//Use collisionAreaX instead of collisionArea.x because this is the default x value position
		collisionAreaX = 0;
		collisionAreaY = 0;
		collisionArea.width = 64;
		collisionArea.height = 64;

		
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/TrainSolidCorner.png"));
		}catch(IOException e) {
			System.out.println("Object Train Error");
			e.printStackTrace();
		}
	}
}

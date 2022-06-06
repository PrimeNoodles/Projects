package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class VendingMachine extends ObjectManager{
	public VendingMachine() {
		name = "VendingMachine";
		
		//Collision area
		//Use collisionAreaX instead of collisionArea.x because this is the default x value position
		collisionAreaX = 0;
		collisionAreaY = 2;
		collisionArea.width = 62;
		collisionArea.height = 60;
		
		collision = true;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/VendingMachine.png"));
		}catch(IOException e) {
			System.out.println("Object Vending Machine Error");
			e.printStackTrace();
		}
	}
}
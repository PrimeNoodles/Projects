package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	//The super class that stores the player and other interactables
	
	public int worldX;
	public int worldY;
	public int speed;
	
	public BufferedImage[] imageSet = new BufferedImage[100]; 
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle collisionArea;

	public int collisionAreaX;
	public int collisionAreaY;
	public boolean collisionOn = false;
}

package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class ObjectManager {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX;
	public int worldY;
	
	public Rectangle collisionArea = new Rectangle();
	public int collisionAreaX;
	public int collisionAreaY;
	
	public void draw(Graphics2D g2d, GamePanel gamePanel) {

		int screenX = worldX - gamePanel.yick.worldX + gamePanel.yick.screenX;
		int screenY = worldY - gamePanel.yick.worldY + gamePanel.yick.screenY;
		
		//This draws the screen close to the camera, not the entire map to improve rendering efficiency
		if(worldX + gamePanel.unitSize > gamePanel.yick.worldX - gamePanel.yick.screenX && 
				worldX - gamePanel.unitSize < gamePanel.yick.worldX + gamePanel.yick.screenX && 
				worldY + gamePanel.unitSize > gamePanel.yick.worldY - gamePanel.yick.screenY && 
				worldY - gamePanel.unitSize < gamePanel.yick.worldY + gamePanel.yick.screenY)
		g2d.drawImage(image, screenX, screenY, gamePanel.unitSize, gamePanel.unitSize, null);
	}
}

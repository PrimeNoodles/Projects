package oldcode;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Wall {
	
	public BufferedImage image;
	public boolean collision = true;
	
	GamePanel gamePanel;
	
	public Wall(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		getWall();
		
	}
	
	public void getWall() {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/mapSet/Wall.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.drawImage(image, 0, 0, 512, 96, null);
	}
}

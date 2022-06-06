package oldcode;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class MapBackground {
	GamePanel gamePanel;
	public BufferedImage image;
	
	public MapBackground(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		getMapBackground();
		
	}
	
	public void getMapBackground() {
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/mapSet/Map.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2d) {
		
		g2d.drawImage(image, 0, 0, 512, 384, null);
	}
}

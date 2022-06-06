package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UserInterface {
	GamePanel gamePanel;
	public BufferedImage healthImage;
	
	public UserInterface(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		getUserInterface();
	}

	public void getUserInterface() {
		try {
			healthImage = ImageIO.read(getClass().getResourceAsStream("/userinterface/UI.png"));
		}
		catch(IOException e){
			System.out.println("User Interface bug");
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(healthImage, gamePanel.unitSize/2, gamePanel.unitSize/2, gamePanel.unitSize*2, gamePanel.unitSize, null);
	}
}

package oldcode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.KeyInput;

public class OldGamePanelv2 extends JPanel implements Runnable{
	
	//Size of each individual sprite
	final int spriteSize = 32;
	//Scale at which to multiply the sprite
	final int scale = 1;
	//Gives the final size of the game
	final int unitSize = spriteSize * scale;
	//Height and Width of screen
	final int screenHeight = spriteSize * 16;
	final int screenWidth = spriteSize * 12;
	
	//Imports the key press inputs
	KeyInput keyP = new KeyInput();
	//This keeps the program running constantly refreshing the screen to show animations
	Thread gameThread;
	
	//The game frames per second; the speed
	int FPS = 60;
	
	//Set player's default position
	int yickX = 100;
	int yickY = 100;
	int yickSpeed = 3;
	
	public OldGamePanelv2() {
		
		this.setPreferredSize(new Dimension(screenHeight, screenWidth));
		this.setBackground(Color.black);
		//Helps rendering the screen
		this.setDoubleBuffered(true);
		this.addKeyListener(keyP);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		//Passing gamePanel class to this thread constructor, instantiating the thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		//This sets the frames per second
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				//Update character position
				update();
				//Update screen with new animations etc
				repaint();
				//This creates the refresh rate
				delta--;
			}
		}
	}
	
	public void update() {
		
		//Check for key press update and makes the logic on character movement
		if(keyP.pressUp == true) {
			yickY -= yickSpeed;
		}
		else if(keyP.pressDown == true) {
			yickY += yickSpeed;
		}
		else if(keyP.pressLeft == true) {
			yickX -= yickSpeed;
		}
		else if(keyP.pressRight == true) {
			yickX += yickSpeed;
		}
	}
	
	//Built in method in JPanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		
		//Square Character
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(yickX, yickY, unitSize, unitSize);
		
		//Saves memory by releasing the graphics from any system using it if it is not in use
		g2.dispose();
	}





}










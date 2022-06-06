package oldcode;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import main.KeyInput;

public class OldGamePanel extends JPanel implements Runnable{
	
	//Size of each individual sprite
	final int spriteSize = 32;
	//Scale at which to multiply the sprite
	final int scale = 3;
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
	
	public OldGamePanel() {
		
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
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			//Update character position
			update();
			//Update screen with new animations etc
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
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
		g2.fillRect(yickX, yickY, spriteSize, spriteSize);
		
		//Saves memory by releasing the graphics from any system using it if it is not in use
		g2.dispose();
	}





}










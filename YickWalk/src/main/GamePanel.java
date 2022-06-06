package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Yick;
import object.ObjectManager;
import tile.TileSet;

public class GamePanel extends JPanel implements Runnable{
	
	//Size of each individual sprite
	final int spriteSize = 32;
	//Scale at which to multiply the sprite
	final int scale = 2;
	//Gives the final size of the game
	public final int unitSize = spriteSize * scale;
	//Column and Row values of screen
	public final int maxScreenColumn = 16;
	public final int maxScreenRow =	12;
	//Height and Width of screen
	public final int screenHeight = unitSize * maxScreenRow;
	public final int screenWidth = unitSize * maxScreenColumn;
	//Column and Row values of World
	public final int maxWorldColumn = 10;
	public final int maxWorldRow = 10;

	//Imports the key press inputs
	KeyInput keyP = new KeyInput();
	//Loads and uses sound + music
	Sound soundEffect = new Sound();
	Sound soundMusic = new Sound();
	//Checks for collisions
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	
	//Puts values for objects
	public ObjectAssign objectAssign = new ObjectAssign(this);
	//Imports the Yick Player entity
	public Yick yick = new Yick(this, keyP);
	//Imports the object set
	public ObjectManager objectManager[] = new ObjectManager[100];
	//Imports the background
	TileSet tileSet = new TileSet(this);
	//Imports UI
	UserInterface userInterface = new UserInterface(this);
	
	//The game frames per second; the speed
	int FPS = 60;
	//This keeps the program running constantly refreshing the screen to show animations
	Thread gameThread;

	
	public GamePanel() {
		//Sets the window resolution
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		//Temporary sets background to black
		this.setBackground(Color.black);
		//Helps rendering the screen
		this.setDoubleBuffered(true);
		this.addKeyListener(keyP);
		this.setFocusable(true);
	}
	
	//Must run before game start
	public void gameSet() {
		objectAssign.setObject();
		playMusic(0);
	}
	
	public void startGameThread() {
		//Passing gamePanel class to this thread constructor, instantiating the thread
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		//This sets the frames per second
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			//Sets up the constant updated screen
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta >= 1) {
				//Refocuses the window so that it can update/accept keypress
				requestFocusInWindow();
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
		yick.update();
	}	
	//Built in method in JPanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		Graphics2D g2d = (Graphics2D)g;
		
		//draws the background
		tileSet.draw(g2d);
		
		//draws objects
		for(int i = 0; i < objectManager.length; i++) {
			if(objectManager[i] != null) {
				objectManager[i].draw(g2d, this);
			}
		}
		
		//draws UI
		userInterface.draw(g2d);
		
		//draws the yick
		yick.draw(g2d);
		
		//Saves memory by releasing the graphics from any system using it if it is not in use
		g2d.dispose();
	}
	//Music gets looped
	public void playMusic(int index) {
		soundMusic.setFile(index);
		soundMusic.play();
		soundMusic.loop();
	}
	public void stopMusic() {
		soundMusic.stop();
	}
	//sound effect gets played once
	public void playSound(int index) {
		soundEffect.setFile(index);
		soundEffect.play();
	}
}










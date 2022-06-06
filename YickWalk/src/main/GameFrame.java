package main;
import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public GameFrame() {
		//Lets the game close with x click
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Disables resizing window through corner drag
		this.setResizable(false);
		this.setTitle("Yick");
		
		GamePanel gamePanel = new GamePanel();
		this.add(gamePanel);
		this.pack();
		
		//Centers window to middle of screen
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		gamePanel.gameSet();
		gamePanel.startGameThread();
	}
}

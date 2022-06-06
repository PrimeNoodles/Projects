package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInput;

public class Yick extends Entity {
	
	GamePanel gamePanel;
	KeyInput keyInput;
	public final int screenX;
	public final int screenY;
	
	int idleCounter = 0;
	
	public Yick(GamePanel gamePanel, KeyInput keyInput) {
		
		this.gamePanel = gamePanel;
		this.keyInput = keyInput;
		
		//Centers camera in the middle of the screen
		screenX = gamePanel.screenWidth/2 - (gamePanel.unitSize/2);
		screenY = gamePanel.screenHeight/2 - (gamePanel.unitSize/2);
		
		//sets collision on the yick model
		collisionArea = new Rectangle();
		collisionArea.x = 17;
		collisionArea.y = 50;
		
		//Sets the default x and y collision area
		collisionAreaX = collisionArea.x;
		collisionAreaY = collisionArea.y;
		
		collisionArea.width = 29;
		collisionArea.height = 19;
		
		//set the default position of yick
		setDefaultValues();
		//loads the images of yick to be used in movement
		getYickImage();
	}
	public void setDefaultValues() {
		//Initial position of player
		worldX = gamePanel.unitSize * 2;
		worldY = gamePanel.unitSize * 2;
		speed = 2;
		direction = "down";
	}
	
	public void getYickImage() {
		try {
			//Walk Up
			imageSet[0] = ImageIO.read(getClass().getResourceAsStream("/yick/YickUp1.png"));
			imageSet[1] = ImageIO.read(getClass().getResourceAsStream("/yick/YickUp2.png"));
			imageSet[2] = ImageIO.read(getClass().getResourceAsStream("/yick/YickUp3.png"));
			imageSet[3] = ImageIO.read(getClass().getResourceAsStream("/yick/YickUp2a.png"));
			imageSet[4] = ImageIO.read(getClass().getResourceAsStream("/yick/YickUp3a.png"));
			//Walk Down
			imageSet[5] = ImageIO.read(getClass().getResourceAsStream("/yick/YickDown1.png"));
			imageSet[6] = ImageIO.read(getClass().getResourceAsStream("/yick/YickDown2.png"));
			imageSet[7] = ImageIO.read(getClass().getResourceAsStream("/yick/YickDown3.png"));
			imageSet[8] = ImageIO.read(getClass().getResourceAsStream("/yick/YickDown2a.png"));
			imageSet[9] = ImageIO.read(getClass().getResourceAsStream("/yick/YickDown3a.png"));
			//Walk Left
			imageSet[10] = ImageIO.read(getClass().getResourceAsStream("/yick/YickLeft1.png"));
			imageSet[11] = ImageIO.read(getClass().getResourceAsStream("/yick/YickLeft2.png"));
			imageSet[12] = ImageIO.read(getClass().getResourceAsStream("/yick/YickLeft3.png"));
			imageSet[13] = ImageIO.read(getClass().getResourceAsStream("/yick/YickLeft2a.png"));
			imageSet[14] = ImageIO.read(getClass().getResourceAsStream("/yick/YickLeft3a.png"));
			//Walk Right
			imageSet[15] = ImageIO.read(getClass().getResourceAsStream("/yick/YickRight1.png"));
			imageSet[16] = ImageIO.read(getClass().getResourceAsStream("/yick/YickRight2.png"));
			imageSet[17] = ImageIO.read(getClass().getResourceAsStream("/yick/YickRight3.png"));
			imageSet[18] = ImageIO.read(getClass().getResourceAsStream("/yick/YickRight2a.png"));
			imageSet[19] = ImageIO.read(getClass().getResourceAsStream("/yick/YickRight3a.png"));
			//Shadows
			imageSet[20] = ImageIO.read(getClass().getResourceAsStream("/yick/Shadow1.png"));
			imageSet[21] = ImageIO.read(getClass().getResourceAsStream("/yick/Shadow2.png"));
			imageSet[22] = ImageIO.read(getClass().getResourceAsStream("/yick/Shadow3.png"));
			
		} catch(IOException e) {
			System.out.println("Entity YicK error");
			e.printStackTrace();
		}
	}
	
	public void update() {
		//Check for key press update and makes the logic on character movement
		if(keyInput.pressUp == true || keyInput.pressDown == true || keyInput.pressLeft == true || keyInput.pressRight == true) {
			//Diagonal movements
			if(keyInput.pressUp == true && keyInput.pressRight == true) {
				direction = "upright";
			}
			else if(keyInput.pressUp == true && keyInput.pressLeft == true) {
				direction = "upleft";
			}
			else if(keyInput.pressDown == true && keyInput.pressRight == true) {
				direction = "downright";
			}
			else if(keyInput.pressDown == true && keyInput.pressLeft == true) {
				direction = "downleft";
			}
			
			//Up, Down, Left, Right movements
			else if(keyInput.pressUp == true) {
				//Gives sprite information on which image to show based off keypress
				direction = "up";
				//Changes coordinates of character based off keypress/directional movement
			}
			else if(keyInput.pressDown == true) {
				direction = "down";
			}
			else if(keyInput.pressLeft == true) {
				direction = "left";
			}
			else if(keyInput.pressRight == true) {
				direction = "right";
			}
			
			//Stops movement by checking for collision on tile
			collisionOn = false;
			gamePanel.collisionChecker.checkCollisionTile(this);
			
			//Checks movement collision on object
			int objectIndex = gamePanel.collisionChecker.checkCollisionObject(this, true);
			
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				case "upleft":
					worldY -= speed;
					worldX -= speed;
					break;
				case "upright":
					worldY -= speed;
					worldX += speed;
					break;
				case "downleft":
					worldY += speed;
					worldX -= speed;
					break;
				case "downright":
					worldY += speed;
					worldX += speed;
					break;
				}
			}
			
			//Used to update the sprite to know which sprite image to use in the movement animation
			spriteCounter++;
			if(spriteCounter > 6) {
				if(spriteNum == 8) {
					spriteNum = 1;
				}
				else{
					spriteNum += 1;
				}
				spriteCounter = 0;
			}
		}
		//Set sprite animation to idle if no key is pressed
		else {
			idleCounter++;
			//The time set until idle stance is played
			if(idleCounter == 20) {
				spriteNum = 1;
				idleCounter = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2d) {
		
		//loads the yick character player
		BufferedImage image = null;
		//loads the shadow under yick
		BufferedImage imageShadow = null;
		
		//Sets the specific image to the frame of movement
		switch(direction) {
			case "up":
				if(spriteNum == 1) {
					image = imageSet[0];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[1];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[2];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[1];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[0];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[3];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[4];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[3];
					imageShadow = imageSet[21];
				}
				break;
			case "down":
				if(spriteNum == 1) {
					image = imageSet[5];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[6];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[7];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[6];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[5];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[8];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[9];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[8];
					imageShadow = imageSet[21];
				}
				break;
			case "left":
				if(spriteNum == 1) {
					image = imageSet[10];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[11];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[12];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[11];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[10];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[13];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[14];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[13];
					imageShadow = imageSet[21];
				}
				break;
			case "right":
				if(spriteNum == 1) {
					image = imageSet[15];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[16];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[17];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[16];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[15];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[18];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[19];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[18];
					imageShadow = imageSet[21];
				}
				break;
			case "upleft":
				if(spriteNum == 1) {
					image = imageSet[10];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[11];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[12];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[11];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[10];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[13];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[14];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[13];
					imageShadow = imageSet[21];
				}
				break;
			case "upright":
				if(spriteNum == 1) {
					image = imageSet[15];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[16];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[17];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[16];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[15];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[18];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[19];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[18];
					imageShadow = imageSet[21];
				}
				break;
			case "downleft":
				if(spriteNum == 1) {
					image = imageSet[10];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[11];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[12];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[11];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[10];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[13];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[14];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[13];
					imageShadow = imageSet[21];
				}
				break;
			case "downright":
				if(spriteNum == 1) {
					image = imageSet[15];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 2) {
					image = imageSet[16];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 3) {
					image = imageSet[17];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 4) {
					image = imageSet[16];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 5) {
					image = imageSet[15];
					imageShadow = imageSet[20];
				}
				if(spriteNum == 6) {
					image = imageSet[18];
					imageShadow = imageSet[21];
				}
				if(spriteNum == 7) {
					image = imageSet[19];
					imageShadow = imageSet[22];
				}
				if(spriteNum == 8) {
					image = imageSet[18];
					imageShadow = imageSet[21];
				}
				break;
		}
		
		//Draws the shadow under yick
		g2d.drawImage(imageShadow, screenX, screenY + 5, gamePanel.unitSize, gamePanel.unitSize, null);
		//Draws the yick character
		g2d.drawImage(image, screenX, screenY, gamePanel.unitSize, gamePanel.unitSize, null);
	}
}



















package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileSet {
	
	GamePanel gamePanel;
	public int mapTileNum[][];
	public Tile[] tile;
	int [][] map;

	
	public TileSet(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		tile = new Tile[100];
		mapTileNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
		try {
			//Start with 10s for easier map draw on the text file, easier to see what the heck i'm modifying
			
			//Floor Tiles
			//Empty space
			tile[10] = new Tile();
			tile[10].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/NullSpace.png"));
			//Tech floor
			tile[11] = new Tile();
			tile[11].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechTile.png"));
			//Tech road center
			tile[12] = new Tile();
			tile[12].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechTileRoad.png"));
			//Tech road left
			tile[13] = new Tile();
			tile[13].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechTileRoadLeft.png"));
			//Tech road right
			tile[14] = new Tile();
			tile[14].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechTileRoadRight.png"));
			//Tech platform intersection left
			tile[15] = new Tile();
			tile[15].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechTilePlatformLeft.png"));
			//Metro railroad long
			tile[16] = new Tile();
			tile[16].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/MetroRailLong.png"));
			
			
			//Collidable Tiles
			//Tech solid
			tile[50] = new Tile();
			tile[50].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/SolidWall.png"));
			tile[50].collision = true;
			//Tech wall middle
			tile[51] = new Tile();
			tile[51].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechWall.png"));
			tile[51].collision = true;
			//Tech wall left
			tile[52] = new Tile();
			tile[52].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechWallLeft.png"));
			tile[52].collision = true;
			//Tech wall right
			tile[53] = new Tile();
			tile[53].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechWallRight.png"));
			tile[53].collision = true;
			//Tech platform intersection
			tile[54] = new Tile();
			tile[54].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/TechTilePlatformDown.png"));
			tile[54].collision = true;
			//Metro railroad spam
			tile[56] = new Tile();
			tile[56].imageSet = ImageIO.read(getClass().getResourceAsStream("/mapTiles/MetroRail.png"));
			tile[56].collision = true;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String mapPath) {
		
		try {
			InputStream inputStream = getClass().getResourceAsStream(mapPath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			
			int column = 0;
			int row = 0;
			
			while(column < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {
				//Reads a single line of the text file
				String line = bufferedReader.readLine();
				
				while(column < gamePanel.maxWorldColumn) {
					//Changes string into integer to be able to read it in num
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[column]);
					
					mapTileNum[column][row] = num;
					column++;
				}
				if(column == gamePanel.maxWorldColumn) {
					column = 0;
					row++;
				}
			}
			bufferedReader.close();	 
		//NOTE the maxWorldColumn and maxWorldRow must match the map.txt size exact from the value given in gamePanel, otherwise it won't read properly
		} catch(Exception e) {
			System.out.println("Wololo, something's wrong in the tileSet Mapload function, make sure your maxworldcolumn + maxworldrow matches the map.txt dimensions");
		}
	}

	public void draw(Graphics2D g2d) {
		
		int column = 0;
		int row = 0;
		//Implements the camera following the player
		//REMEMBER column -> left right --- row -> up down
		while(column < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {
			
			int tileNum = mapTileNum[column][row];
			
			int x = column * gamePanel.unitSize;
			int y = row * gamePanel.unitSize;
			int screenX = x - gamePanel.yick.worldX + gamePanel.yick.screenX;
			int screenY = y - gamePanel.yick.worldY + gamePanel.yick.screenY;
			
			//This draws the screen close to the camera, not the entire map to improve rendering efficiency
			if(x + gamePanel.unitSize > gamePanel.yick.worldX - gamePanel.yick.screenX && 
					x - gamePanel.unitSize < gamePanel.yick.worldX + gamePanel.yick.screenX && 
					y + gamePanel.unitSize > gamePanel.yick.worldY - gamePanel.yick.screenY && 
					y - gamePanel.unitSize < gamePanel.yick.worldY + gamePanel.yick.screenY)
			g2d.drawImage(tile[tileNum].imageSet, screenX, screenY, gamePanel.unitSize, gamePanel.unitSize, null);
			column++;
			
			if(column == gamePanel.maxWorldColumn) {
				column = 0; 
				row++;
			}
		}
	}
}


package main;

import entity.Entity;

public class CollisionChecker {

		GamePanel gamePanel;
	
		public CollisionChecker(GamePanel gamePanel) {
			this.gamePanel = gamePanel;
		}
		
		public void checkCollisionTile(Entity entity) {
			//calculates the area in which the character entity has collision with. Gives the four point coordinates in a rectangle collision box
			int entityLeftX = entity.worldX + entity.collisionArea.x;
			int entityRightX = entity.worldX + entity.collisionArea.x + entity.collisionArea.width;
			int entityUpY = entity.worldY + entity.collisionArea.y;
			int entityDownY = entity.worldY + entity.collisionArea.y + entity.collisionArea.height;
			
			int entityLeftCol = entityLeftX/gamePanel.unitSize;
			int entityRightCol = entityRightX/gamePanel.unitSize;
			int entityUpRow = entityUpY/gamePanel.unitSize;
			int entityDownRow = entityDownY/gamePanel.unitSize;
			
			int point1;
			int point2;
			//This point is used for diagonal movement
			int point3;
			
			switch(entity.direction) {
				//Lateral and longitudinal movements
				case "up":
					entityUpRow = (entityUpY - entity.speed) / gamePanel.unitSize;
					//Sets the top left and top right point to draw a line which will check for collision
					point1 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityUpRow];
					point2 = gamePanel.tileSet.mapTileNum[entityRightCol][entityUpRow];
					//If either points are touched, turn on collision stopping movement
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true) {
						entity.collisionOn = true;
					}
					break;
				case "down":
					entityDownRow = (entityDownY + entity.speed) / gamePanel.unitSize;
					point1 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityDownRow];
					point2 = gamePanel.tileSet.mapTileNum[entityRightCol][entityDownRow];
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true) {
						entity.collisionOn = true;
					}
					break;
				case "left":
					entityLeftCol = (entityLeftX - entity.speed) / gamePanel.unitSize;
					point1 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityUpRow];
					point2 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityDownRow];
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true) {
						entity.collisionOn = true;
					}
					break;
				case "right":
					entityRightCol = (entityRightX + entity.speed) / gamePanel.unitSize;
					point1 = gamePanel.tileSet.mapTileNum[entityRightCol][entityUpRow];
					point2 = gamePanel.tileSet.mapTileNum[entityRightCol][entityDownRow];
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true) {
						entity.collisionOn = true;
					}
				//Diagonal Movements
				case "upleft":
					entityUpRow = (entityUpY - entity.speed) / gamePanel.unitSize;
					entityLeftCol = (entityLeftX - entity.speed) / gamePanel.unitSize;
					//Sets the top left and top right point to draw a line which will check for collision
					point1 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityUpRow];
					point2 = gamePanel.tileSet.mapTileNum[entityRightCol][entityUpRow];
					point3 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityDownRow];
					//If either points are touched, turn on collision stopping movement
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true || gamePanel.tileSet.tile[point3].collision == true) {
						entity.collisionOn = true;
					}
					break;
				case "upright":
					entityRightCol = (entityRightX + entity.speed) / gamePanel.unitSize;
					entityUpRow = (entityUpY - entity.speed) / gamePanel.unitSize;
					point1 = gamePanel.tileSet.mapTileNum[entityRightCol][entityUpRow];
					point2 = gamePanel.tileSet.mapTileNum[entityRightCol][entityDownRow];
					point3 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityUpRow];
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true || gamePanel.tileSet.tile[point3].collision == true) {
						entity.collisionOn = true;
					}
					break;	
				case "downleft":
					entityLeftCol = (entityLeftX - entity.speed) / gamePanel.unitSize;
					entityDownRow = (entityDownY + entity.speed) / gamePanel.unitSize;
					point1 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityUpRow];
					point2 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityDownRow];
					point3 = gamePanel.tileSet.mapTileNum[entityRightCol][entityDownRow];
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true || gamePanel.tileSet.tile[point3].collision == true) {
						entity.collisionOn = true;
					}
					break;
				case "downright":
					entityDownRow = (entityDownY + entity.speed) / gamePanel.unitSize;
					entityRightCol = (entityRightX + entity.speed) / gamePanel.unitSize;
					point1 = gamePanel.tileSet.mapTileNum[entityLeftCol][entityDownRow];
					point2 = gamePanel.tileSet.mapTileNum[entityRightCol][entityDownRow];
					point3 = gamePanel.tileSet.mapTileNum[entityRightCol][entityUpRow];
					if(gamePanel.tileSet.tile[point1].collision == true || gamePanel.tileSet.tile[point2].collision == true || gamePanel.tileSet.tile[point3].collision == true) {
						entity.collisionOn = true;
					}
					break;
			}
		}
		
		//Check if the entity is the player
		public int checkCollisionObject(Entity entity, boolean yick) {
			int index = 999;
			for(int i = 0; i < gamePanel.objectManager.length; i++) {
				if(gamePanel.objectManager[i] != null) {
					//Uses the entity's collision area
					entity.collisionArea.x = entity.worldX + entity.collisionArea.x;
					entity.collisionArea.y = entity.worldY + entity.collisionArea.y;
					//Uses the object's collision area
					gamePanel.objectManager[i].collisionArea.x = gamePanel.objectManager[i].worldX + gamePanel.objectManager[i].collisionArea.x;
					gamePanel.objectManager[i].collisionArea.y = gamePanel.objectManager[i].worldY + gamePanel.objectManager[i].collisionArea.y;
					
					switch(entity.direction) {
					case "up":
						entity.collisionArea.y -= entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					case "down":
						entity.collisionArea.y += entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					case "left":
						entity.collisionArea.x -= entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					case "right":
						entity.collisionArea.x += entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					case "upleft":
						entity.collisionArea.x -= entity.speed;
						entity.collisionArea.y -= entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;

					case "upright":
						entity.collisionArea.x += entity.speed;
						entity.collisionArea.y -= entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					case "downleft":
						entity.collisionArea.x -= entity.speed;
						entity.collisionArea.y += entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					case "downright":
						entity.collisionArea.x += entity.speed;
						entity.collisionArea.y += entity.speed;
						if(entity.collisionArea.intersects(gamePanel.objectManager[i].collisionArea)) {
							if(gamePanel.objectManager[i].collision == true) {
								entity.collisionOn = true;
							}
							if(yick == true) {
								index = i;
							}
						}
						break;
					}
					
					//Updates the new x and y with the old default x and y value so it does not get added on continuously
					entity.collisionArea.x = entity.collisionAreaX;
					entity.collisionArea.y = entity.collisionAreaY;
					gamePanel.objectManager[i].collisionArea.x = gamePanel.objectManager[i].collisionAreaX;
					gamePanel.objectManager[i].collisionArea.y = gamePanel.objectManager[i].collisionAreaY;
				}
			}
			return index;
		}
}


















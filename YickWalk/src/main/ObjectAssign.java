package main;

import object.PurpleBench;
import object.TrainDoor;
import object.TrainHead;
import object.TrainSolid;
import object.TrainSolidCorner;
import object.TrainSolidTop;
import object.TrainWindow;
import object.VendingMachine;

public class ObjectAssign {

		GamePanel gamePanel;
		
		public ObjectAssign(GamePanel gamePanel) {
			this.gamePanel = gamePanel;
		}
		
		public void setObject() {
			//Generates a bench
			gamePanel.objectManager[0] = new PurpleBench();
			gamePanel.objectManager[0].worldX = 7 * gamePanel.unitSize;
			gamePanel.objectManager[0].worldY = 2 * gamePanel.unitSize - 40;
			gamePanel.objectManager[1] = new PurpleBench();
			gamePanel.objectManager[1].worldX = 8 * gamePanel.unitSize;
			gamePanel.objectManager[1].worldY = 2 * gamePanel.unitSize - 40;
			gamePanel.objectManager[6] = new PurpleBench();
			gamePanel.objectManager[6].worldX = 2 * gamePanel.unitSize;
			gamePanel.objectManager[6].worldY = 2 * gamePanel.unitSize - 40;
			gamePanel.objectManager[7] = new PurpleBench();
			gamePanel.objectManager[7].worldX = 1 * gamePanel.unitSize;
			gamePanel.objectManager[7].worldY = 2 * gamePanel.unitSize - 40;
			
			gamePanel.objectManager[2] = new PurpleBench();
			gamePanel.objectManager[2].worldX = 7 * gamePanel.unitSize;
			gamePanel.objectManager[2].worldY = 8 * gamePanel.unitSize;
			gamePanel.objectManager[3] = new PurpleBench();
			gamePanel.objectManager[3].worldX = 8 * gamePanel.unitSize;
			gamePanel.objectManager[3].worldY = 8 * gamePanel.unitSize;
			gamePanel.objectManager[4] = new PurpleBench();
			gamePanel.objectManager[4].worldX = 2 * gamePanel.unitSize;
			gamePanel.objectManager[4].worldY = 8 * gamePanel.unitSize;
			gamePanel.objectManager[5] = new PurpleBench();
			gamePanel.objectManager[5].worldX = 1 * gamePanel.unitSize;
			gamePanel.objectManager[5].worldY = 8 * gamePanel.unitSize;
			
			//Generates a vending machine
			gamePanel.objectManager[10] = new VendingMachine();
			gamePanel.objectManager[10].worldX = 6 * gamePanel.unitSize;
			gamePanel.objectManager[10].worldY = 2 * gamePanel.unitSize - 45;
			
			//Generating a train
			gamePanel.objectManager[40] = new TrainSolidTop();
			gamePanel.objectManager[40].worldX = 1 * gamePanel.unitSize;
			gamePanel.objectManager[40].worldY = 5 * gamePanel.unitSize - 15;
			gamePanel.objectManager[41] = new TrainSolidTop();
			gamePanel.objectManager[41].worldX = 2 * gamePanel.unitSize;
			gamePanel.objectManager[41].worldY = 5 * gamePanel.unitSize - 15;
			gamePanel.objectManager[42] = new TrainSolidTop();
			gamePanel.objectManager[42].worldX = 3 * gamePanel.unitSize;
			gamePanel.objectManager[42].worldY = 5 * gamePanel.unitSize - 15;
			gamePanel.objectManager[43] = new TrainSolidTop();
			gamePanel.objectManager[43].worldX = 4 * gamePanel.unitSize;
			gamePanel.objectManager[43].worldY = 5 * gamePanel.unitSize - 15;
			gamePanel.objectManager[44] = new TrainSolidCorner();
			gamePanel.objectManager[44].worldX = 5 * gamePanel.unitSize;
			gamePanel.objectManager[44].worldY = 5 * gamePanel.unitSize - 15;
			
			gamePanel.objectManager[50] = new TrainSolid();
			gamePanel.objectManager[50].worldX = 1 * gamePanel.unitSize;
			gamePanel.objectManager[50].worldY = 6 * gamePanel.unitSize - 15;
			gamePanel.objectManager[51] = new TrainDoor();
			gamePanel.objectManager[51].worldX = 2 * gamePanel.unitSize;
			gamePanel.objectManager[51].worldY = 6 * gamePanel.unitSize - 15;
			gamePanel.objectManager[52] = new TrainSolid();
			gamePanel.objectManager[52].worldX = 3 * gamePanel.unitSize;
			gamePanel.objectManager[52].worldY = 6 * gamePanel.unitSize - 15;
			gamePanel.objectManager[53] = new TrainWindow();
			gamePanel.objectManager[53].worldX = 4 * gamePanel.unitSize;
			gamePanel.objectManager[53].worldY = 6 * gamePanel.unitSize - 15;
			gamePanel.objectManager[54] = new TrainSolid();
			gamePanel.objectManager[54].worldX = 5 * gamePanel.unitSize;
			gamePanel.objectManager[54].worldY = 6 * gamePanel.unitSize - 15;
			gamePanel.objectManager[55] = new TrainHead();
			gamePanel.objectManager[55].worldX = 6 * gamePanel.unitSize;
			gamePanel.objectManager[55].worldY = 6 * gamePanel.unitSize - 15;
			

			

		}
}

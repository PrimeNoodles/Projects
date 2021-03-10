import java.util.*;

public class DiskScheExtra {
	
	//Generates 1000 random requests for array of cylinders to be used and stores the cylinder number in an array
	static void randPick(int arr[], int arr1[]){
		Random rand = new Random();
		for(int i = 0; i < 1000; i++) {
			arr1[i] = arr[rand.nextInt(4999)];
		}
	}	
	
	//This is the scheduling algorithm for FCFS
	static void FCFS(int pos, int arr[]) {
		int distance;
		int head;
		int count = 0;

		for(int i = 0; i < arr.length; i++) {
			head = arr[i];
			//finds the distance between current head and next position
			distance = Math.abs(head - pos);
			//counts the total amount of distance moved by head
			count = count + distance;			
			//updates new head position
			pos = head;
		}
		System.out.println("The total amount of head movement by FCFS is: " + count);
	}

	//This is the scheduling algorithm for SSTF
	static void SSTF(int pos, int arr[], int arr1[]) {
		boolean[] check = new boolean[1000];
		int count = 0;
		int head = 0;
		int distance = arr1.length + 1;
		
		//goes through each request
		for(int i = 0; i < arr.length; i++) {
			//finds the request closest to the current head
			for(int j = 0; j < arr.length; j++) {
				if(Math.abs(pos - arr[j]) < distance && !check[j]) {
					head = j;
					distance = Math.abs(pos - arr[j]);
				}
			//marks the current request as checked
			check[head] = true;
			//counts the total amount of distance moved by head 
			count = count + distance;
			//updates new head position
			pos = arr[head];	
			}
		}
		System.out.println("The total amount of head movement by SSTF is: " + count);
	}
	
	//This is the scheduling algorithm for SCAN
	static void SCAN(int pos, int arr[], int arr1[]) {
		int count = 0;
		int head;
		int distance;
		String direction = "left";
		Vector<Integer> left = new Vector<Integer>(), right = new Vector<Integer>();
		Vector<Integer> seekSeq = new Vector<Integer>();
		
		//adds conditions to check edge cases of last values of both directions
		if(direction == "left") {
			left.add(0);
		}
		if(direction == "right") {
			right.add(arr1.length - 1);
		}
		//reverses the direction
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < pos) {
				left.add(arr[i]);
			}
			if(arr[i] > pos) {
				right.add(arr[i]);
			}
		}
		//Both directions are then run through a sort
		Collections.sort(left);
		Collections.sort(right);
		
		//Goes through left and right side
		for(int i = 0; i < 2; i++) {
			if(direction == "left") {
				for(int j = left.size() - 1; j >= 0; j--) {
					head = left.get(j);
					//adds on the head to the seek sequence
					seekSeq.add(head);
					//finds the distance between current head and next position
					distance = Math.abs(head - pos);
					//counts the total amount of distance moved by head 
					count = count + distance;
					//updates new head position
					pos = head;		
				}
				//once the left side is done, switches to right side
				direction = "right";
			}
			else if(direction == "right") {
				for(int j = right.size() - 1; j >= 0; j--) {
					head = right.get(j);
					//adds on the head to the seek sequence
					seekSeq.add(head);
					//finds the distance between current head and next position
					distance = Math.abs(head - pos);
					//counts the total amount of distance moved by head 
					count = count + distance;
					//updates new head position
					pos = head;		
				}
				//once the right side is done, switches to left side
				direction = "left";
			}
		}
		System.out.println("The total amount of head movement by SCAN is: " + count);
	}
	
	//This is the scheduling algorithm for CSCAN
	static void CSCAN(int pos, int arr[], int arr1[]) {
		int count = 0;
		int head;
		int distance;
		Vector<Integer> left = new Vector<Integer>(), right = new Vector<Integer>();
		Vector<Integer> seekSeq = new Vector<Integer>();
		
		//adds conditions to check edge cases of last values of both directions
			left.add(0);
			right.add(arr1.length - 1);
		//loops the head when it completes a directional loop
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < pos) {
				left.add(arr[i]);
			}
			if(arr[i] > pos) {
				right.add(arr[i]);
			}
		}
		//Both directions are then run through a sort
		Collections.sort(left);
		Collections.sort(right);
		
		//Goes through the left side first
		for(int i = 0; i < left.size(); i++) {
				head = left.get(i);
				//adds on the head to the seek sequence
				seekSeq.add(head);
				//finds the distance between current head and next position
				distance = Math.abs(head - pos);
				//counts the total amount of distance moved by head 
				count = count + distance;
				//updates new head position
				pos = head;		
			}
		//once the left side is done, switches to 0 to start right side
		head = 0;
		//Goes through right side now
		for(int i = 0; i < right.size(); i++) {
			head = right.get(i);
			//adds on the head to the seek sequence
			seekSeq.add(head);
			//finds the distance between current head and next position
			distance = Math.abs(head - pos);
			//counts the total amount of distance moved by head 
			count = count + distance;
			//updates new head position
			pos = head;		
			}
		System.out.println("The total amount of head movement by CSCAN is: " + count);
	}
	
	//This is the scheduling algorithm for LOOK
	static void LOOK(int pos, int arr[], int arr1[]) {
		int count = 0;
		int head;
		int distance;
		String direction = "left";
		Vector<Integer> left = new Vector<Integer>(), right = new Vector<Integer>();
		Vector<Integer> seekSeq = new Vector<Integer>();
		
		//adds values at both left and right direction from current head position
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < pos) {
				left.add(arr[i]);
			}
			if(arr[i] > pos) {
				right.add(arr[i]);
			}
		}
		//Both directions are then run through a sort
		Collections.sort(left);
		Collections.sort(right);
		
		//Goes through left and right side
		for(int i = 0; i < 2; i++) {
			if(direction == "left") {
				for(int j = left.size() - 1; j >= 0; j--) {
					head = left.get(j);
					//adds on the head to the seek sequence
					seekSeq.add(head);
					//finds the distance between current head and next position
					distance = Math.abs(head - pos);
					//counts the total amount of distance moved by head 
					count = count + distance;
					//updates new head position
					pos = head;		
				}
				//once the left side is done, switches to right side
				direction = "right";
			}
			else if(direction == "right") {
				for(int j = right.size() - 1; j >= 0; j--) {
					head = right.get(j);
					//adds on the head to the seek sequence
					seekSeq.add(head);
					//finds the distance between current head and next position
					distance = Math.abs(head - pos);
					//counts the total amount of distance moved by head 
					count = count + distance;
					//updates new head position
					pos = head;		
				}
				//once the right side is done, switches to left side
				direction = "left";
			}
		}
		System.out.println("The total amount of head movement by LOOK is: " + count);
	}
	
	//This is the scheduling algorithm for CLOOK
	static void CLOOK(int pos, int arr[], int arr1[]) {
		int count = 0;
		int head;
		int distance;
		Vector<Integer> left = new Vector<Integer>(), right = new Vector<Integer>();
		Vector<Integer> seekSeq = new Vector<Integer>();
		
		//loops the head when it completes a directional loop
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] < pos) {
				left.add(arr[i]);
			}
			if(arr[i] > pos) {
				right.add(arr[i]);
			}
		}
		//Both directions are then run through a sort
		Collections.sort(left);
		Collections.sort(right);
		
		//Goes through the left side first
		for(int i = 0; i < left.size(); i++) {
				head = left.get(i);
				//adds on the head to the seek sequence
				seekSeq.add(head);
				//finds the distance between current head and next position
				distance = Math.abs(head - pos);
				//counts the total amount of distance moved by head 
				count = count + distance;
				//updates new head position
				pos = head;		
			}
		//Loops back to the last track needed in right direction
		count = count + Math.abs(pos - right.get(0));
		pos = right.get(0);
		//Goes through right side now
		for(int i = 0; i < right.size(); i++) {
			head = right.get(i);
			//adds on the head to the seek sequence
			seekSeq.add(head);
			//finds the distance between current head and next position
			distance = Math.abs(head - pos);
			//counts the total amount of distance moved by head 
			count = count + distance;
			//updates new head position
			pos = head;		
			}
		System.out.println("The total amount of head movement by CLOOK is: " + count);
	}
	
	public static void main(String[] args) throws Exception{
		//creates an array of the disk drive with 5000 cylinders
		int[] cylinders = new int[5000];
		//creates an array of 1000 to input random requests from the 5000 cylinders
		int[] requests = new int [1000];
		for(int i = 0; i < cylinders.length; i++) {
			cylinders[i] = i;
		}
		
		//picks 1000 random cylinder to be used and stores it in requests
		randPick(cylinders, requests);
		
		//user prompt to enter initial head position
		Scanner sc = new Scanner(System.in);	
		System.out.println("Enter the initial position of the disk head between 0 and 4999: ");
		int initHead = sc.nextInt();
		
		//Runs through the disk scheduling algorithms
		FCFS(initHead, requests);
		SSTF(initHead, requests, cylinders);
		SCAN(initHead, requests, cylinders);
		CSCAN(initHead, requests, cylinders);
		LOOK(initHead, requests, cylinders);
		CLOOK(initHead, requests, cylinders);
	}
}

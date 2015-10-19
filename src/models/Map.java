package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Map {
	
	final int XSIZE = 10;
	final int YSIZE = 10;
	
	Room[][] matrix;
	
	Wumpus wumpus;
	Hunter hunter;
	MapMessenger messenger = new MapMessenger();
	
	public Map(Wumpus wumpus, ArrayList<SlimePit> slimePits, Hunter hunter) {
		this.matrix    = new Room[XSIZE][YSIZE];
		
		this.wumpus = wumpus;
		this.hunter = hunter;
		
		placeRooms();
		placeSlimePits(slimePits);
		placeHunter(hunter);
		placeWumpus(wumpus);
	}
	
	public Map() {
		this.matrix = new Room[XSIZE][YSIZE];
		for(int r = 0; r < XSIZE; r++) {
			for(int c = 0; c < YSIZE; c++) {
				this.matrix[r][c] = new Room(null);
			}
		}
	}
	
	public int getXSize() {
		return XSIZE;
	}
	
	public int getYSize() {
		return YSIZE;
	}
	
	public Room[][] getMatrix() {
		return this.matrix;
	}
	
	private void placeWumpus(Wumpus wumpus) {
		int [] wumpusPosition = placeInRandomPosition(wumpus);
		wumpus.setPosition(wumpusPosition[0], wumpusPosition[1]);
		placeBloodOrGoop(wumpusPosition);	
	}
	
	public void placeWumpus(Wumpus wumpus, int x, int y) {
		matrix[x][y] = new Room(wumpus);
		int[] wumpusPosition = {x,y};
		placeBloodOrGoop(wumpusPosition);
	}
	
	private void placeHunter(Hunter hunter) {
		int[] position = getRandomPosition(); 
		while ( matrix[position[0]][position[1]].getContents() != null) {
			position = getRandomPosition();
		}
		Room room = new Room(null);
		room.setHunter(hunter);
		room.visit();
		matrix[position[0]][position[1]] = room;
		hunter.setPosition(position[0], position[1]);
	}
	
	public void placeHunter(Hunter hunter, int x, int y) {
		Room room = new Room(null);
		matrix[x][y] = room;
		room.setHunter(hunter);
	}
	
	public void placeSlime(ArrayList<SlimePit> slimePit, int x, int y) {
		matrix[x][y] = new Room(slimePit);
		int[] slimePitPosition = {x,y};
		placeSlime(slimePitPosition);
	}
	
	private void placeSlimePits(ArrayList<SlimePit> slimePits) {
		for(SlimePit slimepit : slimePits) {
			int[] slimePitPosition = placeInRandomPosition(slimepit);
			placeSlime(slimePitPosition);
		}
	}
	
	public void placeSlimePit(SlimePit slimepit, int x, int y) {
		matrix[x][y] = new Room(slimepit);
		int[] slimePitPosition = {x,y};
		placeSlime(slimePitPosition);
	}
	
	private void placeSlime(int[] slimePitPosition) {
		int x = slimePitPosition[0];
		int y = slimePitPosition[1];
		if (x < XSIZE-1) {
			placeSlimeOrGoop(x+1, y);
		}
		if (0 < x) {
			placeSlimeOrGoop(x-1, y);
		}
		
		if (y < YSIZE-1) {
			placeSlimeOrGoop(x, y+1);
		}
		if (0 < y) {
			placeSlimeOrGoop(x, y-1);
		}
	}
	
	private void placeSlimeOrGoop(int x, int y) {
		if (matrix[x][y] != null && matrix[x][y].getContents() instanceof Blood ) {
			matrix[x][y] = new Room(new Goop());
		} else {
			matrix[x][y] = new Room(new Slime());
		}
	}
	
	private void placeBloodOrGoop(int[] wumpusPosition) {
		int x = wumpusPosition[0];
		int y = wumpusPosition[1];
		int k = 0;
		
		if(x+1 < XSIZE && y+1 < YSIZE) {matrix[x+1][y+1] = _placeBloodOrGoop(x+1, y+1); }
		if(x+1 < XSIZE && y-1 > 0) { matrix[x+1][y-1] = _placeBloodOrGoop(x+1, y-1); }
		if(x-1 > 0 && y+1 < YSIZE) { matrix[x-1][y+1]   = _placeBloodOrGoop(x-1, y+1); }
		if(x-1 > 0 && y-1 > 0)matrix[x-1][y-1] =  _placeBloodOrGoop(x-1, y-y);
		
		while(x < XSIZE-1 && k < 2) {
			matrix[++x][y] = _placeBloodOrGoop(x, y);
			k++;
		}
		k = 0;
		x = wumpusPosition[0];
		
		while(0 < x && k < 2) {
			matrix[--x][y] = _placeBloodOrGoop(x, y);
			k++;
		}
		
		k = 0;
		x = wumpusPosition[0];
		
		while(y < YSIZE-1 && k < 2) {
			matrix[x][++y] = _placeBloodOrGoop(x, y);
			k++;
		}
		
		k = 0;
		y = wumpusPosition[1];
		
		while(0 < y && k < 2) {
			matrix[x][--y] = _placeBloodOrGoop(x, y);
			k++;
		}
	}
	
	public Room _placeBloodOrGoop(int x,int y) {
		if (matrix[x][y].getContents() instanceof Slime) {
			return new Room(new Goop());
		}
		
		return new Room(new Blood());
	}
	
	public void placeRooms() {
		for(int r = 0; r < XSIZE; r++) {
			for(int c = 0; c < YSIZE; c++) {
				if (matrix[r][c] == null) {
					matrix[r][c] = new Room(null);
				}
			}
		}
	}
	
	private int[] placeInRandomPosition(Object obj) {
		int[] coordinates = getRandomPosition();
		while (matrix[coordinates[0]][coordinates[1]].getContents() != null ) {
			coordinates = getRandomPosition();
		}
		matrix[coordinates[0]][coordinates[1]] = new Room(obj);
		
		//System.out.println("Placed: " + obj.getClass() + " in " + coordinates[0] + " " + coordinates[1]);
		return coordinates;
	}
	
	private int[] getRandomPosition() {
		Random random = new Random();
		int randomX   = random.nextInt((10-1)+1);
		int randomY   = random.nextInt((10-1)+1);
		int[] coordinates = new int[2];
		coordinates[0] = randomX;
		coordinates[1] = randomY;
		return coordinates;
	}
	

	
	public char objectSymbol(Object obj) {
		if(obj instanceof Wumpus){return 'W'; }
		if(obj instanceof Hunter) {return 'O'; }
		if(obj instanceof SlimePit){return 'P'; }
		if(obj instanceof Slime){return 'S'; }
		if(obj instanceof Goop){return 'G'; }
		if(obj instanceof Blood){return 'B'; }
		return 'v';
	}
	
	private char getRoomObjectSymbol(Room room) {
		if (room.getHunter() != null) {
			return objectSymbol(room.getHunter());
		}
		if (room.isVisited && room.getContents() != null) {
			return objectSymbol(room.getContents());
		}
		if (room.isVisited && room.getContents() == null) {
			return 'v';
		}
		return 'X';
	}
	
	public Wumpus getWumpus() {
		return this.wumpus;
	}
	
	public Hunter getHunter() {
		return this.hunter;
	}
	
	public void place(Object object, int x, int y) {
		matrix[x][y] = new Room(object);
		
	}
	
	public void changed() {
		messenger.send("Change");
		
	}
	
	public MapMessenger getMapMessenger() {
		return this.messenger;
	}
	
	public String toString() {
		String mapAsString = "";
		for(int r = 0; r < XSIZE; r++) {
			for(int c = 0; c < YSIZE; c++) {
				
				if (getRoomObjectSymbol(matrix[r][c]) == 'v') {
					mapAsString += "[ ] ";
				}
				else if (matrix[r][c] != null) {
					mapAsString += "[" + getRoomObjectSymbol(matrix[r][c]) + "] ";
				} else {
					mapAsString += " [?] ";
				}
			}
			mapAsString += "\n";
		}
		return mapAsString;
	}
	
	public void revealWholeMap(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j].visit();
			}
		}
		
		

	}
	
	public class MapMessenger extends Observable {
		
		String message;
		
		public MapMessenger() {}
		
		public void send(String message) {
			this.message = message;
			setChanged();
			notifyObservers(message);
		}
	}
}
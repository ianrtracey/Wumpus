package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Map extends Observable {
	
	final int XSIZE = 10;
	final int YSIZE = 10;
	
	Room[][] matrix;
	
	Wumpus wumpus;
	Hunter hunter;
	
	public Map(Wumpus wumpus, ArrayList<SlimePit> slimePits, Hunter hunter) {
		this.matrix    = new Room[XSIZE][YSIZE];
		
		this.wumpus = wumpus;
		this.hunter = hunter;
		
		placeWumpus(wumpus);
		placeSlimePits(slimePits);
		placeHunter(hunter);
		placeRooms();
	}
	
	public Map() {
		this.matrix    = new Room[XSIZE][YSIZE];
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
		placeBlood(wumpusPosition);	
	}
	
	public void placeWumpus(Wumpus wumpus, int x, int y) {
		matrix[x][y] = new Room(wumpus);
		int[] wumpusPosition = {x,y};
		placeBlood(wumpusPosition);
	}
	
	private void placeHunter(Hunter hunter) {
		int[] position = getRandomPosition(); 
		while ( matrix[position[0]][position[1]] != null) {
			position = getRandomPosition();
		}
		matrix[position[0]][position[1]] = new Room(hunter);
		hunter.setPosition(position[0], position[1]);
	}
	
	public void placeHunter(Hunter hunter, int x, int y) {
		matrix[x][y] = new Room(hunter);
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
	
	private void placeBlood(int[] wumpusPosition) {
		int x = wumpusPosition[0];
		int y = wumpusPosition[1];
		int k = 0;
		
		while(x < XSIZE-1 && k < 2) {
			matrix[++x][y] = new Room(new Blood());
			k++;
		}
		k = 0;
		x = wumpusPosition[0];
		
		while(0 < x && k < 2) {
			matrix[--x][y] = new Room(new Blood());
			k++;
		}
		
		k = 0;
		x = wumpusPosition[0];
		
		while(y < YSIZE-1 && k < 2) {
			matrix[x][++y] = new Room( new Blood());
			k++;
		}
		
		k = 0;
		y = wumpusPosition[1];
		
		while(0 < y && k < 2) {
			matrix[x][--y] = new Room(new Blood());
			k++;
		}
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
		if(obj instanceof Hunter){return 'O'; }
		if(obj instanceof SlimePit){return 'P'; }
		if(obj instanceof Slime){return 'S'; }
		if(obj instanceof Goop){return 'G'; }
		if(obj instanceof Blood){return 'B'; }
		if(obj instanceof Room){ return getRoomObjectSymbol((Room)obj); }
		return 'i';
	}
	
	private char getRoomObjectSymbol(Room room) {
		if (room.isVisited && room.getContents() != null) {
			return objectSymbol(room.getContents());
		} else if (room.isVisited) {
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
		setChanged();
		notifyObservers(this);
	}
	
	public String toString() {
		String mapAsString = "";
		for(int r = 0; r < XSIZE; r++) {
			for(int c = 0; c < YSIZE; c++) {
				if (objectSymbol(matrix[r][c]) == 'v') {
					mapAsString += "[ ] ";
				}
				else if (matrix[r][c] != null) {
					mapAsString += "[" + objectSymbol(matrix[r][c]) + "] ";
				} else {
					mapAsString += " [?] ";
				}
			}
			mapAsString += "\n";
		}
		return mapAsString;
	}

	
	// randomly generate Wumpus
	// 3-5 bottomless slime pits
	// randomly generated hunter position (except not
	// generated on top of any hazards
	// needs wrap-around
	// needs to know if room is visited or unvisited
	
	

}

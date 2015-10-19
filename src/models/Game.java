package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Game {
	
	Wumpus wumpus;
	Hunter hunter;
	ArrayList<SlimePit> slimePits = new ArrayList<SlimePit>();
	Map map;
	Boolean status;
	
	
	public Game() {
		this.map = new Map( generateWumpus(), generateSlimePits(), generateHunter()); 
	}
	
	public Map getMap() {
		return this.map;
	}
	

	
	public Wumpus getWumpus() {
		return wumpus;
	}
	
	public Hunter getHunter() {
		return hunter;
	}
	
	private Wumpus generateWumpus() {
		if (wumpus == null) {
			wumpus = new Wumpus();
		}
		return wumpus;
	}
	
	private Hunter generateHunter() {
		if (hunter == null) {
			hunter = new Hunter();
		}
		return hunter;
		
	}
	
	public boolean determineHitOnWumpus(int startingX, int startingY, int incrementX, int incrementY) {
		
		int[] incPosition = wrapAroundPositionIncrement(startingX, startingY, incrementX, incrementY);
		System.out.println("starting inc :" + incPosition[0] + " " + incPosition[1]);
		while (startingX != incPosition[0] || startingY != incPosition[1]) {
			System.out.println("looping inc :" + incPosition[0] + " " + incPosition[1]);
			if (map.getMatrix()[incPosition[0]][incPosition[1]].getContents() instanceof Wumpus) {
				return true;
			}
			incPosition = wrapAroundPositionIncrement(incPosition[0], incPosition[1], incrementX, incrementY);
		}
		
		return false;

	}
	
	private int[] wrapAroundPositionIncrement(int currentX, int currentY, int incX, int incY) {
		int safePositionX, safePositionY;
		int[] position;
		
		if (currentX+incX < 0) {
			safePositionX = map.getXSize()-1;
		} else {
		safePositionX = (currentX+incX) % map.getXSize();
		}
		
		if (currentY+incY < 0) {
			safePositionY = map.getYSize()-1;
		} else {
		safePositionY = (currentY+incY) % map.getYSize();
		}
		
		position = new int[] {safePositionX, safePositionY};
		return position;
		
	}
	
	
	public Object hazardExistsInRoom(int x, int y) {
		
		if(map.getMatrix()[x][y].getContents() instanceof SlimePit) {
			return map.getMatrix()[x][y].getContents();
		}
		if (map.getMatrix()[x][y].getContents() instanceof Wumpus) {
			return map.getMatrix()[x][y].getContents();
		}
		if (map.getMatrix()[x][y].getContents() instanceof Slime) {
			return map.getMatrix()[x][y].getContents();
		}
		if (map.getMatrix()[x][y].getContents() instanceof Goop) {
			return map.getMatrix()[x][y].getContents();
		}	
		if (map.getMatrix()[x][y].getContents() instanceof Blood) {
			return map.getMatrix()[x][y].getContents();
		}
		
		return null;
	}
	
	// generate 3-5 random slime pits
	private ArrayList<SlimePit> generateSlimePits() {
		if (slimePits.isEmpty()) {
			Random r = new Random();
			int numOfSlimePits = r.nextInt((5-3)+1)+3;
			System.out.println("number " + numOfSlimePits);
			for(int i = 0; i < numOfSlimePits; i++ ){
				slimePits.add(new SlimePit());
			}
		}
		return slimePits;
	}
	
	

}
// randomly generate Wumpus
// 3-5 bottomless slime pits
// randomly generated hunter position (except not
// generated on top of any hazards
// needs wrap-around
// needs to know if room is visited or unvisited
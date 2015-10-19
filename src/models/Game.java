package models;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable {
	
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
	
	public void changeStatus(){
		this.status = false;
		setChanged();
		notifyObservers(this);
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
	
	public boolean determineHitOnWumpus(int arrowX, int arrowY) {
		if (map.getMatrix()[arrowX][arrowY].getContents() instanceof Wumpus) {
			return true;
		}
		return false;
	}
	
	public Object hazardExistsInRoom(int x, int y) {
		
		if(map.getMatrix()[x][y].getContents() instanceof SlimePit) {
			return map.getMatrix()[x][y].getContents();
		}
		if (map.getMatrix()[x][y].getContents() instanceof Wumpus) {
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
package models;

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	Wumpus wumpus;
	Hunter hunter;
	ArrayList<SlimePit> slimePits = new ArrayList<SlimePit>();
	Map map;
	
	
	public Game() {
		map = new Map( generateWumpus(), generateSlimePits(), generateHunter()); 
		System.out.println( map.toString() );
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
	
	// generate 3-5 random slime pits
	private ArrayList<SlimePit> generateSlimePits() {
		if (slimePits.isEmpty()) {
			Random r = new Random();
			int numOfSlimePits = r.nextInt((6-3)+3);
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
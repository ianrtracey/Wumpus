package models;

import java.util.Random;

public class Map {
	
	final int XSIZE = 10;
	final int YSIZE = 10;
	
	char[][] matrix;
	Wumpus wumpus;
	SlimePit slimePits;
	Player hunter;
	
	public Map(Wumpus wumpus, SlimePit slimePits, Player hunter) {
		matrix = new char[XSIZE][YSIZE];
		this.wumpus    = wumpus;
		this.slimePits = slimePits;
		this.hunter    = hunter;
		
	}
	
	private void placeInRandomPosition(Object obj) {
		Random random = new Random();
		int randomX = random.nextInt((10-1)+1)+1;
		int randomY = random.nextInt((10-1)+1)+1;
	} 
	
	private char generateObjectCode(Object obj) {
		if(obj instanceof Wumpus){return 'w'; }
		if(obj instanceof Player){return 'O'; }
		if(obj instanceof SlimePit){return 'S'; }
		return 'i';
	}
	
	// randomly generate Wumpus
	// 3-5 bottomless slime pits
	// randomly generated hunter position (except not
	// generated on top of any hazards
	// needs wrap-around
	// needs to know if room is visited or unvisited
	
	

}

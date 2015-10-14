package models;

import java.util.ArrayList;
import java.util.Random;

public class Map {
	
	final int XSIZE = 10;
	final int YSIZE = 10;
	
	Object[][] matrix;
	Wumpus wumpus;
	ArrayList<SlimePit> slimePits;
	Hunter hunter;
	
	public Map(Wumpus wumpus, ArrayList<SlimePit> slimePits, Hunter hunter) {
		this.matrix    = new Object[XSIZE][YSIZE];
		
		placeInRandomPosition(wumpus);
		
		for(SlimePit slimepit: slimePits) {
		  placeInRandomPosition(slimepit);
		}
		
		placeInRandomPosition(hunter);
		
		
	}
	
	private void placeInRandomPosition(Object obj) {
		Random random = new Random();
		int randomX   = random.nextInt((10-1)+1);
		int randomY   = random.nextInt((10-1)+1);
		matrix[randomX][randomY] = obj;
		System.out.println("Placed: " + obj.getClass() + " in " + randomX + " " +randomY);
	} 
	
	public char objectSymbol(Object obj) {
		if(obj instanceof Wumpus){return 'W'; }
		if(obj instanceof Hunter){return 'O'; }
		if(obj instanceof SlimePit){return 'P'; }
		if(obj instanceof Slime){return 'S'; }
		if(obj instanceof Goop){return 'G'; }
		if(obj instanceof Blood){return 'B'; }
		return 'i';
	}
	
	public String toString() {
		String mapAsString = "";
		for(int r = 0; r < XSIZE; r++) {
			for(int c = 0; c < YSIZE; c++) {
				if (matrix[r][c] != null) {
					mapAsString += "[" + objectSymbol(matrix[r][c]) + "] ";
				} else {
					mapAsString += "[ ] ";
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

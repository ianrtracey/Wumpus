package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import models.*;

import org.junit.Test;

public class MapTest {

	@Test
	public void testMatrixObjectChars() {
		Wumpus wumpus = new Wumpus();
		ArrayList<SlimePit> slimePits = new ArrayList<SlimePit>();
		slimePits.add(new SlimePit());
		slimePits.add(new SlimePit());
		Hunter hunter = new Hunter();
		Blood blood = new Blood();
		Slime slime = new Slime();
		
		Map map = new Map(wumpus, slimePits, hunter);
		assertTrue(map.objectSymbol(wumpus) == 'W');
		assertTrue(map.objectSymbol(slimePits.get(0)) == 'P');
		assertTrue(map.objectSymbol(hunter) == 'O');
		assertTrue(map.objectSymbol(blood) == 'B');
		assertTrue(map.objectSymbol(slime) == 'S');
		
	}
	
	@Test
	public void testBloodSlimeGoopGeneration() {
		Map map = new Map();
		map.placeWumpus(new Wumpus(), 5, 5);
		SlimePit slimepit1 = new SlimePit();
		SlimePit slimepit2 = new SlimePit();
		SlimePit slimepit3 = new SlimePit();
		map.placeSlimePit(slimepit1, 5, 8);
		map.placeSlimePit(slimepit1, 2, 3);
		map.placeSlimePit(slimepit1, 3, 4);
		map.placeHunter(new Hunter(), 0, 0);
		System.out.println( map.toString() );
		assertTrue(map.getMatrix()[5][5] instanceof Wumpus);
		assertTrue(map.getMatrix()[0][0] instanceof Hunter);
		assertTrue(map.getMatrix()[5][8] instanceof SlimePit);
		assertTrue(map.getMatrix()[2][3] instanceof SlimePit);
		assertTrue(map.getMatrix()[3][4] instanceof SlimePit);
		
		assertTrue(map.getMatrix()[4][8] instanceof Slime);
		assertTrue(map.getMatrix()[6][8] instanceof Slime);
		assertTrue(map.getMatrix()[5][9] instanceof Slime);
		
		assertTrue(map.getMatrix()[5][6] instanceof Blood);
		assertTrue(map.getMatrix()[5][4] instanceof Blood);
		assertTrue(map.getMatrix()[5][3] instanceof Blood);
		assertTrue(map.getMatrix()[6][5] instanceof Blood);
	}
	

}

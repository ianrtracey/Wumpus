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
	public void testMapRandomPlacement() {
		
	}
	

}

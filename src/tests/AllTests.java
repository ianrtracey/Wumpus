package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import models.*;

public class AllTests {

	@Test
	public void WumpusPosition_Test001(){
		Wumpus myWumpus = new Wumpus();
		myWumpus.setPosition(20, 15);
		assertEquals(20, myWumpus.getPositionX() );   
		assertEquals(15, myWumpus.getPositionY() );
	}
	
	@Test
	public void CanCreateArrow_Test002(){
		Arrow arrow = new Arrow();
	}
	
	@Test
	public void CanCreateBlood_Test003(){
		Blood blood = new Blood();
	}
	
	@Test
	public void CanCreateSlime_Test004(){
		Slime slime = new Slime();
	}
	
	@Test
	public void CanCreateGoop_Test005(){
		Goop goop = new Goop();
	}
	
	@Test
	public void CanCreateSlimePit_Test006(){
		SlimePit pit = new SlimePit();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	// >>> Ian's Tests
	
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
		assertTrue(map.objectSymbol(blood)  == 'B');
		assertTrue(map.objectSymbol(slime)  == 'S');
		
	}
	
	@Test
	public void testBloodSlimeGoopGeneration() {
		Map map = new Map();
		map.placeWumpus(new Wumpus(), 5, 5);
		SlimePit slimepit1 = new SlimePit();
		SlimePit slimepit2 = new SlimePit();
		SlimePit slimepit3 = new SlimePit();
		map.placeSlimePit(slimepit1, 5, 8);
		map.placeSlimePit(slimepit2, 2, 3);
		map.placeSlimePit(slimepit3, 3, 4);
		map.placeHunter(new Hunter(), 0, 0);
		assertTrue(map.getMatrix()[5][5].getContents() instanceof Wumpus);
		assertTrue(map.getMatrix()[5][8].getContents() instanceof SlimePit);
		assertTrue(map.getMatrix()[2][3].getContents() instanceof SlimePit);
		assertTrue(map.getMatrix()[3][4].getContents() instanceof SlimePit);
		
		assertTrue(map.getMatrix()[4][8].getContents() instanceof Slime);
		assertTrue(map.getMatrix()[6][8].getContents() instanceof Slime);
		assertTrue(map.getMatrix()[5][9].getContents() instanceof Slime);
		
		assertTrue(map.getMatrix()[5][6].getContents() instanceof Blood);
		assertTrue(map.getMatrix()[5][4].getContents() instanceof Blood);
		assertTrue(map.getMatrix()[5][3].getContents() instanceof Blood);
		assertTrue(map.getMatrix()[6][5].getContents() instanceof Blood);
	}
	
	@Test
	public void testIfMapIsNull() {
		Game game = new Game();
		assertNotNull(game.getMap());
		game.getMap().toString();
	}
	

}

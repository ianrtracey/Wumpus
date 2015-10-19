package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import View.MainGUI;
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
	public void HunterHasArrow(){
		Hunter hunter = new Hunter();
		assertTrue(hunter.fire());
		assertFalse(hunter.fire());
	}
	
	@Test 
	public void testingRoomVisit() {
		Room room = new Room(null);
		assertFalse(room.isVisited());
		room.visit();
		assertTrue(room.isVisited());
	}
	
	@Test
	public void CanPutBloodInRoow(){
		Blood blood = new Blood();
		Room room = new Room(blood);
		assertNotNull(room);
		assertTrue(room.getContents() instanceof Blood);
		
	}
	
	@Test
	public void CanPutSlimeInRoom(){
		Slime slime = new Slime();
		Room room = new Room(slime);
		assertTrue(room.getContents() instanceof Slime);
	}
	
	@Test
	public void CanPutGoopInRoom(){
		Goop goop = new Goop();
		Room room = new Room(goop);
		assertTrue(room.getContents() instanceof Goop);
	}
	
	@Test
	public void CanPutSlimePitInRoom(){
		SlimePit pit = new SlimePit();
		Room room = new Room(pit);
		assertTrue(room.getContents() instanceof SlimePit);
	}
	
	@Test
	public void testGameInit() {
		Game game = new Game();
		assertNotNull(game.getWumpus());
		assertTrue(game.getWumpus() instanceof Wumpus);
		assertNotNull(game.getHunter());
		assertTrue(game.getHunter() instanceof Hunter);
	}
	
	@Test
	public void testGameWumpusHit() {
		Game game = new Game();
		Wumpus wumpus = game.getWumpus();
		Hunter hunter = game.getHunter();
		if (wumpus.getPositionX() == hunter.getPositionX()) {
			if (wumpus.getPositionX() >= hunter.getPositionX()) {
			assertTrue(game.determineHitOnWumpus(hunter.getPositionX(), hunter.getPositionY(), 1, 0));
			} else {
			assertTrue(game.determineHitOnWumpus(hunter.getPositionX(), hunter.getPositionY(), -1, 0));
			}
		}
		
		if (wumpus.getPositionY() == hunter.getPositionY()) {
			if (wumpus.getPositionY() >= hunter.getPositionY()) {
			assertTrue(game.determineHitOnWumpus(hunter.getPositionX(), hunter.getPositionY(), 0, 1));
			} else {
			assertTrue(game.determineHitOnWumpus(hunter.getPositionX(), hunter.getPositionY(), 0, -1));
			}
			
		}		
	}
	
	@Test
	public void determineHitOnWumpusTest() {
		Map map = new Map();
		map.place(new Hunter(), 5, 5);
		map.place(new Wumpus(), 9, 5);
		assertTrue(map.getMatrix()[5][5].getContents() instanceof Hunter);
		assertTrue(map.getMatrix()[9][5].getContents() instanceof Wumpus);
		Game game = new Game();
		game.setMap(map);
		Map map2 = new Map();
		map2.place(new Hunter(), 5, 5);
		map2.place(new Wumpus(), 1, 5);
		game.setMap(map2);
		game.determineHitOnWumpus(5, 5, -1, 0);
		
	}
	
	@Test
	public void testHazardExistsInRoom() {
		
		Map map = new Map();
		Game game = new Game();
		game.setMap(map);
		map.placeSlimePit(new SlimePit(), 5, 5);
		map.placeWumpus(new Wumpus(), 7, 7);
		assertTrue(map.getMatrix()[7][7].getContents() instanceof Wumpus);
		assertTrue(map.getMatrix()[5][5].getContents() instanceof SlimePit);
		assertTrue(game.hazardExistsInRoom(7, 7) instanceof Wumpus);
		assertTrue(game.hazardExistsInRoom(5, 5) instanceof SlimePit);
		assertTrue(game.hazardExistsInRoom(7, 8) instanceof Blood);
		assertTrue(game.hazardExistsInRoom(5, 6) instanceof Slime);
		map.placeSlimePit(new SlimePit(), 7, 5);
		assertTrue(game.hazardExistsInRoom(7, 6) instanceof Goop);
		
		
		
	}
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
	public void testWrapAround() {
		Game game = new Game();
		int[] coordinates = game.wrapAroundPositionIncrement(0, 1, -1, 0);
		assertTrue(coordinates[0] == 9);
		assertTrue(coordinates[1] == 1);
		
		coordinates = game.wrapAroundPositionIncrement(9, 0, 1, 0);
		assertTrue(coordinates[0] == 0);
		assertTrue(coordinates[1] == 0);
	}
	
	@Test
	public void testSlimePlacement() {
		Map m = new Map();
		ArrayList<SlimePit> slimePits = new ArrayList<SlimePit>();
		slimePits.add(new SlimePit());
		m.placeSlime(slimePits, 5, 5);
		assertTrue(m.getMatrix()[5][6].getContents() instanceof Slime);
		
	}
	
	@Test
	public void testIfMapIsNull() {
		Game game = new Game();
		assertNotNull(game.getMap());
		game.getMap().toString();
	}
	
	

}

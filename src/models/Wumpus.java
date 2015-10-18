package models;


public class Wumpus {
	
	int positionX, positionY;
	
	public Wumpus() {}
	
	public void setPosition(int x, int y) {
		
		this.positionX = x;
		this.positionY = y;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

}

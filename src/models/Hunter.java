package models;


public class Hunter {
	
	int positionX, positionY;
	
	public Hunter() {}
	
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

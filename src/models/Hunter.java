package models;


public class Hunter {
	
	int positionX, positionY;
	Arrow arrow;
	
	public Hunter() { this.arrow = new Arrow(); }
	
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
	
	public boolean fire() {
		if (arrow != null) {
			this.arrow = null;
			return true;
		}
		return false;
	}
	
	

}

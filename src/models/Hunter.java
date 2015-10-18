package models;


public class Hunter {
	
	int positionX, positionY;
	Arrow arrow;
	
	public Hunter() {}
	
	public void setPosition(int x, int y) {
		
		this.positionX = x;
		this.positionY = y;
		this.arrow = new Arrow();
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

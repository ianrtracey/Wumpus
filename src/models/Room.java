package models;

public class Room {
	
	boolean isVisited;
	
	public Room() {
		this.isVisited = false;
	}
	
	public void visit() {
		this.isVisited = true;
	}
	
	public boolean isVisited() {
		return this.isVisited;
	}

}

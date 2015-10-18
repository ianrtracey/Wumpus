package models;

public class Room {
	
	boolean isVisited;
	Object contents;
	
	public Room(Object object) {
		this.contents = object;
		this.isVisited = false;
	}
	
	public void visit() {
		this.isVisited = true;
	}
	
	public boolean isVisited() {
		return this.isVisited;
	}
	
	public Object getContents() {
		return contents;
	}
	
	

}

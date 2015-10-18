package models;

public class Room {
	
	boolean isVisited;
	Object contents;
	Hunter hunter;

	
	public Room(Object object) {
		this.contents = object;
		this.isVisited = false;
		this.hunter = null;

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
	
	public Hunter getHunter() {
		return  hunter;
	}
	
	public void setHunter(Hunter hunter) {
		this.hunter = hunter;
	}
	
	public void removeHunter() {
		this.hunter = null;
	}
	
	
}

package View;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import models.Game;
import models.Map;
import models.Room;

public class ButtonListenerFactory {
	
	Map map;
	Game game;
	
	public ButtonListenerFactory(Map map, JTextArea textMap, Game game) { this.map = map;
															   this.game = game; }
	
	public UpArrowListener createUpArrowListener() { return new UpArrowListener(); }
	
	public LeftArrowListener createLeftArrowListener() { return new LeftArrowListener(); }
	
	public RightArrowListener createRightArrowListener() { return new RightArrowListener(); }
	
	public DownArrowListener createDownArrowListener() { return new DownArrowListener(); }
	
	public UpFireArrowListener createUpFireArrowListener() { return new UpFireArrowListener(); }
	
	public LeftFireArrowListener createLeftFireArrowListener() { return new LeftFireArrowListener(); }
	
	public RightFireArrowListener createRightFireArrowListener() { return new RightFireArrowListener(); }
	
	public DownFireArrowListener createDownFireArrowListener() { return new DownFireArrowListener(); }
	
	
		
		public class UpArrowListener implements ActionListener {
			
			int newYPosition = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				moveInXDirection(-1);
				
			}
			
		}
		
		public class LeftArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("left arrow");
				moveInYDirection(-1);
				
				
			}
			
		}
		
		public class RightArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("right arrow");
				moveInYDirection(1);
				
			}
			
		}
		
		public class DownArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("down arrow");
				moveInXDirection(1);
				
				
			}
			
		}
		
		public class DownFireArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fire down arrow");
				fireInXDirection(1);

					
			}
			
		}
		
		public class UpFireArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fire up arrow");
				fireInXDirection(-1);
				
				
			}
			
		}
		
		public class LeftFireArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fire left arrow");
				fireInYDirection(-1);
				
				
			}
			
		}
		
		public class RightFireArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("fire right arrow");
				fireInYDirection(1);
				
				
			}
			
		}
		
		private void moveInXDirection(int incrementValue) {
			int newXPosition = 0;
			
			if (map.getHunter().getPositionX()+incrementValue < 0) {
				newXPosition = map.getXSize()-1;
			} else {
				newXPosition = (map.getHunter().getPositionX()+incrementValue) % map.getXSize();
			}
			map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()]; 
			Room room;
			if (map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()].getContents() != null) {
				room = new Room(map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()].getContents());
			} else {
				room = new Room(null);
			}
			
			room.visit();
			map.place(room, map.getHunter().getPositionX(), map.getHunter().getPositionY());
			map.getHunter().setPosition(newXPosition, map.getHunter().getPositionY());

			System.out.println("Hunter: " + map.getHunter().getPositionX() + " " +
											map.getHunter().getPositionY());

			
		}
		
		private void moveInYDirection(int incrementValue) {
			int newYPosition = 0;
			
			if (map.getHunter().getPositionY()+incrementValue < 0) {
				newYPosition = map.getXSize()-1;
			} else {
				newYPosition = (map.getHunter().getPositionY()+incrementValue) % map.getXSize();
			}
			if ( game.hazardExistsInRoom(map.getHunter().getPositionX(), newYPosition) ) {
				System.out.println("You're Dead!");
			} else {
			map.place(map.getHunter(), map.getHunter().getPositionX(), newYPosition);
			}
			Room room = new Room(null);
			room.visit();
			map.place(room, map.getHunter().getPositionX(), map.getHunter().getPositionY());
			map.getHunter().setPosition(map.getHunter().getPositionX(), newYPosition);

			System.out.println("Hunter: " + map.getHunter().getPositionX() + " " +
											map.getHunter().getPositionY());

			
		}
		
		private void fireInXDirection(int incrementValue) {
			if(game.getHunter().fire() && game.determineHitOnWumpus(game.getHunter().getPositionX()+incrementValue,
					  game.getHunter().getPositionY()) ){
			System.out.println("wumpus hit!");
			} else {
			System.out.println("you're dead!");
			}	
		}
		
		private void fireInYDirection(int incrementValue) {
			if(game.getHunter().fire() && game.determineHitOnWumpus(game.getHunter().getPositionX(),
					  game.getHunter().getPositionY()+incrementValue) ){
			System.out.println("wumpus hit!");
			} else {
			System.out.println("you're dead!");
			}	
		}
		

		
}
	


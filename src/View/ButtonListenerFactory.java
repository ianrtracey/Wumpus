package View;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import models.*;

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
			Hunter hunter = map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()].getHunter();
			map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()].removeHunter();
			Room nextRoom = map.getMatrix()[newXPosition][map.getHunter().getPositionY()];
			map.getHunter().setPosition(newXPosition, map.getHunter().getPositionY());
			nextRoom.visit();
			nextRoom.setHunter(hunter);
			map.changed();
			handleMovementMessage();
		}
		
		private void moveInYDirection(int incrementValue) {
			int newYPosition = 0;
			
			if (map.getHunter().getPositionY()+incrementValue < 0) {
				newYPosition = map.getXSize()-1;
			} else {
				newYPosition = (map.getHunter().getPositionY()+incrementValue) % map.getXSize();
			}
			Hunter hunter = map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()].getHunter();
			if (hunter == null) {
				System.out.println("hunter null");
			}
			map.getMatrix()[map.getHunter().getPositionX()][map.getHunter().getPositionY()].removeHunter();
			Room nextRoom = map.getMatrix()[map.getHunter().getPositionX()][newYPosition];
			map.getHunter().setPosition(map.getHunter().getPositionX(), newYPosition);
			nextRoom.visit();
			nextRoom.setHunter(hunter);
			map.changed();
			handleMovementMessage();

		}
		
		private void fireInXDirection(int incrementValue) {
			handleFireMessage(incrementValue,0);
		}
		
		private void fireInYDirection(int incrementValue) {
			handleFireMessage(0,incrementValue);
		}
		
		private void handleMovementMessage() {
			Object hazard = game.hazardExistsInRoom(map.getHunter().getPositionX(), map.getHunter().getPositionY());
			if (hazard != null) {
				if (hazard instanceof Wumpus) {
					map.getMapMessenger().send("WUMPUS");
				}
				if (hazard instanceof SlimePit) {
					map.getMapMessenger().send("SLIMEPIT");
				}
				if (hazard instanceof Slime) {
					map.getMapMessenger().send("SLIME");
				}
				if (hazard instanceof Blood) {
					map.getMapMessenger().send("BLOOD");
				}
				if (hazard instanceof Goop) {
					map.getMapMessenger().send("GOOP");
				}
			}
			
		}
		
		private void handleFireMessage(int incrementX, int incrementY) {
			
			if(game.getHunter().fire() && game.determineHitOnWumpus(map.getHunter().getPositionX(),
																	map.getHunter().getPositionY(),
																	incrementX, incrementY) ){
				
				map.getMapMessenger().send("HITWUMPUS");
				
			} else {
				
				map.getMapMessenger().send("MISSEDWUMPUS");
			}
			
		}
		

		
}
	


package view;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import models.Map;
import models.Room;

public class ButtonListenerFactory {
	
	Map map;
	JTextArea textMap;
	
	public ButtonListenerFactory(Map map, JTextArea textMap) { this.map = map;
															   this.textMap = textMap; }
	
	public UpArrowListener createUpArrowListener() { return new UpArrowListener(); }
	
	public LeftArrowListener createLeftArrowListener() { return new LeftArrowListener(); }
	
	public RightArrowListener createRightArrowListener() { return new RightArrowListener(); }
	
	public DownArrowListener createDownArrowListener() { return new DownArrowListener(); }
	
	
		
		public class UpArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("up arrow");
				int newPosition = (map.getHunter().getPositionX()+1) % map.getYSize();
				map.place(map.getHunter(), map.getHunter().getPositionX(), newXPosition);
				Room room = new Room();
				room.visit();
				map.place(room, map.getHunter().getPositionX(), map.getHunter().getPositionY());
				map.getHunter().setPosition(map.getHunter().getPositionX(), newYPosition);
				System.out.println("Hunter: " + map.getHunter().getPositionX() + " " +
												map.getHunter().getPositionY());
				textMap.setText(map.toString());
				
			}
			
		}
		
		public class LeftArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("left arrow");
				
			}
			
		}
		
		public class RightArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("right arrow");
				
			}
			
		}
		
		public class DownArrowListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("down arrow");
				
			}
			
		}
		
}
	


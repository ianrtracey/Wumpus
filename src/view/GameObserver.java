package View;
import java.util.Observable;
import java.util.Observer;

import models.Game;

public class GameObserver implements Observer {
	
	MainGUI gui;
	Game game;
	
	public GameObserver(Game game) {
		this.game = game;
	}
	
	public void registerGUI(MainGUI gui) {
		this.gui = gui;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("GAME HAS BEEN CHANGED");
		
	}
	

}

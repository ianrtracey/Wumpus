package View;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import models.Map;
import models.Room;

public class GraphicsView extends JPanel implements Observer{
	// using a IMG prefix and caps here like pretend constants just because...
	private Image IMG_GOOP;
	private Image IMG_BLOOD;
	private Image IMG_GROUND;
	private Image IMG_SLIME;
	private Image IMG_PIT;
	private Image IMG_PLAYER;
	private Image IMG_PLAYLINK;
	private Image IMG_CACO;
	private Image IMG_WUMPUS;
	private Image IMG_CLOUDS;
	private Image IMG_LEST; // <------ DELETE THIS BEFORE SUBMIT
	
	Map gameMap;
	Room[][] gameState;
	
	public GraphicsView(Map theMap){
		this.gameMap = theMap;
		this.gameState = gameMap.getMatrix();
		this.loadImages();
	} // Ends Constructor

	private void loadImages() {	
		try {
			IMG_GOOP     = ImageIO.read(new File("./img/Goop.png"));
			IMG_BLOOD    = ImageIO.read(new File("./img/Blood.png"));
			IMG_GROUND   = ImageIO.read(new File("./img/Ground.png"));
			IMG_SLIME    = ImageIO.read(new File("./img/Slime.png"));
			IMG_PIT      = ImageIO.read(new File("./img/SlimePit.png"));
			IMG_PLAYER   = ImageIO.read(new File("./img/TheHunter.png"));
			IMG_WUMPUS   = ImageIO.read(new File("./img/Wumpus.png"));
			IMG_CLOUDS   = ImageIO.read(new File("./img/Clouds.png"));
			IMG_CACO     = ImageIO.read(new File("./img/Cacodemon.png")); 
			IMG_PLAYLINK = ImageIO.read(new File("./img/PlayerLink.png"));
			IMG_LEST     = ImageIO.read(new File("./img/Lest.png")); // <------ DELETE THIS BEFORE SUBMIT
		} catch (Exception e) {
			System.out.println(">>> ERROR: Could not load image resource(s). \n    Closing Program...");
			System.exit(1); // Quit program cause we want image resources to load
		}	
	} // Ends Method loadImages
	
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Graphics2D g2 = (Graphics2D) g;
	    
	    int curRow = 0;
	    int curCol = 0;
	    for (int c = 0; c < 500; c += 50){
	    	curCol = 0;
	      for (int r = 0; r < 500; r += 50){
	    	  
	    	  // Ground / Hidden Clouds layer first
	    	  if(!gameState[curRow][curCol].isVisited()){
	    		  g2.drawImage(IMG_CLOUDS, r, c, null);
	    	  }
	    	  else {
	    		  g2.drawImage(IMG_GROUND, r, c, null);
	    	  
		    	  if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'S'){g2.drawImage(IMG_SLIME, r, c, null);}
		    	  if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'B'){g2.drawImage(IMG_BLOOD, r, c, null);}
		    	  if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'G'){g2.drawImage(IMG_GOOP,  r, c, null);}
		    	  if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'P'){g2.drawImage(IMG_PIT,   r, c, null);}	    	  
		    	  
		    	  // Finally: Actors	    	  
		    	  if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'O'){g2.drawImage(IMG_PLAYLINK, r, c, null);}
		    	  if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'W'){g2.drawImage(IMG_LEST  , r, c, null);} //   <------ MCCANN EASTER EGG - DELETE THIS BEFORE SUBMIT
		    	  //if(gameMap.objectSymbol(gameState[curRow][curCol].getContents()) == 'W'){g2.drawImage(IMG_WUMPUS, r, c, null);} // <------ THIS IS THE REAL ONE 	  
	    	  }   
	    	  curCol++;
	      }
	    curRow++;
	  }
	} // Ends Method paintComponent

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}
} // Ends Class GraphicsView

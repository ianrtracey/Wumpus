package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicsView extends JPanel{
	// using a IMG prefix and caps here like pretend constants just because...
	private Image IMG_GOOP;
	private Image IMG_BLOOD;
	private Image IMG_GROUND;
	private Image IMG_SLIME;
	private Image IMG_PIT;
	private Image IMG_PLAYER;
	private Image IMG_WUMPUS;
	private Image IMG_CLOUDS;
	private Image IMG_LEST; // <------ DELETE THIS BEFORE SUBMIT
	
	// TEMP REPRESENTATION - NEED TO GET THE BOARD STATUS (THE UPDATED MAP)
	private char [][] TEMP_MATRIX = { 	{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', },
										{ 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'X', 'X', 'X', },
										{ 'X', 'X', 'X', 'S', 'P', 'S', 'X', 'B', 'X', 'X', },
										{ 'X', 'X', 'X', 'X', 'S', 'X', 'X', 'B', 'X', 'X', },
										{ 'X', 'X', 'X', 'S', 'X', 'B', 'B', 'W', 'B', 'B', },
										{ 'X', 'X', 'S', 'P', 'S', 'S', 'X', 'B', 'X', 'X', },
										{ 'X', 'X', 'X', 'S', 'S', 'P', 'S', 'B', 'X', 'X', },
										{ 'X', 'X', 'X', 'X', 'X', 'S', 'S', 'X', 'X', 'X', },
										{ 'X', 'O', 'X', 'X', 'X', 'S', 'P', 'S', 'X', 'S', },
										{ 'X', 'X', 'X', 'X', 'X', 'X', 'S', 'X', 'S', 'P', }
									};

	public GraphicsView(){
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
	    	  if(TEMP_MATRIX[curRow][curCol] == 'X'){g2.drawImage(IMG_CLOUDS, r, c, null);}
	    	  else                                  {g2.drawImage(IMG_GROUND, r, c, null);}
	    	  
	    	  // Then Environment layer
	    	  if(TEMP_MATRIX[curRow][curCol] == 'S'){g2.drawImage(IMG_SLIME, r, c, null);}
	    	  if(TEMP_MATRIX[curRow][curCol] == 'B'){g2.drawImage(IMG_BLOOD, r, c, null);}
	    	  if(TEMP_MATRIX[curRow][curCol] == 'G'){g2.drawImage(IMG_GOOP,  r, c, null);}
	    	  if(TEMP_MATRIX[curRow][curCol] == 'P'){g2.drawImage(IMG_PIT,   r, c, null);}
	    	  
	    	  // Finally: Actors	    	  
	    	  if(TEMP_MATRIX[curRow][curCol] == 'O'){g2.drawImage(IMG_PLAYER, r, c, null);}
	    	  if(TEMP_MATRIX[curRow][curCol] == 'W'){g2.drawImage(IMG_LEST  , r, c, null);} //   <------ MCCANN EASTER EGG - DELETE THIS BEFORE SUBMIT
	    	  //if(TEMP_MATRIX[curRow][curCol] == 'W'){g2.drawImage(IMG_WUMPUS, r, c, null);} // <------ THIS IS THE REAL ONE
	    	  curCol++;
	      }
	    curRow++;
	  }
	} // Ends Method paintComponent
} // Ends Class GraphicsView

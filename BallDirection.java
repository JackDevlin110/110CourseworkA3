import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
* <h1>Jack's Brick Breaker Game</h1>
* The Brick Breaker program implements an application that
* runs a simple ball game that the user can attempt to complete
* by hitting all the blocks on the screen before they reach the bottom.
* <p>
* 
* @author Jack Devlin
* @version 1.0
*/ 

public class Start extends JPanel implements MouseListener,KeyListener, ActionListener{
	private boolean go = false, brickHere = false, notBot = true, mouseClicked = false;
	private int score = 0, wait = 8, mouseClickX = 0, mouseClickY = 0, amountBricks = 21;
	private Timer clock;
	private int bPosX = 340, bPosY = 975, newBricks = 0;
	private double ballXDir, ballYDir;
	
	
	private Environment map;
	
	/**
	* This constructor creates the environemnt that will be used
	* to implement the game.
	*/
	public Start() {
		map = new Environment(3,7);
		addMouseListener(this);
		addKeyListener(this);
		clock = new Timer(wait, this);
		clock.start();
	}
	
	/**
	* This method paints the environment different ways and
	* portrays how the user will see the window that the game is
	* being run on.
	*/
	public void paint(Graphics g) {
		
		//Paints the Background.
		g.setColor(Color.black);
		g.fillRect(1, 1, 700, 1000);
		
		//Paints the Scoreboard.
		g.setColor(Color.yellow);
		g.setFont(new Font("comic sans",Font.BOLD, 30));
		g.drawString("Score: "+score, 40, 40);
		
		//Counts the Clicks.
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		
		
		//Paint the Map.
		map.draw((Graphics2D)g);
		if (map.bricksatBottom == true) {
			loseState();
		}
		
		//Paints the Ball
		g.setColor(Color.YELLOW);
		g.fillOval(bPosX, bPosY, 20, 20);
		g.dispose();
	}
	
	/**
	* This method depicts the direction that the ball will travel and
	* assigns it to different variables.
	*/
	public void ballDirection(){
		double XDir;
		double YDir;
		
		XDir = mouseClickX - bPosX;
		YDir = mouseClickY - bPosY;
		
		XDir = XDir/100;
		YDir = YDir/100;
		
		ballXDir = XDir;
		ballYDir = YDir;
	}
	
	/**
	* This method implements the user clicking to which direction
	* the ball is going to travel on the screen.
	*/
	@Override
	public void mouseClicked(MouseEvent e) {
		if (mouseClicked==false) {
			mouseClicked = true;
			mouseClickX = e.getX();
			mouseClickY = e.getY();
			ballDirection();
			System.out.println(ballXDir);
			System.out.println(ballYDir);
			go = true;
		}

	}
	

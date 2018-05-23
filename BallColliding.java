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
	
	/**
	* This method shows how the ball is going to move and how it will
	* intersect with the different bricks in the window. It also allows
	* the ball to bounce off the bricks and the border of the window to
	* contain the ball within the environment.
	*/
	public void moveBall() {
		clock.start();
		if(go && notBot) {
			//Second map produced is the one being displayed in the environment.
			for(int i =0; i<map.map.length; i++ ) {
				for (int j =0; j<map.map[0].length; j++) {
					
					if(map.map[i][j]>0) {
						int brickX = j*map.brickW + 80;
						int brickY = i* map.brickH + (map.amountFall * 50);
						int brickW = map.brickW;
						int brickH = map.brickH;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickW, brickH);
						Rectangle ballRect = new Rectangle(bPosX, bPosY,20,20);
						Rectangle brickRect = rect;
						
						//When the ball hits the brick, the score counter is increased by 1.
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0,i,j);
							amountBricks--;
							
							score +=1;
							
							if((bPosX +19) <= brickRect.x || (bPosX +1) >= (brickRect.x +brickRect.width)) {
								ballXDir = -ballXDir;
							}
							else {
								ballYDir = -ballYDir;
							}
						
							
						}
					}
				}
			}
			
				bPosX += ballXDir;
				bPosY += ballYDir;
				
				//Change direction of ball if it collides with left panel.
				if (bPosX < 0) {
					ballXDir = -ballXDir;
				}
				//Change direction of ball if it collides with top panel.
				if (bPosY < 0 ) {
					ballYDir = -ballYDir;
				}
				//Change direction of ball if it collides with right panel.
				if (bPosX > 695) {
					ballXDir = - ballXDir;
				}
				//Resets the ball if it collides with bottom panel.
				if (bPosY>1000) {
					notBot = false;
						resetBall();
						brickHere = false;
						map.amountFall++;
				}
				//Detect whether the user has won the game.
				if (amountBricks == 0) {
					winState();
				}
				//This will reset the style of the screen.
				repaint();
				
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		moveBall();
	}
	

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
* <h1>Jack's Brick Breaker Game Environment</h1>
* This program implements the environment of the window so that the
* game can run properly.
* <p>
* 
* @author Jack Devlin
* @version 1.0
*/ 
public class Environment {
	public int map[][];
	public int brickW;
	public int brickH;
	public int amountFall =1;
	public boolean bricksatBottom = false;
	
	public Environment (int row,int col) {
		map = new int[row][col];
		for(int i=-0;i<map.length;i++) {
			for(int j = 0;j<map[0].length;j++) {
				map[i][j]=1;
			}
		}
		
		//This sets the width and height of the bricks to fit on the screen
		brickW = 540/7;
		brickH = 150/3;
	}
	
	public void draw(Graphics2D g) {
		for(int i=-0;i<map.length;i++) {
			for(int j = 0;j<map[0].length;j++) {
				if(map[i][j] >0) {
						if (i*brickH + (amountFall *50) > 924) {
							bricksatBottom = true;
						}
						else {
							g.setColor(Color.WHITE);
							g.fillRect(j*brickW +80, i*brickH +(amountFall * 50), brickW, brickH);
							
							g.setStroke(new BasicStroke(3));
							g.setColor(Color.black);
							g.drawRect(j*brickW +80, i*brickH +(amountFall * 50), brickW, brickH);
						}
				}
			}
		}
	}
	
	public void setBrickValue(int value,int row, int col) {
		map[row][col] = value;
	}
}

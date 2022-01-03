package brickBallBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class brickMapping {
	public int[][] brickMap;
	public int brickWidth;
	public int brickHeight;
	
	public brickMapping(int row,int col) {
		brickMap = new int[row][col];
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				brickMap[i][j] = 1;
			}
		}
		brickWidth = 570 / col;
		brickHeight = 140 / row;
	}
	
	public void draw(Graphics2D g2d) {
		for(int i=0;i<brickMap.length;i++) {
			for(int j=0;j<brickMap[0].length;j++) {
				if(brickMap[i][j] > 0) {
					// draw brick
					g2d.setColor(Color.RED);
					g2d.fillRect(j * brickWidth + 60, i * brickHeight + 60, brickWidth, brickHeight);
					
					// add borders to brick
					g2d.setStroke(new BasicStroke(3));
					g2d.setColor(Color.BLACK);
					g2d.drawRect(j * brickWidth + 60, i * brickHeight + 60, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int val,int row, int col) {
		brickMap[row][col] = val;
	}
}

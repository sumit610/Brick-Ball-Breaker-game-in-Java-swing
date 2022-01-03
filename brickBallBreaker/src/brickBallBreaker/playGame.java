package brickBallBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class playGame extends JPanel implements KeyListener,ActionListener{
    private boolean playing = false;
    private int gameScore = 0;
    private int totalBricks = 30;
    
	private Timer time;
	private int delay = 6;
	
	private int playerX = 310;
	
	private int ballXaxis = 310;
	private int ballYaxis = 230;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private brickMapping brickMap;
	
	public playGame() {
		brickMap = new brickMapping(3, 10);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics graph) {
		//background
		graph.setColor(Color.BLACK);
		graph.fillRect(1, 1, 691, 591);
		
		// draw bricks map
		brickMap.draw((Graphics2D)graph);
		
		// borders
		graph.setColor(Color.YELLOW);
		graph.fillRect(0, 0, 3, 591);
		graph.fillRect(0, 0, 691, 3);
		graph.fillRect(681, 0, 3, 591);
		
		//scores
		graph.setColor(Color.WHITE);
		graph.setFont(new Font("serif",Font.BOLD,25));
		graph.drawString(""+gameScore, 590, 30);
		
		// the paddle
		graph.setColor(Color.BLUE);
		graph.fillRect(playerX, 550, 170,60);
		
		// the ball
		graph.setColor(Color.GREEN);
		graph.fillOval(ballXaxis, ballYaxis, 25,25);
		
		if(ballYaxis > 570 || totalBricks <= 0) {
			playing = false;
			ballXdir = 0;
			ballYdir = 0;
			graph.setColor(Color.red);
			graph.setFont(new Font("serif",Font.BOLD,30));
			if(totalBricks <= 0) {
				graph.drawString( "You Won ! Scores : "+ gameScore, 190, 300);
			}else {
				graph.drawString( "Game over, Scores : "+ gameScore, 190, 300);
			}
			graph.setFont(new Font("serif",Font.BOLD,20));
			graph.drawString( "Press enter to restart game... ", 230, 340);
		}
		
		// destroy the JFrame
		graph.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		if(playing) {
			//ball colliding with paddle case
			if(new Rectangle(ballXaxis, ballYaxis, 25,25).intersects(new Rectangle(playerX, 550, 170,60))) {
				ballYdir = -ballYdir;
			}
			
			// ball hitting the brick case
			A: for(int i=0;i < brickMap.brickMap.length;i++) {
				for(int j=0;j < brickMap.brickMap[0].length;j++) {
					if(brickMap.brickMap[i][j] > 0) {
						int brickHeight =  brickMap.brickHeight;
						int brickWidth =  brickMap.brickWidth;
						int brickY = i * brickMap.brickHeight + 60;
						int brickX = j * brickMap.brickWidth + 60;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle brkBall = new Rectangle(ballXaxis, ballYaxis, 25, 25);
						Rectangle brkRect = rect;
						
						if(brkBall.intersects(brkRect)) {
							// removing brick
							brickMap.setBrickValue(0, i, j);
							totalBricks--;
							gameScore += 10;
							
							if(ballXaxis + 15 < brkRect.x || ballXaxis + 1 > brkRect.x + brkRect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = -ballYdir;
							}
							
							break A;
						}
					}
				}
			}
			
			// ball travelling downwards
			ballXaxis += ballXdir;
			ballYaxis += ballYdir;
			//ball hitting left side wall
			if(ballXaxis < 0) {
				ballXdir = -ballXdir;
			}
			//ball hitting upper wall
			if(ballYaxis < 0) {
				ballYdir = -ballYdir;
			}
			//ball hitting right side wall
			if(ballXaxis > 670) {
				ballXdir = -ballXdir;
			}
		}
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// moving paddle right
			if(playerX >= 600) {
				playerX = 600;
			}else {
				movePaddleRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			// moving paddle left
			if(playerX < 10 ) {
				playerX = 10;
			}else {
				movePaddleLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!playing) {
				playing = true;
				ballXaxis = 310;
				ballYaxis = 230;
				ballXdir = -1;
				ballYdir = -2;
				gameScore = 0;
				totalBricks = 30;
				playerX = 310;
				brickMap = new brickMapping(3, 10);
				repaint();
			}
		}
		
	}

	private void movePaddleLeft() {
		// TODO Auto-generated method stub
		playing = true;
		playerX -= 20;
	}

	private void movePaddleRight() {
		// TODO Auto-generated method stub
		playing = true;
		playerX += 20;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

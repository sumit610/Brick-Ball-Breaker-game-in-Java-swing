package brickBallBreaker;

import javax.swing.JFrame;

public class gameEngine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frameObj = new JFrame();
		playGame game = new playGame();
		frameObj.setBounds(10,10,700,600);
		frameObj.setTitle("Brick Ball Breaker V 1.0");
		frameObj.setResizable(true);
		frameObj.setVisible(true);
		frameObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameObj.add(game);
	}

}

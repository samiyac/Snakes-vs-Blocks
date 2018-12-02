package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Ball objects.
 */
public class BallFactory {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new ball factory.
	 *
	 * @param main the main
	 */
	public BallFactory(Main main) {
		this.main = main;
	}

	/**
	 * Ball animation.
	 */
	public void BallAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new BallHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * The Class BallHandler.
	 */
	private class BallHandler implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(ActionEvent event) {
			if (!main.isBLOCK_HIT() && !main.isEnd()) {
				ArrayList<ArrayList<Ball>> BallsOnScreen = main.getBallsOnScreen();
				for (int i = 0; i < BallsOnScreen.size(); i++) {
					for (int j = 0; j < BallsOnScreen.get(i).size(); j++) {
						StackPane St = BallsOnScreen.get(i).get(j).getPane();
						St.setTranslateY(St.getTranslateY() + main.getVelocity());
					}
				}
				checkBallScroll();
			}

		}
	}

	/**
	 * Check ball scroll.
	 */
	public void checkBallScroll() {
		ArrayList<ArrayList<Ball>> BallsOnScreen = main.getBallsOnScreen();
		if (BallsOnScreen.size() > 0) {
			if (!BallsOnScreen.get(BallsOnScreen.size() - 1).isEmpty()) {
				Ball ball1 = BallsOnScreen.get(BallsOnScreen.size() - 1).get(0);
				if ((int) ball1.getPane().getTranslateY() > 280 - (int) ball1.getPane().getLayoutY()) {
					setBalls(-50);
				}
			}

			if (!BallsOnScreen.get(0).isEmpty()) {
				Ball ball2 = BallsOnScreen.get(0).get(0);
				if (ball2.getPane().getTranslateY() >= 1300) {
					BallsOnScreen.remove(0);
				}
			}
		}
	}

	/**
	 * Sets the balls.
	 *
	 * @param dis the new balls
	 */
	public void setBalls(int dis) {
		ArrayList<ArrayList<Ball>> BallsOnScreen = main.getBallsOnScreen();
		System.out.println("setting balls");
		ArrayList<Ball> ballList = new ArrayList<>();
		Random rand = new Random();
		int c = ((int) (Math.random() * 2) + 1);
		for (int i = 0; i < c; i++) {
			int x = (int) (Math.random() * 400 + 15);
			int y = (int) (dis);
			while (!checkBallPosition(x, y, ballList)) {
				x = (int) (Math.random() * 400 + 15);
				y = y - 1;
			}
			Ball s = new Ball(x, y, 10, main.getColorBall(), main.getMode());
			ballList.add(s);
			main.getRoot().getChildren().add(s.getPane());
		}
		BallsOnScreen.add(ballList);
	}

	/**
	 * Check ball position.
	 *
	 * @param x the poition x
	 * @param y the position y
	 * @param ballList the list of balls currently on screen
	 * @return true, if successful
	 */
	private boolean checkBallPosition(int x, int y, ArrayList<Ball> ballList) {
		ArrayList<ArrayList<Block>> BlockOnScreen = main.getBlockOnScreen();
		ArrayList<ArrayList<Wall>> WallsOnScreen = main.getWallsOnScreen();
		Shield ShieldOnScreen = main.getShieldOnScreen();
		Magnet MagnetOnScreen = main.getMagnetOnScreen();
		DestroyBlock DestroyBlockOnScreen = main.getDestroyBlockOnScreen();
		for (int j = 0; j < BlockOnScreen.size(); j++) {
			for (int k = 0; k < BlockOnScreen.get(j).size(); k++) {
				StackPane block = BlockOnScreen.get(j).get(k).getStack();
				int blockPosX = (int) block.getLayoutX();
				int blockPosY = (int) (block.getLayoutY() + block.getTranslateY());
				if (x >= blockPosX - 20 && x <= blockPosX + 120 && y >= blockPosY - 20 && y <= blockPosY + 120) {
					return false;
				}
			}
		}
		for (int j = 0; j < ballList.size(); j++) {
			float ballPosX = ballList.get(j).getLOCATION_X();
			float ballPosY = ballList.get(j).getLOCATION_Y();
			if (x >= ballPosX - 30 && x <= ballPosX + 30 && y >= ballPosY - 30 && y <= ballPosY + 30) {
				return false;
			}
		}
		for (int j = 0; j < WallsOnScreen.size(); j++) {
			for (int k = 0; k < WallsOnScreen.get(j).size(); k++) {
				int wallPosX = (int) WallsOnScreen.get(j).get(k).getLocationX();
				int wallPosY = (int) (WallsOnScreen.get(j).get(k).getLoactionY()
						+ WallsOnScreen.get(j).get(k).getRect().getTranslateY());
				int heightWall = (int) WallsOnScreen.get(j).get(k).getHeight();
				if (x >= wallPosX - 40 && x <= wallPosX + 40 && y >= wallPosY - 40 && y <= wallPosY + heightWall + 40) {
					return false;
				}
			}
		}

		if (MagnetOnScreen != null) {
			StackPane magnet = MagnetOnScreen.getStack();
			int magnetPosX = (int) magnet.getLayoutX();
			int magnetPosY = (int) (magnet.getTranslateY());
			if (x >= magnetPosX - 40 && x <= magnetPosX + 40 && y >= magnetPosY - 40 && y <= magnetPosY + 40) {
				return false;
			}
		}

		if (DestroyBlockOnScreen != null) {
			StackPane DB = DestroyBlockOnScreen.getStack();
			int DBPosX = (int) DB.getLayoutX();
			int DBPosY = (int) (DB.getTranslateY());
			if (x >= DBPosX - 40 && x <= DBPosX + 40 && y >= DBPosY - 40 && y <= DBPosY + 40) {
				return false;
			}
		}

		if (ShieldOnScreen != null) {
			StackPane shield = ShieldOnScreen.getStack();
			int shieldPosX = (int) shield.getLayoutX();
			int shieldPosY = (int) (shield.getTranslateY());
			if (x >= shieldPosX - 40 && x <= shieldPosX + 40 && y >= shieldPosY - 40 && y <= shieldPosY + 40) {
				return false;
			}
		}
		return true;
	}

}

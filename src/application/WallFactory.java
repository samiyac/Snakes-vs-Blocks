/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;


import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Wall objects.
 */
public class WallFactory {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new wall factory.
	 *
	 * @param main the main
	 */
	public WallFactory(Main main) {
		this.main = main;
	}
	
	/**
	 * Wall animation.
	 */
	public void WallAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new WallHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * The Class WallHandler.
	 */
	private class WallHandler implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(ActionEvent event) {
			if (!main.isBLOCK_HIT() && !main.isEnd()) {
				ArrayList<ArrayList<Wall>> WallsOnScreen=main.getWallsOnScreen();
				for (int i = 0; i < WallsOnScreen.size(); i++) {
					for (int j = 0; j < WallsOnScreen.get(i).size(); j++) {
						Wall wall = WallsOnScreen.get(i).get(j);
						wall.getRect().setTranslateY(wall.getRect().getTranslateY() + main.getVelocity());
					}
				}
				checkWallScroll();
			}

		}
	}

	/**
	 * Check wall scroll.
	 */
	void checkWallScroll() {
		if (!main.getWallsOnScreen().isEmpty()) {
			ArrayList<Wall> W1 = main.getWallsOnScreen().get(main.getWallsOnScreen().size() - 1);
			float maxHeight = 0;
			Wall reqWall = null;
			for (Wall wall : W1) {
				if (wall.getHeight() > maxHeight) {
					maxHeight = wall.getHeight();
					reqWall = wall;
				}
			}
			if (reqWall != null) {
				if ((int) (reqWall.getRect().getTranslateY() + reqWall.getRect().getY()) >= 150) {
					setWalls();
				}
			}
			W1 = main.getWallsOnScreen().get(0);
			maxHeight = 0;
			reqWall = null;
			for (Wall wall : W1) {
				if (wall.getHeight() > maxHeight) {
					maxHeight = wall.getHeight();
					reqWall = wall;
				}
			}
			if (reqWall != null) {
				if ((int) reqWall.getRect().getTranslateY() >= 1000 + maxHeight - reqWall.getRect().getY()) {
					main.getWallsOnScreen().remove(0);
				}
			}
		}
	}

	/**
	 * Sets the walls.
	 */
	public void setWalls() {
		System.out.println("setting walls");
		int[] xCoord = { 100, 200, 300, 400 };
		ArrayList<Wall> W = new ArrayList<>();
		int c = (int) (Math.random() * 15) + 1;
		String bin = Integer.toBinaryString(c);
		while (bin.length() < 4) {
			bin = "0".concat(bin);
		}
		for (int i = 0; i < bin.length(); i++) {
			int h = (int) (Math.random() * 750 + 150);
			int y = -h;
			if (bin.charAt(i) == '1') {
				if (checkWallPosition(xCoord[i], y, h)) {
					Wall wall = new Wall(xCoord[i], y, h);
					W.add(wall);
					main.getRoot().getChildren().add(wall.getRect());
				}
			}
		}
		if (!W.isEmpty()) {
			main.getWallsOnScreen().add(W);
		}
	}

	/**
	 * Check wall position.
	 *
	 * @param x the position x
	 * @param y the position y
	 * @param h the height
	 * @return true, if successful
	 */
	private boolean checkWallPosition(int x, int y, int h) {
		// TODO Auto-generated method stub
		if (main.getMagnetOnScreen() != null) {
			StackPane magnet = main.getMagnetOnScreen().getStack();
			int magnetPosX = (int) magnet.getLayoutX();
			int magnetPosY = (int) (magnet.getTranslateY());
			if (x >= magnetPosX - 30 && x <= magnetPosX + 30 && magnetPosY >= y - 20 && magnetPosY < y + h + 20) {
				return false;
			}
		}

		if (main.getDestroyBlockOnScreen() != null) {
			StackPane DB = main.getDestroyBlockOnScreen().getStack();
			int DBPosX = (int) DB.getLayoutX();
			int DBPosY = (int) (DB.getTranslateY());
			if (x >= DBPosX - 30 && x <= DBPosX + 30 && DBPosY >= y - 20 && DBPosY < y + h + 20) {
				return false;
			}
		}

		if (main.getShieldOnScreen() != null) {
			StackPane shield = main.getShieldOnScreen().getStack();
			int shieldPosX = (int) shield.getLayoutX();
			int shieldPosY = (int) (shield.getTranslateY());
			if (x >= shieldPosX - 30 && x <= shieldPosX + 30 && shieldPosY >= y - 20 && shieldPosY < y + h + 20) {
				return false;
			}
		}
		return true;
	}

}

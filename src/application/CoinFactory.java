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
 * A factory for creating Coin objects.
 */
public class CoinFactory {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new coin factory.
	 *
	 * @param main the main
	 */
	public CoinFactory(Main main) {
		this.main = main;
	}

	/**
	 * Coin animation.
	 */
	public void CoinAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new CoinHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * The Class CoinHandler.
	 */
	private class CoinHandler implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(ActionEvent event) {
			if (!main.isBLOCK_HIT() && !main.isEnd()) {
				ArrayList<ArrayList<Coin>> CoinsOnScreen=main.getCoinsOnScreen();
				for (int i = 0; i < CoinsOnScreen.size(); i++) {
					for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
						StackPane St = CoinsOnScreen.get(i).get(j).getStack();
						St.setTranslateY(St.getTranslateY() + main.getVelocity());
					}
				}
				checkCoinScroll();
			}

		}
	}
	
	/**
	 * Check coin scroll.
	 */
	public void checkCoinScroll() {
		if (main.getCoinsOnScreen().size() > 0) {
			if (!main.getCoinsOnScreen().get(main.getCoinsOnScreen().size() - 1).isEmpty()) {
				Coin coin1 = main.getCoinsOnScreen().get(main.getCoinsOnScreen().size() - 1).get(0);
				if ((int) coin1.getStack().getTranslateY() > 280 - (int) coin1.getStack().getLayoutY()) {
					setCoins(-70);
				}
			} else {
				setCoins(-70);
			}

			if (!main.getCoinsOnScreen().get(0).isEmpty()) {
				Coin coin2 = main.getCoinsOnScreen().get(0).get(0);
				if (coin2.getStack().getTranslateY() >= 1300) {
					main.getCoinsOnScreen().remove(0);
				}
			}
		} else {
			setCoins(-70);
		}
	}

	/**
	 * Sets the coins.
	 *
	 * @param dis the new coins
	 */
	public void setCoins(int dis) {
		System.out.println("setting coins");

		ArrayList<Coin> coinList = new ArrayList<>();
		int c = (int) (Math.random() * 1 + 1);
		for (int i = 0; i < c; i++) {
			int x = (int) (Math.random() * 200 + 15);
			int y = (int) (dis);
			while (!checkCoinPosition(x, y, coinList) && y >= -100) {
				x = (int) (Math.random() * 200 + 15);
				y = y - 1;
			}
			if (y >= -100) {
				System.out.println("setting");
				Coin s = new Coin(x, y, main.getMode());
				coinList.add(s);
				main.getRoot().getChildren().add(s.getStack());
			}
		}
		main.getCoinsOnScreen().add(coinList);
	}

	/**
	 * Check coin position.
	 *
	 * @param x the x
	 * @param y the y
	 * @param coinList the coin list
	 * @return true, if successful
	 */
	private boolean checkCoinPosition(int x, int y, ArrayList<Coin> coinList) {

		for (int j = 0; j < main.getBallsOnScreen().size(); j++) {
			for (int k = 0; k < main.getBallsOnScreen().get(j).size(); k++) {
				StackPane ball = main.getBallsOnScreen().get(j).get(k).getPane();
				int ballPosX = (int) ball.getLayoutX();
				int ballPosY = (int) (ball.getLayoutY() + ball.getTranslateY());
				if (x >= ballPosX - 40 && x <= ballPosX + 40 && y >= ballPosY - 40 && y <= ballPosY + 40) {
					return false;
				}
			}
		}

		for (int j = 0; j < main.getBlockOnScreen().size(); j++) {
			for (int k = 0; k < main.getBlockOnScreen().get(j).size(); k++) {
				StackPane block = main.getBlockOnScreen().get(j).get(k).getStack();
				int blockPosX = (int) block.getLayoutX();
				int blockPosY = (int) (block.getLayoutY() + block.getTranslateY());
				if (x >= blockPosX - 20 && x <= blockPosX + 120 && y >= blockPosY - 20 && y <= blockPosY + 120) {
					return false;
				}
			}
		}
		for (int j = 0; j < coinList.size(); j++) {
			float coinPosX = coinList.get(j).getLOCATION_X();
			float coinPosY = coinList.get(j).getLOCATION_Y();
			if (x >= coinPosX - 40 && x <= coinPosX + 40 && y >= coinPosY - 40 && y <= coinPosY + 40) {
				return false;
			}
		}
		for (int j = 0; j < main.getWallsOnScreen().size(); j++) {
			for (int k = 0; k < main.getWallsOnScreen().get(j).size(); k++) {
				int wallPosX = (int) main.getWallsOnScreen().get(j).get(k).getLocationX();
				int wallPosY = (int) (main.getWallsOnScreen().get(j).get(k).getLoactionY()
						+ main.getWallsOnScreen().get(j).get(k).getRect().getTranslateY());
				int heightWall = (int) main.getWallsOnScreen().get(j).get(k).getHeight();
				if (x >= wallPosX - 40 && x <= wallPosX + 40 && y >= wallPosY - 40 && y <= wallPosY + heightWall + 40) {
					return false;
				}
			}
		}

		if (main.getMagnetOnScreen() != null) {
			StackPane magnet = main.getMagnetOnScreen().getStack();
			int magnetPosX = (int) magnet.getLayoutX();
			int magnetPosY = (int) (magnet.getLayoutY() + magnet.getTranslateY());
			if (x >= magnetPosX - 40 && x <= magnetPosX + 40 && y >= magnetPosY - 40 && y <= magnetPosY + 40) {
				return false;
			}
		}

		if (main.getDestroyBlockOnScreen() != null) {
			StackPane DB = main.getDestroyBlockOnScreen().getStack();
			int DBPosX = (int) DB.getLayoutX();
			int DBPosY = (int) (DB.getLayoutY() + DB.getTranslateY());
			if (x >= DBPosX - 40 && x <= DBPosX + 40 && y >= DBPosY - 40 && y <= DBPosY + 40) {
				return false;
			}
		}

		if (main.getShieldOnScreen() != null) {
			StackPane shield = main.getShieldOnScreen().getStack();
			int shieldPosX = (int) shield.getLayoutX();
			int shieldPosY = (int) (shield.getLayoutY() + shield.getTranslateY());
			if (x >= shieldPosX - 40 && x <= shieldPosX + 40 && y >= shieldPosY - 40 && y <= shieldPosY + 40) {
				return false;
			}
		}

		return true;
	}

}

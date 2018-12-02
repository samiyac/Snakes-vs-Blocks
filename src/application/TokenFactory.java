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
 * A factory for creating Token objects.
 */
public class TokenFactory {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new token factory.
	 *
	 * @param main the main
	 */
	public TokenFactory(Main main) {
		this.main = main;
	}
	
	/**
	 * Token animation.
	 */
	public void TokenAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new TokenHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * The Class TokenHandler.
	 */
	private class TokenHandler implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(ActionEvent event) {
			if (!main.isBLOCK_HIT() && !main.isEnd()) {
				if (main.getMagnetOnScreen() != null) {
					StackPane magnet = main.getMagnetOnScreen().getStack();
					magnet.setTranslateY(magnet.getTranslateY() + main.getVelocity());
				}
				if (main.getShieldOnScreen() != null) {
					StackPane shield = main.getShieldOnScreen().getStack();
					shield.setTranslateY(shield.getTranslateY() + main.getVelocity());
				}
				if (main.getDestroyBlockOnScreen() != null) {
					StackPane DB = main.getDestroyBlockOnScreen().getStack();
					DB.setTranslateY(DB.getTranslateY() + main.getVelocity());
				}
				checkTokenScroll();
			}

		}

	}

	/**
	 * Check token scroll.
	 */
	public void checkTokenScroll() {
		// TODO Auto-generated method stub

		if (main.getMagnetOnScreen() != null) {
			StackPane magnet = main.getMagnetOnScreen().getStack();
			if (magnet.getTranslateY() >= 1400) {
				main.setMagnetOnScreen(null);
			}
		}

		if (main.getShieldOnScreen() != null) {
			StackPane shield = main.getShieldOnScreen().getStack();
			if (shield.getTranslateY() >= 1400) {
				main.setShieldOnScreen(null);
			}
		}

		if (main.getDestroyBlockOnScreen() != null) {
			StackPane destroyBlock = main.getDestroyBlockOnScreen().getStack();
			if (destroyBlock.getTranslateY() >= 1400) {
				main.setDestroyBlockOnScreen(null);
			}
		}

		if (main.getMagnetOnScreen() == null && main.getShieldOnScreen() == null
				&& main.getDestroyBlockOnScreen() == null) {
			int c = (int) (Math.random() * 3);
			if (c == 0) {
				setMagnet(-1000);
			}
			if (c == 1) {
				setDB(-1000);
			}
			if (c == 2) {
				setShield(-1000);
			}
		}
	}

	/**
	 * Sets the magnet.
	 *
	 * @param distance the new magnet
	 */
	public void setMagnet(int distance) {
		// TODO Auto-generated method stub
		int x = (int) (Math.random() * 400 + 7);
		int y = (int) (distance);
		Magnet M = new Magnet(x, y, main.getMode());
		main.getRoot().getChildren().add(M.getStack());
		main.setMagnetOnScreen(M);
	}

	/**
	 * Sets the shield.
	 *
	 * @param distance the new shield
	 */
	public void setShield(int distance) {
		// TODO Auto-generated method stub
		int x = (int) (Math.random() * 400 + 7);
		int y = (int) (distance);
		Shield S = new Shield(x, y, main.getMode());
		main.getRoot().getChildren().addAll(S.getStack());
		main.setShieldOnScreen(S);
	}

	/**
	 * Sets the db.
	 *
	 * @param distance the new db
	 */
	public void setDB(int distance) {
		// TODO Auto-generated method stub
		int x = (int) (Math.random() * 400 + 7);
		int y = (int) (distance);
		DestroyBlock DB = new DestroyBlock(x, y, main.getMode());
		main.getRoot().getChildren().addAll(DB.getStack());
		main.setDestroyBlockOnScreen(DB);
	}
}

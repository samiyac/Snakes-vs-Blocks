package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.geometry.BoundsUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Template.
 */
public class Template implements Initializable {

	/** The coin. */
	@FXML
	Label coin = new Label();

	/**
	 * Start classic.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	public void StartClassic() throws ClassNotFoundException, IOException {
		Main m = new Main();
		try {
			Main.mode = 1;
			m.setGameState(new GameState(m));
			m.getGameState().setNewGame();
		} catch (Exception p) {
			p.printStackTrace();
		}
	}

	/**
	 * Start christmas.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	public void StartChristmas() throws ClassNotFoundException, IOException {
		Main m = new Main();
		try {
			Main.mode = 2;
			m.setGameState(new GameState(m));
			m.getGameState().setNewGame();
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	/**
	 * Start halloween.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	public void StartHalloween() throws ClassNotFoundException, IOException {
		Main m = new Main();
		try {
			Main.mode = 3;
			m.setGameState(new GameState(m));
			m.getGameState().setNewGame();
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	/**
	 * Start summer.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	public void StartSummer() throws ClassNotFoundException, IOException {
		Main m = new Main();
		try {
			Main.mode = 4;
			m.setGameState(new GameState(m));
			m.getGameState().setNewGame();
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	/**
	 * Increase shield.
	 */
	@FXML
	public void IncreaseShield() {
		if (Main.bonusCoin >= 10) {
			Main.ShieldTimer = (Main.ShieldTimer + 2);
			Main.bonusCoin = (Main.bonusCoin - 10);
			coin.setText("Coins : " + Integer.toString(Main.bonusCoin));
		}
	}

	/**
	 * Increase magnet.
	 */
	@FXML
	public void IncreaseMagnet() {
		if (Main.bonusCoin >= 10) {
			Main.MagnetTimer = (Main.MagnetTimer + 2);
			Main.bonusCoin = (Main.bonusCoin - 10);
			coin.setText("Coins : " + Integer.toString(Main.bonusCoin));
		}
	}

	/**
	 * Sets the green.
	 */
	@FXML
	public void SetGreen() {
		Main.colorBall = Color.web("#84ff1fe2");
	}

	/**
	 * Sets the blue.
	 */
	@FXML
	public void SetBlue() {
		Main.colorBall = Color.web("#1f85e4");
	}

	/**
	 * Sets the red.
	 */
	@FXML
	public void SetRed() {
		Main.colorBall = Color.web("#ee1f79");
	}

	/**
	 * Sets the yellow.
	 */
	@FXML
	public void SetYellow() {
		Main.colorBall = Color.web("#f5ee24");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		coin.setText("Coins : " + Integer.toString(Main.getBonusCoin()));
	}
}

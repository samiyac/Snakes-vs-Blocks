/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
	private Label coin = new Label();
	
	/** The s. */
	private final Stage s = Main.getStage();

	/**
	 * Start classic game.
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
	 * Start game with christmas theme.
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
	 * Start game with halloween theme.
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
	 * Start game with summer theme.
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
	 * Increase shield timer.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	public void IncreaseShield() throws IOException {
		if (Main.bonusCoin >= 10) {
			Main.ShieldTimer = (Main.ShieldTimer + 2);
			Main.bonusCoin = (Main.bonusCoin - 10);
			coin.setText("Coins : " + Integer.toString(Main.bonusCoin));
		}
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("bonusCoin"));
		os.writeObject(Main.getBonusCoin());
		os.close();
	}

	/**
	 * Increase magnet timer.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	public void IncreaseMagnet() throws IOException {
		if (Main.bonusCoin >= 10) {
			Main.MagnetTimer = (Main.MagnetTimer + 2);
			Main.bonusCoin = (Main.bonusCoin - 10);
			coin.setText("Coins : " + Integer.toString(Main.bonusCoin));
		}
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("bonusCoin"));
		os.writeObject(Main.getBonusCoin());
		os.close();
	}

	/**
	 * Sets the snake as green.
	 */
	@FXML
	public void SetGreen() {
		Main.colorBall = Color.web("#84ff1fe2");
	}

	/**
	 * Sets the snake as blue.
	 */
	@FXML
	public void SetBlue() {
		Main.colorBall = Color.web("#1f85e4");
	}

	/**
	 * Sets the snake as red.
	 */
	@FXML
	public void SetRed() {
		Main.colorBall = Color.web("#ee1f79");
	}

	/**
	 * Sets the snake as yellow.
	 */
	@FXML
	public void SetYellow() {
		Main.colorBall = Color.web("#f5ee24");
	}
	
	/**
	 * Return home.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML 
	 public void ReturnHome() throws IOException{ 
		s.setTitle("Snake vs Block");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();
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

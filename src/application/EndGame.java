package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

// TODO: Auto-generated Javadoc
/**
 * The Class EndGame.
 */
public class EndGame implements Initializable {

	/** The s. */
	Stage s;
	
	/** The score. */
	int score;

	/** The score D. */
	@FXML
	Label scoreD = new Label();

	/**
	 * Instantiates a new end game.
	 *
	 * @param s the s
	 * @param n the n
	 */
	public EndGame(Stage s, int n) {
		System.out.println("endgame 1");
		this.s = s;
		this.score = n;
		// scoreD.setText(Integer.toString(n));
	}

	/**
	 * Instantiates a new end game.
	 */
	public EndGame() {
		System.out.println("default");
		s = Main.getStage();
		score = Main.tempscore;
		Main.tempscore=0;
	}

	/**
	 * Load end screen.
	 *
	 * @throws Exception the exception
	 */
	public void loadEndScreen() throws Exception {
		System.out.println("call");
		Parent root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
		Scene scene = new Scene(root, 500, 1000, Color.BLACK);
		// scoreLabel.setText(Integer.toString(score));
		s.setScene(scene);
		s.setTitle("EndGame");
		s.show();

	}

	/**
	 * Show leaderboard.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void showLeaderboard(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		// Stage stage = new Stage();
		s.setScene(new Scene(root1));
		// Main.stage.hide();
		s.show();
	}

	/**
	 * Play again.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void PlayAgain(ActionEvent event) throws IOException {
		s.setTitle("Snake vs Block");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();

	}

	/**
	 * System close.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void SystemClose(ActionEvent event) throws IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("bonusCoin"));
		os.writeObject(Main.getBonusCoin());
		os.close();
		System.exit(0);

	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreD.setText(Integer.toString(score));
	}
}

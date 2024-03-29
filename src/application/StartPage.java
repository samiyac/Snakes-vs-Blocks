/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class StartPage.
 */
public class StartPage implements Initializable {
	
	/** The btn 3. */
	@FXML
	private Button btn3=new Button();

	/** The stage. */
	private final Stage s;

	/**
	 * Instantiates a new start page.
	 */
	public StartPage() {
		s = Main.getStage();
	}

	/**
	 * Show leaderboard page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void showLeader(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();
	}

	/**
	 * New game.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void NewGame(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Template.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();
	}

	/**
	 * Load old game.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void SerialGame(ActionEvent event) throws IOException {

		try {
			Main m = new Main();
			m.setGameState(new GameState(m));
			File tmpDir = new File("SnakeVsBlock");
			boolean exists = tmpDir.exists();
			if (exists) {
				m.getGameState().ResumeGame(m);
			}

		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		File tmpDir = new File("SnakeVsBlock");
		boolean exists = tmpDir.exists();
		if (!exists) {
			System.out.println("set visible");
			btn3.setVisible(false);
		}
	}
}
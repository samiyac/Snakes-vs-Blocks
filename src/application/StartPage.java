package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class StartPage.
 */
public class StartPage {

	/** The s. */
	Stage s;

	/**
	 * Instantiates a new start page.
	 */
	public StartPage() {
		s = Main.getStage();
	}

	/**
	 * Show leader.
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
	 * Serial game.
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
}
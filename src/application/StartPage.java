package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class StartPage {

	Stage s;

	public StartPage() {
		s = Main.stage;
	}

	@FXML
	protected void showLeader(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();
	}

	@FXML
	protected void NewGame(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Template.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();
	}

	@FXML
	protected void SerialGame(ActionEvent event) throws IOException {

		try {
			Main m = new Main();
			File tmpDir = new File("SnakeVsBlock");
			boolean exists = tmpDir.exists();
			if (exists) {
				m.ResumeGame();
			}

		} catch (Exception E) {
			System.out.println(E);
		}
	}
}
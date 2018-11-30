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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class EndGame implements Initializable {

	Stage s;
	int score;

	@FXML
	Label scoreD = new Label();

	public EndGame(Stage s, int n) {
		this.s = s;
		this.score = n;
		// scoreD.setText(Integer.toString(n));
	}

	public EndGame() {
		s = Main.stage;
	}

	public void loadEndScreen() throws Exception {
		System.out.println("call");
		Parent root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
		Scene scene = new Scene(root, 500, 1000, Color.BLACK);
		// scoreLabel.setText(Integer.toString(score));
		s.setScene(scene);
		s.setTitle("EndGame");
		s.show();

	}

	@FXML
	protected void showLeaderboard(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		// Stage stage = new Stage();
		s.setScene(new Scene(root1));
		// Main.stage.hide();
		s.show();
	}

	@FXML
	protected void PlayAgain(ActionEvent event) throws IOException {
		s.setTitle("Snake vs Block");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();

	}

	@FXML
	protected void SystemClose(ActionEvent event) throws IOException {
		System.exit(0);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scoreD.setText(Integer.toString(score));
	}
}

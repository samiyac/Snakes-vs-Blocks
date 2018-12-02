package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.DateFormatter;
import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class LeaderBoardDisplay.
 */
public class LeaderBoardDisplay implements Initializable {

	/** The s. */
	Stage s= Main.stage;
	
	/** The a. */
	@FXML
	private Label A = new Label(" ");
	
	/** The b. */
	@FXML
	private Label B = new Label(" ");
	
	/** The c. */
	@FXML
	private Label C = new Label(" ");
	
	/** The d. */
	@FXML
	private Label D = new Label(" ");
	
	/** The e. */
	@FXML
	private Label E = new Label(" ");
	
	/** The f. */
	@FXML
	private Label F = new Label(" ");
	
	/** The g. */
	@FXML
	private Label G = new Label(" ");
	
	/** The h. */
	@FXML
	private Label H = new Label(" ");
	
	/** The i. */
	@FXML
	private Label I = new Label(" ");
	
	/** The j. */
	@FXML
	private Label J = new Label(" ");
	
	/** The k. */
	@FXML
	private Label K = new Label(" ");
	
	/** The l. */
	@FXML
	private Label L = new Label(" ");
	
	/** The m. */
	@FXML
	private Label M = new Label(" ");
	
	/** The n. */
	@FXML
	private Label N = new Label(" ");
	
	/** The o. */
	@FXML
	private Label O = new Label(" ");
	
	/** The p. */
	@FXML
	private Label P = new Label(" ");
	
	/** The q. */
	@FXML
	private Label Q = new Label(" ");
	
	/** The r. */
	@FXML
	private Label R = new Label(" ");
	
	/** The s. */
	@FXML
	private Label S = new Label(" ");
	
	/** The t. */
	@FXML
	private Label T = new Label(" ");
	
	/** The List. */
	// private Label B = new Label();
	ArrayList<Label> List = new ArrayList<>();
	
	/** The l. */
	// ArrayList<Node> Data =
	LeaderBoardList l;

	/**
	 * Instantiates a new leader board display.
	 */
	public LeaderBoardDisplay() {

		l = new LeaderBoardList();
		l.printList();

	}

	/**
	 * Returnto main.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	protected void returntoMain(ActionEvent event) throws IOException {
		s.setTitle("Snake vs Block");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		s.setScene(new Scene(root1));
		s.show();

	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (l.board.size() > 0) {
			A.setText(Integer.toString(l.board.get(0).getScore()));
			B.setText(l.board.get(0).getDate());
		}
		if (l.board.size() > 1) {
			C.setText(Integer.toString(l.board.get(1).getScore()));
			D.setText(l.board.get(1).getDate());
		}
		if (l.board.size() > 2) {
			E.setText(Integer.toString(l.board.get(2).getScore()));
			F.setText(l.board.get(2).getDate());

		}
		if (l.board.size() > 3) {
			G.setText(Integer.toString(l.board.get(3).getScore()));
			H.setText(l.board.get(3).getDate());

		}
		if (l.board.size() > 4) {
			I.setText(Integer.toString(l.board.get(4).getScore()));
			J.setText(l.board.get(4).getDate());
		}
		
		if (l.board.size() > 5) {
			K.setText(Integer.toString(l.board.get(5).getScore()));
			L.setText(l.board.get(1).getDate());
		}
		if (l.board.size() > 6) {
			M.setText(Integer.toString(l.board.get(6).getScore()));
			N.setText(l.board.get(2).getDate());

		}
		if (l.board.size() > 7) {
			O.setText(Integer.toString(l.board.get(7).getScore()));
			P.setText(l.board.get(3).getDate());

		}
		if (l.board.size() > 8) {
			Q.setText(Integer.toString(l.board.get(8).getScore()));
			R.setText(l.board.get(4).getDate());
		}
		if (l.board.size() > 9) {
			S.setText(Integer.toString(l.board.get(9).getScore()));
			T.setText(l.board.get(4).getDate());
		}

		
	}

}
/*
 * @author Priya Kaushal and Samiya Caur
 */

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
	private final Stage s = Main.getStage();

	/** All labels. */

	/** The a. */
	@FXML
	private final Label A = new Label(" ");

	/** The b. */
	@FXML
	private final Label B = new Label(" ");

	/** The c. */
	@FXML
	private final Label C = new Label(" ");

	/** The d. */
	@FXML
	private final Label D = new Label(" ");

	/** The e. */
	@FXML
	private final Label E = new Label(" ");

	/** The f. */
	@FXML
	private final Label F = new Label(" ");

	/** The g. */
	@FXML
	private final Label G = new Label(" ");

	/** The h. */
	@FXML
	private final Label H = new Label(" ");

	/** The i. */
	@FXML
	private final Label I = new Label(" ");

	/** The j. */
	@FXML
	private final Label J = new Label(" ");

	/** The k. */
	@FXML
	private final Label K = new Label(" ");

	/** The l. */
	@FXML
	private final Label L = new Label(" ");

	/** The m. */
	@FXML
	private final Label M = new Label(" ");

	/** The n. */
	@FXML
	private final Label N = new Label(" ");

	/** The o. */
	@FXML
	private final Label O = new Label(" ");

	/** The p. */
	@FXML
	private final Label P = new Label(" ");

	/** The q. */
	@FXML
	private final Label Q = new Label(" ");

	/** The r. */
	@FXML
	private final Label R = new Label(" ");

	/** The s. */
	@FXML
	private final Label S = new Label(" ");

	/** The t. */
	@FXML
	private final Label T = new Label(" ");

	/** The List. */
	private final ArrayList<Label> List = new ArrayList<>();

	/** The leaderBoard list with scores and day. */

	private final LeaderBoardList l;

	/**
	 * Instantiates a new leader board display.
	 */
	public LeaderBoardDisplay() {

		l = new LeaderBoardList();
		l.printList();

	}

	/**
	 * Return to start game page.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.fxml.Initializable#initialize(java.net.URL,
	 * java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (l.getBoard().size() > 0) {
			A.setText(Integer.toString(l.getBoard().get(0).getScore()));
			B.setText(l.getBoard().get(0).getDate());
		}
		if (l.getBoard().size() > 1) {
			C.setText(Integer.toString(l.getBoard().get(1).getScore()));
			D.setText(l.getBoard().get(1).getDate());
		}
		if (l.getBoard().size() > 2) {
			E.setText(Integer.toString(l.getBoard().get(2).getScore()));
			F.setText(l.getBoard().get(2).getDate());

		}
		if (l.getBoard().size() > 3) {
			G.setText(Integer.toString(l.getBoard().get(3).getScore()));
			H.setText(l.getBoard().get(3).getDate());

		}
		if (l.getBoard().size() > 4) {
			I.setText(Integer.toString(l.getBoard().get(4).getScore()));
			J.setText(l.getBoard().get(4).getDate());
		}

		if (l.getBoard().size() > 5) {
			K.setText(Integer.toString(l.getBoard().get(5).getScore()));
			L.setText(l.getBoard().get(1).getDate());
		}
		if (l.getBoard().size() > 6) {
			M.setText(Integer.toString(l.getBoard().get(6).getScore()));
			N.setText(l.getBoard().get(2).getDate());

		}
		if (l.getBoard().size() > 7) {
			O.setText(Integer.toString(l.getBoard().get(7).getScore()));
			P.setText(l.getBoard().get(3).getDate());

		}
		if (l.getBoard().size() > 8) {
			Q.setText(Integer.toString(l.getBoard().get(8).getScore()));
			R.setText(l.getBoard().get(4).getDate());
		}
		if (l.getBoard().size() > 9) {
			S.setText(Integer.toString(l.getBoard().get(9).getScore()));
			T.setText(l.getBoard().get(4).getDate());
		}

	}

}
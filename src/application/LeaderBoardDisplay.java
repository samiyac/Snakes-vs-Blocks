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
	 * @param event
	 *            the event
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
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
			System.out.println(l.getBoard().get(0).getDate());
			A.setText(Integer.toString(l.getBoard().get(0).getScore()));
			B.setText(l.getBoard().get(0).getDate());
		}else {
			A.setText(" ");
			B.setText(" ");
		}
		
		if (l.getBoard().size() > 1) {
			C.setText(Integer.toString(l.getBoard().get(1).getScore()));
			D.setText(l.getBoard().get(1).getDate());
		} else {
			C.setText(" ");
			D.setText(" ");
		}
		
		if (l.getBoard().size() > 2) {
			E.setText(Integer.toString(l.getBoard().get(2).getScore()));
			F.setText(l.getBoard().get(2).getDate());

		} else {
			E.setText(" ");
			F.setText(" ");
		}
		
		if (l.getBoard().size() > 3) {
			G.setText(Integer.toString(l.getBoard().get(3).getScore()));
			H.setText(l.getBoard().get(3).getDate());

		} else {
			G.setText(" ");
			H.setText(" ");
		}
		
		if (l.getBoard().size() > 4) {
			I.setText(Integer.toString(l.getBoard().get(4).getScore()));
			J.setText(l.getBoard().get(4).getDate());
		} else {
			I.setText(" ");
			J.setText(" ");
		}
		
		if (l.getBoard().size() > 5) {
			K.setText(Integer.toString(l.getBoard().get(5).getScore()));
			L.setText(l.getBoard().get(1).getDate());
		} else {
			K.setText(" ");
			L.setText(" ");
		}
		
		if (l.getBoard().size() > 6) {
			M.setText(Integer.toString(l.getBoard().get(6).getScore()));
			N.setText(l.getBoard().get(2).getDate());

		} else {
			M.setText(" ");
			N.setText(" ");
		}
		
		if (l.getBoard().size() > 7) {
			O.setText(Integer.toString(l.getBoard().get(7).getScore()));
			P.setText(l.getBoard().get(3).getDate());

		} else {
			O.setText(" ");
			P.setText(" ");
		}
		
		if (l.getBoard().size() > 8) {
			Q.setText(Integer.toString(l.getBoard().get(8).getScore()));
			R.setText(l.getBoard().get(4).getDate());
		} else {
			Q.setText(" ");
			R.setText(" ");
		}
		
		if (l.getBoard().size() > 9) {
			S.setText(Integer.toString(l.getBoard().get(9).getScore()));
			T.setText(l.getBoard().get(4).getDate());
		} else {
			S.setText(" ");
			T.setText(" ");
		}

	}

}
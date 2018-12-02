package application;


import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

// TODO: Auto-generated Javadoc
/**
 * The Class Labels.
 */
public class Labels {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new label.
	 *
	 * @param main the main
	 */
	public Labels(Main main) {
		this.main = main;
	}

	/**
	 * Sets the length label.
	 */
	public void setLengthLabel() {
		main.getLengthLabel().setX(main.getSnake().getLOCATION_X());
		main.getLengthLabel().setY(main.getSnake().getLOCATION_Y() - 20);
		main.getLengthLabel().setText(Integer.toString(main.getSnake().getLength()));
		main.getLengthLabel().setFont(Font.font(15));
		main.getLengthLabel().setFill(Color.WHITE);
		main.getLengthLabel().setTextAlignment(TextAlignment.LEFT);
		main.getRoot().getChildren().add(main.getLengthLabel());
	}

	/**
	 * Update length.
	 */
	public void updateLength() {
		int x = main.getSnake().getSnakeLength().size() - 1;
		main.getLengthLabel().setText(Integer.toString(x));
	}

	/**
	 * Sets the coin label.
	 */
	public void setCoinLabel() {
		main.setCoinLabel(new Text("Coins : " + main.getBonusCoin()));
		main.getCoinLabel().setX(400);
		main.getCoinLabel().setY(100);
		main.getCoinLabel().setFont(Font.font(15));
		main.getCoinLabel().setFill(Color.WHITE);
		main.getRoot().getChildren().addAll(main.getCoinLabel());
		main.getCoinLabel().toFront();
	}
	
	/**
	 * Update coin.
	 *
	 * @param inc the inc
	 */
	public void updateCoin(int inc) {
		main.bonusCoin += inc;
		main.getCoinLabel().setText("Coin : " + main.getBonusCoin());
		main.getCoinLabel().toFront();
	}

	/**
	 * Sets the score.
	 */
	public void setScore() {
		main.setScoreLabel(new Text("Score : " + main.getScore()));
		main.getScoreLabel().setX(400);
		main.getScoreLabel().setY(50);
		main.getScoreLabel().setFont(Font.font(15));
		main.getScoreLabel().setFill(Color.WHITE);
		main.getRoot().getChildren().addAll(main.getScoreLabel());
		main.getScoreLabel().toFront();
	}

	/**
	 * Update score.
	 *
	 * @param inc the inc
	 */
	public void updateScore(int inc) {
		main.setScore(main.getScore() + inc);
		main.getScoreLabel().setText("Score : " + main.getScore());
		main.getScoreLabel().toFront();
	}

	/**
	 * Sets the timer.
	 */
	public void setTimer() {
	
		main.getTimerLabel().setTextFill(Color.WHITE);
		main.getTimerLabel().setFont(Font.font(15));
		main.getTimerLabel().setLayoutY(10);
		main.getTimerLabel().setLayoutX(250);
		main.getRoot().getChildren().add(main.getTimerLabel());
	}
}

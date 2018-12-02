/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;


import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * The Class MagnetHandler.
 */
public class MagnetHandler {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new magnet handler.
	 *
	 * @param main the main
	 */
	public MagnetHandler(Main main) {
		this.main = main;
	}

	/**
	 * Coin attraction animation , coins move towards the snake.
	 */
	public void CoinAttractionAnimation() {
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5), new CoinAttractionHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(main.getMagnetTimer()+1);
		timeline.play();
		if (main.isEnd()) {
			timeline.stop();
		}
	}

	/**
	 * The Class CoinAttractionHandler.
	 */
	private class CoinAttractionHandler implements EventHandler<ActionEvent> {

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(ActionEvent event) {
			int inc = 0;
			ArrayList<ArrayList<Coin>> CoinsOnScreen=main.getCoinsOnScreen();
			for (int i = 0; i < CoinsOnScreen.size(); i++) {
				for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
					Coin temp = CoinsOnScreen.get(i).get(j);
					double checkx = temp.getLOCATION_X();
					double checky = temp.getStack().getTranslateY();
					Circle snakeHead = main.getSnake().getSnakeLength().get(0);

					if (!temp.isEaten() && Math.abs(checkx - snakeHead.getCenterX()) < 150 && checky > 300
							&& checky < 700) {
						temp.setEaten(true);
						main.setBonusCoin(main.getBonusCoin() + 1);
						Path path = new Path();
						path.getElements().add(new MoveTo(checkx, checky));
						path.getElements().add(new LineTo(snakeHead.getCenterX(), 500));
						path.setOpacity(0);
						PathTransition move = new PathTransition();
						move.setDuration(Duration.seconds(0.3));
						move.setPath(path);
						move.setNode(temp.getStack());
						move.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
						move.setCycleCount(1);
						move.setAutoReverse(true);
						main.getRoot().getChildren().add(path);
						move.play();
						move.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								main.getRoot().getChildren().remove(path);
								main.getRoot().getChildren().remove(temp.getStack());
							}
						});
						CoinsOnScreen.get(i).remove(j);
						inc += 1;
					}
				}
			}
			main.getLabels().updateScore(inc);
		}

	}

}

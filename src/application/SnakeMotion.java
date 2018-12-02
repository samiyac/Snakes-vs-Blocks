package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * The Class SnakeMotion.
 */
public class SnakeMotion {

	/** The main. */
	private final Main main;
	
	/** The music. */
	private final Media music;
	
	/** The player tokens balls. */
	private final MediaPlayer playerTokensBalls;
	
	/** The block music. */
	private final Media blockMusic;
	
	/** The player block. */
	private final MediaPlayer playerBlock;

	/**
	 * Instantiates a new snake motion.
	 *
	 * @param main the main
	 */
	public SnakeMotion(Main main) {
		this.main = main;
		music = new Media(new File("sound/mariotrim.wav").toURI().toString());
		playerTokensBalls = new MediaPlayer(music);
		blockMusic = new Media(new File("sound/block.wav").toURI().toString());
		playerBlock = new MediaPlayer(blockMusic);
		playerBlock.setVolume(0.3);
	}
	
	/**
	 * Snake animation.
	 */
	public void SnakeAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new SnakeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * The Class SnakeHandler.
	 */
	private class SnakeHandler implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if (!main.isBLOCK_HIT() && !main.isEnd()) {
				try {
					snakeIntersectBlock();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				main.getTimerLabel().toFront();
				main.getLabels().updateLength();
				snakeIntersectBall();
				snakeIntersectsDB();
				snakeIntersectsShield();
				snakeIntersectsMagnet();
				snakeIntersectCoin();
			}
			if (main.isShield()) {
				main.getSnake().changeColors();
			}
		}
	}

	/**
	 * Snake intersect block.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void snakeIntersectBlock() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		if (!main.isEnd()) {
			try {
				Circle snakeHead = Snake.getSnakeLength().get(0);
				for (int i = 0; i < main.getBlockOnScreen().size(); i++) {
					for (Block block : main.getBlockOnScreen().get(i)) {
						if (snakeHead.intersects(block.getStack().getBoundsInParent()) && block.isEaten() == false) {
							if (snakeHead.getCenterX() > block.getStack().getLayoutX() - 10
									&& snakeHead.getCenterX() < block.getStack().getLayoutX() + 110) {
								if (!main.isShield()) {
									System.out.println(block.getValue());
									main.setHitBlock(block);
									main.setBLOCK_HIT(true);
									main.getSnake().eatBlock(block, main);
								} else {
									block.getStack().setVisible(false);
									block.setEaten(true);
									main.getLabels().updateScore(block.getValue());
								}
								if (block.isEaten()) {
									Bounds blockBounds = block.getStack().getBoundsInParent();
									main.getBurstAnimation().PlayBurst(blockBounds, false, block.getColor());
								}
							}
						}
					}
				}

			} catch (Exception e) {
			}
		}
	}

	/**
	 * Snake intersect ball.
	 */
	public void snakeIntersectBall() {
		// TODO Auto-generated method stub
		if (!main.isEnd()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			ArrayList<ArrayList<Ball>> BallsOnScreen = main.getBallsOnScreen();
			for (int i = 0; i < BallsOnScreen.size(); i++) {
				for (Ball ball : BallsOnScreen.get(i)) {
					if (snakeHead.intersects(ball.getPane().getBoundsInParent()) && !ball.isEaten()) {
						playerTokensBalls.stop();
						playerTokensBalls.play();
						ball.setEaten(true);
						ball.getPane().setVisible(false);
						main.getBurstAnimation().PlayBurst(ball.getPane().getBoundsInParent(), true, Color.YELLOW);
						int inc = ball.getValue();
						int L = Snake.getSnakeLength().size();
						main.getSnake().increaseLength(inc, main);
						for (int j = 0; j < inc; j++) {
							main.getRoot().getChildren().add(Snake.getSnakeLength().get(L + j));
						}
					}
				}
			}
		}
	}

	/**
	 * Snake intersects DB.
	 */
	public void snakeIntersectsDB() {
		if (!main.isEnd()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			if (main.getDestroyBlockOnScreen() != null
					&& snakeHead.intersects(main.getDestroyBlockOnScreen().getStack().getBoundsInParent())) {
				for (int i = 0; i < main.getBlockOnScreen().size(); i++) {
					for (Block block : main.getBlockOnScreen().get(i)) {
						int y = (int) (block.getStack().getTranslateY() + block.getStack().getLayoutY());
						if (y > 0 && y < 1000 && !block.isEaten()) {
							playerTokensBalls.stop();
							playerTokensBalls.play();
							block.getStack().setVisible(false);
							block.setEaten(true);
							main.getLabels().updateScore(block.getValue());
							main.getBurstAnimation().PlayBurst(block.getStack().getBoundsInParent(), true,
									block.getColor());
						}
					}
				}
				main.getDestroyBlockOnScreen().getStack().setVisible(false);
				main.getBurstAnimation().PlayBurst(main.getDestroyBlockOnScreen().getStack().getBoundsInParent(), false,
						null);
			}
		}
	}

	/**
	 * Snake intersects shield.
	 */
	public void snakeIntersectsShield() {
		if (!main.isEnd()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			Shield ShieldOnScreen = main.getShieldOnScreen();
			if (ShieldOnScreen != null && snakeHead.intersects(ShieldOnScreen.getStack().getBoundsInParent())
					&& ShieldOnScreen.getStack().isVisible()) {
				main.setShield(true);
				ShieldOnScreen.getStack().setVisible(false);
				main.getTimerLabel().setVisible(true);
				IntegerProperty timeSeconds = new SimpleIntegerProperty(main.getShieldTimer());
				main.getTimerLabel().textProperty().bind(timeSeconds.asString());
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Timeline timeline = new Timeline();
						timeline.getKeyFrames().add(
								new KeyFrame(Duration.seconds(main.getShieldTimer()), new KeyValue(timeSeconds, -2)));
						timeline.playFromStart();
						if (main.isEnd()) {
							timer.cancel();
						}
					}
				};
				timer.scheduleAtFixedRate(task, 0, 1000);
				TimerTask taskEnd = new TimerTask() {
					public void run() {
						playerTokensBalls.stop();
						playerTokensBalls.play();
						main.setShield(false);
						main.getSnake().originalColors();
						main.getTimerLabel().setVisible(false);
						main.getBurstAnimation().PlayBurst(ShieldOnScreen.getStack().getBoundsInParent(), true, null);
						timer.cancel();
					}

				};

				timer.schedule(taskEnd, (long) (main.getShieldTimer() * 1000));
			}
		}
	}

	/**
	 * Snake intersects magnet.
	 */
	public void snakeIntersectsMagnet() {
		if (!main.isEnd()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			Magnet MagnetOnScreen = main.getMagnetOnScreen();
			if (MagnetOnScreen != null && snakeHead.intersects(MagnetOnScreen.getStack().getBoundsInParent())
					&& MagnetOnScreen.getStack().isVisible()) {
				playerTokensBalls.stop();
				playerTokensBalls.play();
				MagnetOnScreen.getStack().setVisible(false);
				main.getBurstAnimation().PlayBurst(MagnetOnScreen.getStack().getBoundsInParent(), true, null);
				main.getMagnetHandler().CoinAttractionAnimation();
				main.getTimerLabel().setVisible(true);
				IntegerProperty timeSeconds = new SimpleIntegerProperty(main.getMagnetTimer());
				main.getTimerLabel().textProperty().bind(timeSeconds.asString());
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Timeline timeline = new Timeline();
						timeline.getKeyFrames().add(
								new KeyFrame(Duration.seconds(main.getMagnetTimer()), new KeyValue(timeSeconds, -2)));
						timeline.playFromStart();
					}
				};
				timer.scheduleAtFixedRate(task, 0, 1000);
				TimerTask taskEnd = new TimerTask() {
					public void run() {
						main.getTimerLabel().setVisible(false);
						timer.cancel();
					}
				};
				timer.schedule(taskEnd, (long) (main.getMagnetTimer() * 1000));
			}
		}
	}

	/**
	 * Snake intersect coin.
	 */
	public void snakeIntersectCoin() {
		if (!main.isEnd()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			for (int i = 0; i < main.getCoinsOnScreen().size(); i++) {
				for (int j = 0; j < main.getCoinsOnScreen().get(i).size(); j++) {
					Coin coin = main.getCoinsOnScreen().get(i).get(j);
					if (snakeHead.intersects(coin.getStack().getBoundsInParent()) && !coin.isEaten()) {
						coin.getStack().setVisible(false);
						coin.setEaten(true);
						main.getBurstAnimation().PlayBurst(coin.getStack().getBoundsInParent(), true, Color.YELLOW);
						playerTokensBalls.stop();
						playerTokensBalls.play();
						main.getLabels().updateCoin(1);
					}
				}
			}
		}

	}

}

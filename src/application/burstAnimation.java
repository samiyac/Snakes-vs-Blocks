/*
 * @author Priya Kaushal and Samiya Caur
 */
package application;


import java.io.File;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The Class burstAnimation.
 */
public class burstAnimation {
	
	/** The main. */
	private final Main main;
	
	/** The block music. */
	private final Media blockMusic;
	
	/** The media player. */
	private final MediaPlayer playerBlock;

	/**
	 * Instantiates a new burst animation.
	 *
	 * @param main the main
	 */
	public burstAnimation(Main main) {
		blockMusic = new Media(new File("sound/block.wav").toURI().toString());
		playerBlock = new MediaPlayer(blockMusic);
		playerBlock.setVolume(0.3);
		this.main=main;
	}

	/**
	 * Play burst.
	 *
	 * @param bounds the bounds
	 * @param destroyB if destroy block or not 
	 * @param color the color
	 */
	public void PlayBurst(Bounds bounds, boolean destroyB, Color color) {
		if (!destroyB) {
			playerBlock.stop();
			playerBlock.play();
		}
		double x = bounds.getMaxX() + bounds.getMinX();
		x /= 2;
		double y = bounds.getMaxY() + bounds.getMinY();
		y /= 2;
		BurstAnimation(x, y, color);
	}

	/**
	 * Burst animation.
	 *
	 * @param x the position x
	 * @param y the position y
	 * @param color the color
	 */
	public void BurstAnimation(double x, double y, Color color) {
		KeyFrame kf = new KeyFrame(Duration.millis(2), new BurstAnimationHandler(x, y, color));
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(1);
		timeline.play();
	}

	/**
	 * The Class BurstAnimationHandler.
	 */
	private class BurstAnimationHandler implements EventHandler<ActionEvent> {

		/** The position x. */
		final double x;
		
		/** The position y. */
		final double y;
		
		/** The color. */
		final Color color;
		
		/** The duration. */
		final long duration = java.time.Duration.ofSeconds(10).toNanos();
		
		/** The side. */
		final int side = 150;
		
		/** The radius. */
		final double radius = Math.sqrt(2) * side;
		
		/** The rectangles. */
		final Rectangle[] rectangles = new Rectangle[50];
		
		/** The delays. */
		final ArrayList<Long> delays = new ArrayList<>();
		
		/** The angles. */
		final ArrayList<Double> angles = new ArrayList<Double>();

		/**
		 * Instantiates a new burst animation handler.
		 *
		 * @param x the position x
		 * @param y the position y
		 * @param color the color
		 */
		public BurstAnimationHandler(double x, double y, Color color) {
			// TODO Auto-generated constructor stub
			this.x = x;
			this.y = y;
			this.color = color;
		}

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			for (int i = 0; i < 50; i++) {
				int rand = (int) (Math.random() * 360) + 1;
				if (color == null) {
					rectangles[i] = new Rectangle(10, 10, Color.hsb(rand, 1, 1));
				} else {
					rectangles[i] = new Rectangle(10, 10, color);
				}
				angles.add(2 * Math.random() * Math.PI);
				delays.add((long) (Math.random() * duration));
			}
			main.getRoot().getChildren().addAll(rectangles);

			AnimationTimer Burst = new AnimationTimer() {
				int k = 0;

				@Override
				public void handle(long now) {
					// TODO Auto-generated method stub
					k++;
					if (k < 800) {
						for (int i = 0; i < 50; i++) {
							Rectangle rect = rectangles[i];
							long time = (now - delays.get(i)) % duration;
							double d = time * radius / duration;
							rect.setTranslateX(Math.cos(angles.get(i)) * d + x);
							rect.setTranslateY(Math.sin(angles.get(i)) * d + y);
							rect.setOpacity((duration - time) / (double) duration);

						}
					} else {
						this.stop();
						main.getRoot().getChildren().removeAll(rectangles);
					}

				}
			};
			Burst.start();
		}
	}
}

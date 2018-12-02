package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * The Class Snake.
 */
public class Snake extends Circle {
	
	/** The Constant snakeLength. */
	private final static ArrayList<Circle> snakeLength = new ArrayList<Circle>();
	
	/** The length. */
	private int length = 4;
	
	/** The location x. */
	private int LOCATION_X;
	
	/** The location y. */
	private int LOCATION_Y;
	
	/** The radius. */
	private final double radius;
	
	/** The color. */
	private Color color;
	
	/** The l. */
	private LeaderBoardList l = new LeaderBoardList();

	/**
	 * Instantiates a new snake.
	 *
	 * @param x the x
	 * @param y the y
	 * @param r the r
	 * @param col the col
	 */
	public Snake(int x, int y, double r, Color col)

	{
		LOCATION_X = x;
		LOCATION_Y = y;
		this.radius = r;
		color = col;
		for (int i = 0; i < 4; i++) {
			if (i == 0) {
				Circle c = new Circle(x, (y + 2 * i * r), r, Color.RED);
				c.setCenterX(x);
				snakeLength.add(c);
			} else {
				Circle c = new Circle(x, (y + 2 * i * r), r, col);
				snakeLength.add(c);
			}
		}

	}

	/**
	 * Instantiates a new snake.
	 *
	 * @param length the length
	 * @param lOCATION_X the l OCATIO N X
	 * @param lOCATION_Y the l OCATIO N Y
	 * @param snakeHeadPosX the snake head pos X
	 * @param radius the radius
	 * @param color the color
	 * @param main the main
	 */
	public Snake(int length, int lOCATION_X, int lOCATION_Y, double snakeHeadPosX, double radius, String color,
			Main main) {
		this.length = length;
		this.LOCATION_X = lOCATION_X;
		this.LOCATION_Y = lOCATION_Y;
		this.radius = (int) radius;
		this.color = Color.web(color);
		for (int i = 0; i < length; i++) {
			if (i == 0) {
				Circle c = new Circle(lOCATION_X, (lOCATION_Y + 2 * i * radius), radius, Color.RED);
				c.setCenterX(lOCATION_X);
				snakeLength.add(c);
			} else {
				Circle c = new Circle(lOCATION_X, (lOCATION_Y + 2 * i * radius), radius, this.color);
				snakeLength.add(c);
			}
		}
		for (int i = 0; i < length; i++) {
			snakeLength.get(i).setCenterX(snakeHeadPosX);
		}
	}

	/**
	 * Gets the snake length.
	 *
	 * @return the snake length
	 */
	public static ArrayList<Circle> getSnakeLength() {
		return snakeLength;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param L the new length
	 */
	public void setLength(int L) {
		this.length = L;
	}

	/**
	 * Gets the location x.
	 *
	 * @return the location x
	 */
	public int getLOCATION_X() {
		return LOCATION_X;
	}

	/**
	 * Sets the location x.
	 *
	 * @param xpos the new location x
	 */
	public void setLOCATION_X(int xpos) {
		LOCATION_X = xpos;
	}

	/**
	 * Gets the location y.
	 *
	 * @return the location y
	 */
	public int getLOCATION_Y() {
		return LOCATION_Y;
	}

	/**
	 * Sets the location y.
	 *
	 * @param ypos the new location y
	 */
	public void setLOCATION_Y(int ypos) {
		LOCATION_Y = ypos;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Move R.
	 */
	public void moveR() {
		for (int i = 0; i < snakeLength.size(); i++) {
			snakeLength.get(i).setCenterX(snakeLength.get(i).getCenterX() + 10);
		}

	}

	/**
	 * Move L.
	 */
	public void moveL() {
		for (int i = 0; i < snakeLength.size(); i++) {
			snakeLength.get(i).setCenterX(snakeLength.get(i).getCenterX() - 10);
		}
	}

	/**
	 * Translate.
	 */
	public void translate() {
		for (int i = 0; i < snakeLength.size(); i++) {
			snakeLength.get(i).setCenterY(snakeLength.get(i).getCenterY() - 0.5);
		}
	}

	/**
	 * Increase length.
	 *
	 * @param inc the inc
	 * @param main the main
	 */
	public void increaseLength(int inc, Main main) {
		// TODO Auto-generated method stub
		for (int i = 0; i < inc; i++) {
			double x = snakeLength.get(length - 1).getCenterX();
			double y = snakeLength.get(length - 1).getCenterY();
			Circle c = new Circle(x, (y + 2 * radius), radius, color);
			snakeLength.add(c);
			length++;
			main.updateVelocity(0.2);
		}

	}

	/**
	 * Eat block.
	 *
	 * @param block the block
	 * @param main the main
	 * @return the int
	 */
	public int eatBlock(Block block, Main main) {
		System.out.println("inside eatblock");
		int value = block.getValue();
		int v = value;
		int temp = length;
		while (temp > 0 && v > 0) {
			v--;
			temp--;
		}
		if (value > 5) {
			SnakeEatsAnimation(block, value, main);
		} else {
			blockLessThan5(value - v, block, main);
		}
		return value - v;
	}

	/**
	 * Block less than 5.
	 *
	 * @param count the count
	 * @param block the block
	 * @param main the main
	 */
	public void blockLessThan5(int count, Block block, Main main) {
		for (int i = 0; i < count; i++) {
			snakeLength.get(length - 1).setVisible(false);
			snakeLength.remove(length - 1);
			length--;
			main.updateVelocity(-0.2);
			main.getLabels().updateScore(1);
		}
		if (length > 0) {
			block.setEaten(true);
			block.getStack().setVisible(false);

			main.setBLOCK_HIT(false);
		} else {
			try {
				throw new EndGameException("end game");
			} catch (EndGameException e) {
				// TODO Auto-generated catch block
				endGame(main);
			}
		}
	}

	/**
	 * Snake eats animation.
	 *
	 * @param block the block
	 * @param value the value
	 * @param main the main
	 */
	public void SnakeEatsAnimation(Block block, int value, Main main) {
		System.out.println("inside eatanim");
		KeyFrame kf = new KeyFrame(Duration.millis(10 * length), new SnakeEatsHandler(block, value, main));
		Timeline timeline = new Timeline(kf);
		int c = (value > length) ? length : value;
		timeline.setCycleCount(c);
		timeline.setRate(0.7);
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (block.getValue() == 0 && length > 0) {
					block.setEaten(true);
					block.getStack().setVisible(false);
					main.getBurstAnimation().PlayBurst(block.getStack().getBoundsInParent(), false, block.getColor());
				} else if (length == 0) {
					try {
						throw new EndGameException("end game");
					} catch (EndGameException e) {
						// TODO Auto-generated catch block
						endGame(main);
					}

				}
				System.out.println("play");
				if (main.getHitBlock() == block) {
					main.setBLOCK_HIT(false);
				}
			}
		});
	}

	/**
	 * End game.
	 *
	 * @param main the main
	 */
	protected void endGame(Main main) {
		// TODO Auto-generated method stub
		int x = main.getScore();
		String Date = java.time.LocalDate.now().toString();
		Node n = new Node(x, Date);
		l.board.add(n);
		l.serialise();
		File tmpDir = new File("SnakeVsBlock");
		boolean exists = tmpDir.exists();
		if (exists) {
			tmpDir.delete();
		}
		main.setEnd(true);
		main.getGameState().endgame();
	}

	/**
	 * The Class SnakeEatsHandler.
	 */
	private class SnakeEatsHandler implements EventHandler<ActionEvent> {

		/** The block. */
		private final Block block;
		
		/** The value. */
		private int value;
		
		/** The main. */
		private final Main main;
		
		/** The intersecting. */
		private boolean intersecting = true;

		/**
		 * Instantiates a new snake eats handler.
		 *
		 * @param block the block
		 * @param value the value
		 * @param main the main
		 */
		public SnakeEatsHandler(Block block, int value, Main main) {
			// TODO Auto-generated constructor stub
			this.block = block;
			this.value = value;
			this.main = main;
		}

		/* (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if (length > 0) {
				Circle snakeHead = snakeLength.get(0);
				if (snakeHead.intersects(block.getStack().getBoundsInParent()) && block.isEaten() == false) {
					snakeLength.get(length - 1).setVisible(false);
					snakeLength.remove(length - 1);
					length--;
					main.updateVelocity(-0.2);
					value--;
					block.updateValue(value);
					main.getLabels().updateScore(1);
				} else if (intersecting) {
					intersecting = false;
					if (main.getHitBlock() == block) {
						main.setHitBlock(null);
						main.setBLOCK_HIT(false);
						System.out.println("blockhandle");
					}
				}
			}
		}
	}

	/**
	 * Change colors.
	 */
	public void changeColors() {
		// TODO Auto-generated method stub
		ArrayList<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLUE);
		colors.add(Color.RED);
		colors.add(Color.GREEN);
		colors.add(Color.DARKMAGENTA);
		colors.add(Color.PURPLE);
		Collections.shuffle(colors);
		int j = 0;
		for (int i = 0; i < length; i++) {
			snakeLength.get(i).setFill(colors.get(j));
			j++;
			if (j == 5) {
				j = 0;
			}
		}
	}

	/**
	 * Original colors.
	 */
	public void originalColors() {
		snakeLength.get(0).setFill(Color.RED);
		for (int i = 1; i < length; i++) {
			snakeLength.get(i).setFill(color);
		}
	}
}
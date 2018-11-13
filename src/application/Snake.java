package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Snake extends Circle {
	private final static ArrayList<Circle> snakeLength = new ArrayList<Circle>();
	private int length = 4;
	private int LOCATION_X;
	private int LOCATION_Y;
	private final double radius;
	private Color color;

	public Snake(int x, int y, double r, Color col)

	{
		LOCATION_X = x;
		LOCATION_Y = y;
		this.radius = r;
		System.out.println(radius + " init");
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
				System.out.println(radius + " radius");
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

	public static ArrayList<Circle> getSnakeLength() {
		return snakeLength;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int L) {
		this.length = L;
	}

	public int getLOCATION_X() {
		return LOCATION_X;
	}

	public void setLOCATION_X(int xpos) {
		LOCATION_X = xpos;
	}

	public int getLOCATION_Y() {
		return LOCATION_Y;
	}

	public void setLOCATION_Y(int ypos) {
		LOCATION_Y = ypos;
	}

	public Color getColor() {
		return color;
	}

	public void moveR() {
		for (int i = 0; i < snakeLength.size(); i++) {
			snakeLength.get(i).setCenterX(snakeLength.get(i).getCenterX() + 10);
		}

	}

	public void moveL() {
		for (int i = 0; i < snakeLength.size(); i++) {
			snakeLength.get(i).setCenterX(snakeLength.get(i).getCenterX() - 10);
		}
	}

	public void translate() {
		for (int i = 0; i < snakeLength.size(); i++) {
			snakeLength.get(i).setCenterY(snakeLength.get(i).getCenterY() - 0.5);
		}
	}

	public void increaseLength(int inc, Main main) {
		// TODO Auto-generated method stub
		for (int i = 0; i < inc; i++) {
			double x = snakeLength.get(length - 1).getCenterX();
			double y = snakeLength.get(length - 1).getCenterY();
			Circle c = new Circle(x, (y + 2 * radius), radius, color);
			snakeLength.add(c);
			length++;
			main.setVelocity(0.2);
		}

	}

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

	public void blockLessThan5(int count, Block block, Main main) {
		for (int i = 0; i < count; i++) {
			snakeLength.get(length - 1).setVisible(false);
			snakeLength.remove(length - 1);
			length--;
			main.setVelocity(-0.2);
			main.updateScore(1);
		}
		if (length > 0) {
			block.setEaten(true);
			block.getStack().setVisible(false);

			main.setBlockHit(false);
		} else {
			System.exit(0);
		}
	}

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
					main.PlayBurst(block.getStack().getBoundsInParent(), false);
				} else if (length == 0) {
					System.exit(0);
				}
				System.out.println("play");
				if (main.getHitBlock() == block) {
					main.setBlockHit(false);
				}
			}
		});
	}

	private class SnakeEatsHandler implements EventHandler<ActionEvent> {

		private final Block block;
		private int value;
		private final Main main;
		private boolean intersecting = true;

		public SnakeEatsHandler(Block block, int value, Main main) {
			// TODO Auto-generated constructor stub
			this.block = block;
			this.value = value;
			this.main = main;
		}

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if (length > 0) {
				Circle snakeHead = snakeLength.get(0);
				if (snakeHead.intersects(block.getStack().getBoundsInParent()) && block.isEaten() == false) {
					snakeLength.get(length - 1).setVisible(false);
					snakeLength.remove(length - 1);
					length--;
					main.setVelocity(-0.2);
					value--;
					block.updateValue(value);
					main.updateScore(1);
				} else if (intersecting) {
					intersecting = false;
					if (main.getHitBlock() == block) {
						main.setHitBlock(null);
						main.setBlockHit(false);
						System.out.println("blockhandle");
					}
				}
			}
		}
	}

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

	public void originalColors() {
		snakeLength.get(0).setFill(Color.RED);
		for (int i = 1; i < length; i++) {
			snakeLength.get(i).setFill(Color.YELLOW);
		}
	}
}
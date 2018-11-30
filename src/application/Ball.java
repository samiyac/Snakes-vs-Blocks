package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class Ball extends Token {
	private final float radius;
	private final Color color;
	private final Circle circle;
	private final int value;
	private boolean eaten = false;
	private final StackPane Pane = new StackPane();

	public Ball(float xpos, float ypos, float radius, Color color) {
		super(xpos, ypos);
		this.radius = radius;
		this.color = color;
		circle = new Circle(xpos, ypos, radius, color);
		value = (int) (Math.random() * 5 + 1);
		Text t = new Text(Integer.toString(value));
		Pane.getChildren().addAll(circle, t);
		Pane.setLayoutX(xpos);
		Pane.setLayoutY(ypos);
	}

	public Ball(float lOCATION_X, float lOCATION_Y, double translateY, float radius, String color, boolean eaten,
				int value) {
		// TODO Auto-generated constructor stub
		super(lOCATION_X, lOCATION_Y);
		this.radius=radius;
		this.eaten = eaten;
		this.value = value;
		this.color = Color.web(color);
		this.LOCATION_X = lOCATION_X;
		this.LOCATION_Y = lOCATION_Y;
		circle = new Circle(LOCATION_X, translateY+LOCATION_Y, radius, this.color);
		Text t = new Text(Integer.toString(value));
		Pane.getChildren().addAll(circle, t);
		Pane.setLayoutX(LOCATION_X);
		Pane.setLayoutY(LOCATION_Y);
//		circle.setTranslateY(translateY+LOCATION_Y);
		Pane.setTranslateY(translateY);
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public float getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	public Circle getCircle() {
		return circle;
	}

	public int getValue() {
		return value;
	}

	public StackPane getPane() {
		return Pane;
	}

}

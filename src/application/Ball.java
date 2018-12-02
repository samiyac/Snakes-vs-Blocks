package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class Ball.
 */
public class Ball extends Token {
	
	/** The radius. */
	private final float radius;
	
	/** The color. */
	private final Color color;
	
	/** The circle. */
	private final Circle circle;
	
	/** The value. */
	private final int value;
	
	/** The eaten , true if the block is eaten. */
	private boolean eaten = false;
	
	/** The StackPane. */
	private final StackPane Pane = new StackPane();

	/**
	 * Instantiates a new ball.
	 *
	 * @param xpos the xposition
	 * @param ypos the yposition
	 * @param radius the radius
	 * @param color the color
	 * @param mode the Gamemode
	 */
	public Ball(float xpos, float ypos, float radius, Color color, int mode) {
		super(xpos, ypos, mode);
		this.radius = radius;
		this.color = color;
		circle = new Circle(xpos, ypos, radius, color);
		value = (int) (Math.random() * 5 + 1);
		Text t = new Text(Integer.toString(value));
		Pane.getChildren().addAll(circle, t);
		Pane.setLayoutX(xpos);
		Pane.setLayoutY(ypos);
	}

	/**
	 * Instantiates a new ball.
	 *
	 * @param lOCATION_X the lOCATIONX
	 * @param lOCATION_Y the lOCATIONY
	 * @param translateY the translateY
	 * @param radius the radius
	 * @param color the color
	 * @param eaten the eaten
	 * @param value the value
	 * @param mode the Gamemode
	 */
	public Ball(float lOCATION_X, float lOCATION_Y, double translateY, float radius, String color, boolean eaten,
			int value, int mode) {
		// TODO Auto-generated constructor stub
		super(lOCATION_X, lOCATION_Y, mode);
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
		Pane.setTranslateY(translateY);
	}

	/**
	 * Checks if is eaten.
	 *
	 * @return true, if is eaten
	 */
	public boolean isEaten() {
		return eaten;
	}

	/**
	 * Sets the eaten.
	 *
	 * @param sets the ball as eaten
	 */
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	/**
	 * Gets the radius.
	 *
	 * @return the radius
	 */
	public float getRadius() {
		return radius;
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
	 * Gets the circle.
	 *
	 * @return the circle
	 */
	public Circle getCircle() {
		return circle;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Gets the pane.
	 *
	 * @return the pane
	 */
	public StackPane getPane() {
		return Pane;
	}

}

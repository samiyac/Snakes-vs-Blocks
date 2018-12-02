package application;

import java.io.Serializable;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

// TODO: Auto-generated Javadoc
/**
 * The Class Block.
 */
public class Block {
	
	/** The location x. */
	private int LOCATION_X;
	
	/** The location y. */
	private int LOCATION_Y;
	
	/** The width. */
	private final int width;
	
	/** The height. */
	private final int height;
	
	/** The value. */
	private int value;
	
	/** The stack. */
	private final StackPane stack;
	
	/** The color. */
	private final Color color;
	
	/** The eaten. */
	private boolean eaten;

	/**
	 * Instantiates a new block.
	 *
	 * @param LOCATION_X the location x
	 * @param LOCATION_Y the location y
	 * @param w the width
	 * @param h the height
	 * @param color the color
	 * @param val the weight of the block
	 * @param text the text on the block
	 */
	public Block(int LOCATION_X, int LOCATION_Y, int w, int h, Color color, int val, Text text) {
		this.LOCATION_X = LOCATION_X;
		this.LOCATION_Y = LOCATION_Y;
		this.width = w;
		this.height = h;
		this.color = color;
		value = val;
		eaten = false;
		Rectangle r = new Rectangle(w, h, color);
		r.setArcHeight(30);
		r.setArcWidth(30);
		r.setX(LOCATION_X);
		r.setY(LOCATION_Y);
		stack = new StackPane();
		stack.getChildren().addAll(r, text);
		stack.setLayoutX(LOCATION_X);
		stack.setLayoutY(LOCATION_Y);
	}

	/**
	 * Instantiates a new block.
	 *
	 * @param width the width
	 * @param height the height
	 * @param color the color
	 * @param val the val
	 * @param eaten if is eaten
	 * @param lOCATION_X the lOCATION X
	 * @param lOCATION_Y the lOCATION Y
	 * @param translate_Y the translateY
	 */
	public Block(int width, int height, String color, int val, boolean eaten, int lOCATION_X, int lOCATION_Y,
			double translate_Y) {
		// TODO Auto-generated constructor stub
		this.LOCATION_X = lOCATION_X;
		this.LOCATION_Y = lOCATION_Y;
		this.width = width;
		this.height = height;
		value = val;
		this.eaten = eaten;
		this.color = Color.web(color);
		Rectangle r = new Rectangle(width, height, this.color);
		r.setArcHeight(30);
		r.setArcWidth(30);
		r.setX(LOCATION_X);
		r.setY(translate_Y+LOCATION_Y);
		//r.setTranslateY(translate_Y-100);
		stack = new StackPane();
		Text text = new Text(Integer.toString(val));
		stack.getChildren().addAll(r, text);
		stack.setLayoutX(LOCATION_X);
		stack.setLayoutY(LOCATION_Y);
		stack.setTranslateY(translate_Y);
		
	}

	/**
	 * Gets the location X.
	 *
	 * @return the location X
	 */
	public int getLocationX() {
		return LOCATION_X;
	}

	/**
	 * Sets the location X.
	 *
	 * @param LOCATION_X the new location X
	 */
	public void setLocationX(int LOCATION_X) {
		this.LOCATION_X = LOCATION_X;
	}

	/**
	 * Gets the location Y.
	 *
	 * @return the location Y
	 */
	public int getLocationY() {
		return LOCATION_Y;
	}

	/**
	 * Sets the location Y.
	 *
	 * @param LOCATION_Y the new location Y
	 */
	public void setLocationY(int LOCATION_Y) {
		this.LOCATION_Y = LOCATION_Y;
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
	 * @param eaten to set the block as eaten or not 
	 */
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
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
	 * Update value.
	 *
	 * @param v the updated value
	 */
	public void updateValue(int v) {
		this.value = v;
		Text text = new Text(Integer.toString(v));
		stack.getChildren().remove(1);
		stack.getChildren().add(text);
	}

	/**
	 * Gets the stack.
	 *
	 * @return the stack
	 */
	public StackPane getStack() {
		return stack;
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
}

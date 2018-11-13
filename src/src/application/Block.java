package application;

import java.io.Serializable;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Block {
	private int LOCATION_X;
	private int LOCATION_Y;
	private final int width;
	private final int height;
	private int value;
	private final StackPane stack;
	private final Color color;
	private boolean eaten;

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

	public int getLocationX() {
		return LOCATION_X;
	}

	public void setLocationX(int LOCATION_X) {
		this.LOCATION_X = LOCATION_X;
	}

	public int getLocationY() {
		return LOCATION_Y;
	}

	public void setLocationY(int LOCATION_Y) {
		this.LOCATION_Y = LOCATION_Y;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getValue() {
		return value;
	}

	public void updateValue(int v) {
		this.value = v;
		Text text = new Text(Integer.toString(v));
		stack.getChildren().remove(1);
		stack.getChildren().add(text);
	}

	public StackPane getStack() {
		return stack;
	}

	public Color getColor() {
		return color;
	}

}

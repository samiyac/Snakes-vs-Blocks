package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

abstract public class Token {
	private float LOCATION_X;
	private float LOCATION_Y;

	public Token(float x, float y) {
		LOCATION_X = x;
		LOCATION_Y = y;
	}

	public float getLOCATION_X() {
		return LOCATION_X;
	}

	public float getLOCATION_Y() {
		return LOCATION_Y;
	}

	public void setLocation_X(float location_X) {
		LOCATION_X = location_X;
	}

	public void setLocation_Y(float location_Y) {
		LOCATION_Y = location_Y;
	}
}

class Shield extends Token {
	private final String IMAGE_SRC = "file:/home/samiya/eclipse-workspace/AP_Project/src/application/protection.png";
	private final StackPane stack;

	public Shield(float x, float y) {
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
		// TODO Auto-generated constructor stub
	}

	public String getIMAGE_SRC() {
		return IMAGE_SRC;
	}

	public StackPane getStack() {
		return stack;
	}
}

class DestroyBlock extends Token {
	private final String IMAGE_SRC = "file:/home/samiya/eclipse-workspace/AP_Project/src/application/blockchain.png";
	private final StackPane stack;

	public DestroyBlock(float x, float y) {
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	public String getIMAGE_SRC() {
		return IMAGE_SRC;
	}

	public StackPane getStack() {
		return stack;
	}
}

class Magnet extends Token {
	private final String IMAGE_SRC = "file:/home/samiya/eclipse-workspace/AP_Project/src/application/horseshoe.png";
	private final StackPane stack;

	public Magnet(float x, float y) {
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	public String getIMAGE_SRC() {
		return IMAGE_SRC;
	}

	public StackPane getStack() {
		return stack;
	}
}

class Coin extends Token {
	private final String IMAGE_SRC = "file:/home/samiya/eclipse-workspace/AP_Project/src/application/dollar.png";
	private final StackPane stack;
	private boolean eaten = false;

	public Coin(float x, float y) {
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	public String getIMAGE_SRC() {
		return IMAGE_SRC;
	}

	public StackPane getStack() {
		return stack;
	}

	public boolean isEaten() {
		return eaten;
	}

	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}
}
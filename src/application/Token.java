package application;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

abstract public class Token implements Serializable {
	protected float LOCATION_X;
	protected float LOCATION_Y;

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
	private final String IMAGE_SRC = "file:" + System.getProperty("user.dir")
			+ "/src/application/images/protection.png";
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

	public Shield(float x, float y, double translate_Y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(translate_Y);
		stack.getChildren().addAll(I);
	}

	public String getIMAGE_SRC() {
		return IMAGE_SRC;
	}

	public StackPane getStack() {
		return stack;
	}
}

class DestroyBlock extends Token {
	private final String IMAGE_SRC = "file:" + System.getProperty("user.dir")
			+ "/src/application/images/blockchain.png";
	private final StackPane stack;

	public DestroyBlock(float x, float y) {
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	public DestroyBlock(float x, float y, double translate_Y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(translate_Y);
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
	private final String IMAGE_SRC = "file:" + System.getProperty("user.dir") + "/src/application/images/horseshoe.png";
	private final StackPane stack;

	public Magnet(float x, float y) {
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	public Magnet(float x, float y, double translate_Y) {
		// TODO Auto-generated constructor stub
		super(x, y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(x);
		stack.setTranslateY(translate_Y);
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
	private final String IMAGE_SRC = "file:" + System.getProperty("user.dir") + "/src/application/images/dollar.png";
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

	public Coin(float lOCATION_X, float lOCATION_Y, double translate_Y, boolean eaten2) {
		// TODO Auto-generated constructor stub
		super(lOCATION_X, lOCATION_Y);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(lOCATION_X);
		stack.setTranslateY(translate_Y);
		stack.getChildren().addAll(I);
		eaten = eaten2;
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
/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * The Class Coin.
 */
class Coin extends Token {
	
	/** The image source. */
	private final String IMAGE_SRC;
	
	/** The stack. */
	private final StackPane stack;
	
	/** The eaten. */
	private boolean eaten = false;

	/**
	 * Instantiates a new coin.
	 *
	 * @param x the position x
	 * @param y the position y
	 * @param mode the mode
	 */
	public Coin(float x, float y, int mode) {
		super(x, y, mode);
		System.out.println("make coin");
		String M = "";
		if (mode == 1) {
			M = "classic";
		} else if (mode == 2) {
			M = "christmas";
		} else if (mode == 3) {
			M = "halloween";
		} else if (mode == 4) {
			M = "summer";
		}
		IMAGE_SRC = "file:" + System.getProperty("user.dir") + "/src/application/AP_Images/" + M + "/coin.png";
		System.out.println(IMAGE_SRC);
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	/**
	 * Instantiates a new coin.
	 *
	 * @param lOCATION_X the lOCATION X
	 * @param lOCATION_Y the lOCATION Y
	 * @param translate_Y the translate Y
	 * @param eaten2 if the coin is eaten
	 * @param mode the Game mode
	 * @param img the image source
	 */
	public Coin(float lOCATION_X, float lOCATION_Y, double translate_Y, boolean eaten2, int mode, String img) {
		// TODO Auto-generated constructor stub
		super(lOCATION_X, lOCATION_Y, mode);
		this.IMAGE_SRC = img;
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(lOCATION_X);
		stack.setTranslateY(translate_Y);
		stack.getChildren().addAll(I);
		eaten = eaten2;
	}

	/**
	 * Gets the image src.
	 *
	 * @return the image src
	 */
	public String getIMAGE_SRC() {
		return IMAGE_SRC;
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
	 * @param eaten sets if the coin is eaten
	 */
	public void setEaten(boolean eaten) {
		this.eaten = eaten;
	}

}
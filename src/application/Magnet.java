/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Magnet.
 */
class Magnet extends Token {
	
	/** The image src. */
	private final String IMAGE_SRC;
	
	/** The stack. */
	private final StackPane stack;

	/**
	 * Instantiates a new magnet.
	 *
	 * @param x the position x
	 * @param y the position y
	 * @param mode the Game mode
	 */
	public Magnet(float x, float y, int mode) {
		super(x, y, mode);
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
		IMAGE_SRC = "file:" + System.getProperty("user.dir") + "/src/application/AP_Images/" + M + "/magnet.png";
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
	}

	/**
	 * Instantiates a new magnet.
	 *
	 * @param x the position x
	 * @param y the position y
	 * @param translate_Y the translate Y
	 * @param mode the Game mode
	 * @param img the image source
	 */
	public Magnet(float x, float y, double translate_Y, int mode, String img) {
		// TODO Auto-generated constructor stub
		super(x, y, mode);
		this.IMAGE_SRC = img;
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setTranslateX(x);
		stack.setTranslateY(translate_Y);
		stack.getChildren().addAll(I);
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
}


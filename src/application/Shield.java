package application;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * The Class Shield.
 */
class Shield extends Token {
	
	/** The image src. */
	private final String IMAGE_SRC;
	
	/** The stack. */
	private final StackPane stack;

	/**
	 * Instantiates a new shield.
	 *
	 * @param x the x
	 * @param y the y
	 * @param mode the mode
	 */
	public Shield(float x, float y, int mode) {
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
		IMAGE_SRC = "file:" + System.getProperty("user.dir") + "/src/application/AP_Images/" + M + "/shield.png";
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
		stack.setTranslateY(y);
		stack.getChildren().addAll(I);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new shield.
	 *
	 * @param x the x
	 * @param y the y
	 * @param translate_Y the translate Y
	 * @param mode the mode
	 * @param img the img
	 */
	public Shield(float x, float y, double translate_Y, int mode, String img) {
		// TODO Auto-generated constructor stub
		super(x, y, mode);
		this.IMAGE_SRC = img;
		ImageView I = new ImageView(IMAGE_SRC);
		stack = new StackPane();
		stack.setLayoutX(x);
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

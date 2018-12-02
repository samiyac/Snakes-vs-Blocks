package application;

import java.io.Serializable;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Token.
 */
abstract public class Token implements Serializable {

	/** The location x. */
	protected float LOCATION_X;

	/** The location y. */
	protected float LOCATION_Y;

	/** The mode. */
	protected final int mode;

	/**
	 * Instantiates a new token.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param mode
	 *            the mode
	 */
	public Token(float x, float y, int mode) {
		LOCATION_X = x;
		LOCATION_Y = y;
		this.mode = mode;
	}

	/**
	 * Gets the location x.
	 *
	 * @return the location x
	 */
	public float getLOCATION_X() {
		return LOCATION_X;
	}

	/**
	 * Gets the location y.
	 *
	 * @return the location y
	 */
	public float getLOCATION_Y() {
		return LOCATION_Y;
	}

	/**
	 * Sets the location X.
	 *
	 * @param location_X
	 *            the new location X
	 */
	public void setLocation_X(float location_X) {
		LOCATION_X = location_X;
	}

	/**
	 * Sets the location Y.
	 *
	 * @param location_Y
	 *            the new location Y
	 */
	public void setLocation_Y(float location_Y) {
		LOCATION_Y = location_Y;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

}
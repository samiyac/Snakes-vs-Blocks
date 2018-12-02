/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;

import java.io.Serializable;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// TODO: Auto-generated Javadoc
/**
 * The Class Wall.
 */
public class Wall implements Serializable {
	
	/** The location x. */
	private float LOCATION_X;
	
	/** The location y. */
	private float LOCATION_Y;
	
	/** The height. */
	private final float height;
	
	/** The color. */
	private final Color color = Color.BURLYWOOD;
	
	/** The rect. */
	private final Rectangle rect;

	/**
	 * Instantiates a new wall.
	 *
	 * @param LOCATION_X the location x
	 * @param LOCATION_Y the location y
	 * @param height the height
	 */
	public Wall(float LOCATION_X, float LOCATION_Y, float height) {
		this.LOCATION_X = LOCATION_X;
		this.LOCATION_Y = LOCATION_Y;
		this.height = height;
		rect = new Rectangle(5, height, color);
		rect.setX(LOCATION_X);
		rect.setY(LOCATION_Y);
	}

	/**
	 * Instantiates a new wall.
	 *
	 * @param lOCATION_X the location X
	 * @param lOCATION_Y the location Y
	 * @param translate_Y the translate Y
	 * @param height the height
	 */
	public Wall(float lOCATION_X, float lOCATION_Y, double translate_Y, float height) {
		// TODO Auto-generated constructor stub
		this.LOCATION_X = lOCATION_X;
		this.LOCATION_Y = lOCATION_Y;
		this.height = height;
		rect = new Rectangle(5, height, color);
		rect.setX(LOCATION_X);
		rect.setY(LOCATION_Y);
		rect.setTranslateY(translate_Y);
	}

	/**
	 * Gets the location X.
	 *
	 * @return the location X
	 */
	public float getLocationX() {
		return LOCATION_X;
	}

	/**
	 * Sets the location X.
	 *
	 * @param xpos the new location X
	 */
	public void setLocationX(float xpos) {
		this.LOCATION_X = xpos;
	}

	/**
	 * Gets the location Y.
	 *
	 * @return the location Y
	 */
	public float getLoactionY() {
		return LOCATION_Y;
	}

	/**
	 * Sets the location Y.
	 *
	 * @param ypos the new location Y
	 */
	public void setLocationY(float ypos) {
		this.LOCATION_Y = ypos;
	}

	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public float getHeight() {
		return height;
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
	 * Gets the rect.
	 *
	 * @return the rect
	 */
	public Rectangle getRect() {
		return rect;
	}
}

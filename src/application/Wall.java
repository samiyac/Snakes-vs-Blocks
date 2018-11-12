package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {
	private float LOCATION_X;
	private float LOCATION_Y;
	private final float height;
	private final Color color = Color.BURLYWOOD;
	private final Rectangle rect;

	public Wall(float LOCATION_X, float LOCATION_Y, float height) {
		this.LOCATION_X = LOCATION_X;
		this.LOCATION_Y = LOCATION_Y;
		this.height = height;
		rect = new Rectangle(5, height, color);
		rect.setX(LOCATION_X);
		rect.setY(LOCATION_Y);
	}

	public float getLocationX() {
        return LOCATION_X;
    }

    public void setLocationX(float xpos) {
        this.LOCATION_X = xpos;
    }

    public float getLoactionY() {
        return LOCATION_Y;
    }

    public void setLocationY(float ypos) {
        this.LOCATION_Y = ypos;
    }


	public float getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public Rectangle getRect() {
		return rect;
	}
}

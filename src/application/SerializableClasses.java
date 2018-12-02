/*
 * 
 */
package application;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class SerializableBlock.
 */
class SerializableBlock implements Serializable {
	
	/** The location x. */
	private int LOCATION_X;
	
	/** The location y. */
	private int LOCATION_Y;
	
	/** The Translate Y. */
	private final double Translate_Y;
	
	/** The width. */
	private final int width;
	
	/** The height. */
	private final int height;
	
	/** The value. */
	private int value;
	
	/** The color. */
	private final String color;
	
	/** The eaten. */
	private boolean eaten;

	/**
	 * Instantiates a new serializable block.
	 *
	 * @param block the block
	 */
	public SerializableBlock(Block block) {
		// TODO Auto-generated constructor stub
		LOCATION_X = block.getLocationX();
		LOCATION_Y = block.getLocationY();
		this.width = block.getWidth();
		this.height = block.getHeight();
		this.color = block.getColor().toString();
		this.eaten = block.isEaten();
		this.value = block.getValue();
		this.Translate_Y = block.getStack().getTranslateY();
	}

	/**
	 * Construct block.
	 *
	 * @param main the main
	 * @return the block
	 */
	public Block constructBlock(Main main) {
		Block block = new Block(width, height, color, value, eaten, LOCATION_X, LOCATION_Y, Translate_Y);
		main.getRoot().getChildren().add(block.getStack());
		return block;
	}

}

/**
 * The Class SerializableWall.
 */
class SerializableWall implements Serializable {
	
	/** The location x. */
	private float LOCATION_X;
	
	/** The location y. */
	private float LOCATION_Y;
	
	/** The Translate Y. */
	private final double Translate_Y;
	
	/** The height. */
	private final float height;

	/**
	 * Instantiates a new serializable wall.
	 *
	 * @param wall the wall
	 */
	public SerializableWall(Wall wall) {
		// TODO Auto-generated constructor stub
		LOCATION_X = wall.getLocationX();
		LOCATION_Y = wall.getLoactionY();
		Translate_Y = wall.getRect().getTranslateY();
		height = wall.getHeight();
	}

	/**
	 * Construct wall.
	 *
	 * @param main the main
	 * @return the wall
	 */
	public Wall constructWall(Main main) {
		Wall wall = new Wall(LOCATION_X, LOCATION_Y, Translate_Y, height);
		main.getRoot().getChildren().add(wall.getRect());
		System.out.println("wall set");
		return wall;
	}
}

/**
 * The Class SerializableSnake.
 */
class SerializableSnake implements Serializable {
	
	/** The length. */
	private int length;
	
	/** The location x. */
	private int LOCATION_X;
	
	/** The location y. */
	private int LOCATION_Y;
	
	/** The snake head pos X. */
	private double snakeHeadPosX;
	
	/** The radius. */
	private final double radius;
	
	/** The color. */
	private String color;

	/**
	 * Instantiates a new serializable snake.
	 *
	 * @param snake the snake
	 */
	public SerializableSnake(Snake snake) {
		length = snake.getLength();
		LOCATION_X = snake.getLOCATION_X();
		LOCATION_Y = snake.getLOCATION_Y();
		snakeHeadPosX = snake.getSnakeLength().get(0).getCenterX();
		radius = snake.getSnakeLength().get(0).getRadius();
		color = snake.getColor().toString();
	}

	/**
	 * Construct snake.
	 *
	 * @param main the main
	 * @return the snake
	 */
	public Snake constructSnake(Main main) {
		System.out.println(length+" length");
		Snake snake = new Snake(length, LOCATION_X, LOCATION_Y, snakeHeadPosX, radius, color, main);
		return snake;
	}
}

/**
 * The Class SerializableBall.
 */
class SerializableBall extends Token implements Serializable {
	
	/** The radius. */
	private final float radius;
	
	/** The color. */
	private final String color;
	
	/** The value. */
	private final int value;
	
	/** The Translate Y. */
	private final double Translate_Y;
	
	/** The eaten. */
	private boolean eaten = false;

	/**
	 * Instantiates a new serializable ball.
	 *
	 * @param ball the ball
	 */
	public SerializableBall(Ball ball) {
		// TODO Auto-generated constructor stub
		super(ball.getLOCATION_X(), ball.getLOCATION_Y(), ball.getMode());
		this.radius = ball.getRadius();
		this.color = ball.getColor().toString();
		this.eaten = ball.isEaten();
		this.value = ball.getValue();
		this.Translate_Y = ball.getPane().getTranslateY();
	}

	/**
	 * Construct ball.
	 *
	 * @param main the main
	 * @return the ball
	 */
	public Ball constructBall(Main main) {
		Ball ball = new Ball(LOCATION_X, LOCATION_Y, Translate_Y, radius, color, eaten, value, mode);
		main.getRoot().getChildren().add(ball.getPane());
		return ball;
	}
}

/**
 * The Class SerializableShield.
 */
class SerializableShield extends Token implements Serializable {

	/** The Translate Y. */
	private double Translate_Y;
	
	/** The img. */
	private String img;

	/**
	 * Instantiates a new serializable shield.
	 *
	 * @param X the x
	 * @param Y the y
	 * @param translateY the translate Y
	 * @param mode the mode
	 * @param img the img
	 */
	public SerializableShield(float X, float Y, double translateY, int mode, String img) {
		// TODO Auto-generated constructor stub
		super(X, Y, mode);
		Translate_Y = translateY;
		this.img = img;
	}

	/**
	 * Construct shield.
	 *
	 * @param main the main
	 * @return the shield
	 */
	public Shield constructShield(Main main) {
		Shield shield = new Shield(LOCATION_X, LOCATION_Y, Translate_Y, mode, img);
		main.getRoot().getChildren().add(shield.getStack());
		return shield;
	}
}

/**
 * The Class SerializableMagnet.
 */
class SerializableMagnet extends Token implements Serializable {

	/** The Translate Y. */
	private double Translate_Y;
	
	/** The img. */
	private String img;

	/**
	 * Instantiates a new serializable magnet.
	 *
	 * @param X the x
	 * @param Y the y
	 * @param translateY the translate Y
	 * @param mode the mode
	 * @param img the img
	 */
	public SerializableMagnet(float X, float Y, double translateY, int mode, String img) {
		// TODO Auto-generated constructor stub
		super(X, Y, mode);
		Translate_Y = translateY;
		this.img = img;
	}

	/**
	 * Construct magnet.
	 *
	 * @param main the main
	 * @return the magnet
	 */
	public Magnet constructMagnet(Main main) {
		Magnet magnet = new Magnet(LOCATION_X, LOCATION_Y, Translate_Y, mode, img);
		// magnet.getStack().setTranslateY(Translate_Y);
		main.getRoot().getChildren().add(magnet.getStack());
		return magnet;
	}
}

/**
 * The Class SerializableDB.
 */
class SerializableDB extends Token implements Serializable {

	/** The Translate Y. */
	private double Translate_Y;
	
	/** The img. */
	private String img;

	/**
	 * Instantiates a new serializable DB.
	 *
	 * @param X the x
	 * @param Y the y
	 * @param translateY the translate Y
	 * @param mode the mode
	 * @param img the img
	 */
	public SerializableDB(float X, float Y, double translateY, int mode, String img) {
		// TODO Auto-generated constructor stub
		super(X, Y, mode);
		Translate_Y = translateY;
		this.img = img;
	}

	/**
	 * Construct DB.
	 *
	 * @param main the main
	 * @return the destroy block
	 */
	public DestroyBlock constructDB(Main main) {
		DestroyBlock DB = new DestroyBlock(LOCATION_X, LOCATION_Y, Translate_Y, mode, img);
		// DB.getStack().setTranslateY(Translate_Y);
		main.getRoot().getChildren().add(DB.getStack());
		return DB;
	}
}

/**
 * The Class SerializableCoin.
 */
class SerializableCoin extends Token implements Serializable {

	/** The eaten. */
	private boolean eaten;
	
	/** The Translate Y. */
	private final double Translate_Y;
	
	/** The img. */
	String img;

	/**
	 * Instantiates a new serializable coin.
	 *
	 * @param coin the coin
	 */
	public SerializableCoin(Coin coin) {
		super(coin.getLOCATION_X(), coin.getLOCATION_Y(), coin.getMode());
		Translate_Y = coin.getStack().getTranslateY();
		this.eaten = coin.isEaten();
		this.img = coin.getIMAGE_SRC();
	}

	/**
	 * Construct coin.
	 *
	 * @param main the main
	 * @return the coin
	 */
	public Coin constructCoin(Main main) {
		Coin coin = new Coin(LOCATION_X, LOCATION_Y, Translate_Y, eaten, mode, img);
		main.getRoot().getChildren().add(coin.getStack());
		return coin;
	}

}

/**
 * The Class SerializableClasses.
 */
public class SerializableClasses {
	
	/**
	 * Construct block list.
	 *
	 * @param serializableBlocks the serializable blocks
	 * @param main the main
	 * @return the array list
	 */
	public ArrayList<ArrayList<Block>> constructBlockList(ArrayList<ArrayList<SerializableBlock>> serializableBlocks,
			Main main) {
		ArrayList<ArrayList<Block>> blocks = new ArrayList<>();
		for (int i = 0; i < serializableBlocks.size(); i++) {
			blocks.add(new ArrayList<Block>());
			for (SerializableBlock sBlock : serializableBlocks.get(i)) {
				Block temp = sBlock.constructBlock(main);
				blocks.get(i).add(temp);
				if (temp.isEaten()) {
					temp.getStack().setVisible(false);
				}
			}
		}
		return blocks;
	}

	/**
	 * Construct ball list.
	 *
	 * @param serializableBalls the serializable balls
	 * @param main the main
	 * @return the array list
	 */
	public ArrayList<ArrayList<Ball>> constructBallList(ArrayList<ArrayList<SerializableBall>> serializableBalls,
			Main main) {
		ArrayList<ArrayList<Ball>> balls = new ArrayList<>();
		for (int i = 0; i < serializableBalls.size(); i++) {
			balls.add(new ArrayList<Ball>());
			for (SerializableBall sBall : serializableBalls.get(i)) {
				Ball temp = sBall.constructBall(main);
				balls.get(i).add(temp);
				if (temp.isEaten()) {
					temp.getPane().setVisible(false);
				}
			}
		}
		return balls;
	}

	/**
	 * Construct wall list.
	 *
	 * @param serializableWalls the serializable walls
	 * @param main the main
	 * @return the array list
	 */
	public ArrayList<ArrayList<Wall>> constructWallList(ArrayList<ArrayList<SerializableWall>> serializableWalls,
			Main main) {
		ArrayList<ArrayList<Wall>> walls = new ArrayList<>();
		for (int i = 0; i < serializableWalls.size(); i++) {
			walls.add(new ArrayList<Wall>());
			for (SerializableWall sWall : serializableWalls.get(i)) {
				System.out.println("walls");
				Wall temp = sWall.constructWall(main);
				walls.get(i).add(temp);
			}
		}
		return walls;
	}

	/**
	 * Construct coin list.
	 *
	 * @param serializableCoins the serializable coins
	 * @param main the main
	 * @return the array list
	 */
	public ArrayList<ArrayList<Coin>> constructCoinList(ArrayList<ArrayList<SerializableCoin>> serializableCoins,
			Main main) {
		ArrayList<ArrayList<Coin>> coins = new ArrayList<>();
		for (int i = 0; i < serializableCoins.size(); i++) {
			coins.add(new ArrayList<Coin>());
			for (SerializableCoin sCoin : serializableCoins.get(i)) {
				Coin temp = sCoin.constructCoin(main);
				coins.get(i).add(temp);
				if (temp.isEaten()) {
					temp.getStack().setVisible(false);
				}
			}
		}
		return coins;
	}

	/**
	 * Construct serializable block list.
	 *
	 * @param blockOnScreen the block on screen
	 * @return the array list
	 */
	public ArrayList<ArrayList<SerializableBlock>> constructSerializableBlockList(
			ArrayList<ArrayList<Block>> blockOnScreen) {
		ArrayList<ArrayList<SerializableBlock>> Sblocks = new ArrayList<>();
		for (int i = 0; i < blockOnScreen.size(); i++) {
			Sblocks.add(new ArrayList<SerializableBlock>());
			for (Block block : blockOnScreen.get(i)) {
				Sblocks.get(i).add(new SerializableBlock(block));
			}
		}
		return Sblocks;
	}

	/**
	 * Construct serializable ball list.
	 *
	 * @param ballsOnScreen the balls on screen
	 * @return the array list
	 */
	public ArrayList<ArrayList<SerializableBall>> constructSerializableBallList(
			ArrayList<ArrayList<Ball>> ballsOnScreen) {
		ArrayList<ArrayList<SerializableBall>> Sballs = new ArrayList<>();
		for (int i = 0; i < ballsOnScreen.size(); i++) {
			Sballs.add(new ArrayList<SerializableBall>());
			for (Ball ball : ballsOnScreen.get(i)) {
				Sballs.get(i).add(new SerializableBall(ball));
			}
		}
		return Sballs;
	}

	/**
	 * Construct serializable wall list.
	 *
	 * @param wallsOnScreen the walls on screen
	 * @return the array list
	 */
	public ArrayList<ArrayList<SerializableWall>> constructSerializableWallList(
			ArrayList<ArrayList<Wall>> wallsOnScreen) {
		ArrayList<ArrayList<SerializableWall>> Swalls = new ArrayList<>();
		for (int i = 0; i < wallsOnScreen.size(); i++) {
			Swalls.add(new ArrayList<SerializableWall>());
			for (Wall wall : wallsOnScreen.get(i)) {
				Swalls.get(i).add(new SerializableWall(wall));
			}
		}
		return Swalls;
	}

	/**
	 * Construct serializable coin list.
	 *
	 * @param coinsOnScreen the coins on screen
	 * @return the array list
	 */
	public ArrayList<ArrayList<SerializableCoin>> constructSerializableCoinList(
			ArrayList<ArrayList<Coin>> coinsOnScreen) {
		ArrayList<ArrayList<SerializableCoin>> Scoins = new ArrayList<>();
		for (int i = 0; i < coinsOnScreen.size(); i++) {
			Scoins.add(new ArrayList<SerializableCoin>());
			for (Coin coin : coinsOnScreen.get(i)) {
				Scoins.get(i).add(new SerializableCoin(coin));
			}
		}
		return Scoins;
	}
}
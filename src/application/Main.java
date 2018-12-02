package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main extends Application {

	/** The Block on screen. */
	private ArrayList<ArrayList<Block>> BlockOnScreen = new ArrayList<>();
	
	/** The Balls on screen. */
	private ArrayList<ArrayList<Ball>> BallsOnScreen = new ArrayList<>();
	
	/** The Walls on screen. */
	private ArrayList<ArrayList<Wall>> WallsOnScreen = new ArrayList<>();
	
	/** The Coins on screen. */
	private ArrayList<ArrayList<Coin>> CoinsOnScreen = new ArrayList<>();

	/** The Shield on screen. */
	private Shield ShieldOnScreen;
	
	/** The Magnet on screen. */
	private Magnet MagnetOnScreen;
	
	/** The Destroy block on screen. */
	private DestroyBlock DestroyBlockOnScreen;

	/** The snake. */
	private Snake snake;
	
	/** The ball factory. */
	private BallFactory ballFactory = new BallFactory(this);
	
	/** The wall factory. */
	private WallFactory wallFactory = new WallFactory(this);
	
	/** The block factory. */
	private BlockFactory blockFactory = new BlockFactory(this);
	
	/** The coin factory. */
	private CoinFactory coinFactory = new CoinFactory(this);
	
	/** The token factory. */
	private TokenFactory tokenFactory = new TokenFactory(this);
	
	/** The labels. */
	private Labels labels = new Labels(this);
	
	/** The game state. */
	private GameState gameState;
	
	/** The deserializer. */
	private Deserializer deserializer = new Deserializer(this);
	
	/** The serializer. */
	private Serializer serializer = new Serializer(this);
	
	/** The drop down. */
	private DropDown dropDown = new DropDown(this);
	
	/** The snake motion. */
	private SnakeMotion snakeMotion = new SnakeMotion(this);
	
	/** The Burst animation. */
	private burstAnimation BurstAnimation = new burstAnimation(this);
	
	/** The magnet handler. */
	private MagnetHandler magnetHandler = new MagnetHandler(this);

	/** The score. */
	private int score;
	
	/** The velocity. */
	private double velocity;

	/** The score label. */
	private Text scoreLabel = new Text();
	
	/** The coin label. */
	private Text coinLabel = new Text();
	
	/** The length label. */
	private Text lengthLabel = new Text();
	
	/** The timer label. */
	private Label timerLabel = new Label();
	
	/** The drop down menu. */
	private ChoiceBox<String> dropDownMenu = new ChoiceBox<>();

	/** The hit block. */
	private Block hitBlock;

	/** The shield. */
	private boolean shield;
	
	/** The end. */
	private boolean end;
	
	/** The block hit. */
	private boolean BLOCK_HIT;
	
	/** The restart. */
	private boolean restart;

	/** The root. */
	private Group root;
	
	/** The scene. */
	private Scene scene;
	
	/** The stage. */
	private static Stage stage;

	/** The bonus coin. */
	public static int bonusCoin;
	
	/** The tempscore. */
	public static int tempscore;
	
	/** The mode. */
	public static int mode;
	
	/** The color ball. */
	public static Color colorBall;
	
	/** The Shield timer. */
	public static int ShieldTimer = 5;
	
	/** The Magnet timer. */
	public static int MagnetTimer = 5;

	/**
	 * Instantiates a new main.
	 *
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Main() throws ClassNotFoundException, IOException {
		deserializer.deserialize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		startpage(primaryStage);
	}

	/**
	 * Startpage.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	public void startpage(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Snake vs Block");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		primaryStage.setScene(new Scene(root1));
		primaryStage.show();
	}

	/**
	 * Playgame.
	 *
	 * @param s
	 *            the s
	 */
	public final void playgame(Stage s) {
		if (colorBall == null) {
			colorBall = Color.YELLOW;
		}
		root = new Group();
		scene = new Scene(root, 500, 1000, Color.BLACK);
		System.out.println("hello");
		s.setScene(scene);
		s.show();
		keyInputs();
		if (!restart) {
			blockFactory.BlockAnimation();
			ballFactory.BallAnimation();
			tokenFactory.TokenAnimation();
			wallFactory.WallAnimation();
			snakeMotion.SnakeAnimation();
			coinFactory.CoinAnimation();
		}
		System.out.println(velocity + " velocity");
		labels.setTimer();
		closeRequest(s);
	}

	/**
	 * Key inputs.
	 */
	public void keyInputs() {
		scene.setOnKeyPressed(e -> {
			KeyCode x = e.getCode();

			if (x == KeyCode.LEFT) {
				snake.moveL();
				lengthLabel.setX(lengthLabel.getX() - 10);

				boolean move = false;
				try {
					isWall();
					isBlock();
				} catch (InvalidMoveException EX) {
					// System.out.println(EX);
					move = true;
				}

				if (move || (!snake.getSnakeLength().isEmpty() && snake.getSnakeLength().get(0).getCenterX() < 20)) {
					snake.moveR();
					lengthLabel.setX(lengthLabel.getX() + 10);
				}
			}

			if (x == KeyCode.RIGHT) {

				snake.moveR();
				lengthLabel.setX(lengthLabel.getX() + 10);

				boolean move = false;
				try {
					isWall();
					isBlock();
				} catch (InvalidMoveException EX) {
					// System.out.println(EX);
					move = true;
				}

				if (move || (!snake.getSnakeLength().isEmpty() && snake.getSnakeLength().get(0).getCenterX() > 480)) {
					snake.moveL();
					lengthLabel.setX(lengthLabel.getX() - 10);
				}
			}

		});
	}

	/**
	 * Close request.
	 *
	 * @param s the s
	 */
	public void closeRequest(Stage s) {
		s.setOnCloseRequest(event -> {
			System.out.println("Stage is closing");
			try {
				throw new EndGameException("exit");
			} catch (EndGameException e1) {
				// TODO Auto-generated catch block
				try {
					serializer.saveGameState();
					serializer.serialize();
					end = true;
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				} finally {
					System.exit(0);
				}

			}
		});
	}

	/**
	 * Checks if is wall.
	 *
	 * @throws InvalidMoveException the invalid move exception
	 */
	public void isWall() throws InvalidMoveException {
		if (!snake.getSnakeLength().isEmpty()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			for (int i = 0; i < WallsOnScreen.size(); i++) {
				for (Wall wall : WallsOnScreen.get(i)) {
					if (snakeHead.intersects(wall.getRect().getBoundsInParent())) {
						throw new InvalidMoveException("wall is present");
					}
				}
			}
		}
	}

	/**
	 * Checks if is block.
	 *
	 * @throws InvalidMoveException the invalid move exception
	 */
	public void isBlock() throws InvalidMoveException {
		if (!snake.getSnakeLength().isEmpty()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			for (int i = 0; i < BlockOnScreen.size(); i++) {
				for (Block block : BlockOnScreen.get(i)) {
					if (snakeHead.intersects(block.getStack().getBoundsInParent()) && !block.isEaten()) {
						if (block.getStack().getLayoutY() + block.getStack().getTranslateY() > 456) {
							throw new InvalidMoveException("block is present");
						}
					}
				}
			}
		}
	}

	/**
	 * Gets the block on screen.
	 *
	 * @return the block on screen
	 */
	public ArrayList<ArrayList<Block>> getBlockOnScreen() {
		return BlockOnScreen;
	}

	/**
	 * Sets the block on screen.
	 *
	 * @param blockOnScreen the new block on screen
	 */
	public void setBlockOnScreen(ArrayList<ArrayList<Block>> blockOnScreen) {
		BlockOnScreen = blockOnScreen;
	}

	/**
	 * Gets the balls on screen.
	 *
	 * @return the balls on screen
	 */
	public ArrayList<ArrayList<Ball>> getBallsOnScreen() {
		return BallsOnScreen;
	}

	/**
	 * Sets the balls on screen.
	 *
	 * @param ballsOnScreen the new balls on screen
	 */
	public void setBallsOnScreen(ArrayList<ArrayList<Ball>> ballsOnScreen) {
		BallsOnScreen = ballsOnScreen;
	}

	/**
	 * Gets the walls on screen.
	 *
	 * @return the walls on screen
	 */
	public ArrayList<ArrayList<Wall>> getWallsOnScreen() {
		return WallsOnScreen;
	}

	/**
	 * Sets the walls on screen.
	 *
	 * @param wallsOnScreen the new walls on screen
	 */
	public void setWallsOnScreen(ArrayList<ArrayList<Wall>> wallsOnScreen) {
		WallsOnScreen = wallsOnScreen;
	}

	/**
	 * Gets the coins on screen.
	 *
	 * @return the coins on screen
	 */
	public ArrayList<ArrayList<Coin>> getCoinsOnScreen() {
		return CoinsOnScreen;
	}

	/**
	 * Sets the coins on screen.
	 *
	 * @param coinsOnScreen the new coins on screen
	 */
	public void setCoinsOnScreen(ArrayList<ArrayList<Coin>> coinsOnScreen) {
		CoinsOnScreen = coinsOnScreen;
	}

	/**
	 * Gets the shield on screen.
	 *
	 * @return the shield on screen
	 */
	public Shield getShieldOnScreen() {
		return ShieldOnScreen;
	}

	/**
	 * Sets the shield on screen.
	 *
	 * @param shieldOnScreen the new shield on screen
	 */
	public void setShieldOnScreen(Shield shieldOnScreen) {
		ShieldOnScreen = shieldOnScreen;
	}

	/**
	 * Gets the magnet on screen.
	 *
	 * @return the magnet on screen
	 */
	public Magnet getMagnetOnScreen() {
		return MagnetOnScreen;
	}

	/**
	 * Sets the magnet on screen.
	 *
	 * @param magnetOnScreen the new magnet on screen
	 */
	public void setMagnetOnScreen(Magnet magnetOnScreen) {
		MagnetOnScreen = magnetOnScreen;
	}

	/**
	 * Gets the destroy block on screen.
	 *
	 * @return the destroy block on screen
	 */
	public DestroyBlock getDestroyBlockOnScreen() {
		return DestroyBlockOnScreen;
	}

	/**
	 * Sets the destroy block on screen.
	 *
	 * @param destroyBlockOnScreen the new destroy block on screen
	 */
	public void setDestroyBlockOnScreen(DestroyBlock destroyBlockOnScreen) {
		DestroyBlockOnScreen = destroyBlockOnScreen;
	}

	/**
	 * Gets the snake.
	 *
	 * @return the snake
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * Sets the snake.
	 *
	 * @param snake the new snake
	 */
	public void setSnake(Snake snake) {
		this.snake = snake;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Gets the velocity.
	 *
	 * @return the velocity
	 */
	public double getVelocity() {
		return velocity;
	}

	/**
	 * Sets the velocity.
	 *
	 * @param v the new velocity
	 */
	public void setVelocity(double v) {
		this.velocity = v;
	}

	/**
	 * Update velocity.
	 *
	 * @param inc the inc
	 */
	public void updateVelocity(double inc) {
		this.velocity = velocity + inc;
	}

	/**
	 * Gets the score label.
	 *
	 * @return the score label
	 */
	public Text getScoreLabel() {
		return scoreLabel;
	}

	/**
	 * Sets the score label.
	 *
	 * @param scoreLabel the new score label
	 */
	public void setScoreLabel(Text scoreLabel) {
		this.scoreLabel = scoreLabel;
	}

	/**
	 * Gets the coin label.
	 *
	 * @return the coin label
	 */
	public Text getCoinLabel() {
		return coinLabel;
	}

	/**
	 * Sets the coin label.
	 *
	 * @param coinLabel the new coin label
	 */
	public void setCoinLabel(Text coinLabel) {
		this.coinLabel = coinLabel;
	}

	/**
	 * Gets the length label.
	 *
	 * @return the length label
	 */
	public Text getLengthLabel() {
		return lengthLabel;
	}

	/**
	 * Sets the length label.
	 *
	 * @param lengthLabel the new length label
	 */
	public void setLengthLabel(Text lengthLabel) {
		this.lengthLabel = lengthLabel;
	}

	/**
	 * Gets the timer label.
	 *
	 * @return the timer label
	 */
	public Label getTimerLabel() {
		return timerLabel;
	}

	/**
	 * Sets the timer label.
	 *
	 * @param timerLabel the new timer label
	 */
	public void setTimerLabel(Label timerLabel) {
		this.timerLabel = timerLabel;
	}

	/**
	 * Gets the drop down menu.
	 *
	 * @return the drop down menu
	 */
	public ChoiceBox<String> getDropDownMenu() {
		return dropDownMenu;
	}

	/**
	 * Sets the drop down menu.
	 *
	 * @param dropDownMenu the new drop down menu
	 */
	public void setDropDownMenu(ChoiceBox<String> dropDownMenu) {
		this.dropDownMenu = dropDownMenu;
	}

	/**
	 * Gets the hit block.
	 *
	 * @return the hit block
	 */
	public Block getHitBlock() {
		return hitBlock;
	}

	/**
	 * Sets the hit block.
	 *
	 * @param hitBlock the new hit block
	 */
	public void setHitBlock(Block hitBlock) {
		this.hitBlock = hitBlock;
	}

	/**
	 * Checks if is shield.
	 *
	 * @return true, if is shield
	 */
	public boolean isShield() {
		return shield;
	}

	/**
	 * Sets the shield.
	 *
	 * @param shield the new shield
	 */
	public void setShield(boolean shield) {
		this.shield = shield;
	}

	/**
	 * Checks if is end.
	 *
	 * @return true, if is end
	 */
	public boolean isEnd() {
		return end;
	}

	/**
	 * Sets the end.
	 *
	 * @param end the new end
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}

	/**
	 * Checks if is block hit.
	 *
	 * @return true, if is block hit
	 */
	public boolean isBLOCK_HIT() {
		return BLOCK_HIT;
	}

	/**
	 * Sets the block hit.
	 *
	 * @param bLOCK_HIT the new block hit
	 */
	public void setBLOCK_HIT(boolean bLOCK_HIT) {
		BLOCK_HIT = bLOCK_HIT;
	}

	/**
	 * Checks if is restart.
	 *
	 * @return true, if is restart
	 */
	public boolean isRestart() {
		return restart;
	}

	/**
	 * Sets the restart.
	 *
	 * @param restart the new restart
	 */
	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public Group getRoot() {
		return root;
	}

	/**
	 * Sets the root.
	 *
	 * @param root the new root
	 */
	public void setRoot(Group root) {
		this.root = root;
	}

	/**
	 * Gets the scene.
	 *
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	/**
	 * Sets the scene.
	 *
	 * @param scene the new scene
	 */
	public void setScene(Scene scene) {
		this.scene = scene;
	}

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public static Stage getStage() {
		return stage;
	}

	/**
	 * Sets the stage.
	 *
	 * @param stage the new stage
	 */
	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	/**
	 * Gets the bonus coin.
	 *
	 * @return the bonus coin
	 */
	public static int getBonusCoin() {
		return bonusCoin;
	}

	/**
	 * Sets the bonus coin.
	 *
	 * @param bonusCoin the new bonus coin
	 */
	public static void setBonusCoin(int bonusCoin) {
		Main.bonusCoin = bonusCoin;
	}

	/**
	 * Gets the tempscore.
	 *
	 * @return the tempscore
	 */
	public static int getTempscore() {
		return tempscore;
	}

	/**
	 * Sets the tempscore.
	 *
	 * @param tempscore the new tempscore
	 */
	public static void setTempscore(int tempscore) {
		Main.tempscore = tempscore;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public static int getMode() {
		return mode;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public static void setMode(int mode) {
		Main.mode = mode;
	}

	/**
	 * Gets the color ball.
	 *
	 * @return the color ball
	 */
	public static Color getColorBall() {
		return colorBall;
	}

	/**
	 * Sets the color ball.
	 *
	 * @param colorBall the new color ball
	 */
	public static void setColorBall(Color colorBall) {
		Main.colorBall = colorBall;
	}

	/**
	 * Gets the shield timer.
	 *
	 * @return the shield timer
	 */
	public static int getShieldTimer() {
		return ShieldTimer;
	}

	/**
	 * Sets the shield timer.
	 *
	 * @param shieldTimer the new shield timer
	 */
	public static void setShieldTimer(int shieldTimer) {
		ShieldTimer = shieldTimer;
	}

	/**
	 * Gets the magnet timer.
	 *
	 * @return the magnet timer
	 */
	public static int getMagnetTimer() {
		return MagnetTimer;
	}

	/**
	 * Sets the magnet timer.
	 *
	 * @param magnetTimer the new magnet timer
	 */
	public static void setMagnetTimer(int magnetTimer) {
		MagnetTimer = magnetTimer;
	}

	/**
	 * Gets the ball factory.
	 *
	 * @return the ball factory
	 */
	public BallFactory getBallFactory() {
		return ballFactory;
	}

	/**
	 * Sets the ball factory.
	 *
	 * @param ballFactory the new ball factory
	 */
	public void setBallFactory(BallFactory ballFactory) {
		this.ballFactory = ballFactory;
	}

	/**
	 * Gets the wall factory.
	 *
	 * @return the wall factory
	 */
	public WallFactory getWallFactory() {
		return wallFactory;
	}

	/**
	 * Sets the wall factory.
	 *
	 * @param wallFactory the new wall factory
	 */
	public void setWallFactory(WallFactory wallFactory) {
		this.wallFactory = wallFactory;
	}

	/**
	 * Gets the block factory.
	 *
	 * @return the block factory
	 */
	public BlockFactory getBlockFactory() {
		return blockFactory;
	}

	/**
	 * Sets the block factory.
	 *
	 * @param blockFactory the new block factory
	 */
	public void setBlockFactory(BlockFactory blockFactory) {
		this.blockFactory = blockFactory;
	}

	/**
	 * Gets the coin factory.
	 *
	 * @return the coin factory
	 */
	public CoinFactory getCoinFactory() {
		return coinFactory;
	}

	/**
	 * Sets the coin factory.
	 *
	 * @param coinFactory the new coin factory
	 */
	public void setCoinFactory(CoinFactory coinFactory) {
		this.coinFactory = coinFactory;
	}

	/**
	 * Gets the token factory.
	 *
	 * @return the token factory
	 */
	public TokenFactory getTokenFactory() {
		return tokenFactory;
	}

	/**
	 * Sets the token factory.
	 *
	 * @param tokenFactory the new token factory
	 */
	public void setTokenFactory(TokenFactory tokenFactory) {
		this.tokenFactory = tokenFactory;
	}

	/**
	 * Gets the labels.
	 *
	 * @return the labels
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * Sets the labels.
	 *
	 * @param labels the new labels
	 */
	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	/**
	 * Gets the game state.
	 *
	 * @return the game state
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * Sets the game state.
	 *
	 * @param gameState the new game state
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * Gets the deserializer.
	 *
	 * @return the deserializer
	 */
	public Deserializer getDeserializer() {
		return deserializer;
	}

	/**
	 * Sets the deserializer.
	 *
	 * @param deserializer the new deserializer
	 */
	public void setDeserializer(Deserializer deserializer) {
		this.deserializer = deserializer;
	}

	/**
	 * Gets the serializer.
	 *
	 * @return the serializer
	 */
	public Serializer getSerializer() {
		return serializer;
	}

	/**
	 * Sets the serializer.
	 *
	 * @param serializer the new serializer
	 */
	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	/**
	 * Gets the drop down.
	 *
	 * @return the drop down
	 */
	public DropDown getDropDown() {
		return dropDown;
	}

	/**
	 * Sets the drop down.
	 *
	 * @param dropDown the new drop down
	 */
	public void setDropDown(DropDown dropDown) {
		this.dropDown = dropDown;
	}

	/**
	 * Gets the snake motion.
	 *
	 * @return the snake motion
	 */
	public SnakeMotion getSnakeMotion() {
		return snakeMotion;
	}

	/**
	 * Sets the snake motion.
	 *
	 * @param snakeMotion the new snake motion
	 */
	public void setSnakeMotion(SnakeMotion snakeMotion) {
		this.snakeMotion = snakeMotion;
	}

	/**
	 * Gets the burst animation.
	 *
	 * @return the burst animation
	 */
	public burstAnimation getBurstAnimation() {
		return BurstAnimation;
	}

	/**
	 * Sets the burst animation.
	 *
	 * @param burstAnimation the new burst animation
	 */
	public void setBurstAnimation(burstAnimation burstAnimation) {
		BurstAnimation = burstAnimation;
	}

	/**
	 * Gets the magnet handler.
	 *
	 * @return the magnet handler
	 */
	public MagnetHandler getMagnetHandler() {
		return magnetHandler;
	}

	/**
	 * Sets the magnet handler.
	 *
	 * @param magnetHandler the new magnet handler
	 */
	public void setMagnetHandler(MagnetHandler magnetHandler) {
		this.magnetHandler = magnetHandler;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

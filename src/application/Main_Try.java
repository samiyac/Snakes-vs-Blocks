//package application;
//
//import javafx.animation.Animation;
//import javafx.animation.AnimationTimer;
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Bounds;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.StackPane;
//import javafx.scene.media.AudioClip;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.scene.shape.Ellipse;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class Main_Try extends Application {
//
//	private final ArrayList<ArrayList<Block>> BlockOnScreen = new ArrayList<>();
//	private final ArrayList<ArrayList<Ball>> BallsOnScreen = new ArrayList<>();
//	private final ArrayList<ArrayList<Wall>> WallsOnScreen = new ArrayList<>();
//	private final ArrayList<ArrayList<Coin>> CoinsOnScreen = new ArrayList<>();
//	private Shield ShieldOnScreen;
//	private Magnet MagnetOnScreen;
//	private DestroyBlock DestroyBlockOnScreen;
//	private final Snake snake = new Snake(250, 550, 10, Color.YELLOW);
//	private int score = 0;
//	private double velocity = 7;
//	private Text scoreLabel;
//	ChoiceBox<String> dropDownMenu;
//	private boolean BLOCK_HIT = false;
//	private Block hitBlock;
//	private boolean shield = false;
//	Media music = new Media(new File("mariotrim.wav").toURI().toString());
//	MediaPlayer player = new MediaPlayer(music);
//	private final static Group root = new Group();
//	Stage stage;
//	private Scene scene = new Scene(root, 500, 1000, Color.BLACK);
//
//	public int getScore() {
//		return score;
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		stage = primaryStage;
//		primaryStage.setTitle("Snake vs Block");
//		primaryStage.setScene(scene);
//		setGame();
//		primaryStage.show();
//		scene.setOnKeyPressed(e -> {
//			// System.out.println(snake.getSnakeLength().get(0).getCenterX());
//			switch (e.getCode()) {
//			case LEFT: {
//				snake.moveL();
//				if (isWall() || isBlock()
//						|| (!snake.getSnakeLength().isEmpty() && snake.getSnakeLength().get(0).getCenterX() < 20)) {
//					snake.moveR();
//				}
//			}
//				break;
//			case RIGHT: {
//				snake.moveR();
//				if (isWall() || isBlock()
//						|| (!snake.getSnakeLength().isEmpty() && snake.getSnakeLength().get(0).getCenterX() > 480)) {
//					snake.moveL();
//				}
//			}
//				break;
//			}
//
//		});
//
//		BlockAnimation();
//		BallAnimation();
//		TokenAnimation();
//		WallAnimation();
//		SnakeAnimation(stage);
//		CoinAnimation();
//	}
//
//	public void setGame() {
//		for (int i = 0; i < Snake.getSnakeLength().size(); i++) {
//			root.getChildren().add(Snake.getSnakeLength().get(i));
//		}
//		setDropDownBox();
//		setWalls();
//		setBlocks(-100);
//		setBalls(-100);
//		setCoins(-100);
//		setScore();
//	}
//
//	private void setScore() {
//		scoreLabel = new Text("Score : " + score);
//		scoreLabel.setX(400);
//		scoreLabel.setY(50);
//		scoreLabel.setFont(Font.font(15));
//		scoreLabel.setFill(Color.WHITE);
//		root.getChildren().addAll(scoreLabel);
//		scoreLabel.toFront();
//	}
//
//	public void updateScore(int inc) {
//		this.score += inc;
//		scoreLabel.setText("Score : " + score);
//		scoreLabel.toFront();
//	}
//
//	private void setDropDownBox() {
//		dropDownMenu = new ChoiceBox<>();
//		dropDownMenu.getItems().addAll("Restart", "Exit Game");
//		dropDownMenu.setLayoutX(400);
//		dropDownMenu.setLayoutY(3);
//		dropDownMenu.setValue("Exit Game");
//		dropDownMenu.setBackground(Background.EMPTY);
//		String style = "-fx-background-color: rgba(255,255,0);";
//		dropDownMenu.setStyle(style);
//		root.getChildren().addAll(dropDownMenu);
//		dropDownMenu.toFront();
//	}
//
//	public boolean isWall() {
//		if (!snake.getSnakeLength().isEmpty()) {
//			Circle snakeHead = Snake.getSnakeLength().get(0);
//			for (int i = 0; i < WallsOnScreen.size(); i++) {
//				for (Wall wall : WallsOnScreen.get(i)) {
//					if (snakeHead.intersects(wall.getRect().getBoundsInParent())) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
//
//	public boolean isBlock() {
//		if (!snake.getSnakeLength().isEmpty()) {
//			Circle snakeHead = Snake.getSnakeLength().get(0);
//			for (int i = 0; i < BlockOnScreen.size(); i++) {
//				for (Block block : BlockOnScreen.get(i)) {
//					if (snakeHead.intersects(block.getStack().getBoundsInParent()) && !block.isEaten()) {
//						// System.out.println(block.getStack().getLayoutY() +
//						// block.getStack().getTranslateY());
//						if (block.getStack().getLayoutY() + block.getStack().getTranslateY() > 456) {
//							return true;
//						}
//					}
//				}
//			}
//		}
//		return false;
//	}
//
//	public void BlockAnimation() {
//		KeyFrame kf = new KeyFrame(Duration.millis(50), new BlockHandler());
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
//
//	private class BlockHandler implements EventHandler<ActionEvent> {
//		public void handle(ActionEvent event) {
//			if (!BLOCK_HIT) {
//				for (int i = 0; i < BlockOnScreen.size(); i++) {
//					{
//						for (int j = 0; j < BlockOnScreen.get(i).size(); j++) {
//							StackPane St = BlockOnScreen.get(i).get(j).getStack();
//							St.setTranslateY(St.getTranslateY() + velocity);
//						}
//					}
//				}
//				// System.out.println("notblock");
//				scoreLabel.toFront();
//				checkBlockScroll();
//				dropDownMenu.toFront();
//			} else {
//				// System.out.println("block");
//			}
//
//		}
//	}
//
//	public void CoinAnimation() {
//		KeyFrame kf = new KeyFrame(Duration.millis(50), new CoinHandler());
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
//
//	private class CoinHandler implements EventHandler<ActionEvent> {
//
//		public void handle(ActionEvent event) {
//			if (!BLOCK_HIT) {
//				for (int i = 0; i < CoinsOnScreen.size(); i++) {
//					for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
//						StackPane St = CoinsOnScreen.get(i).get(j).getStack();
//						St.setTranslateY(St.getTranslateY() + velocity);
//					}
//				}
//				checkCoinScroll();
//			}
//
//		}
//	}
//
//	public void BallAnimation() {
//		KeyFrame kf = new KeyFrame(Duration.millis(50), new BallHandler());
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
//
//	private class BallHandler implements EventHandler<ActionEvent> {
//
//		public void handle(ActionEvent event) {
//			if (!BLOCK_HIT) {
//				for (int i = 0; i < BallsOnScreen.size(); i++) {
//					for (int j = 0; j < BallsOnScreen.get(i).size(); j++) {
//						StackPane St = BallsOnScreen.get(i).get(j).getPane();
//						St.setTranslateY(St.getTranslateY() + velocity);
//					}
//				}
//				checkBallScroll();
//			}
//
//		}
//	}
//
//	public void TokenAnimation() {
//		KeyFrame kf = new KeyFrame(Duration.millis(50), new TokenHandler());
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//
//	}
//
//	private class TokenHandler implements EventHandler<ActionEvent> {
//		public void handle(ActionEvent event) {
//			if (!BLOCK_HIT) {
//				if (MagnetOnScreen != null) {
//					StackPane magnet = MagnetOnScreen.getStack();
//					magnet.setTranslateY(magnet.getTranslateY() + velocity);
//				}
//				if (ShieldOnScreen != null) {
//					StackPane shield = ShieldOnScreen.getStack();
//					shield.setTranslateY(shield.getTranslateY() + velocity);
//				}
//				if (DestroyBlockOnScreen != null) {
//					StackPane DB = DestroyBlockOnScreen.getStack();
//					DB.setTranslateY(DB.getTranslateY() + velocity);
//				}
//				checkTokenScroll();
//			}
//
//		}
//
//	}
//
//	public void WallAnimation() {
//		KeyFrame kf = new KeyFrame(Duration.millis(50), new WallHandler());
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
//
//	private class WallHandler implements EventHandler<ActionEvent> {
//		public void handle(ActionEvent event) {
//			if (!BLOCK_HIT) {
//				for (int i = 0; i < WallsOnScreen.size(); i++) {
//					for (int j = 0; j < WallsOnScreen.get(i).size(); j++) {
//						Wall wall = WallsOnScreen.get(i).get(j);
//						wall.getRect().setTranslateY(wall.getRect().getTranslateY() + velocity);
//					}
//				}
//				checkWallScroll();
//			}
//
//		}
//	}
//
//	public void SnakeAnimation(Stage stage) {
//		KeyFrame kf = new KeyFrame(Duration.millis(50), new SnakeHandler(stage));
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(Animation.INDEFINITE);
//		timeline.play();
//	}
//
//	private class SnakeHandler implements EventHandler<ActionEvent> {
//
//		Stage s;
//
//		public SnakeHandler(Stage s) {
//			this.s = s;
//		}
//
//		@Override
//		public void handle(ActionEvent event) {
//			// TODO Auto-generated method stub
//			if (!BLOCK_HIT) {
//				snakeIntersectBlock();
//				snakeIntersectBall();
//				snakeIntersectsDB();
//				snakeIntersectsShield();
//				snakeIntersectsMagnet();
//				snakeIntersectCoin();
//			}
//			if (shield) {
//				snake.changeColors();
//			}
//		}
//	}
//
//	public void PlayBurst(Bounds bounds) {
//		double x = bounds.getMaxX() + bounds.getMinX();
//		x /= 2;
//		double y = bounds.getMaxY() + bounds.getMinY();
//		y /= 2;
//		BurstAnimation(x, y);
//	}
//
//	public void BurstAnimation(double x, double y) {
//		KeyFrame kf = new KeyFrame(Duration.millis(2), new BurstAnimationHandler(x, y));
//		Timeline timeline = new Timeline(kf);
//		timeline.setCycleCount(1);
//		timeline.play();
//	}
//
//	private class BurstAnimationHandler implements EventHandler<ActionEvent> {
//
//		final double x;
//		final double y;
//		final long duration = java.time.Duration.ofSeconds(10).toNanos();
//		final int side = 150;
//		final double radius = Math.sqrt(2) * side;
//		final Rectangle[] rectangles = new Rectangle[50];
//		final ArrayList<Long> delays = new ArrayList<>();
//		final ArrayList<Double> angles = new ArrayList<Double>();
//
//		public BurstAnimationHandler(double x, double y) {
//			// TODO Auto-generated constructor stub
//			this.x = x;
//			this.y = y;
//		}
//
//		@Override
//		public void handle(ActionEvent event) {
//			// TODO Auto-generated method stub
//			// System.out.println("hello");
//			for (int i = 0; i < 50; i++) {
//				int rand = (int) (Math.random() * 360) + 1;
//				rectangles[i] = new Rectangle(10, 10, Color.hsb(rand, 1, 1));
//				angles.add(2 * Math.random() * Math.PI);
//				delays.add((long) (Math.random() * duration));
//			}
//			root.getChildren().addAll(rectangles);
//
//			AnimationTimer Burst = new AnimationTimer() {
//				int k = 0;
//
//				@Override
//				public void handle(long now) {
//					// TODO Auto-generated method stub
//					k++;
//					// System.out.println(k);
//					if (k < 800) {
//						for (int i = 0; i < 50; i++) {
//							Rectangle rect = rectangles[i];
//							long time = (now - delays.get(i)) % duration;
//							double d = time * radius / duration;
//							rect.setTranslateX(Math.cos(angles.get(i)) * d + x);
//							rect.setTranslateY(Math.sin(angles.get(i)) * d + y);
//							rect.setOpacity((duration - time) / (double) duration);
//
//						}
//					} else {
//						this.stop();
//						root.getChildren().removeAll(rectangles);
//					}
//
//				}
//			};
//			Burst.start();
//		}
//	}
//
//	private void checkBlockScroll() {
//		int[] distance = { -100, -200 };
//		int c = (int) (Math.random() * 2);
//		if (BlockOnScreen.size() > 0) {
//			StackPane block = BlockOnScreen.get(BlockOnScreen.size() - 1).get(0).getStack();
//			if (block.getTranslateY() + block.getLayoutY() > 100) {
//				setBlocks(distance[c]);
//			}
//			block = BlockOnScreen.get(0).get(0).getStack();
//			if (block.getTranslateY() >= 1100 - block.getLayoutY()) {
//				BlockOnScreen.remove(0);
//			}
//		}
//		if (BlockOnScreen.size() == 0) {
//			setBlocks(distance[c]);
//		}
//	}
//
//	private void setBlocks(int distance) {
//		int[] xCoord = { 0, 100, 200, 300, 400 };
//		ArrayList<Color> colors = new ArrayList<>();
//		colors.add(Color.DODGERBLUE);
//		colors.add(Color.PLUM);
//		colors.add(Color.TOMATO);
//		colors.add(Color.SALMON);
//		colors.add(Color.AQUAMARINE);
//		Collections.shuffle(colors);
//		int lengthSnake = Snake.getSnakeLength().size();
//		ArrayList<Block> blockList = new ArrayList<>();
//		String bin = "00000";
//		while (bin.equals("00000")) {
//			int x = (int) (Math.random() * 31) + 1;
//			bin = Integer.toBinaryString(x);
//			while (bin.length() < 5) {
//				bin = "0".concat(bin);
//			}
//
//			int counter = 0;
//			String in = bin;
//			while (counter < bin.length()) {
//				if (!checkBlockPosition(xCoord[counter], distance)) {
//
//					if (counter > 0 && counter < bin.length() - 1) {
//						bin = bin.substring(0, counter) + "0" + bin.substring(counter + 1);
//					}
//					if (counter == 0) {
//						bin = "0" + bin.substring(counter + 1);
//					}
//					if (counter == bin.length() - 1) {
//						bin = bin.substring(0, counter) + "0";
//					}
//
//				}
//				counter++;
//			}
//		}
//
//		boolean[] chain = new boolean[5];
//		for (int i = 0; i < bin.length() - 1; i++) {
//			if (bin.charAt(i) == bin.charAt(i + 1) && bin.charAt(i) == '1') {
//				chain[i] = true;
//				chain[i + 1] = true;
//			}
//		}
//
//		int counter = 0;
//		ArrayList<Integer> reqBlocks = new ArrayList<>();
//		while (counter < 5) {
//			if (chain[counter]) {
//				int c = counter;
//				int endOfChain = 0;
//				for (int j = counter; j < chain.length; j++) {
//					if (!chain[j]) {
//						endOfChain = j - 1;
//						counter = j;
//						break;
//					} else if (j == chain.length - 1) {
//						endOfChain = j;
//						counter = j + 1;
//					}
//				}
//				int lengthChain = endOfChain - c + 1;
//				int b = (int) (Math.random() * lengthChain) + c;
//				reqBlocks.add(b);
//			} else {
//				counter++;
//			}
//
//		}
//
//		for (int i = 0; i < bin.length(); i++) {
//			if (bin.charAt(i) == '1') {
//				Color c = colors.get(i);
//				int val = 0;
//				Text t = null;
//				if (reqBlocks.contains(i)) {
//					val = (int) (Math.random() * (lengthSnake - 1)) + 1;
//					t = new Text(Integer.toString(val));
//				} else {
//					val = (int) (Math.random() * 49) + 1;
//					t = new Text(Integer.toString(val));
//				}
//				Block block = new Block(xCoord[i], distance, 100, 100, c, val, t);
//				blockList.add(block);
//				root.getChildren().add(block.getStack());
//			}
//		}
//		if (!blockList.isEmpty()) {
//			BlockOnScreen.add(blockList);
//		}
//	}
//
//	private boolean checkBlockPosition(int x, int y) {
//		if (MagnetOnScreen != null) {
//			StackPane magnet = MagnetOnScreen.getStack();
//			int magnetPosX = (int) magnet.getLayoutX();
//			int magnetPosY = (int) (magnet.getLayoutY() + magnet.getTranslateY());
//			if (x >= magnetPosX - 120 && x <= magnetPosX + 20 && y >= magnetPosY - 120 && y <= magnetPosY + 20) {
//				return false;
//			}
//		}
//
//		if (DestroyBlockOnScreen != null) {
//			StackPane DB = DestroyBlockOnScreen.getStack();
//			int DBPosX = (int) DB.getLayoutX();
//			int DBPosY = (int) (DB.getLayoutY() + DB.getTranslateY());
//			if (x >= DBPosX - 120 && x <= DBPosX + 20 && y >= DBPosY - 120 && y <= DBPosY + 20) {
//				return false;
//			}
//		}
//
//		if (ShieldOnScreen != null) {
//			StackPane shield = ShieldOnScreen.getStack();
//			int shieldPosX = (int) shield.getLayoutX();
//			int shieldPosY = (int) (shield.getLayoutY() + shield.getTranslateY());
//			if (x >= shieldPosX - 120 && x <= shieldPosX + 20 && y >= shieldPosY - 120 && y <= shieldPosY + 20) {
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	private void checkCoinScroll() {
//		if (CoinsOnScreen.size() > 0) {
//			if (!CoinsOnScreen.get(CoinsOnScreen.size() - 1).isEmpty()) {
//				Coin ball1 = CoinsOnScreen.get(CoinsOnScreen.size() - 1).get(0);
//				if ((int) ball1.getStack().getTranslateY() > 280 - (int) ball1.getStack().getLayoutY()) {
//					setCoins(-50);
//				}
//			}
//
//			if (!CoinsOnScreen.get(0).isEmpty()) {
//				Coin ball2 = CoinsOnScreen.get(0).get(0);
//				if (ball2.getStack().getTranslateY() >= 1300) {
//					CoinsOnScreen.remove(0);
//				}
//			}
//		}
//	}
//
//	private void checkBallScroll() {
//		if (BallsOnScreen.size() > 0) {
//			if (!BallsOnScreen.get(BallsOnScreen.size() - 1).isEmpty()) {
//				Ball ball1 = BallsOnScreen.get(BallsOnScreen.size() - 1).get(0);
//				if ((int) ball1.getPane().getTranslateY() > 280 - (int) ball1.getPane().getLayoutY()) {
//					setBalls(-50);
//				}
//			}
//
//			if (!BallsOnScreen.get(0).isEmpty()) {
//				Ball ball2 = BallsOnScreen.get(0).get(0);
//				if (ball2.getPane().getTranslateY() >= 1300) {
//					BallsOnScreen.remove(0);
//				}
//			}
//		}
//	}
//
//	private void setCoins(int dis) {
//		ArrayList<Coin> coinList = new ArrayList<>();
//		// Random rand = new Random();
//		int c = (int) (Math.random() * 1 + 1);
//		for (int i = 0; i < c; i++) {
//			int x = (int) (Math.random() * 200 + 15);
//			int y = (int) (dis);
//			while (!checkCoinPosition(x, y, coinList)) {
//				x = (int) (Math.random() * 200 + 15);
//				y = y - 1;
//			}
//			// System.out.println(x + " " + y + " coin");
//			Coin s = new Coin(x, y);
//			coinList.add(s);
//			Main_Try.root.getChildren().add(s.getStack());
//		}
//		CoinsOnScreen.add(coinList);
//	}
//
//	private boolean checkCoinPosition(int x, int y, ArrayList<Coin> coinList) {
//
//		for (int j = 0; j < BallsOnScreen.size(); j++) {
//			for (int k = 0; k < BallsOnScreen.get(j).size(); k++) {
//				StackPane ball = BallsOnScreen.get(j).get(k).getPane();
//				int ballPosX = (int) ball.getLayoutX();
//				int ballPosY = (int) (ball.getLayoutY() + ball.getTranslateY());
//				if (x >= ballPosX - 40 && x <= ballPosX + 40 && y >= ballPosY - 40 && y <= ballPosY + 40) {
//					return false;
//				}
//			}
//		}
//
//		for (int j = 0; j < BlockOnScreen.size(); j++) {
//			for (int k = 0; k < BlockOnScreen.get(j).size(); k++) {
//				StackPane block = BlockOnScreen.get(j).get(k).getStack();
//				int blockPosX = (int) block.getLayoutX();
//				int blockPosY = (int) (block.getLayoutY() + block.getTranslateY());
//				if (x >= blockPosX - 20 && x <= blockPosX + 120 && y >= blockPosY - 20 && y <= blockPosY + 120) {
//					return false;
//				}
//			}
//		}
//		for (int j = 0; j < coinList.size(); j++) {
//			float coinPosX = coinList.get(j).getLOCATION_X();
//			float coinPosY = coinList.get(j).getLOCATION_Y();
//			if (x >= coinPosX - 40 && x <= coinPosX + 40 && y >= coinPosY - 40 && y <= coinPosY + 40) {
//				return false;
//			}
//		}
//		for (int j = 0; j < WallsOnScreen.size(); j++) {
//			for (int k = 0; k < WallsOnScreen.get(j).size(); k++) {
//				int wallPosX = (int) WallsOnScreen.get(j).get(k).getLocationX();
//				int wallPosY = (int) (WallsOnScreen.get(j).get(k).getLoactionY()
//						+ WallsOnScreen.get(j).get(k).getRect().getTranslateY());
//				int heightWall = (int) WallsOnScreen.get(j).get(k).getHeight();
//				if (x >= wallPosX - 40 && x <= wallPosX + 40 && y >= wallPosY - 40 && y <= wallPosY + heightWall + 40) {
//					return false;
//				}
//			}
//		}
//
//		if (MagnetOnScreen != null) {
//			StackPane magnet = MagnetOnScreen.getStack();
//			int magnetPosX = (int) magnet.getLayoutX();
//			int magnetPosY = (int) (magnet.getLayoutY() + magnet.getTranslateY());
//			if (x >= magnetPosX - 40 && x <= magnetPosX + 40 && y >= magnetPosY - 40 && y <= magnetPosY + 40) {
//				return false;
//			}
//		}
//
//		if (DestroyBlockOnScreen != null) {
//			StackPane DB = DestroyBlockOnScreen.getStack();
//			int DBPosX = (int) DB.getLayoutX();
//			int DBPosY = (int) (DB.getLayoutY() + DB.getTranslateY());
//			if (x >= DBPosX - 40 && x <= DBPosX + 40 && y >= DBPosY - 40 && y <= DBPosY + 40) {
//				return false;
//			}
//		}
//
//		if (ShieldOnScreen != null) {
//			StackPane shield = ShieldOnScreen.getStack();
//			int shieldPosX = (int) shield.getLayoutX();
//			int shieldPosY = (int) (shield.getLayoutY() + shield.getTranslateY());
//			if (x >= shieldPosX - 40 && x <= shieldPosX + 40 && y >= shieldPosY - 40 && y <= shieldPosY + 40) {
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	private void setBalls(int dis) {
//		ArrayList<Ball> ballList = new ArrayList<>();
//		Random rand = new Random();
//		int c = ((int) (Math.random() * 2) + 1);
//		for (int i = 0; i < c; i++) {
//			int x = (int) (Math.random() * 400 + 15);
//			int y = (int) (dis);
//			while (!checkBallPosition(x, y, ballList)) {
//				x = (int) (Math.random() * 400 + 15);
//				y = y - 1;
//			}
//			// System.out.println(x + " " + y + " coin");
//			Ball s = new Ball(x, y, 10, Color.YELLOW);
//			ballList.add(s);
//			Main_Try.root.getChildren().add(s.getPane());
//		}
//		BallsOnScreen.add(ballList);
//	}
//
//	private boolean checkBallPosition(int x, int y, ArrayList<Ball> ballList) {
//		for (int j = 0; j < BlockOnScreen.size(); j++) {
//			for (int k = 0; k < BlockOnScreen.get(j).size(); k++) {
//				StackPane block = BlockOnScreen.get(j).get(k).getStack();
//				int blockPosX = (int) block.getLayoutX();
//				int blockPosY = (int) (block.getLayoutY() + block.getTranslateY());
//				if (x >= blockPosX - 20 && x <= blockPosX + 120 && y >= blockPosY - 20 && y <= blockPosY + 120) {
//					return false;
//				}
//			}
//		}
//		for (int j = 0; j < ballList.size(); j++) {
//			float ballPosX = ballList.get(j).getLOCATION_X();
//			float ballPosY = ballList.get(j).getLOCATION_Y();
//			if (x >= ballPosX - 30 && x <= ballPosX + 30 && y >= ballPosY - 30 && y <= ballPosY + 30) {
//				return false;
//			}
//		}
//		for (int j = 0; j < WallsOnScreen.size(); j++) {
//			for (int k = 0; k < WallsOnScreen.get(j).size(); k++) {
//				int wallPosX = (int) WallsOnScreen.get(j).get(k).getLocationX();
//				int wallPosY = (int) (WallsOnScreen.get(j).get(k).getLoactionY()
//						+ WallsOnScreen.get(j).get(k).getRect().getTranslateY());
//				int heightWall = (int) WallsOnScreen.get(j).get(k).getHeight();
//				if (x >= wallPosX - 40 && x <= wallPosX + 40 && y >= wallPosY - 40 && y <= wallPosY + heightWall + 40) {
//					return false;
//				}
//			}
//		}
//
//		if (MagnetOnScreen != null) {
//			StackPane magnet = MagnetOnScreen.getStack();
//			int magnetPosX = (int) magnet.getLayoutX();
//			int magnetPosY = (int) (magnet.getLayoutY() + magnet.getTranslateY());
//			if (x >= magnetPosX - 40 && x <= magnetPosX + 40 && y >= magnetPosY - 40 && y <= magnetPosY + 40) {
//				return false;
//			}
//		}
//
//		if (DestroyBlockOnScreen != null) {
//			StackPane DB = DestroyBlockOnScreen.getStack();
//			int DBPosX = (int) DB.getLayoutX();
//			int DBPosY = (int) (DB.getLayoutY() + DB.getTranslateY());
//			if (x >= DBPosX - 40 && x <= DBPosX + 40 && y >= DBPosY - 40 && y <= DBPosY + 40) {
//				return false;
//			}
//		}
//
//		if (ShieldOnScreen != null) {
//			StackPane shield = ShieldOnScreen.getStack();
//			int shieldPosX = (int) shield.getLayoutX();
//			int shieldPosY = (int) (shield.getLayoutY() + shield.getTranslateY());
//			if (x >= shieldPosX - 40 && x <= shieldPosX + 40 && y >= shieldPosY - 40 && y <= shieldPosY + 40) {
//				return false;
//			}
//		}
//
//		return true;
//	}
//
//	private void checkWallScroll() {
//		if (!WallsOnScreen.isEmpty()) {
//			ArrayList<Wall> W1 = WallsOnScreen.get(WallsOnScreen.size() - 1);
//			float maxHeight = 0;
//			Wall reqWall = null;
//			for (Wall wall : W1) {
//				if (wall.getHeight() > maxHeight) {
//					maxHeight = wall.getHeight();
//					reqWall = wall;
//				}
//			}
//			if (reqWall != null) {
//				if ((int) (reqWall.getRect().getTranslateY() + reqWall.getRect().getY()) >= 150) {
//					setWalls();
//				}
//			}
//			W1 = WallsOnScreen.get(0);
//			maxHeight = 0;
//			reqWall = null;
//			for (Wall wall : W1) {
//				if (wall.getHeight() > maxHeight) {
//					maxHeight = wall.getHeight();
//					reqWall = wall;
//				}
//			}
//			if (reqWall != null) {
//				if ((int) reqWall.getRect().getTranslateY() >= 1000 + maxHeight - reqWall.getRect().getY()) {
//					WallsOnScreen.remove(0);
//				}
//			}
//		}
//	}
//
//	private void setWalls() {
//		int[] xCoord = { 100, 200, 300, 400 };
//		ArrayList<Wall> W = new ArrayList<>();
//		int c = (int) (Math.random() * 15) + 1;
//		String bin = Integer.toBinaryString(c);
//		while (bin.length() < 4) {
//			bin = "0".concat(bin);
//		}
//		for (int i = 0; i < bin.length(); i++) {
//			int h = (int) (Math.random() * 750 + 150);
//			int y = -h;
//			if (bin.charAt(i) == '1') {
//				Wall wall = new Wall(xCoord[i], y, h);
//				W.add(wall);
//				Main_Try.root.getChildren().add(wall.getRect());
//			}
//		}
//		if (!W.isEmpty()) {
//			WallsOnScreen.add(W);
//		}
//	}
//
//	public void checkTokenScroll() {
//		// TODO Auto-generated method stub
//		if (MagnetOnScreen != null) {
//			StackPane magnet = MagnetOnScreen.getStack();
//			if (magnet.getTranslateY() >= 1400) {
//				MagnetOnScreen = null;
//			}
//		}
//
//		if (ShieldOnScreen != null) {
//			StackPane shield = ShieldOnScreen.getStack();
//			if (shield.getTranslateY() >= 1400) {
//				ShieldOnScreen = null;
//			}
//		}
//
//		if (DestroyBlockOnScreen != null) {
//			StackPane destroyBlock = DestroyBlockOnScreen.getStack();
//			if (destroyBlock.getTranslateY() >= 1400) {
//				DestroyBlockOnScreen = null;
//			}
//		}
//
//		if (MagnetOnScreen == null && ShieldOnScreen == null && DestroyBlockOnScreen == null) {
//			int c = (int) (Math.random() * 3);
//			if (c == 0) {
//				setMagnet(-1000);
//			}
//			if (c == 1) {
//				setDB(-1000);
//				// setMagnet(-1000);
//			}
//			if (c == 2) {
//				setShield(-1000);
//				// setMagnet(-1000);
//			}
//		}
//	}
//
//	public void setMagnet(int distance) {
//		// TODO Auto-generated method stub
//		int x = (int) (Math.random() * 400 + 7);
//		int y = (int) (distance);
//		Magnet M = new Magnet(x, y);
//		root.getChildren().addAll(M.getStack());
//		MagnetOnScreen = M;
//	}
//
//	public void setShield(int distance) {
//		// TODO Auto-generated method stub
//		int x = (int) (Math.random() * 400 + 7);
//		int y = (int) (distance);
//		Shield S = new Shield(x, y);
//		root.getChildren().addAll(S.getStack());
//		ShieldOnScreen = S;
//	}
//
//	public void setDB(int distance) {
//		// TODO Auto-generated method stub
//		int x = (int) (Math.random() * 400 + 7);
//		int y = (int) (distance);
//		DestroyBlock DB = new DestroyBlock(x, y);
//		root.getChildren().addAll(DB.getStack());
//		DestroyBlockOnScreen = DB;
//	}
//
//	public void snakeIntersectCoin() {
//		Circle snakeHead = Snake.getSnakeLength().get(0);
//		for (int i = 0; i < CoinsOnScreen.size(); i++) {
//			for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
//				Coin coin = CoinsOnScreen.get(i).get(j);
//				if (snakeHead.intersects(coin.getStack().getBoundsInParent()) && !coin.isEaten()) {
//					coin.getStack().setVisible(false);
//					coin.setEaten(true);
//					player.stop();
//					player.play();
//					updateScore(1);
//				}
//			}
//		}
//
//	}
//
//	public void snakeIntersectBlock() {
//		// TODO Auto-generated method stub
//		Circle snakeHead = Snake.getSnakeLength().get(0);
//		for (int i = 0; i < BlockOnScreen.size(); i++) {
//			for (Block block : BlockOnScreen.get(i)) {
//				// System.out.println();
//				if (snakeHead.intersects(block.getStack().getBoundsInParent()) && block.isEaten() == false) {
//					if (snakeHead.getCenterX() > block.getStack().getLayoutX() - 10
//							&& snakeHead.getCenterX() < block.getStack().getLayoutX() + 110) {
//						if (!shield) {
//							System.out.println(block.getValue());
//							hitBlock = block;
//							BLOCK_HIT = true;
//							try {
//								snake.eatBlock(block, this);
//							} catch (Exception e) {
//
//							}
//						} else {
//							block.getStack().setVisible(false);
//							block.setEaten(true);
//							updateScore(block.getValue());
//						}
//						if (block.isEaten()) {
//							Bounds blockBounds = block.getStack().getBoundsInParent();
//							PlayBurst(blockBounds);
//						}
//					}
//				}
//			}
//		}
//
//	}
//
//	public void snakeIntersectBall() {
//		// TODO Auto-generated method stub
//		Circle snakeHead = Snake.getSnakeLength().get(0);
//		for (int i = 0; i < BallsOnScreen.size(); i++) {
//			for (Ball ball : BallsOnScreen.get(i)) {
//				if (snakeHead.intersects(ball.getPane().getBoundsInParent()) && !ball.isEaten()) {
//					ball.setEaten(true);
//					ball.getPane().setVisible(false);
//					int inc = ball.getValue();
//					int L = Snake.getSnakeLength().size();
//					snake.increaseLength(inc, this);
//					for (int j = 0; j < inc; j++) {
//						root.getChildren().add(Snake.getSnakeLength().get(L + j));
//					}
//				}
//			}
//		}
//	}
//
//	public void snakeIntersectsDB() {
//		Circle snakeHead = Snake.getSnakeLength().get(0);
//		if (DestroyBlockOnScreen != null && snakeHead.intersects(DestroyBlockOnScreen.getStack().getBoundsInParent())) {
//			for (int i = 0; i < BlockOnScreen.size(); i++) {
//				for (Block block : BlockOnScreen.get(i)) {
//					int y = (int) (block.getStack().getTranslateY() + block.getStack().getLayoutY());
//					if (y > 0 && y < 1000 && !block.isEaten()) {
//						block.getStack().setVisible(false);
//						block.setEaten(true);
//						updateScore(block.getValue());
//						PlayBurst(block.getStack().getBoundsInParent());
//					}
//				}
//			}
//			DestroyBlockOnScreen.getStack().setVisible(false);
//			PlayBurst(DestroyBlockOnScreen.getStack().getBoundsInParent());
//		}
//	}
//
//	public void snakeIntersectsShield() {
//		Circle snakeHead = Snake.getSnakeLength().get(0);
//		if (ShieldOnScreen != null && snakeHead.intersects(ShieldOnScreen.getStack().getBoundsInParent())) {
//			shield = true;
//			ShieldOnScreen.getStack().setVisible(false);
//			Timer timer = new Timer();
//			TimerTask task = new TimerTask() {
//				public void run() {
//					// The task you want to do
//					shield = false;
//					snake.originalColors();
//					PlayBurst(ShieldOnScreen.getStack().getBoundsInParent());
//				}
//
//			};
//			timer.schedule(task, 5000l);
//		}
//	}
//
//	public void snakeIntersectsMagnet() {
//		Circle snakeHead = Snake.getSnakeLength().get(0);
//		if (MagnetOnScreen != null && snakeHead.intersects(MagnetOnScreen.getStack().getBoundsInParent())) {
//			int inc = 0;
//			System.out.println("Magnet Active ");
//			MagnetOnScreen.getStack().setVisible(false);
//			for (int i = 0; i < CoinsOnScreen.size(); i++) {
//				for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
//					Coin temp = CoinsOnScreen.get(i).get(j);
//					float checkx = temp.getLOCATION_X();
//					float checky = temp.getLOCATION_Y();
//
//					System.out.println("Coin on screen " + checkx + " " + checky);
//
//					if (checkx > 50 && checkx < 300 && checky > -100 && checky < 200) {
//						System.out.println("Coins in region  " + i + " " + j + " " + checkx + " " + checky);
//						inc += 1;
//						// temp.setEaten(true);
//						temp.getStack().setVisible(false);
//
//						CoinsOnScreen.remove(CoinsOnScreen.get(i).get(j));
//
//					}
//
//				}
//			}
//
//			updateScore(inc);
//
//		}
//
//	}
//
//	public static Group getRoot() {
//		return root;
//	}
//
//	public void setBlockHit(boolean b) {
//		BLOCK_HIT = b;
//	}
//
//	public Block getHitBlock() {
//		return hitBlock;
//	}
//
//	public void setHitBlock(Block b) {
//		hitBlock = b;
//	}
//
//	public void setVelocity(double inc) {
//		velocity += inc;
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//}
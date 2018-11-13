package application;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Main_Try extends Application {

	private final ArrayList<ArrayList<Block>> BlockOnScreen = new ArrayList<>();
	private final ArrayList<ArrayList<Ball>> BallsOnScreen = new ArrayList<>();
	private final ArrayList<ArrayList<Wall>> WallsOnScreen = new ArrayList<>();
	private final ArrayList<ArrayList<Coin>> CoinsOnScreen = new ArrayList<>();
	private Shield ShieldOnScreen;
	private Magnet MagnetOnScreen;
	private DestroyBlock DestroyBlockOnScreen;
	private final Snake snake = new Snake(250, 500, 10, Color.YELLOW);
	private int score = 0;
	private double velocity = 7;
	private Text scoreLabel;
	ChoiceBox<String> dropDownMenu;
	private boolean BLOCK_HIT = false;
	private Block hitBlock;
	private boolean shield = false;
	Media music = new Media(new File("mariotrim.wav").toURI().toString());
	MediaPlayer player = new MediaPlayer(music);
	private final static Group root = new Group();
	Stage stage;
	private Scene scene = new Scene(root, 500, 1000);

	public int getScore() {
		return score;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		primaryStage.setTitle("Snake vs Block");
		primaryStage.setScene(scene);
		setGame();
		primaryStage.show();
//		System.out.println(scene.getFill().toString());
		scene.setOnKeyPressed(e -> {
			// System.out.println(snake.getSnakeLength().get(0).getCenterX());
			switch (e.getCode()) {
			case LEFT: {
				snake.moveL();
				if (isWall() || isBlock()
						|| (!snake.getSnakeLength().isEmpty() && snake.getSnakeLength().get(0).getCenterX() < 20)) {
					snake.moveR();
				}
			}
				break;
			case RIGHT: {
				snake.moveR();
				if (isWall() || isBlock()
						|| (!snake.getSnakeLength().isEmpty() && snake.getSnakeLength().get(0).getCenterX() > 480)) {
					snake.moveL();
				}
			}
				break;
			}

		});

		// BlockAnimation();
		// BallAnimation();
		TokenAnimation();
		// WallAnimation();
		SnakeAnimation(stage);
		CoinAnimation();
	}

	public void setGame() {
		for (int i = 0; i < Snake.getSnakeLength().size(); i++) {
			root.getChildren().add(Snake.getSnakeLength().get(i));
		}
		setDropDownBox();
		setCoins(-100);
		setScore();
	}

	private void setScore() {
		scoreLabel = new Text("Score : " + score);
		scoreLabel.setX(400);
		scoreLabel.setY(50);
		scoreLabel.setFont(Font.font(15));
		scoreLabel.setFill(Color.WHITE);
		root.getChildren().addAll(scoreLabel);
		scoreLabel.toFront();
	}

	public void updateScore(int inc) {
		this.score += inc;
		scoreLabel.setText("Score : " + score);
		scoreLabel.toFront();
	}

	private void setDropDownBox() {
		dropDownMenu = new ChoiceBox<>();
		dropDownMenu.getItems().addAll("Restart", "Exit Game");
		dropDownMenu.setLayoutX(400);
		dropDownMenu.setLayoutY(3);
		dropDownMenu.setValue("Exit Game");
		dropDownMenu.setBackground(Background.EMPTY);
		String style = "-fx-background-color: rgba(255,255,0);";
		dropDownMenu.setStyle(style);
		root.getChildren().addAll(dropDownMenu);
		dropDownMenu.toFront();
	}

	public boolean isWall() {
		if (!snake.getSnakeLength().isEmpty()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			for (int i = 0; i < WallsOnScreen.size(); i++) {
				for (Wall wall : WallsOnScreen.get(i)) {
					if (snakeHead.intersects(wall.getRect().getBoundsInParent())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isBlock() {
		if (!snake.getSnakeLength().isEmpty()) {
			Circle snakeHead = Snake.getSnakeLength().get(0);
			for (int i = 0; i < BlockOnScreen.size(); i++) {
				for (Block block : BlockOnScreen.get(i)) {
					if (snakeHead.intersects(block.getStack().getBoundsInParent()) && !block.isEaten()) {
						// System.out.println(block.getStack().getLayoutY() +
						// block.getStack().getTranslateY());
						if (block.getStack().getLayoutY() + block.getStack().getTranslateY() > 456) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void CoinAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new CoinHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private class CoinHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {
			if (!BLOCK_HIT) {
				for (int i = 0; i < CoinsOnScreen.size(); i++) {
					for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
						StackPane St = CoinsOnScreen.get(i).get(j).getStack();
						St.setTranslateY(St.getTranslateY() + velocity);
					}
				}
				checkCoinScroll();
			}

		}
	}

	public void TokenAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new TokenHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

	}

	private class TokenHandler implements EventHandler<ActionEvent> {
		public void handle(ActionEvent event) {
			if (!BLOCK_HIT) {
				if (MagnetOnScreen != null) {
					StackPane magnet = MagnetOnScreen.getStack();
					magnet.setTranslateY(magnet.getTranslateY() + velocity);
				}
				if (ShieldOnScreen != null) {
					StackPane shield = ShieldOnScreen.getStack();
					shield.setTranslateY(shield.getTranslateY() + velocity);
				}
				if (DestroyBlockOnScreen != null) {
					StackPane DB = DestroyBlockOnScreen.getStack();
					DB.setTranslateY(DB.getTranslateY() + velocity);
				}
				checkTokenScroll();
			}

		}

	}

	public void SnakeAnimation(Stage stage) {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new SnakeHandler(stage));
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private class SnakeHandler implements EventHandler<ActionEvent> {

		Stage s;

		public SnakeHandler(Stage s) {
			this.s = s;
		}

		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			if (!BLOCK_HIT) {
				snakeIntersectsMagnet();
				snakeIntersectCoin();
			}
			if (shield) {
				snake.changeColors();
			}
		}
	}

	public void CoinAttractionAnimation() {
		KeyFrame kf = new KeyFrame(Duration.seconds(1), new CoinAttractionHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(5);
		timeline.play();
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				System.out.println("stop");
			}
		});
	}

	private class CoinAttractionHandler implements EventHandler<ActionEvent> {

		public void handle(ActionEvent event) {
			int inc = 0;
			for (int i = 0; i < CoinsOnScreen.size(); i++) {
				for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
					Coin temp = CoinsOnScreen.get(i).get(j);
					double checkx = temp.getLOCATION_X();
					double checky = temp.getLOCATION_Y() + temp.getStack().getTranslateY();
					Circle snakeHead = snake.getSnakeLength().get(0);

					if (!temp.isEaten() && Math.abs(checkx - snakeHead.getCenterX()) < 150 && checky > 300
							&& checky < 700) {
						temp.setEaten(true);
						System.out.println("Hey Coin on screen " + snakeHead.getCenterX());
						Path path = new Path();
						path.getElements().add(new MoveTo(checkx, checky));
						path.setOpacity(0);
						path.getElements().add(new LineTo(snakeHead.getCenterX(), 500));
						PathTransition move = new PathTransition();
						move.setDuration(Duration.seconds(0.5));
						move.setPath(path);
						move.setNode(temp.getStack());
						move.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
						move.setCycleCount(1);
						move.setAutoReverse(true);
						root.getChildren().add(path);
						move.play();
						move.setOnFinished(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								root.getChildren().remove(path);
								root.getChildren().remove(temp.getStack());
							}
						});
						CoinsOnScreen.get(i).remove(j);
						inc += 1;
					}
				}
			}
			updateScore(inc);
		}

	}

	private void checkCoinScroll() {
		if (CoinsOnScreen.size() > 0) {
			if (!CoinsOnScreen.get(CoinsOnScreen.size() - 1).isEmpty()) {
				Coin coin1 = CoinsOnScreen.get(CoinsOnScreen.size() - 1).get(0);
				if ((int) coin1.getStack().getTranslateY() > 280 - (int) coin1.getStack().getLayoutY()) {
					setCoins(-50);
				}
			}

			if (!CoinsOnScreen.get(0).isEmpty()) {
				Coin coin2 = CoinsOnScreen.get(0).get(0);
				if (coin2.getStack().getTranslateY() >= 1300) {
					CoinsOnScreen.remove(0);
				}
			}
		}
	}

	private void setCoins(int dis) {
		ArrayList<Coin> coinList = new ArrayList<>();
		// Random rand = new Random();
		int c = (int) (Math.random() * 1 + 1);
		for (int i = 0; i < c; i++) {
			int x = (int) (Math.random() * 200 + 15);
			int y = (int) (dis);
			while (!checkCoinPosition(x, y, coinList)) {
				x = (int) (Math.random() * 200 + 15);
				y = y - 1;
			}
			// System.out.println(x + " " + y + " coin");
			Coin s = new Coin(x, y);
			coinList.add(s);
			// System.out.println(s.getIMAGE_SRC());
			root.getChildren().add(s.getStack());
		}
		CoinsOnScreen.add(coinList);
	}

	private boolean checkCoinPosition(int x, int y, ArrayList<Coin> coinList) {

		for (int j = 0; j < BallsOnScreen.size(); j++) {
			for (int k = 0; k < BallsOnScreen.get(j).size(); k++) {
				StackPane ball = BallsOnScreen.get(j).get(k).getPane();
				int ballPosX = (int) ball.getLayoutX();
				int ballPosY = (int) (ball.getLayoutY() + ball.getTranslateY());
				if (x >= ballPosX - 40 && x <= ballPosX + 40 && y >= ballPosY - 40 && y <= ballPosY + 40) {
					return false;
				}
			}
		}

		for (int j = 0; j < BlockOnScreen.size(); j++) {
			for (int k = 0; k < BlockOnScreen.get(j).size(); k++) {
				StackPane block = BlockOnScreen.get(j).get(k).getStack();
				int blockPosX = (int) block.getLayoutX();
				int blockPosY = (int) (block.getLayoutY() + block.getTranslateY());
				if (x >= blockPosX - 20 && x <= blockPosX + 120 && y >= blockPosY - 20 && y <= blockPosY + 120) {
					return false;
				}
			}
		}
		for (int j = 0; j < coinList.size(); j++) {
			float coinPosX = coinList.get(j).getLOCATION_X();
			float coinPosY = coinList.get(j).getLOCATION_Y();
			if (x >= coinPosX - 40 && x <= coinPosX + 40 && y >= coinPosY - 40 && y <= coinPosY + 40) {
				return false;
			}
		}
		for (int j = 0; j < WallsOnScreen.size(); j++) {
			for (int k = 0; k < WallsOnScreen.get(j).size(); k++) {
				int wallPosX = (int) WallsOnScreen.get(j).get(k).getLocationX();
				int wallPosY = (int) (WallsOnScreen.get(j).get(k).getLoactionY()
						+ WallsOnScreen.get(j).get(k).getRect().getTranslateY());
				int heightWall = (int) WallsOnScreen.get(j).get(k).getHeight();
				if (x >= wallPosX - 40 && x <= wallPosX + 40 && y >= wallPosY - 40 && y <= wallPosY + heightWall + 40) {
					return false;
				}
			}
		}

		if (MagnetOnScreen != null) {
			StackPane magnet = MagnetOnScreen.getStack();
			int magnetPosX = (int) magnet.getLayoutX();
			int magnetPosY = (int) (magnet.getLayoutY() + magnet.getTranslateY());
			if (x >= magnetPosX - 40 && x <= magnetPosX + 40 && y >= magnetPosY - 40 && y <= magnetPosY + 40) {
				return false;
			}
		}

		if (DestroyBlockOnScreen != null) {
			StackPane DB = DestroyBlockOnScreen.getStack();
			int DBPosX = (int) DB.getLayoutX();
			int DBPosY = (int) (DB.getLayoutY() + DB.getTranslateY());
			if (x >= DBPosX - 40 && x <= DBPosX + 40 && y >= DBPosY - 40 && y <= DBPosY + 40) {
				return false;
			}
		}

		if (ShieldOnScreen != null) {
			StackPane shield = ShieldOnScreen.getStack();
			int shieldPosX = (int) shield.getLayoutX();
			int shieldPosY = (int) (shield.getLayoutY() + shield.getTranslateY());
			if (x >= shieldPosX - 40 && x <= shieldPosX + 40 && y >= shieldPosY - 40 && y <= shieldPosY + 40) {
				return false;
			}
		}

		return true;
	}

	public void checkTokenScroll() {
		// TODO Auto-generated method stub
		if (MagnetOnScreen != null) {
			StackPane magnet = MagnetOnScreen.getStack();
			if (magnet.getTranslateY() >= 1400) {
				MagnetOnScreen = null;
			}
		}

		if (ShieldOnScreen != null) {
			StackPane shield = ShieldOnScreen.getStack();
			if (shield.getTranslateY() >= 1400) {
				ShieldOnScreen = null;
			}
		}

		if (DestroyBlockOnScreen != null) {
			StackPane destroyBlock = DestroyBlockOnScreen.getStack();
			if (destroyBlock.getTranslateY() >= 1400) {
				DestroyBlockOnScreen = null;
			}
		}

		if (MagnetOnScreen == null && ShieldOnScreen == null && DestroyBlockOnScreen == null) {
			int c = 0;
			if (c == 0) {
				setMagnet(-1000);
			}
			if (c == 1) {
				setDB(-1000);
				// setMagnet(-1000);
			}
			if (c == 2) {
				setShield(-1000);
				// setMagnet(-1000);
			}
		}
	}

	public void setMagnet(int distance) {
		// TODO Auto-generated method stub
		int x = (int) (Math.random() * 400 + 7);
		int y = (int) (distance);
		Magnet M = new Magnet(x, y);
		root.getChildren().addAll(M.getStack());
		MagnetOnScreen = M;
	}

	public void setShield(int distance) {
		// TODO Auto-generated method stub
		int x = (int) (Math.random() * 400 + 7);
		int y = (int) (distance);
		Shield S = new Shield(x, y);
		root.getChildren().addAll(S.getStack());
		ShieldOnScreen = S;
	}

	public void setDB(int distance) {
		// TODO Auto-generated method stub
		int x = (int) (Math.random() * 400 + 7);
		int y = (int) (distance);
		DestroyBlock DB = new DestroyBlock(x, y);
		root.getChildren().addAll(DB.getStack());
		DestroyBlockOnScreen = DB;
	}

	public void snakeIntersectCoin() {
		Circle snakeHead = Snake.getSnakeLength().get(0);
		for (int i = 0; i < CoinsOnScreen.size(); i++) {
			for (int j = 0; j < CoinsOnScreen.get(i).size(); j++) {
				Coin coin = CoinsOnScreen.get(i).get(j);
				if (snakeHead.intersects(coin.getStack().getBoundsInParent()) && !coin.isEaten()) {
					coin.getStack().setVisible(false);
					coin.setEaten(true);
					player.stop();
					player.play();
					updateScore(1);
				}
			}
		}

	}

	public void snakeIntersectsMagnet() {
		Circle snakeHead = Snake.getSnakeLength().get(0);
		if (MagnetOnScreen != null && snakeHead.intersects(MagnetOnScreen.getStack().getBoundsInParent())) {
			// playerTokensBalls.stop();
			// playerTokensBalls.play();
			int inc = 0;
			// System.out.println("Magnet Active ");
			MagnetOnScreen.getStack().setVisible(false);
			CoinAttractionAnimation();

		}
	}

	public static Group getRoot() {
		return root;
	}

	public void setBlockHit(boolean b) {
		BLOCK_HIT = b;
	}

	public Block getHitBlock() {
		return hitBlock;
	}

	public void setHitBlock(Block b) {
		hitBlock = b;
	}

	public void setVelocity(double inc) {
		velocity += inc;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
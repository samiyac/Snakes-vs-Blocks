package application;


import java.io.IOException;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class GameState.
 */
public class GameState {

	/** The main. */
	private Main main;

	/**
	 * Instantiates a new game state.
	 *
	 * @param main the main
	 */
	public GameState(Main main) {
		this.main = main;
	}

	/**
	 * Resume game.
	 *
	 * @param main the main
	 */
	public void ResumeGame(Main main) {
		main.setRestart(false);
		main.getSnake().getSnakeLength().clear();
		main.playgame(main.getStage());
		try {
			main.getDeserializer().loadOldGame();
		} catch (ClassNotFoundException | IOException e) {
		}
	}

	/**
	 * Sets the new game.
	 */
	public void setNewGame() {
		if (main.getSnake() != null) {
			main.getSnake().getSnakeLength().clear();
		}
		main.playgame(main.getStage());
		main.setVelocity(6);
		main.setBlockOnScreen(new ArrayList<>());
		main.setBallsOnScreen(new ArrayList<>());
		main.setWallsOnScreen(new ArrayList<>());
		main.setCoinsOnScreen(new ArrayList<>());
		main.setLabels(main.getLabels());
		main.setSnake(new Snake(250, 500, 10, main.getColorBall()));
		main.setScore(0);
		main.setBLOCK_HIT(false);
		main.setShield(false);
		Snake snake = main.getSnake();
		for (int i = 0; i < snake.getSnakeLength().size(); i++) {
			main.getRoot().getChildren().add(snake.getSnakeLength().get(i));
		}
		main.getDropDown().setDropDownBox();
		main.getWallFactory().setWalls();
		main.getBlockFactory().setBlocks(-100);
		main.getBallFactory().setBalls(-100);
		main.getCoinFactory().setCoins(-50);
		main.getLabels().setScore();
		main.getLabels().setLengthLabel();
		main.getLabels().setCoinLabel();
	}

	/**
	 * Endgame.
	 */
	public void endgame() {
		EndGame e = new EndGame(main.getStage(), main.getScore());
		main.setTempscore(main.getScore());
		main.setEnd(true);
		main.setRestart(false);
		try {
			e.loadEndScreen();
			exitGame();
		} catch (Exception E) {
		}
	}

	/**
	 * Exit game.
	 */
	public void exitGame() {
		main.getBallsOnScreen().clear();
		main.getBlockOnScreen().clear();
		main.getCoinsOnScreen().clear();
		main.getWallsOnScreen().clear();
		main.setMagnetOnScreen(null);
		main.setShieldOnScreen(null);
		main.setDestroyBlockOnScreen(null);
	}
}

package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.scene.paint.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class Deserializer.
 */
public class Deserializer {

	/** The main. */
	private Main main;

	/**
	 * Instantiates a new deserializer.
	 *
	 * @param main the main
	 */
	public Deserializer(Main main) {
		this.main = main;
	}

	/**
	 * Load old game.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void loadOldGame() throws IOException, ClassNotFoundException {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("SnakeVsBlock"));
			ArrayList<ArrayList<SerializableBlock>> serializableBlocks = (ArrayList<ArrayList<SerializableBlock>>) is
					.readObject();
			ArrayList<ArrayList<SerializableBall>> serializableBalls = (ArrayList<ArrayList<SerializableBall>>) is
					.readObject();
			ArrayList<ArrayList<SerializableWall>> serializableWalls = (ArrayList<ArrayList<SerializableWall>>) is
					.readObject();
			ArrayList<ArrayList<SerializableCoin>> serializableCoins = (ArrayList<ArrayList<SerializableCoin>>) is
					.readObject();
			SerializableShield serializableShield = (SerializableShield) is.readObject();
			SerializableMagnet serializableMagnet = (SerializableMagnet) is.readObject();
			SerializableDB serializableDB = (SerializableDB) is.readObject();
			SerializableSnake serializableSnake = (SerializableSnake) is.readObject();
			main.setScore((Integer) is.readObject());
			main.setVelocity((Double) is.readObject());
			main.setBLOCK_HIT((Boolean) is.readObject());
			main.setShield((Boolean) is.readObject());
			main.setMode((Integer) is.readObject());
			main.setColorBall(Color.web((String) is.readObject()));
			is.close();

			SerializableClasses SC = new SerializableClasses();

			main.setBlockOnScreen(SC.constructBlockList(serializableBlocks, main));
			main.setBallsOnScreen(SC.constructBallList(serializableBalls, main));
			main.setWallsOnScreen(SC.constructWallList(serializableWalls, main));
			main.setCoinsOnScreen(SC.constructCoinList(serializableCoins, main));

			if (serializableShield.getLOCATION_X() != -2000 && serializableShield.getLOCATION_Y() != -2000) {
				main.setShieldOnScreen(serializableShield.constructShield(main));
			}
			if (serializableMagnet.getLOCATION_X() != -2000 && serializableMagnet.getLOCATION_Y() != -2000) {
				main.setMagnetOnScreen(serializableMagnet.constructMagnet(main));
			}

			if (serializableDB.getLOCATION_X() != -2000 && serializableDB.getLOCATION_Y() != -2000) {
				main.setDestroyBlockOnScreen(serializableDB.constructDB(main));
			}

			main.setSnake(serializableSnake.constructSnake(main));
			for (int i = 0; i < main.getSnake().getSnakeLength().size(); i++) {
				main.getRoot().getChildren().add(main.getSnake().getSnakeLength().get(i));
			}
			main.getDropDown().setDropDownBox();
			main.getLabels().setScore();
			main.getLabels().setLengthLabel();
			main.getLengthLabel().setX(main.getSnake().getSnakeLength().get(0).getCenterX());
			main.getLabels().setCoinLabel();
		} catch (FileNotFoundException e1) {
			System.err.println("No old game");
			main.getGameState().setNewGame();
		}
	}

	/**
	 * Deserialize.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void deserialize() throws IOException, ClassNotFoundException {
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("bonusCoin"));
			main.setBonusCoin((Integer) is.readObject());
			is.close();
		} catch (FileNotFoundException e1) {
			main.setBonusCoin(0);
		}
	}

}

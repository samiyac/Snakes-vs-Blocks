package application;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Serializer.
 */
public class Serializer {

	/** The main. */
	private Main main;

	/**
	 * Instantiates a new serializer.
	 *
	 * @param main the main
	 */
	public Serializer(Main main) {
		this.main = main;
	}

	/**
	 * Save game state.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveGameState() throws FileNotFoundException, IOException {
		SerializableClasses SC = new SerializableClasses();
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("SnakeVsBlock"));
		os.writeObject(SC.constructSerializableBlockList(main.getBlockOnScreen()));
		os.writeObject(SC.constructSerializableBallList(main.getBallsOnScreen()));
		os.writeObject(SC.constructSerializableWallList(main.getWallsOnScreen()));
		os.writeObject(SC.constructSerializableCoinList(main.getCoinsOnScreen()));
		if (main.getShieldOnScreen() != null) {
			float X = main.getShieldOnScreen().getLOCATION_X();
			float Y = main.getShieldOnScreen().getLOCATION_Y();
			double translateY = main.getShieldOnScreen().getStack().getTranslateY();
			String img = main.getShieldOnScreen().getIMAGE_SRC();
			os.writeObject(new SerializableShield(X, Y, translateY, main.getMode(), img));
		} else {
			os.writeObject(new SerializableShield(-2000, -2000, -2000, 0, ""));
		}
		if (main.getMagnetOnScreen() != null) {
			float X = main.getMagnetOnScreen().getLOCATION_X();
			float Y = main.getMagnetOnScreen().getLOCATION_Y();
			double translateY = main.getMagnetOnScreen().getStack().getTranslateY();
			String img = main.getMagnetOnScreen().getIMAGE_SRC();
			os.writeObject(new SerializableMagnet(X, Y, translateY, main.getMode(), img));
		} else {
			os.writeObject(new SerializableMagnet(-2000, -2000, -2000, 0, ""));
		}
		if (main.getDestroyBlockOnScreen() != null) {
			float X = main.getDestroyBlockOnScreen().getLOCATION_X();
			float Y = main.getDestroyBlockOnScreen().getLOCATION_Y();
			double translateY = main.getDestroyBlockOnScreen().getStack().getTranslateY();
			String img = main.getDestroyBlockOnScreen().getIMAGE_SRC();
			os.writeObject(new SerializableDB(X, Y, translateY, main.getMode(), img));
		} else {
			os.writeObject(new SerializableDB(-2000, -2000, -2000, 0, ""));
		}

		os.writeObject(new SerializableSnake(main.getSnake()));
		os.writeObject(main.getScore());
		os.writeObject(main.getVelocity());
		os.writeObject(main.isBLOCK_HIT());
		os.writeObject(main.isShield());
		os.writeObject(main.getMode());
		os.writeObject(main.getColorBall().toString());
		os.close();
	}
	
	/**
	 * Serialize.
	 *
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void serialize() throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("bonusCoin"));
		os.writeObject(main.getBonusCoin());
		os.close();
	}
}

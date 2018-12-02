package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Block objects.
 */
public class BlockFactory {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new block factory.
	 *
	 * @param main the main
	 */
	public BlockFactory(Main main) {
		this.main = main;
	}

	/**
	 * Block animation.
	 */
	public void BlockAnimation() {
		KeyFrame kf = new KeyFrame(Duration.millis(50), new BlockHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	/**
	 * The Class BlockHandler.
	 */
	private class BlockHandler implements EventHandler<ActionEvent> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
		public void handle(ActionEvent event) {
			if (!main.isBLOCK_HIT() && !main.isEnd()) {
				ArrayList<ArrayList<Block>> BlockOnScreen = main.getBlockOnScreen();
				for (int i = 0; i < BlockOnScreen.size(); i++) {
					{
						for (int j = 0; j < BlockOnScreen.get(i).size(); j++) {
							StackPane St = BlockOnScreen.get(i).get(j).getStack();
							St.setTranslateY(St.getTranslateY() + main.getVelocity());
						}
					}
				}
				main.getCoinLabel().toFront();
				main.getScoreLabel().toFront();
				checkBlockScroll();
				main.getDropDownMenu().toFront();
			}

		}
	}

	/**
	 * Check block scroll.
	 */
	void checkBlockScroll() {
		int[] distance = { -100, -200 };
		int c = (int) (Math.random() * 2);
		if (main.getBlockOnScreen().size() > 0) {
			StackPane block = main.getBlockOnScreen().get(main.getBlockOnScreen().size() - 1).get(0).getStack();
			if (block.getTranslateY() + block.getLayoutY() > 100) {
				setBlocks(distance[c]);
			}
			block = main.getBlockOnScreen().get(0).get(0).getStack();
			if (block.getTranslateY() >= 1100 - block.getLayoutY()) {
				main.getBlockOnScreen().remove(0);
			}
		}
		if (main.getBlockOnScreen().size() == 0) {
			setBlocks(distance[c]);
		}
	}

	/**
	 * Sets the blocks.
	 *
	 * @param distance the new blocks
	 */
	public void setBlocks(int distance) {
		System.out.println("setting blocks");
		int[] xCoord = { 0, 100, 200, 300, 400 };
		ArrayList<Color> colors = new ArrayList<>();
		colors.add(Color.DODGERBLUE);
		colors.add(Color.PLUM);
		colors.add(Color.TOMATO);
		colors.add(Color.SALMON);
		colors.add(Color.AQUAMARINE);
		Collections.shuffle(colors);
		int lengthSnake = Snake.getSnakeLength().size();
		ArrayList<Block> blockList = new ArrayList<>();
		String bin = "00000";
		while (bin.equals("00000")) {
			int x = (int) (Math.random() * 31) + 1;
			bin = Integer.toBinaryString(x);
			while (bin.length() < 5) {
				bin = "0".concat(bin);
			}

			int counter = 0;
			String in = bin;
			while (counter < bin.length()) {
				if (!checkBlockPosition(xCoord[counter], distance)) {

					if (counter > 0 && counter < bin.length() - 1) {
						bin = bin.substring(0, counter) + "0" + bin.substring(counter + 1);
					}
					if (counter == 0) {
						bin = "0" + bin.substring(counter + 1);
					}
					if (counter == bin.length() - 1) {
						bin = bin.substring(0, counter) + "0";
					}

				}
				counter++;
			}
		}

		boolean[] chain = new boolean[5];
		for (int i = 0; i < bin.length() - 1; i++) {
			if (bin.charAt(i) == bin.charAt(i + 1) && bin.charAt(i) == '1') {
				chain[i] = true;
				chain[i + 1] = true;
			}
		}

		int counter = 0;
		ArrayList<Integer> reqBlocks = new ArrayList<>();
		while (counter < 5) {
			if (chain[counter]) {
				int c = counter;
				int endOfChain = 0;
				for (int j = counter; j < chain.length; j++) {
					if (!chain[j]) {
						endOfChain = j - 1;
						counter = j;
						break;
					} else if (j == chain.length - 1) {
						endOfChain = j;
						counter = j + 1;
					}
				}
				int lengthChain = endOfChain - c + 1;
				int b = (int) (Math.random() * lengthChain) + c;
				reqBlocks.add(b);
			} else {
				counter++;
			}

		}

		for (int i = 0; i < bin.length(); i++) {
			if (bin.charAt(i) == '1') {
				Color c = colors.get(i);
				int val = 0;
				Text t = null;
				if (reqBlocks.contains(i)) {
					val = (int) (Math.random() * (lengthSnake - 1)) + 1;
					t = new Text(Integer.toString(val));
				} else {
					val = (int) (Math.random() * 49) + 1;
					t = new Text(Integer.toString(val));
				}
				Block block = new Block(xCoord[i], distance, 100, 100, c, val, t);
				blockList.add(block);
				main.getRoot().getChildren().add(block.getStack());
			}
		}
		if (!blockList.isEmpty()) {
			main.getBlockOnScreen().add(blockList);
		}
	}

	/**
	 * Check block position.
	 *
	 * @param x the x
	 * @param y the y
	 * @return true, if successful
	 */
	private boolean checkBlockPosition(int x, int y) {
		if (main.getMagnetOnScreen() != null) {
			StackPane magnet = main.getMagnetOnScreen().getStack();
			int magnetPosX = (int) magnet.getLayoutX();
			int magnetPosY = (int) (magnet.getTranslateY());
			if (x >= magnetPosX - 120 && x <= magnetPosX + 20 && y >= magnetPosY - 120 && y <= magnetPosY + 20) {
				return false;
			}
		}

		if (main.getDestroyBlockOnScreen() != null) {
			StackPane DB = main.getDestroyBlockOnScreen().getStack();
			int DBPosX = (int) DB.getLayoutX();
			int DBPosY = (int) (DB.getTranslateY());
			if (x >= DBPosX - 120 && x <= DBPosX + 20 && y >= DBPosY - 120 && y <= DBPosY + 20) {
				return false;
			}
		}

		if (main.getShieldOnScreen() != null) {
			StackPane shield = main.getShieldOnScreen().getStack();
			int shieldPosX = (int) shield.getLayoutX();
			int shieldPosY = (int) (shield.getTranslateY());
			if (x >= shieldPosX - 120 && x <= shieldPosX + 20 && y >= shieldPosY - 120 && y <= shieldPosY + 20) {
				return false;
			}
		}

		return true;
	}

}
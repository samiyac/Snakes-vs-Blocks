/*
 * @author Priya Kaushal and Samiya Caur
 */

package application;


import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;

// TODO: Auto-generated Javadoc
/**
 * The Class DropDown.
 */
public class DropDown {

	/** The main. */
	private final Main main;

	/**
	 * Instantiates a new drop down.
	 *
	 * @param main the main
	 */
	public DropDown(Main main) {
		this.main = main;
	}

	/**
	 * Sets the drop down box.
	 */
	public void setDropDownBox() {
		main.setDropDownMenu(new ChoiceBox<>());
		main.getDropDownMenu().getItems().addAll("Restart", "Exit Game");
		main.getDropDownMenu().setLayoutX(400);
		main.getDropDownMenu().setLayoutY(3);
		main.getDropDownMenu().setBackground(Background.EMPTY);
		String style = "-fx-background-color: rgba(255,255,0);";
		main.getDropDownMenu().setStyle(style);
		main.getRoot().getChildren().addAll(main.getDropDownMenu());
		main.getDropDownMenu().toFront();
		main.getDropDownMenu().getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.equals(0)) {
					main.setRestart(true);
					main.getGameState().setNewGame();
				} else if (newValue.equals(1)) {
					main.setEnd(true);
					try {
						main.getSerializer().saveGameState();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						main.startpage(main.getStage());
					} catch (Exception e) {
						System.out.println(e);
					}

				}
			}
		});
	}

}

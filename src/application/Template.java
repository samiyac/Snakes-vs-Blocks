package application;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;

public class Template {

	@FXML
	public void StartClassic() {
		Main m = new Main();
		try {
			m.setNewGame();
			Main.mode = 1;
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	@FXML
	public void StartChristmas() {
		Main m = new Main();
		try {
			m.setNewGame();
			Main.mode = 2;
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	@FXML
	public void StartHalloween() {
		Main m = new Main();
		try {
			m.setNewGame();
			Main.mode = 3;
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	@FXML
	public void StartSummer() {
		Main m = new Main();
		try {
			m.setNewGame();
			Main.mode = 4;
		} catch (Exception p) {
			System.out.println(p);
		}
	}

	@FXML
	public void IncreaseShield() {
		Main.ShieldTimer += 2;
	}

	@FXML
	public void IncreaseMagnet() {
		Main.MagnetTimer += 2;
	}

	@FXML
	public void SetGreen() {
		Main.colorBall = Color.web("#84ff1fe2");
	}

	@FXML
	public void SetBlue() {
		Main.colorBall = Color.web("#1f85e4");
	}

	@FXML
	public void SetRed() {
		Main.colorBall = Color.web("#ee1f79");
	}

	@FXML
	public void SetYellow() {
		Main.colorBall = Color.web("#f5eb24");
	}
}

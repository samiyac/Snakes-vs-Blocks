package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class LeaderBoardDisplay extends Application {

  @Override
  public void start(Stage theStage) throws Exception
  {

          Parent root = FXMLLoader.load(getClass().getResource("LeaderBoard.fxml"));
          Scene scene = new Scene(root, 500, 1000, Color.BLACK);
          theStage.setScene(scene);

      theStage.setTitle( "LeaderBoard" );

      theStage.show();
  }


  public static void main(String[] args) {
      launch(args);
  }
}
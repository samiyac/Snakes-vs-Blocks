package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class StartPage {


   // Class m = Main.class;
    Stage s;


    public StartPage() {
        s=Main.stage;
        
    }

    @FXML
    protected void showLeader(ActionEvent event) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            //Stage stage = new Stage();
            s.setScene(new Scene(root1));
            //Main.stage.hide();
            s.show();
        }



    @FXML
    protected void NewGame(ActionEvent event ) throws IOException {

        Main m = new Main();
       // Stage s = new Stage();
        try{
            m.setNewGame();;

        }
        catch (Exception p){
            System.out.println(p);
        }
    }

    @FXML
    protected void SerialGame(ActionEvent event ) throws IOException {

        try{
            Main m =new Main();
            File tmpDir = new File("SnakeVsBlock");
            boolean exists = tmpDir.exists();
            if(exists) {
            	m.ResumeGame();
            }else {
            	
            }
            
        }
        catch (Exception E){
            System.out.println(E);
        }
    }



}

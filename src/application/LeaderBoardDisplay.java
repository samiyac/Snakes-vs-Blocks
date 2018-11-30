package application;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.text.DateFormatter;
import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.ResourceBundle;


public class LeaderBoardDisplay implements Initializable {

    Stage s = Main.stage;

    @FXML
    private Label A = new Label(" ");
    @FXML
    private Label B = new Label(" ");
    @FXML
    private Label C = new Label(" ");
    @FXML
    private Label D = new Label(" ");
    @FXML
    private Label E = new Label(" ");
    @FXML
    private Label F = new Label(" ");
    @FXML
    private Label G = new Label(" ");
    @FXML
    private Label H = new Label(" ");
    @FXML
    private Label I = new Label(" ");
    @FXML
    private Label J = new Label(" ");
    //private Label B = new Label();
    ArrayList<Label> List = new ArrayList<>();
    //ArrayList<Node> Data =
    LeaderBoardList l;


    public LeaderBoardDisplay() {

        l = new LeaderBoardList();
        l.printList();


    }

    @FXML
    protected void returntoMain(ActionEvent event ) throws IOException {
        s.setTitle("Snake vs Block");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StartPage.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        s.setScene(new Scene(root1));
        s.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(l.board.size()>0){
        A.setText(Integer.toString(l.board.get(0).getScore()));
        B.setText(l.board.get(0).getDate());
        }
        if(l.board.size()>1){
        C.setText(Integer.toString(l.board.get(1).getScore()));
        D.setText(l.board.get(1).getDate());
        }
        if(l.board.size()>2){
        E.setText(Integer.toString(l.board.get(2).getScore()));
        F.setText(l.board.get(2).getDate());

        }
        if(l.board.size()>3){
        G.setText(Integer.toString(l.board.get(3).getScore()));
        H.setText(l.board.get(3).getDate());

        }
        if(l.board.size()>4) {
            I.setText(Integer.toString(l.board.get(4).getScore()));
            J.setText(l.board.get(4).getDate());

        }



    }


}
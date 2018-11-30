package application;

import javax.jws.Oneway;
import java.io.*;
import java.lang.reflect.Array;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoardList implements Serializable {

    ArrayList<Node> board;


    public LeaderBoardList() {
        board = deserialise();
    }

    public void printList(){
        for(int i=0;i<board.size();i++){
            board.get(i).print();
        }
    }

    public void serialise(){

        try{
            FileOutputStream f = new FileOutputStream("LeaderList");
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(board);
            out.close();
            f.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public ArrayList<Node> deserialise(){


        ArrayList<Node>List = new ArrayList<>();

        try {
            FileInputStream f = new FileInputStream("LeaderList");
            ObjectInputStream in = new ObjectInputStream(f);
            List = (ArrayList<Node>) in.readObject();
        }
        catch (Exception e){
            System.out.println(e);
        }

        return List;

    }



}

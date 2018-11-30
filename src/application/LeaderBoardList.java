package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderBoardList implements Serializable {

    ArrayList<Node> board;


    public LeaderBoardList() {
        board = deserialise();
    }

    public void printList(){
    	Collections.sort(board, new sortList());;
        for(int i=0;i<board.size();i++){
            board.get(i).print();
            System.out.println(board.get(i).getScore());
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

    private class sortList implements Comparator<Node>{

		@Override
		public int compare(Node n1, Node n2) {
			// TODO Auto-generated method stub
			if(n1.getScore()>n2.getScore()) {
				return -1;
			}else if(n1.getScore()<n2.getScore()) {
				return 1;
			}
			return 0;
		}
    	
    }

}

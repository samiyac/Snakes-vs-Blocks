package application;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// TODO: Auto-generated Javadoc
/**
 * The Class LeaderBoardList.
 */
public class LeaderBoardList implements Serializable {

	/** The board. */
	ArrayList<Node> board;

	/**
	 * Instantiates a new leader board list.
	 */
	public LeaderBoardList() {
		board = deserialise();
	}

	/**
	 * Prints the list.
	 */
	public void printList() {
		Collections.sort(board, new sortList());
		for (int i = 0; i < board.size(); i++) {
			board.get(i).print();
		}
	}

	/**
	 * Serialise.
	 */
	public void serialise() {

		try {
			FileOutputStream f = new FileOutputStream("LeaderList");
			ObjectOutputStream out = new ObjectOutputStream(f);
			out.writeObject(board);
			out.close();
			f.close();
		} catch (Exception e) {
		}

	}

	/**
	 * Deserialise.
	 *
	 * @return the array list
	 */
	public ArrayList<Node> deserialise() {

		ArrayList<Node> List = new ArrayList<>();

		try {
			FileInputStream f = new FileInputStream("LeaderList");
			ObjectInputStream in = new ObjectInputStream(f);
			List = (ArrayList<Node>) in.readObject();
		} catch (Exception e) {
		}

		return List;

	}

	/**
	 * The Class sortList.
	 */
	private class sortList implements Comparator<Node> {

		/* (non-Javadoc)
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Node n1, Node n2) {
			// TODO Auto-generated method stub
			if (n1.getScore() > n2.getScore()) {
				return -1;
			} else if (n1.getScore() < n2.getScore()) {
				return 1;
			}
			return 0;
		}

	}

}

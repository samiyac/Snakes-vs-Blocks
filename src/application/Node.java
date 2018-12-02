package application;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 */
public class Node implements Serializable {

	/** The score. */
	private int score;
	
	/** The Date. */
	String Date;

	/**
	 * Instantiates a new node.
	 *
	 * @param score the score
	 * @param date the date
	 */
	public Node(int score, String date) {
		this.score = score;
		this.Date = date;
	}

	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return Date;
	}

	/**
	 * Sets the score.
	 *
	 * @param score the new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Prints the.
	 */
	public void print() {
		System.out.println(score + "\t\t" + Date);
	}
}
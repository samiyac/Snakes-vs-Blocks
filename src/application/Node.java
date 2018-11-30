package application;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Node implements Serializable{

    private int score;
   String Date;

    public Node(int score, String date) {
        this.score = score;
        this.Date = date;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return Date;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void print(){
        System.out.println(score+"\t\t"+Date);
    }
}
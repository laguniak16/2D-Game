package org.openjfx;

/**
 * Klasa w ktorej przechowywane sa wyniki
 */
public class Highscore implements Comparable<Highscore>{

    private String nick;
    private int score;
    private int lvlsnumber;

    /**
     * Konstruktor klasy highscore ktory przechowuje wyniki
     */
    public Highscore(String nick,int score,int lvlsnumber) {
        this.score = score;
        this.nick = nick;
        this.lvlsnumber = lvlsnumber;
    }

    /**
     * Metoda wykorzystywana do sortowania po wyniku
     */
    @Override
    public int compareTo(Highscore o) {

        return Integer.compare(score,o.score);
    }

    /**
     * Metoda zwaracajaca nick,wynik,poziom trudnosci jako string
     */
    public String toString() {
        return (nick + " " + score+ " " + lvlsnumber);
    }
}

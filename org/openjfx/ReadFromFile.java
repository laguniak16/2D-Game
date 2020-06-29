package org.openjfx;

import java.io.*;
import java.util.*;

/**
 * Wczytuje zmienne z obu plikow parametrycznych
 */
public class ReadFromFile {
        static String gamename;
        static int levelsnumber;
        static String levelname;
        static int levelsnumbering;
        static String leveldiscribel;
        static int numberofsifficultylevel;
        static int difficultyscale;
        static int gamewidth;
        static int gamehegiht;
        static float primarytargetwidth;
        static String background;
        static String backgroundcolor;
        static String gameobjects;
        static String gamefigure;
        static String[] gamecolor;

    static int gametime;
    static int shieldvalue1;
    static int shieldvalue2;
    static int tobonustime;
    static int bonustime;


   static List<Highscore> highscores = new LinkedList<>();

    /**
     * {@link #fileTxt } Funkcja wczytujaca
     * z pliku parametrycznego .txt
     */
    protected static void fileTxt()
    {
        Properties property = new Properties();
        try (Reader input = new InputStreamReader(new FileInputStream("src/par.txt"), "UTF-8")){
            property.load(input);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        gamename = property.getProperty("nazwaGry");
        levelsnumber = Integer.parseInt(property.getProperty("liczbaPoziomow"));
        levelname = property.getProperty("nazwaBazowaPlikuZOpisemPoziomu");
        levelsnumbering = Integer.parseInt(property.getProperty("numeracjaPoziomowZaczynaSieOd"));
        leveldiscribel = property.getProperty("rozszerzeniePlikuZOpisemPoziomu");
        numberofsifficultylevel = Integer.parseInt(property.getProperty("liczbaStopniTrudnosci"));
        difficultyscale = Integer.parseInt(property.getProperty("zmianaStopniaTrudnosci"));
        gamewidth = Integer.parseInt(property.getProperty("poczatkowaSzerokoscPlanszy"));
        gamehegiht = Integer.parseInt(property.getProperty("poczatkowaWysokoscPlanszy"));
        primarytargetwidth = Float.parseFloat(property.getProperty("poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy"));
        background = property.getProperty("tlo");
        backgroundcolor = property.getProperty("kolortla");
        gameobjects = property.getProperty("obiektyGry");
        gamefigure = property.getProperty("figuraObiektuGry");
        gamecolor = property.getProperty("klorTla").split(" ");
    }
    /**
     * {@link #fileParam } Funkcja wczytujaca
     * z pliku parametrycznego .props
     */
    protected static void fileParam()
    {
        Properties property = new Properties();
        // property.setProperty("par.txt","UTF-8");
        try (Reader input = new InputStreamReader(new FileInputStream("src/param.txt"), "UTF-8")){
            property.load(input);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        gametime = Integer.parseInt(property.getProperty("czasGry"));
        shieldvalue1 = Integer.parseInt(property.getProperty("wartosc1"));
        shieldvalue2 = Integer.parseInt(property.getProperty("wartosc2"));
        tobonustime = Integer.parseInt(property.getProperty("czasDoBonusu"));
        bonustime = Integer.parseInt(property.getProperty("czasBonusu"));
    }

    /**
     * {@link #fileScore()} Funkcja wczytujaca
     * wyniki z pliku wyniki.txt
     */
    protected static void fileScore()
    {
        File file = new File("src/wyniki.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                if (line != null) {
                    Highscore highscore = new Highscore(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    highscores.add(highscore);
                }
            }
            Collections.sort(highscores);
            Collections.reverse(highscores);
        }
        catch (IOException io)
        {
            System.out.println(io);
        }

    }
}

package org.openjfx;

import java.io.*;
import java.net.Socket;
import java.util.Collections;

/**
 * Klasa wykonujaca operacje z serwerem lub wywolujaca pobranie parametrow z plikow lokalnych
 */
public class GetDataFromServer {

    static Socket socket;

    /**
     * Metoda ustawiajaca socket na socket serwera
     * @param var0 socket serwera
     */
    public static void makeMenu(Socket var0) {

        socket=var0;
    }

    /**
     * {@link #runGame(Socket)}Metoda decydujaca skad maja zostac pobrane pliki parametryczne na podstawie tego czy serwer jest wlaczony
     * @param var0 socket serwera
     */
    public static void runGame(Socket var0) {
        if (var0 != null) {
            socket=var0;
            Main.offline=true;
            getFileParamFromServer(var0);
            getConfigFromServer(var0);
            getLevelDescribe(var0);
            ReadFromFile.fileScore();
            refreshHighscore();
            getHighScoresFromServer(var0);

        } else {
            ReadFromFile.fileTxt();
            ReadFromFile.fileParam();
            ReadFromFile.fileScore();
            }

                    makeMenu(var0);

        }

    /**
     *{@link #getConfigFromServer(Socket)}Metoda odbierajaca z serwera zawartosc pliku par.txt
     */
    private static void getConfigFromServer(Socket var0) {
        try {
            OutputStream var1 = var0.getOutputStream();
            PrintWriter var2 = new PrintWriter(var1, true);
            var2.println("SEND_CONFIG_FILE");
            InputStream var3 = var0.getInputStream();
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var3));
            String var5 = var4.readLine();
            String[] parts = var5.split(" ");
            for (int i = 0; i < parts.length; i++) {
                String str = parts[i];
                String[] parts2 = str.split("=");
                switch (parts2[0]) {
                    case "nazwaGry":
                        ReadFromFile.gamename = parts2[1];
                        break;
                    case "liczbaPoziomow":
                        ReadFromFile.levelsnumber = Integer.parseInt(parts2[1]);
                        break;
                    case "nazwaBazowaPlikuZOpisemPoziomu":
                        ReadFromFile.levelname = parts2[1];
                        break;
                    case "numeracjaPoziomowZaczynaSieOd":
                        ReadFromFile.levelsnumbering = Integer.parseInt(parts2[1]);
                        break;
                    case "rozszerzeniePlikuZOpisemPoziomu":
                        ReadFromFile.leveldiscribel = parts2[1];
                        break;
                    case "liczbaStopniTrudnosci":
                        ReadFromFile.numberofsifficultylevel = Integer.parseInt(parts2[1]);
                        break;
                    case "zmianaStopniaTrudnosci":
                        ReadFromFile.difficultyscale = Integer.parseInt(parts2[1]);
                        break;
                    case "poczatkowaSzerokoscPlanszy":
                        ReadFromFile.gamewidth = Integer.parseInt(parts2[1]);
                        break;
                    case "poczatkowaWysokoscPlanszy":
                        ReadFromFile.gamehegiht = Integer.parseInt(parts2[1]);
                        break;
                    case "poczatkowaSzerokoscObiektuGryJakoProcentPoczatkowejSzerokosciPlanszy":
                        ReadFromFile.primarytargetwidth = Float.parseFloat(parts2[1]);
                        break;
                    case "tlo":
                        ReadFromFile.background = parts2[1];
                        break;
                    case "klorTla":
                        str = parts2[1];
                        parts2 = str.split(":");
                        ReadFromFile.gamecolor = parts2;
                        break;
                    case "obiektyGry":
                        ReadFromFile.gameobjects = parts2[1];
                        break;
                    case "figuraObiektuGry":
                        ReadFromFile.gamefigure = parts2[1];
                        break;
                }
            }

        } catch (IOException var7) {
            System.out.println("Zawartosc pliku parametrycznego nie mogla zostac pobrana");
            System.out.println(var7);
        }
    }
    /**
     *{@link #getFileParamFromServer(Socket)}Metoda odbierajaca z serwera zawartosc pliku param.txt
     */
    private static void getFileParamFromServer(Socket var0) {
        try {
            var0.getInputStream().skip((long) var0.getInputStream().available());
            OutputStream var2 = var0.getOutputStream();
            PrintWriter var3 = new PrintWriter(var2, true);
            var3.println("GET_PARAM_FILE");
            InputStream var4 = var0.getInputStream();
            BufferedReader var5 = new BufferedReader(new InputStreamReader(var4));
            String var6 = var5.readLine();
            String[] parts = var6.split(" ");
            for (int i = 0; i < parts.length; i++) {
                String str = parts[i];
                String[] parts2 = str.split("=");
                switch (parts2[0]) {
                    case "czasGry":
                        ReadFromFile.gametime = Integer.parseInt(parts2[1]);
                        break;
                    case "wartosc1":
                        ReadFromFile.shieldvalue1 = Integer.parseInt(parts2[1]);
                        break;
                    case "wartosc2":
                        ReadFromFile.shieldvalue2 = Integer.parseInt(parts2[1]);
                        break;
                    case "czasDoBonusu":
                        ReadFromFile.bonustime = Integer.parseInt(parts2[1]);
                        break;
                    case "czasBonusu":
                        ReadFromFile.tobonustime = Integer.parseInt(parts2[1]);
                        break;
                }
            }
        }catch (IOException var8) {
            System.out.println("Zawartosc pliku parametrycznego nie mogla zostac pobrana");
            System.out.println(var8);
        }

    }
    /**
     *{@link #getLevelDescribe(Socket)} (Socket)}Metoda odbierajaca z serwera zawartosc pliku z opisem poziomu
     */
    private static void getLevelDescribe(Socket var0) {
        try {
            var0.getInputStream().skip((long) var0.getInputStream().available());
            OutputStream var2 = var0.getOutputStream();
            PrintWriter var3 = new PrintWriter(var2, true);
            var3.println("SEND_LEVEL_DESCRIBE");
            InputStream var4 = var0.getInputStream();
            BufferedReader var5 = new BufferedReader(new InputStreamReader(var4));
            String var6 = var5.readLine();
            String[] parts = var6.split(" ");
            for (int i = 0; i < parts.length; i++) {
                String str = parts[i];
                String[] parts2 = str.split("=");
                switch (parts2[0]) {
                    case "iloscPkt1":
                        LevelDescribe.needPoints1 = Integer.parseInt(parts2[1]);
                        break;
                    case "iloscPkt2":
                        LevelDescribe.needPoints2 = Integer.parseInt(parts2[1]);
                        break;
                    case "iloscPkt3":
                        LevelDescribe.needPoints3 = Integer.parseInt(parts2[1]);
                        break;
                    case "iloscPkt4":
                        LevelDescribe.needPoints4 = Integer.parseInt(parts2[1]);
                        break;
                    case "iloscPkt5":
                        LevelDescribe.needPoints5 = Integer.parseInt(parts2[1]);
                        break;
                    case "iloscPkt6":
                        LevelDescribe.needPoints6 = Integer.parseInt(parts2[1]);
                        break;
                }
            }
        }catch (IOException var8) {
            System.out.println("Zawartosc pliku z opisem poziomu nie mogla zostac pobrana");
            System.out.println(var8);
        }

    }
    /**
     *{@link #getHighScoresFromServer(Socket)} Metoda odbierajaca z serwera zawartosc pliku wyniki.txt
     */
    private static void getHighScoresFromServer(Socket var1) {
        try {
           var1.getInputStream().skip((long)var1.getInputStream().available());
            OutputStream var2 = var1.getOutputStream();
            PrintWriter var3 = new PrintWriter(var2, true);
            var3.println("SEND_HIGH_SCORES");
            InputStream var4 = var1.getInputStream();
            BufferedReader var5 = new BufferedReader(new InputStreamReader(var4));
            String var6 = var5.readLine();
            String[] parts = var6.split(" ");

            for (int i = 0; i < 15; i+=3) {
                Highscore highscore = new Highscore(parts[i], Integer.parseInt(parts[i+1]), Integer.parseInt(parts[i+2]));
                ReadFromFile.highscores.add(highscore);
            }
            Collections.sort(ReadFromFile.highscores);
            Collections.reverse(ReadFromFile.highscores);

            for(int i=0;i<ReadFromFile.highscores.size()-1;i++)
            {
                String str1 = ReadFromFile.highscores.get(i).toString();
                String[] str2 = str1.split(" ");
                for(int n=i+1;n<ReadFromFile.highscores.size();n++)
                {
                    String str3 = ReadFromFile.highscores.get(n).toString();
                    String[] str4 = str3.split(" ");
                    if(str2[0].equals(str4[0]))
                        ReadFromFile.highscores.remove(n);
                }
            }
            for(int i=ReadFromFile.highscores.size()-1;i>4;i--) ////TUTAJ SIE SYPIE
            {
                ReadFromFile.highscores.remove(i);
            }
            System.out.println("hej");
        } catch (IOException var8) {
            System.out.println("PLik nie mĂłgĹ‚ zostaÄ‡ pobrany z serwera");
            System.out.println(var8);
        }


    }
    /**
     *{@link #addScore(String, int, int)}Metoda wysylajaca na serwer wynik po udanie zakonczonej rozgrywce
     */
    public static void addScore(String Nick, int Score,int levelsnumber){
        if(socket != null && socket.isConnected()) {
            try {
                socket.getInputStream().skip(socket.getInputStream().available());
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("SEND_SCORE:" + Nick + " " + Score + " " + levelsnumber);
            } catch (IOException e) {
                System.out.println("Nie udało sie dodac wyniku");
                System.out.println(e);
            }
        }
    }

    /**
     *{@link #refreshHighscore()} Metoda wysylajaca na serwer zawartosc pliku wyniki.txt w celu zaaktualizowania wynikow na serwerze
     */
    public static void refreshHighscore(){
        if(socket != null && socket.isConnected()) {
            try {

                socket.getInputStream().skip(socket.getInputStream().available());
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("GAME_SCORES:"+ ReadFromFile.highscores.get(0)+" " + ReadFromFile.highscores.get(1)+" " + ReadFromFile.highscores.get(2)+" " + ReadFromFile.highscores.get(3)+" " +ReadFromFile.highscores.get(4));

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.out.println("Nie udało sie odswiezyc wynikow wyniku");
                System.out.println(e);
            }
        }
    }
}

package org.openjfx;

import java.io.*;
import java.util.Properties;

/**
 * Klasa zapisujaca i wczytujaca parametry kolejnych poziomow
 */
public class LevelDescribe {


    public static String lvlname = ReadFromFile.levelname;
    public static  int needPoints1=0;
    public static  int needPoints2=0;
    public static int needPoints3=0;
    public static int needPoints4=0;
    public static int needPoints5=0;
    public static int needPoints6=0;

    public void main(String[] args){

        if(GetDataFromServer.socket==null)
        readLvlDiscribe(lvlname);
    }

    /**
     * {@link #readLvlDiscribe(String)}Metoda wczytujaca
     * z pliku opis poziomu
     * @param lvlname nazwa pliku z opisem poziomu
     */
    protected static void readLvlDiscribe(String lvlname)
    {
        Properties property = new Properties();
        try (Reader input = new InputStreamReader(new FileInputStream(lvlname+"."+ReadFromFile.leveldiscribel))){
            property.load(input);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if(ReadFromFile.levelsnumber==3)
        {
            if(ReadFromFile.levelsnumbering==0)
            {
                needPoints1 = Integer.parseInt(property.getProperty("iloscPkt1"));
                needPoints2 = Integer.parseInt(property.getProperty("iloscPkt2"));
                needPoints3 = Integer.parseInt(property.getProperty("iloscPkt3"));
                needPoints4 = Integer.parseInt(property.getProperty("iloscPkt4"));
            }
            else
            {
                needPoints1 = Integer.parseInt(property.getProperty("iloscPkt1"));
                needPoints2 = Integer.parseInt(property.getProperty("iloscPkt2"));
                needPoints3 = Integer.parseInt(property.getProperty("iloscPkt3"));
            }
        }
        else if(ReadFromFile.levelsnumber==4)
        {
            if(ReadFromFile.levelsnumbering==0)
            {
                needPoints1 = Integer.parseInt(property.getProperty("iloscPkt1"));
                needPoints2 = Integer.parseInt(property.getProperty("iloscPkt2"));
                needPoints3 = Integer.parseInt(property.getProperty("iloscPkt3"));
                needPoints4 = Integer.parseInt(property.getProperty("iloscPkt4"));
                needPoints5 = Integer.parseInt(property.getProperty("iloscPkt5"));
            }
            else
            {
                needPoints1 = Integer.parseInt(property.getProperty("iloscPkt1"));
                needPoints2 = Integer.parseInt(property.getProperty("iloscPkt2"));
                needPoints3 = Integer.parseInt(property.getProperty("iloscPkt3"));
                needPoints4 = Integer.parseInt(property.getProperty("iloscPkt4"));
            }
        }
        else if(ReadFromFile.levelsnumber==5)
        {
            if(ReadFromFile.levelsnumbering==0)
            {
                needPoints1 = Integer.parseInt(property.getProperty("iloscPkt1"));
                needPoints2 = Integer.parseInt(property.getProperty("iloscPkt2"));
                needPoints3 = Integer.parseInt(property.getProperty("iloscPkt3"));
                needPoints4 = Integer.parseInt(property.getProperty("iloscPkt4"));
                needPoints5 = Integer.parseInt(property.getProperty("iloscPkt5"));
                needPoints6 = Integer.parseInt(property.getProperty("iloscPkt6"));
            }
            else
            {
                needPoints1 = Integer.parseInt(property.getProperty("iloscPkt1"));
                needPoints2 = Integer.parseInt(property.getProperty("iloscPkt2"));
                needPoints3 = Integer.parseInt(property.getProperty("iloscPkt3"));
                needPoints4 = Integer.parseInt(property.getProperty("iloscPkt4"));
                needPoints4 = Integer.parseInt(property.getProperty("iloscPkt5"));
            }
        }
    }
}

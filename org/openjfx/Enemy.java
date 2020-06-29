package org.openjfx;

import javafx.fxml.FXML;

import javafx.scene.layout.Pane;

/**
 * Klasa abstrakcyjna od ktorej dziedzicza wszystkie wystepujace obiekty
 */
public abstract class Enemy {


        public int time;
        public int height = ReadFromFile.gamehegiht;
        public int width = ReadFromFile.gamewidth;
        double changedwidth = ReadFromFile.gamewidth;
        double changedheight = ReadFromFile.gamehegiht;
        @FXML
        public Pane mainstage;

        public void setTime(int time){
            this.time = time;
        }

        /**
         * initializacja danego obiektu
         */
        abstract void initialize();

        abstract void setPause(boolean active);
        abstract public void setDisable();

        /**
         * Ustawienie glownej sceny
         */
        public void setPane(Pane mainstage)
        {
           this.mainstage=mainstage;
        }

        /**
         * Wysokosc sceny
         */
        public void setHeight(int height) { this.height = height; }

        /**
         * Szerokosc sceny
         */
        public void setWidth(int width){
            this.width = width;
        }

        /**
         * Funkcja zapisujaca zmieniajaca sie szerokosc sceny
         */
        public void gameWidth(double width)
        {
            this.changedwidth = width;
        }
        /**
         * Funkcja zapisujaca zmieniajaca sie wysokosc sceny
         */
        public void gameHeight(double height){
            changedheight=height;
        }
        /**
         *{@link #setLayX()}Metoda obliczajaca pozycje X obiektu podczas skalowania
         */
        abstract double setLayX();
        /**
         *{@link #setX(double)}()}Metoda ustwiajaca pozycje X obiektu podczas skalowania
         */
        abstract void setX(double position);
        /**
         *{@link #setLayY()}Metoda obliczajaca pozycje Y obiektu podczas skalowania
         */
        abstract double setLayY();
        /**
         *{@link #setY(double)}()}Metoda ustwiajaca pozycje Y obiektu podczas skalowania
         */
        abstract void setY(double position);
        /**
         * {@link #scale(double)}Metoda skalujaca wielkosc i szerokosc obiektow w zaleznosci od rozmiarow okna
         */
        abstract void scale(double scale);
        /**
         * Metoda rysujaca obiekt do zestrzelenia oraz wylapujaca jego zestrzelenie
         */
        abstract void paint();
    }

package org.openjfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

import static org.openjfx.ReadFromFile.bonustime;

/**
 * Klasa rysujaca bonusbox
 */
public class BonusBox extends Enemy {

    Rectangle rectangle = new Rectangle();
    double scale =((ReadFromFile.primarytargetwidth*ReadFromFile.gamewidth)/100)*0.8;
    Random rand = new Random();
    int x = rand.nextInt(width);
    int y = rand.nextInt(height-70);
    double setx;
    double sety;
    Timeline timer = new Timeline();
    static int boxtime=0;
    static int timetobonusappear=ReadFromFile.tobonustime;
    static boolean boxappeard = false;
    static boolean isBoxappeard = true;
    static int boxvalue;
    static int box;
    /**
     * {@link #initialize()}Metoda ustwaiajaca czas
     * i glowna scene oraz wywolujaca metode
     * rysujaca obiekty do zestrzelenia
     */
    public void initialize()
    {
        //setTime(time);
        setPane(mainstage);
        boxappeard =true;
        timer();
        paint();
    }
    /**
     * {@link #setPause(boolean)} Metoda pauzujaca gre
     * @param active zmienna przechowujaca czy dany obiekt jest aktywny
     */
    public void setPause(boolean active) { this.rectangle.setDisable(active);
    timer.stop();
    }
    /**
     * {@link #setDisable()} Metoda czyszczaca obiekt z planszy przy przejsciu na kolejny poziom
     */
    public void setDisable() { this.rectangle.setVisible(false);
        timer.stop();
    }

    public void setStart()
    {
        timer.playFromStart();
    }

    /**
     *{@link #setLayX()}Metoda obliczajaca pozycje X obiektu podczas skalowania
     */
    public double setLayX(){
        setx = x/(changedwidth);
        return setx;
    }
    /**
     *{@link #setX(double)}()}Metoda ustwiajaca pozycje X obiektu podczas skalowania
     */
    public void setX(double position){
        rectangle.setX(position);
    }
    /**
     *{@link #setLayY()}Metoda obliczajaca pozycje Y obiektu podczas skalowania
     */
    public double setLayY(){
        sety = y/(changedheight);
        return sety;
    }
    /**
     *{@link #setY(double)}()}Metoda ustwiajaca pozycje Y obiektu podczas skalowania
     */
    public void setY(double position){
        rectangle.setY(position);
    }
    /**
     * {@link #scale(double)}Metoda skalujaca wielkosc i szerokosc obiektow w zaleznosci od rozmiarow okna
     */
    public void scale(double scale)
    {
        this.scale = scale;
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
    }
    /**
     * Metoda rysujaca obiekt do zestrzelenia oraz wylapujaca jego zestrzelenie
     */
    public void paint()
    {
        timetobonusappear=ReadFromFile.tobonustime;
        rectangle = new Rectangle(scale,scale);
        x = rand.nextInt(width);

        if(x<scale)
            x+=scale;
        else if(x>width-scale)
            x-=scale;
        y = rand.nextInt(height-70);

        if(y<scale)
            y+=scale;
        else if(y>height-(scale+70))
            y -= scale+70;

        rectangle.setX(x);
        rectangle.setY(y);

        Random rand = new Random();
        box = rand.nextInt(4);

        Image img = null;
        if(box==0) {
            img = new Image("/box2.jpg");
            boxvalue=2;
        }
        else if(box==1) {
            img = new Image("/box3.jpg");
            boxvalue=3;
        }
        else if(box==2) {
            img = new Image("/box4.jpg");
            boxvalue=4;
        }
        else if(box==3) {
            img = new Image("/box5.jpg");
            boxvalue=5;
        }

        rectangle.setFill(new ImagePattern(img));
        rectangle.setVisible(true);
        mainstage.getChildren().add(rectangle);


        rectangle.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                PlayController.finalscore=PlayController.finalscore+PlayController.shieldvalue*boxvalue;
                PlayController.score++;
                rectangle.setVisible(false);
                boxtime=0;
                isBoxappeard= false;
                boxappeard=false;
                PlayController.count=0;
            }
        });
    }

    /**
     * {@link #timer()}Metoda uruchamiajaca zegar ktory liczy czas do pojawienia sie kolejnego bonusu
     */
    public void timer() {
        timer.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(timetobonusappear==0) {
                    paint();
                    isBoxappeard=true;
                    boxappeard = true;
                }

                if(boxtime>= bonustime)
                {
                    //rectangle.setDisable(true);
                    rectangle.setVisible(false);
                    boxtime=0;
                    isBoxappeard=false;
                    boxappeard=false;
                }

                if(boxappeard)
                    boxtime++;

                if(!isBoxappeard) {
                    timetobonusappear--;
                }

            }
            });
        timer.getKeyFrames().add(frame);
        timer.playFromStart();
    }
}
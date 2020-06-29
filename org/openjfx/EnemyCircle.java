package org.openjfx;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

/**
 * Klasa rysujaca cel kolko
 */
public class EnemyCircle extends Enemy
{
    Circle cir = new Circle(10,10,((ReadFromFile.primarytargetwidth*ReadFromFile.gamewidth)/200),Color.AQUA);

    double scale =(ReadFromFile.primarytargetwidth*ReadFromFile.gamewidth)/100;
    Random rand = new Random();
    int x = rand.nextInt(width);
    int y = rand.nextInt(height-70);
    double minsize =20;
    double setx;
    double sety;
    /**
     * {@link #initialize()}Metoda ustwaiajaca czas
     * i glowna scene oraz wywolujaca metode
     * rysujaca obiekty do zestrzelenia
     */
    public void initialize()
    {
        setTime(time);
        setPane(mainstage);
        paint();
    }
    /**
     * {@link #setPause(boolean)} Metoda pauzujaca gre
     * @param active zmienna przechowujaca czy dany obiekt jest aktywny
     */
    public void setPause(boolean active) { this.cir.setDisable(active);}

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
        if(position<scale/2)
             cir.setLayoutX(scale/2);
        else if(position>width-scale)
            cir.setLayoutX(width-scale);
        else
        cir.setLayoutX(position);
    }
    /**
     * {@link #setDisable()} Metoda czyszczaca obiekt z planszy przy przejsciu na kolejny poziom
     */
    public void setDisable() { this.cir.setVisible(false);
        //this.cir = null;
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
        if(position<scale/2)
            cir.setLayoutY(scale/2-40);
        else if(position>height-scale-70)
            cir.setLayoutY(height-scale-70);
        else
        cir.setLayoutY(position);
    }
    /**
     * {@link #scale(double)}Metoda skalujaca wielkosc i szerokosc obiektow w zaleznosci od rozmiarow okna
     */
    public void scale(double scale)
    {
        if(scale<minsize)
            scale = minsize;
        this.scale = scale;
        cir.setRadius(scale);
    }
    /**
     * Metoda rysujaca obiekt do zestrzelenia oraz wylapujaca jego zestrzelenie
     */
    public void paint()
    {
        cir = new Circle();
            cir.setRadius(scale);
            x = rand.nextInt(width);
            if(x<cir.getRadius())
                x+=cir.getRadius();
            else if(x>width-cir.getRadius())
                x-=cir.getRadius();
            y = rand.nextInt(height-70);

            if(y<cir.getRadius())
                y+=cir.getRadius();
            else if(y>height-cir.getRadius()-70) {
                y -= cir.getRadius();
            }
            cir.setLayoutX(x);
            cir.setLayoutY(y);
            cir.setFill(Color.BLACK);
            cir.setVisible(true);
            mainstage.getChildren().add(cir);
        pointAdd();

    }

    public void pointAdd()
    {
        cir.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                PlayController.score++;
                PlayController.finalscore=PlayController.finalscore+2*PlayController.shieldvalue;
                cir.setVisible(false);
                if(PlayController.score<PlayController.scoreGot)
                    paint();
                PlayController.count=0;
            }
        });

    }
}
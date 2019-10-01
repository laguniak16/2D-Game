package org.openjfx;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.*;
import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Klasa rysujaca cel trojkat
 */
public class EnemyTriangle extends Enemy{

        Polygon triangle = new Polygon();

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
    public void setPause(boolean active) { this.triangle.setDisable(active);}

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
                triangle.setLayoutX(scale/2);
            else if(position>width-scale)
                triangle.setLayoutX(width-scale);
            else
            triangle.setLayoutX(position);
        }

    /**
     * {@link #setDisable()} Metoda czyszczaca obiekt z planszy przy przejsciu na kolejny poziom
     */
    public void setDisable() { this.triangle.setVisible(false);
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
                triangle.setLayoutY(scale/2-40);
            else if(position>height-scale-70)
                triangle.setLayoutY(height-scale-70);
            else
            triangle.setLayoutY(position);
        }

    /**
     * {@link #scale(double)}Metoda skalujaca wielkosc i szerokosc obiektow w zaleznosci od rozmiarow okna
     */
    public void scale(double scale)
        {
            if(scale<minsize)
            scale = minsize;

            this.scale = scale;
            double c = sqrt(((scale*scale)-(scale/2)*(scale/2)));
            triangle.getPoints().setAll(
                    0d, 0d,
                    scale, 0d,
                    scale/2, c
            );
        }

    /**
     * Metoda rysujaca obiekt do zestrzelenia oraz wylapujaca jego zestrzelenie
     */
    public void paint()
        {
            triangle = new Polygon();
            double c = sqrt(((scale*scale)-(scale/2)*(scale/2)));
            triangle.getPoints().setAll(
                    0d, 0d,
                    scale, 0d,
                    scale/2, c
            );
            x = rand.nextInt(width);

            if(x<c)
                x+=c;
            else if(x>width-c)
                x-=c;
            triangle.setLayoutX(x);
            y = rand.nextInt(height-70);

            if(y<scale)
                y+=scale;
            else if(y>height-scale-70) {
                y -= scale;
            }
            triangle.setLayoutY(y);
            triangle.setStrokeLineCap(StrokeLineCap.ROUND);

            mainstage.getChildren().add(triangle);
            pointAdd();

        }

    public void pointAdd()
    {
        triangle.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                PlayController.score++;
                PlayController.finalscore=PlayController.finalscore+2*PlayController.shieldvalue;
                triangle.setVisible(false);
                if(PlayController.score<PlayController.scoreGot)
                    paint();
                PlayController.count=0;
            }
        });

    }
}
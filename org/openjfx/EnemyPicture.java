package org.openjfx;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Klasa rysujaca cel zdjecie
 */

class EnemyPicture extends Enemy {

    Rectangle rectangle = new Rectangle();

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
    public void setPause(boolean active) { this.rectangle.setDisable(active);}
    /**
     * {@link #setDisable()} Metoda czyszczaca obiekt z planszy przy przejsciu na kolejny poziom
     */
    public void setDisable() { this.rectangle.setVisible(false);
    //this.rectangle=null;
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
        if(position<scale/2)
            rectangle.setX(scale/2);
        else if(position>width-scale)
            rectangle.setX(width-scale);
        else
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
        if(position<scale/2)
            rectangle.setY(scale/2-40);
        else if(position>height-scale-70)
            rectangle.setY(height-scale-70);
        else
        rectangle.setY(position);
    }
    /**
     * {@link #scale(double)}Metoda skalujaca wielkosc i szerokosc obiektow w zaleznosci od rozmiarow okna
     */
    public void scale(double scale)
    {
        if(scale<minsize)
            scale = minsize;
        this.scale = scale;
            rectangle.setHeight(scale);
            rectangle.setWidth(scale);
    }
    /**
     * Metoda rysujaca obiekt do zestrzelenia oraz wylapujaca jego zestrzelenie
     */
    public void paint()
    {
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
        Image img = new Image("/cel.png");



        rectangle.setFill(new ImagePattern(img));
        rectangle.setVisible(true);
        mainstage.getChildren().add(rectangle);
        pointAdd();
    }

    public void pointAdd()
    {
        rectangle.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                PlayController.score++;
                PlayController.finalscore=PlayController.finalscore+2*PlayController.shieldvalue;
                rectangle.setVisible(false);
                if(PlayController.score<PlayController.scoreGot)
                    paint();
                PlayController.count=0;
            }
        });

    }
}

package org.openjfx;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

/**
 * Klasa rysujaca cel kwadrat i prostokat
 */
 class EnemyRectangle extends Enemy {

        Rectangle rectangle = new Rectangle();
        //PlayController playController;

        public EnemyRectangle(PlayController playController)
        {
           // this.playController=playController;
        }
        double scale =(ReadFromFile.primarytargetwidth*ReadFromFile.gamewidth)/100;
        Random rand = new Random();
        int x = rand.nextInt(width);
        int y = rand.nextInt(height-70);
        double setx;
        double sety;
        double choose= scale/2;
        double minsize =20;

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
            }
    public void setOff(boolean active)
    {
        this.rectangle.setVisible(active);
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

            if(ReadFromFile.gamefigure.equals("kwadraty")) {
                rectangle.setHeight(scale);
                rectangle.setWidth(scale);
            }
            else if(ReadFromFile.gamefigure.equals("prostokaty"))
            {
                rectangle.setHeight(scale);
                rectangle.setWidth(scale/2);
            }
        }
    /**
     * Metoda rysujaca obiekt do zestrzelenia oraz wylapujaca jego zestrzelenie
     */
        public void paint()
        {

            if(ReadFromFile.gamefigure.equals("kwadraty"))
                choose = scale;
            else if(ReadFromFile.gamefigure.equals("prostokaty"))
            choose = scale/2;

            rectangle = new Rectangle(choose,scale);
            x = rand.nextInt(width);
            if(x>width-scale)
                x-=scale;

            y = rand.nextInt(height-70);
            if(y>height-(scale+70))
                y -= scale+70;
            rectangle.setX(x);
            rectangle.setY(y);
            rectangle.setFill(Color.BLACK);
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
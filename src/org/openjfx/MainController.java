package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;


/**
 * Glowny kontroler podstawowego staga wywolywanego z main
 */
public class MainController {

    @FXML
    private StackPane mainstage;

    /**
     *{@link #initialize()} Funkcja wywolywana z maina przy
     * rozpoczeciu dzialania programu
     */
    @FXML
    public void initialize()
    {
        loadmenuwindow();
    }

    /**
     * {@link #loadmenuwindow()} Funkcja ustawiajaca na glownym stage scene
     * z menu gry.Ustawia ona kolor tla lub obraz w tle
     */
    public void loadmenuwindow() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MenuWindow.fxml"));
        Pane pane = null;
        try {
           pane = loader.load();
        } catch(IOException e){e.printStackTrace();}



        if(ReadFromFile.background.equals("jednolite"))
        //mainstage.setStyle("-fx-background-color: #"+ReadFromFile.gamecolor[0]+ReadFromFile.gamecolor[1]+ReadFromFile.gamecolor[2]+";");
        mainstage.setStyle("-fx-background-color: rgb("+ReadFromFile.gamecolor[0]+","+ReadFromFile.gamecolor[1]+","+ReadFromFile.gamecolor[2]+");");
        else
        {
            Image image=new Image("/back.jpg");
            BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,false,false,false,true);
            BackgroundImage backgroundImage = new BackgroundImage(image,BackgroundRepeat.REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,backgroundSize);
            Background background = new Background(backgroundImage);
            mainstage.setBackground(background);
        }


        mainstage.setPrefHeight(ReadFromFile.gamehegiht);
        mainstage.setPrefWidth(ReadFromFile.gamewidth);

        pane.setPrefSize(ReadFromFile.gamewidth,ReadFromFile.gamehegiht);
        MenuController menucontroller = loader.getController();
        menucontroller.setMainController(this);
        menucontroller.setStackPane(mainstage);
        setpane(pane);
    }

    /**
     * {@link #setpane(Pane)}Ustawia obraz na glownym stage
     * @param pane Jest to scena zawierajaca menu
     */
   public void setpane(Pane pane) {
        mainstage.getChildren().clear();
        mainstage.getChildren().add(pane);

    }

    public void setstackpane(SplitPane pane) {
        mainstage.getChildren().clear();
        mainstage.getChildren().add(pane);

    }
}

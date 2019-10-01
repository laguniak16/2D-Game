package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Klasa kontrolera wyswietlajacego wyniki
 */
public class ScoresController {


    private MainController mainController;
    @FXML
    Button button;
    @FXML
    Label lb1;
    @FXML
    Label lb2;
    @FXML
    Label lb3;
    @FXML
    Label lb4;
    @FXML
    Label lb5;

    static boolean readed=false;

    /**
     * {@link #initialize()}  } Funkcja wyswietlajaca wyniki
     */
    public void initialize() throws Exception
    {

        //getfile();
        if (!readed)
        {
            //ReadFromFile.fileScore();
            readed=true;
        }
        if(ReadFromFile.highscores.size()<1)
            lb1.setText("1. ");
        else
        lb1.setText("1. "+ReadFromFile.highscores.get(0));
        if(ReadFromFile.highscores.size()<2)
            lb2.setText("2. ");
        else
        lb2.setText("2. "+ReadFromFile.highscores.get(1));
        if(ReadFromFile.highscores.size()<3)
            lb3.setText("3. ");
        else
        lb3.setText("3. "+ReadFromFile.highscores.get(2));
        if(ReadFromFile.highscores.size()<4)
            lb4.setText("4. ");
        else
        lb4.setText("4. "+ReadFromFile.highscores.get(3));
        if(ReadFromFile.highscores.size()<5)
            lb5.setText("5. ");
        else
        lb5.setText("5. "+ReadFromFile.highscores.get(4));
    }
    /**
     * {@link #close()} ()} Zamyka okno biezace okno
     */
    public void close()
    {
        Stage dialogStage = (Stage) button.getScene().getWindow();
        dialogStage.close();
    }

    /**
     * {@link #setMainController(MainController)} Przeslanie glownego kontrolera
     * @param mainController Ustawia glowny kontroler jako mainController
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }
}

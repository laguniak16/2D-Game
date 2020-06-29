package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Klasa wyswietlajaca okno po zakonczeniu rozgrywki z wynikiem i nickeim
 */
public class GameEndController {


    private MainController mainController;
    @FXML
    Label lb1;
    @FXML
    Label lb2;
    @FXML
    Button button;


    /**
     * {@link #initialize()}
     * Funkcja ustawiajaca nick i wyniki po zakonczeniu rozgrywki
     */
    public void initialize()
    {
        lb1.setText(NickController.nickname);
        lb2.setText(PlayController.finalscore+"");
    }

    /**
     * {@link #close()} Zamyka okno
     */
    public void close()
    {
        PlayController.finalscore=0;
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

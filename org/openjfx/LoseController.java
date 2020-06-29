package org.openjfx;

import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Klasa przywracajaca menu po przegranej rozgrywce
 */
public class LoseController {

@FXML
    Button button;
@FXML
    Label lb1;
@FXML
private MainController mainController;

public void initialize()
{
    lb1.setText(NickController.nickname);

}

    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }

    /**
     * {@link #close()} Metoda zamykajaca plansze z gra po przegranej i wczytujaca glowne menu
     */
public void close()
{
    PlayController.finalscore=0;
    Stage dialogStage = (Stage) button.getScene().getWindow();
    dialogStage.close();
    mainController.loadmenuwindow();
    dialogStage.setWidth(ReadFromFile.gamewidth);
    dialogStage.setHeight(ReadFromFile.gamehegiht);
    PlayController.score = 0;

}


}

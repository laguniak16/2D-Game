package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Klasa kontrolera do wyswietlenia okna z komunikatem o przejsciu na kolejny poziom
 */
public class NextLevController {

@FXML
    Button button;
    /**
     * {@link #close()} ()} Zamyka okno
     */
    public void close()
    {
        Stage dialogStage = (Stage) button.getScene().getWindow();
        dialogStage.close();
    }
}

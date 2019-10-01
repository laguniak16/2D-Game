package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Klasa wysiwetlajaca okno z pomoca do gry
 */
public class HelpController {

    @FXML
    Button button;

    /**
     * {@link #close()}Zamyka okno z podaniem nicku po nacisnieciu anuluj
     */
    public void close()
    {
        Stage dialogStage = (Stage) button.getScene().getWindow();
        dialogStage.close();
    }

}

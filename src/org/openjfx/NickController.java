package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Kontroler do okienka z podaniem nicku gracza
 */
public class NickController {


    @FXML
   public TextField nick;
    static String nickname;


    @FXML
    Button anuluj;
    @FXML
    Button okej;
    private MainController mainController;
    private StackPane mainstage;


    /**
     * {@link #initialize()}
     * Funkcja ustwiajaca przycisk ok w okienku z nickiem na nieaktywny
     */
    public void initialize()
    {
            okej.setDisable(true);
        nick.textProperty().addListener(
                (observable, old_value, new_value) -> {
                    if(new_value.contains(" "))
                    {
                        nick.setText(old_value);
                    }
                }
        );
    }

    /**
     * {@link #setMainController(MainController)} Przeslanie glownego kontrolera
     * @param mainController Ustawia glowny kontroler jako mainController
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }
    /**
     * {@link #setMainController(MainController)} Przeslanie glownego staga
     * @param mainstage Ustawia glowny stage jako mainstage
     */
    public void setStackPane(StackPane mainstage) {
        this.mainstage = mainstage;
    }

    /**
     * {@link #cancel()} Zamyka okno z podaniem nicku po nacisnieciu anuluj
     */
    public void cancel()
    {
        Stage dialogStage = (Stage) anuluj.getScene().getWindow();
        dialogStage.close();
    }
    /**
     * {@link #play()} Funkcja uruchamiajaca okienko z plansza gry
     */
    public void play()
    {
        nickname = nick.getText();
        Stage dialogStage = (Stage) okej.getScene().getWindow();
        dialogStage.close();

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PlayWindow.fxml"));
        Pane pane = new Pane();
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
            PlayController playcontroller = loader.getController();
            playcontroller.setMainController(mainController);
            playcontroller.setPane(mainstage);
            playcontroller.setPane(pane);
        mainController.setpane(pane);
    }
    /**
     * {@link #writenick()} ()} Funkcja sprawdzajaca czy zostal podany nick
     */
    public void writenick()
    {
        if(nick.getText().isEmpty())
            okej.setDisable(true);
        else
            okej.setDisable(false);
    }

}

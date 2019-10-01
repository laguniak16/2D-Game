package org.openjfx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * Klasa do wyboru poziomu trudnosci
 */
public class DifficultyController {

    @FXML
    ChoiceBox chbox;
    @FXML
    Button button1;

    static String difficulty="Latwy";
    private MainController mainController;

    /**
     * {@link #initialize()}Funkcja ustawiajaca mozliwe poziomy trudnosci
     * w zaleznosci od tego ile ma byc poziomow
     */
   public void initialize()
   {
       if(ReadFromFile.numberofsifficultylevel == 3) {
           chbox.setItems(FXCollections.observableArrayList(
                   "Latwy", "Sredni", "Trudny "
           ));
           chbox.setValue("Latwy");
       }
       else if(ReadFromFile.numberofsifficultylevel == 2)
       {
           chbox.setItems(FXCollections.observableArrayList(
                   "Latwy","Trudny "
           ));
           chbox.setValue("Latwy");
       }
       else if(ReadFromFile.numberofsifficultylevel == 4)
       {
           chbox.setItems(FXCollections.observableArrayList(
                   "Latwy","Sredni","Trudny","Bardzo Trudny"
           ));
           chbox.setValue("Latwy");
       }
       else if(ReadFromFile.numberofsifficultylevel == 5)
       {
           chbox.setItems(FXCollections.observableArrayList(
                   "Bardzo Latwy","Latwy","Sredni","Trudny","Bardzo Trudny"
           ));
           chbox.setValue("Bardzo Latwy");
       }

   }
   public void accept(){
       button1.setOnAction(e->getChoice(chbox));
   }

    /**
     *{@link #setMainController(MainController)} Przeslanie glownego kontrolera
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }

    /**
     *{@link #getChoice(ChoiceBox)}Funkcja zapisujaca wybrany poziom trudnosci
     */
   private void getChoice(ChoiceBox<String> choiceBox)
   {
       difficulty = choiceBox.getValue();
       Stage dialogStage = (Stage) button1.getScene().getWindow();
       dialogStage.close();
       mainController.loadmenuwindow();
   }

    /**
     * {@link #close()}Funkcja zamykajaca okno z wyborem poziomu trudnosci
     */
   public void close()
   {
       Stage dialogStage = (Stage) button1.getScene().getWindow();
       dialogStage.close();
   }


}

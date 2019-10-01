package org.openjfx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

/**
 * Klasa sterujaca Menu Gry
 */
public class MenuController {


    @FXML
    private MainController mainController;
    private StackPane mainstage;
    @FXML
    Button cancel;
    @FXML
    Button button2;
    @FXML
    Button button3;
    @FXML
    Button button4;
    @FXML
    Button button1;
    @FXML
    Label menuImage;
    @FXML
    Label difficulty;

    /**
     * {@link #initialize()}Metoda ustawiajaca
     * rozmiary przyciskow w menu gry
     */
    public void initialize()
    {
        button1.setLayoutX((float)ReadFromFile.gamewidth/2-button1.getPrefWidth()/2);
        button2.setLayoutX((float)ReadFromFile.gamewidth/2-button2.getPrefWidth()/2);
        button3.setLayoutX((float)ReadFromFile.gamewidth/2-button3.getPrefWidth()/2);
        button4.setLayoutX((float)ReadFromFile.gamewidth/2-button4.getPrefWidth()/2);
        cancel.setLayoutX((float)ReadFromFile.gamewidth/2-cancel.getPrefWidth()/2);
        menuImage.setLayoutX((float)ReadFromFile.gamewidth/2-menuImage.getPrefWidth()/2);
        difficulty.setLayoutX((float)ReadFromFile.gamewidth/2-difficulty.getPrefWidth()/2);
        difficulty.setText("Poziom: "+DifficultyController.difficulty);
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
     * {@link #close()} Zamyka okno menu po nasisnieciu klawisza wyjscie
     */
    public void close()
    {
        if(GetDataFromServer.socket != null && GetDataFromServer.socket.isConnected()) {
            try {

                GetDataFromServer.socket.getInputStream().skip(GetDataFromServer.socket.getInputStream().available());
                OutputStream os = GetDataFromServer.socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                pw.println("LOGOUT");
                    Stage dialogStage = (Stage) mainstage.getScene().getWindow();
                    dialogStage.close();
            } catch (IOException e) {
                System.out.println("Nie udało sie zamkonczyc polaczenia z klientem");
                System.out.println(e);
            }

        }
        else
        {
            Stage dialogStage = (Stage) mainstage.getScene().getWindow();
            dialogStage.close();
        }
    }

    /**
     * {@link #start()} Funkcja ktora jest aktywowana po nacisnieciu klawisza
     * start.Tworzy ona nowego stage na ktorym wywolywane jest okno do podania
     * nicku gracza.
     */
    @FXML
    public void start(){

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NickWindow.fxml"));
            Stage stage = new Stage();
            Pane pane = null;
            try {
                pane = loader.load();
                stage.setTitle("Nick");
                stage.setScene(new Scene(pane));
                stage.initModality(Modality.APPLICATION_MODAL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            NickController nickController = loader.getController();
            nickController.setMainController(mainController);
            nickController.setStackPane(mainstage);
            stage.setResizable(false);
            stage.show();

    }
    public void resize()
    {
    }

    /**
     * {@link #chooseDiff()}Metoda wywolujaca
     * okieko z wyborem poziomu trudnosci
     */
    public void chooseDiff(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DifficultyWindow.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try {
            pane = loader.load();
            stage.setTitle("Poziom Trudności");
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DifficultyController difficultyController = loader.getController();
        difficultyController.setMainController(mainController);
        stage.setResizable(false);
        stage.show();

    }
    /**
     * {@link #scores()} Metoda wywolujaca
     * okieko wyswietlajace 5 najlepszych wynikow
     */
    public void scores(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ScoresWindow.fxml"));
        Stage stage = new Stage();
        AnchorPane pane = null;
        try {
            pane = loader.load();
            stage.setTitle("Wyniki");
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoresController scoresController = loader.getController();
        scoresController.setMainController(mainController);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * {@link #help()} Metoda wywolujaca
     * okieko wyswietlajaca instrukcje gry
     */
    @FXML
    public void help(){

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("HelpWindow.fxml"));
        Stage stage = new Stage();
        Pane pane = null;
        try {
            pane = loader.load();
            stage.setTitle("Pomoc");
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.show();

    }
}

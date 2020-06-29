package org.openjfx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Optional;


/**
 * Glowna klasa rozpoczynajaca dzialanie calego programu
 */
public class Main extends Application {

    /**
     *  {@link #start(Stage)} funkcja wywolywana z maina
     *  ustawia glowna scene na mainwindow i laduje glownego
     *  stage na ktorym beda wyswietlane wszystkie kolejne sceny
     */
    public static boolean offline = false;
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainWindow.fxml"));
        System.out.println(loader.getLocation());
        StackPane stackpane = loader.load();
        Scene scene = new Scene(stackpane, ReadFromFile.gamewidth, ReadFromFile.gamehegiht);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(ReadFromFile.gamename);
        stage.show();
    }


    /** {@link #main(String[])}  funkcja wywolywana z maina,probuje nawiazac polaczenie z serwerem
     *  @param args argument maina
     */
    public static void main(String[] args) throws Exception {



        GetDataFromServer.runGame(connectToServer());
                LevelDescribe lv = new LevelDescribe();
                lv.main(args);
                launch(args);


    }



    private static Socket connectToServer() {
        try {
            BufferedReader br ;
            Socket serverSocket = new Socket("localhost", 8080);
            OutputStream os = serverSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("LOGIN");
            InputStream is = serverSocket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            if(br.readLine().contains("LOGGED_IN")){
                return serverSocket;
            }
            else{
                return null;
            }
        }
        catch (Exception e) {
            System.out.println("Nie udało sie nawiązac połączenia");
            System.out.println("error: "+e);
        }
        return null;
    }
}

package org.openjfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.util.Random;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Collections;

import static org.openjfx.ReadFromFile.*;


/**
 * Kontroler do obslugi okna z plansza gry
 */
public class PlayController {

    @FXML
    public MainController mainController;
    Timeline time = new Timeline();
    @FXML
    private StackPane mainstage;

    @FXML
    Button cancel;
    @FXML
    Button start;
    @FXML
    Button pause;


    @FXML
    private Pane Game;
    @FXML
    private Label lb1;
    @FXML
    private Label lb2;
    @FXML
    private Pane plansza;
    @FXML
    private Separator separate;
    @FXML
    private  Button next;
    @FXML
    private  Label lb3;
    @FXML
    private Label lb4;


    private int seconds = ReadFromFile.gametime;
    public static boolean ifpause = true;
    static int count = 0;
    public static int score = 0;
    public static int scoreGot = 0;
    static int finalscore = 0;
    static int shieldvalue;
    public static int currentlevel;
    public static int currenttime=0;
    public static boolean timestop = false;
    public static boolean once=true;
    private int a=0;
    private int timetobonus;
    static int timeleft;
    boolean isIfpause =false;



    double gameHeight = ReadFromFile.gamehegiht;
    double gameWidth = ReadFromFile.gamewidth;

    @FXML
    Enemy enemy;
    Enemy enemybonus;
    /**
     * {@link #initialize()} Metoda ustawiajaca
     * poczatkowe miejsce wystepowania
     * przyciskow oraz initializujaca obiekt do zestrzelenia
     */
    public void initialize() {

        shieldValue();
        if(ReadFromFile.levelsnumbering==0)
            currentlevel=0;
        else currentlevel=1;

        if(ReadFromFile.gameobjects.equals("figuryGeometryczne")) {
            if (ReadFromFile.gamefigure.equals("kwadraty") || ReadFromFile.gamefigure.equals("prostokaty")) {
                enemy = new EnemyRectangle(this);
            } else if (ReadFromFile.gamefigure.equals("kolka")) {
                enemy = new EnemyCircle();
            } else if (ReadFromFile.gamefigure.equals("trojkaty")) {
                enemy = new EnemyTriangle();
            }
        }
        else
        {
            enemy = new EnemyPicture();
        }

        enemybonus= new BonusBox();
        //finalscore=0;


        Game.setPrefSize(ReadFromFile.gamewidth, ReadFromFile.gamehegiht);

        pause.setDisable(true);
        lb2.setText("Nastepny: " + + score+"/"+LevelDescribe.needPoints1);
        lb3.setText("Punkty:" + finalscore);
        lb4.setText("Wartosc za tarcze: "+shieldvalue);
        start.setLayoutX(0);
        cancel.setLayoutX(start.getPrefWidth()+pause.getPrefWidth());
        pause.setLayoutX(start.getPrefWidth());
        lb1.setLayoutX(start.getPrefWidth()+pause.getPrefWidth()+cancel.getPrefWidth());
        lb2.setLayoutX(start.getPrefWidth()+pause.getPrefWidth()+cancel.getPrefWidth()+lb1.getPrefWidth());
        lb3.setLayoutX(start.getPrefWidth()+pause.getPrefWidth()+cancel.getPrefWidth()+lb1.getPrefWidth()+lb2.getPrefWidth());
        lb4.setLayoutX(start.getPrefWidth()+pause.getPrefWidth()+cancel.getPrefWidth()+lb1.getPrefWidth()+lb2.getPrefWidth()+lb3.getPrefWidth());


        next.setLayoutX(0);
        next.setLayoutY(50);

        separate.setLayoutX(0);
        start.setLayoutY(ReadFromFile.gamehegiht-2*start.getPrefHeight());
        cancel.setLayoutY(ReadFromFile.gamehegiht - 2*cancel.getPrefHeight());
        pause.setLayoutY(ReadFromFile.gamehegiht - 2*pause.getPrefHeight());
        lb1.setLayoutY(ReadFromFile.gamehegiht - 3*lb1.getPrefHeight());
        lb2.setLayoutY(ReadFromFile.gamehegiht - 3*lb2.getPrefHeight());
        lb3.setLayoutY(ReadFromFile.gamehegiht - 3*lb3.getPrefHeight());
        lb4.setLayoutY(ReadFromFile.gamehegiht - 3*lb4.getPrefHeight());
        separate.setLayoutY(ReadFromFile.gamehegiht - 2*start.getPrefHeight());
        separate.setPrefWidth(ReadFromFile.gamewidth);
        setDifficulty();
        setLevel();
        next.setVisible(false);
        currenttime = seconds;
        lb1.setText("Czas: "+seconds);
    }

    /**
     * {@link #setMainController(MainController)} Przeslanie glownego kontrolera
     *
     * @param mainController Ustawia glowny kontroler jako mainController
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@link #setPane(Pane)}Ustawia obraz na glownym stage
     * @param mainstage glowny stage
     */
    public void setPane(StackPane mainstage) {
        this.mainstage = mainstage;
    }

    /**
     * {@link #setPane(Pane)} Metoda przesylajaca
     * glowna plansze gry na ktorej wyswietlane
     * sa obiekty
     */
    public void setPane(Pane pane) {
        plansza = pane;
    }

    @FXML
    /**
     * {@link #cancel()} Zamyka okno z podaniem nicku po nacisnieciu anuluj
     */
    public void close()
    {
        Stage dialogStage = (Stage) cancel.getScene().getWindow();
        mainController.loadmenuwindow();
        dialogStage.setWidth(ReadFromFile.gamewidth);
        dialogStage.setHeight(ReadFromFile.gamehegiht);
        PlayController.score = 0;
        time.stop();
        timestop=false;
        finalscore=0;

    }

    /**
     * {@link #resize()}Metoda skalujaca obiekty
     * do wielkosci planszy
     */
    public void resize() {

        Stage dialogStage = (Stage) Game.getScene().getWindow();
        dialogStage.widthProperty().addListener((obs, oldVal, newVal) -> {

                Game.prefWidthProperty().bind(dialogStage.widthProperty());
                enemy.setWidth((int) dialogStage.getWidth());


                enemybonus.setWidth((int) dialogStage.getWidth());//

                start.setLayoutX(0);
                cancel.setLayoutX(start.getPrefWidth() + pause.getPrefWidth());
                pause.setLayoutX(start.getPrefWidth());
                lb1.setLayoutX(start.getPrefWidth() + pause.getPrefWidth() + cancel.getPrefWidth());
                lb2.setLayoutX(start.getPrefWidth() + pause.getPrefWidth() + cancel.getPrefWidth() + lb1.getPrefWidth());
                lb3.setLayoutX(start.getPrefWidth() + pause.getPrefWidth() + cancel.getPrefWidth() + lb1.getPrefWidth() + lb2.getPrefWidth());
                lb4.setLayoutX(start.getPrefWidth() + pause.getPrefWidth() + cancel.getPrefWidth() + lb1.getPrefWidth() + lb2.getPrefWidth() + lb3.getPrefWidth());

                separate.setLayoutX(0);
                separate.setPrefWidth(Game.getWidth());


            enemybonus.setX((Game.getWidth() * enemybonus.setLayX()));//


                enemy.setX((Game.getWidth() * enemy.setLayX()));

                enemy.scale((ReadFromFile.primarytargetwidth * ReadFromFile.gamewidth) / 100 * ((Game.getWidth()) / ReadFromFile.gamewidth) * Game.getHeight() / ReadFromFile.gamehegiht);
                gameHeight = Game.getHeight();
        });

        dialogStage.heightProperty().addListener((obs, oldVal, newVal) -> {

            enemy.setHeight((int) dialogStage.getHeight());//czy to cos zmienia?

            //enemybonus.setHeight((int) dialogStage.getWidth());

            start.setLayoutY(Game.getHeight()-2*start.getPrefHeight());
            cancel.setLayoutY(Game.getHeight() - 2*cancel.getPrefHeight());
            pause.setLayoutY(Game.getHeight() - 2*pause.getPrefHeight());
            lb1.setLayoutY(Game.getHeight() - 3*lb1.getPrefHeight());
            lb2.setLayoutY(Game.getHeight() - 3*lb2.getPrefHeight());
            lb3.setLayoutY(Game.getHeight() - 3*lb3.getPrefHeight());
            lb4.setLayoutY(Game.getHeight() - 3*lb4.getPrefHeight());
            separate.setLayoutY(Game.getHeight() - 2*start.getPrefHeight());

            enemybonus.setY((dialogStage.getHeight() * enemybonus.setLayY()));//


            enemy.setY((dialogStage.getHeight() * enemy.setLayY()));
            enemy.scale((ReadFromFile.primarytargetwidth * ReadFromFile.gamewidth) / 100 * ((Game.getWidth()) / ReadFromFile.gamewidth) * Game.getHeight() / ReadFromFile.gamehegiht);
            Game.prefHeightProperty().bind(dialogStage.heightProperty());
            gameWidth = Game.getWidth();
        });
    }


    /**
     * {@link #startGame()}Metoda rozpoczynajaca
     * gre po nacisnieciu przycisku start
     */
    public void startGame() {
        setScore();
        addPoints();
        enemy.setPane(plansza);
        enemybonus.setPane(plansza);
        enemy.initialize();
        start.setDisable(true);
        pause.setDisable(false);

        if(!timestop) {
            timer();
            timestop=true;
        }
        else {
            ((BonusBox)enemybonus).setStart();
            time.playFromStart();
        }

    }

    /**
     * {@link #timer}Metoda odliczajaca
     * czas gry
     */
    public void timer() {
        time.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enemy.setTime(seconds);
                if (seconds <= 0) {
                    time.stop();
                    pause.setDisable(true);
                    enemy.setPause(true);
                    enemybonus.setPause(true);//
                    lostgame();
                }
                if (seconds > 0&&score<scoreGot)
                {
                    seconds--;
                    timeleft=seconds;

                    Random rand = new Random();
                    timetobonus = rand.nextInt(3);
                    if(timetobonus==0 && a==0)
                    {
                        enemybonus.initialize();
                        a=1;
                    }
                }
                lb1.setText("Czas: " + seconds);
               /* if (count % 2 == 0) {
                    if (ReadFromFile.gamefigure.equals("kwadraty")|| ReadFromFile.gamefigure.equals("prostokaty")) {
                        enemyRectangle.setDisable();
                    }
                    else if (ReadFromFile.gamefigure.equals("kolka")) {
                        enemyCircle.setDisable();
                    }
                    else if(ReadFromFile.gamefigure.equals("trojkaty"))
                    {
                        enemyTriangle.setDisable();
                    }
                }*/
            }

        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /**
     * {@link #pause()}Metoda pozwalajaca
     * zapauzowac gre
     */
    public void pause() {
        if (ifpause == true) {
            enemy.setPause(true);
            enemybonus.setPause(true);//
            isIfpause =true;

            ifpause = false;
            time.stop();
            pause.setText("UnPause");

        } else {
            enemy.setPause(false);
            enemybonus.setPause(false);//

            ((BonusBox)enemybonus).setStart();
            ifpause = true;
            time.play();
            pause.setText("Pause");
            isIfpause =false;

        }
    }

    /**
     * {@link #windowSize()}Metoda zczytujaca
     * aktualna szerokosc planszy
     */
    public void windowSize() {
        Stage dialogStage = (Stage) Game.getScene().getWindow();
        enemy.gameWidth(dialogStage.getWidth());
        enemy.gameHeight(dialogStage.getHeight());
        enemybonus.gameWidth(dialogStage.getWidth());
        enemybonus.gameHeight(dialogStage.getHeight());

    }

    /**
     * {@link #setDifficulty()}Metoda ustawiajaca
     * czas rozgrywki na podstawie zczytanej ilosci
     * poziomow
     */
    public void setDifficulty()
    {
        int diff2 =seconds - (seconds * ReadFromFile.difficultyscale) / 100;
        int diff3 = diff2-(diff2 * ReadFromFile.difficultyscale) / 100;
        int diff4 = diff3-(diff3 * ReadFromFile.difficultyscale) / 100;
        if(ReadFromFile.numberofsifficultylevel == 2) {
            if(DifficultyController.difficulty=="Latwy")
                seconds=ReadFromFile.gametime;
            else
                seconds = seconds - (seconds * ReadFromFile.difficultyscale) / 100;
        }
        else if(ReadFromFile.numberofsifficultylevel == 3)
        {
            if(DifficultyController.difficulty=="Latwy")
                seconds=ReadFromFile.gametime;
            else if(DifficultyController.difficulty=="Sredni")
                seconds = seconds - (seconds * ReadFromFile.difficultyscale) / 100;
            else
                seconds=diff2-(diff2 * ReadFromFile.difficultyscale) / 100;
        }
        else if(ReadFromFile.numberofsifficultylevel == 4)
        {
            if(DifficultyController.difficulty=="Latwy")
            seconds=ReadFromFile.gametime;
        else if(DifficultyController.difficulty=="Sredni")
            seconds=seconds-(seconds*ReadFromFile.difficultyscale)/100;
        else if(DifficultyController.difficulty=="Trudny")
            seconds=diff2-(diff2 * ReadFromFile.difficultyscale) / 100;
        else
            seconds=diff3-(diff3 * ReadFromFile.difficultyscale) / 100;

        }
        else if(ReadFromFile.numberofsifficultylevel == 5)
        {
            if(DifficultyController.difficulty=="Bardzo Latwy")
                seconds=ReadFromFile.gametime;
            else if(DifficultyController.difficulty=="Latwy")
                seconds=seconds-(seconds*ReadFromFile.difficultyscale)/100;
            else if(DifficultyController.difficulty=="Sredni")
                seconds=diff2-(diff2 * ReadFromFile.difficultyscale) / 100;
            else if(DifficultyController.difficulty=="Trudny")
                seconds=diff3-(diff3 * ReadFromFile.difficultyscale) / 100;
            else
                seconds=diff4-(diff4 * ReadFromFile.difficultyscale) / 100;

        }

    }

    /**
     * {@link #setLevel()} Metoda ustawiajaca
     * ilosc potrzebnych punktow do przejscia
     * na kolejny poziom
     */
    public void setLevel()
    {
        if(ReadFromFile.levelsnumber==3)
        {
            if(ReadFromFile.levelsnumbering==0)
            {
                if (currentlevel == 0)
                    scoreGot = LevelDescribe.needPoints1;
                else if (currentlevel == 1)
                    scoreGot = LevelDescribe.needPoints2;
                else if (currentlevel == 2)
                    scoreGot = LevelDescribe.needPoints3;
                else
                    scoreGot = LevelDescribe.needPoints4;
            }
            if(ReadFromFile.levelsnumbering==1)
            {
                if (currentlevel == 1)
                    scoreGot = LevelDescribe.needPoints1;
                else if (currentlevel == 2)
                    scoreGot = LevelDescribe.needPoints2;
                else if (currentlevel == 3)
                    scoreGot = LevelDescribe.needPoints3;
            }

        }
        else if(ReadFromFile.levelsnumber==4)
        {
            if(ReadFromFile.levelsnumbering==0)
            {
                if (currentlevel == 0)
                    scoreGot = LevelDescribe.needPoints1;
                else if (currentlevel == 1)
                    scoreGot = LevelDescribe.needPoints2;
                else if (currentlevel == 2)
                    scoreGot = LevelDescribe.needPoints3;
                else if (currentlevel == 3)
                    scoreGot = LevelDescribe.needPoints4;
                else
                    scoreGot = LevelDescribe.needPoints5;
            }
            if(ReadFromFile.levelsnumbering==1)
            {
                if (currentlevel == 1)
                    scoreGot = LevelDescribe.needPoints1;
                else if (currentlevel == 2)
                    scoreGot = LevelDescribe.needPoints2;
                else if (currentlevel == 3)
                    scoreGot = LevelDescribe.needPoints3;
                else
                    scoreGot = LevelDescribe.needPoints4;
            }

        }
        else if(ReadFromFile.levelsnumber==5)
        {
            if(ReadFromFile.levelsnumbering==0)
            {
                if (currentlevel == 0)
                    scoreGot = LevelDescribe.needPoints1;
                else if (currentlevel == 1)
                    scoreGot = LevelDescribe.needPoints2;
                else if (currentlevel == 2)
                    scoreGot = LevelDescribe.needPoints3;
                else if (currentlevel == 3)
                    scoreGot = LevelDescribe.needPoints4;
                else if (currentlevel == 4)
                    scoreGot = LevelDescribe.needPoints5;
                else
                    scoreGot = LevelDescribe.needPoints6;
            }
            if(ReadFromFile.levelsnumbering==1)
            {
                if (currentlevel == 1)
                    scoreGot = LevelDescribe.needPoints1;
                else if (currentlevel == 2)
                    scoreGot = LevelDescribe.needPoints2;
                else if (currentlevel == 3)
                    scoreGot = LevelDescribe.needPoints3;
                else if (currentlevel == 4)
                    scoreGot = LevelDescribe.needPoints4;
                else
                    scoreGot = LevelDescribe.needPoints5;
            }

        }
    }

    /**
     * {@link #goNext()}Metoda opisujaca
     * zachowanie sie planszy przy przejsciu
     * na kolejny poziom
     */
    public void goNext()
    {
        score=0;
        scoreGot=0;
        setDifficulty();
        pause.setDisable(true);
        enemy.setDisable();
        enemybonus.setDisable();//
        finalscore+=timeleft;

        if(once) {
            if (ReadFromFile.levelsnumbering == 0)
                currentlevel = 0;
            else
                currentlevel = 1;
            once = false;
        }

        if(currentlevel==0) {
            currentlevel = 1;
        }
        else if(currentlevel==1)
        {
            currentlevel=2;
        }
        else if(currentlevel==2)
        {
            currentlevel=3;
        }
        else if(currentlevel==3)
        {
            if(ReadFromFile.levelsnumber==3)
            {
                mainController.loadmenuwindow();
                endGame();
                timestop =false;
                score =0;
            }
            currentlevel=4;
        }
        else if(currentlevel==4)
        {
            if(ReadFromFile.levelsnumber==4)
            {
                mainController.loadmenuwindow();
                endGame();
                score =0;
                timestop =false;
            }
            currentlevel=5;
        }
        else if(currentlevel==5)
        {
            if(ReadFromFile.levelsnumber==5)
            {
                mainController.loadmenuwindow();
                endGame();
                score =0;
                timestop =false;
            }
            currentlevel=6;
        }
        else if(currentlevel==6)
        {
            if(ReadFromFile.levelsnumber==6)
            {
                mainController.loadmenuwindow();
                endGame();
                score =0;
                timestop =false;
            }
            currentlevel=7;
        }
        setLevel();
        start.setDisable(false);
        seconds = currenttime;
        lb1.setText("Czas: " + seconds);
        lb2.setText("Nastepny: " + + score+"/"+scoreGot);
        lb3.setText("Punkty: " + finalscore);
        lb4.setText("Wartosc za tarcze: "+shieldvalue);

    }

    /**
     * {@link #nextLev()}Metoda wywolujaca okno
     * do przejcia na kolejny poziom
     */
    public void nextLev()
    {
        goNext();
        if(timestop) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("NextLevWindow.fxml"));
            Stage stage = new Stage();
            AnchorPane pane = null;
            try {
                pane = loader.load();
                stage.setTitle("Next");
                stage.setScene(new Scene(pane));
                stage.initModality(Modality.APPLICATION_MODAL);
            } catch (IOException e) {
                e.printStackTrace();
            }

            NextLevController nextLevController = loader.getController();
            stage.setResizable(false);
            stage.show();
        }

    }
    /**
     * {@link #endGame()} Metoda konczaca
     * gre zapisujaca wyniki do pliku lokalnego
     * lub  wysylajaca go na serwer
     */
    public void endGame()
    {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GameEndWindow.fxml"));
        Stage stage = new Stage();
        AnchorPane pane = null;
        try {
            pane = loader.load();
            stage.setTitle("Next");
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameEndController gameEndController = loader.getController();
        gameEndController.setMainController(mainController);
        stage.setResizable(false);
        stage.show();



            Collections.sort(highscores);
            Collections.reverse(highscores);
            Highscore highscore = new Highscore(NickController.nickname, finalscore, ReadFromFile.levelsnumber);
            boolean done = true;

            for (int i = 0; i < highscores.size(); i++) {
                String str = highscores.get(i) + "";
                String[] strings = str.split(" ");
                if (Integer.parseInt(strings[1]) < finalscore && done) {
                    highscores.remove(4);
                    highscores.add(highscore);
                    done = false;
                }
            }
            Collections.sort(highscores);
            Collections.reverse(highscores);




        if(GetDataFromServer.socket==null) {
            try {
                FileWriter writer = new FileWriter("src/wyniki.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(writer);
                for (int i = 0; i < highscores.size(); i++) {
                    bufferedWriter.write(highscores.get(i) + " ");
                    bufferedWriter.newLine();
                }
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GetDataFromServer.addScore(NickController.nickname,finalscore,ReadFromFile.levelsnumber);
       // finalscore = 0;
    }

    /**
     * {@link #setScore()} Metoda sluzaca
     * do wyswietlania i aktualizowania wyniku
     */
    public void setScore()
    {

        mainstage.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent e) {
                if(ifpause)
                    shieldValue();
            if(timestop) {
                if (score >= scoreGot) {
                    nextLev();
                    time.stop();
                    pause.setDisable(true);
                    lb2.setText("Nastepny: " + scoreGot + "/" + scoreGot);
                }

                if (score < scoreGot) {
                    if(score==0)
                    lb2.setText("Nastepny: " + 0 + "/" + scoreGot);
                    else
                        lb2.setText("Nastepny: " + score + "/" + scoreGot);

                }
                    lb4.setText("Wartosc za tarcze: "+shieldvalue);
            }

            lb3.setText("Punkty:" + finalscore);


        }});

        //mainstage.setOnMouseClicked(e-> {
       // });

    }
    /**
     * {@link #shieldValue()} Metoda ustawiajaca
     * wartosc za zestrzelenie przeciwnika
     */
    public void shieldValue()
    {
        Random rand = new Random();
        shieldvalue = rand.nextInt(shieldvalue2 - shieldvalue1+1) + shieldvalue1;
    }
    /**
     * {@link #goNext()}Metoda odejmujaca punkty
     * za zly strzal
     */
    public void addPoints()
    {
            plansza.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent e) {
                    if (!isIfpause)
                    PlayController.finalscore = PlayController.finalscore - PlayController.shieldvalue;
                }
            });

    }
    /**
     * {@link #lostgame()} Metoda konczaca gre
     * po porazce
     */
    public void lostgame()
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LoseWindow.fxml"));
        Stage stage = new Stage();
        AnchorPane pane = null;
        try {
            pane = loader.load();
            stage.setTitle("Lost Game");
            stage.setScene(new Scene(pane));
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoseController loseController = loader.getController();
        loseController.setMainController(mainController);
        stage.setResizable(false);
        stage.show();
        time.stop();
        timestop=false;
        //finalscore=0;
    }

}




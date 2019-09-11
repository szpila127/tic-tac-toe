package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeApplication extends Application {

    private static Image back = new Image("file:src/main/resources/back.png");
    public static Board board = new Board();
    public static boolean turnX = true;
    public static boolean possibleMove = true;
    public static Label label = new Label();
    public static Label vsWho = new Label();
    public static Label counter = new Label();
    public static Counter playerX = new Counter(0);
    public static Counter playerO = new Counter(0);
    public static boolean vsComputer = true;

    private static Parent createScene() {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Pane root = new Pane();
        root.setPrefSize(600, 700);
        root.setBackground(background);

        Button reset = new Button();
        reset.setText("RESET");
        reset.setFont(new Font("Arial", 20));
        reset.setLayoutX(450);
        reset.setLayoutY(601);
        reset.setPrefSize(150, 49);
        reset.setOnAction(action -> {
            State.gameReset();
        });

        Button vsComp = new Button();
        vsComp.setText("NEW GAME vs. COMP");
        vsComp.setWrapText(true);
        vsComp.setFont(new Font("Arial", 15));
        vsComp.setLayoutX(0);
        vsComp.setLayoutY(601);
        vsComp.setPrefSize(150, 49);
        vsComp.setOnAction(action -> {
            vsComputer = true;
            playerO.setCount(0);
            playerX.setCount(0);
            counter.setText("X  [" + playerX.getCount()
                    + "] : [" + playerO.getCount() + "]  O");
            vsWho.setText("YOU vs. COMPUTER");
            State.gameReset();
        });

        Button vsPlay = new Button();
        vsPlay.setText("NEW GAME vs. PLAYER");
        vsPlay.setWrapText(true);
        vsPlay.setFont(new Font("Arial", 15));
        vsPlay.setLayoutX(0);
        vsPlay.setLayoutY(651);
        vsPlay.setPrefSize(150, 49);
        vsPlay.setOnAction(action -> {
            vsComputer = false;
            playerX.setCount(0);
            playerO.setCount(0);
            counter.setText("X  [" + playerX.getCount()
                    + "] : [" + playerO.getCount() + "]  O");
            vsWho.setText("YOU vs. 2nd PLAYER");
            State.gameReset();
        });

        Button exit = new Button();
        exit.setText("EXIT");
        exit.setFont(new Font("Arial", 20));
        exit.setLayoutX(450);
        exit.setLayoutY(651);
        exit.setPrefSize(150, 49);
        exit.setOnAction(action -> {
            Platform.exit();
        });

        label.setText("X TURN");
        label.setTextFill(Color.RED);
        label.setFont(new Font("Arial", 28));
        label.layoutXProperty().bind(root.widthProperty().subtract(label.widthProperty()).divide(2));
        label.setLayoutY(605);

        counter.setText("X  [" + playerX.getCount() + "] : [" + playerO.getCount() + "]  O");
        counter.setTextFill(Color.BLACK);
        counter.setFont(new Font("Arial", 23));
        counter.layoutXProperty().bind(root.widthProperty().subtract(counter.widthProperty()).divide(2));
        counter.setLayoutY(638);

        vsWho.setText("YOU vs. COMPUTER");
        vsWho.setTextFill(Color.GREEN);
        vsWho.setFont(new Font("Arial", 21));
        vsWho.layoutXProperty().bind(root.widthProperty().subtract(vsWho.widthProperty()).divide(2));
        vsWho.setLayoutY(671);

        root.getChildren().addAll(Arrays.stream(board.fields).flatMap(Arrays::stream).collect(Collectors.toList()));
        root.getChildren().addAll(reset, exit, vsComp, vsPlay, label, counter, vsWho);
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(TicTacToeApplication.createScene());

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
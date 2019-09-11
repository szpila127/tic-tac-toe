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
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TicTacToeApplication extends Application {

    private static Image back = new Image("file:src/main/resources/back.png");
    public static Board board = new Board();
    public static boolean turnX = true;
    public static boolean possibleMove = true;
    public static Label label = new Label();
    public static Label counter = new Label();
    public static Counter playerX = new Counter(0);
    public static Counter playerO = new Counter(0);
    public static boolean vsComputer = false;

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

        Button vsComputer = new Button();
        vsComputer.setText("vs. COMP");
        vsComputer.setFont(new Font("Arial", 20));
        vsComputer.setLayoutX(0);
        vsComputer.setLayoutY(601);
        vsComputer.setPrefSize(150, 49);
        vsComputer.setOnAction(action -> {
            TicTacToeApplication.vsComputer = true;
        });

        Button vsPlayer = new Button();
        vsPlayer.setText("vs. PLAYER");
        vsPlayer.setFont(new Font("Arial", 20));
        vsPlayer.setLayoutX(0);
        vsPlayer.setLayoutY(651);
        vsPlayer.setPrefSize(150, 49);
        vsPlayer.setOnAction(action -> {
            TicTacToeApplication.vsComputer = false;
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

        label.setText("CROSS TURN");
        label.setTextFill(Color.RED);
        label.setFont(new Font("Arial", 35));
        label.layoutXProperty().bind(root.widthProperty().subtract(label.widthProperty()).divide(2));
        label.setLayoutY(605);

        counter.setText("CROSS[" + playerX.getCount() + "] : [" + playerO.getCount() + "]CIRCLE");
        counter.setTextFill(Color.BLACK);
        counter.setFont(new Font("Arial", 25));
        counter.layoutXProperty().bind(root.widthProperty().subtract(counter.widthProperty()).divide(2));
        counter.setLayoutY(655);

        root.getChildren().addAll(Arrays.stream(board.fields).flatMap(Arrays::stream).collect(Collectors.toList()));
        root.getChildren().addAll(reset, exit, vsComputer, vsPlayer, label, counter);
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
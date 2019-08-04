package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootApplication
public class TicTacToeApplication extends Application {

    private static Image back = new Image("file:src/main/resources/back.png");
    private static Board board = new Board();
    static boolean turnX = true;
    static boolean possibleMove = true;

    private static Parent createScene() {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Pane root = new Pane();
        root.setPrefSize(600, 650);
        root.setBackground(background);

        root.getChildren().addAll(Arrays.stream(board.fields).flatMap(Arrays::stream).collect(Collectors.toList()));

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

        List<State> states = new ArrayList<>();

        for (int y = 0; y < 3; y++) {
            states.add(new State(board.fields[0][y], board.fields[1][y], board.fields[2][y]));
        }

        for (int x = 0; x < 3; x++) {
            states.add(new State(board.fields[x][0], board.fields[x][1], board.fields[x][2]));
        }

        states.add(new State(board.fields[0][0], board.fields[1][1], board.fields[2][2]));
        states.add(new State(board.fields[2][0], board.fields[1][1], board.fields[0][2]));
        launch(args);
    }
}

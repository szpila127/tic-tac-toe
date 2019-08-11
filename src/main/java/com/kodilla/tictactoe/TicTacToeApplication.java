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
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class TicTacToeApplication extends Application {

    private static Image back = new Image("file:src/main/resources/back.png");
    public static Board board = new Board();
    public static boolean turnX = true;
    public static boolean possibleMove = true;
    public static Label label = new Label();

    private static Parent createScene() {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        Pane root = new Pane();
        root.setPrefSize(600, 650);
        root.setBackground(background);

        Button reset = new Button();
        reset.setText("RESET");
        reset.setLayoutX(0);
        reset.setLayoutY(601);
        reset.setPrefSize(150, 49);
        reset.setOnAction(action -> {
            State.gameReset();
        });

        Button exit = new Button();
        exit.setText("EXIT");
        exit.setLayoutX(450);
        exit.setLayoutY(601);
        exit.setPrefSize(150, 49);
        exit.setOnAction(action -> {
            Platform.exit();
        });

        label.setText("CROSS TURN");
        label.setTextFill(Color.RED);
        label.setFont(new Font("Arial", 35));
        label.layoutXProperty().bind(root.widthProperty().subtract(label.widthProperty()).divide(2));
        label.setLayoutY(605);

        root.getChildren().addAll(Arrays.stream(board.fields).flatMap(Arrays::stream).collect(Collectors.toList()));

        root.getChildren().addAll(reset, exit, label);

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

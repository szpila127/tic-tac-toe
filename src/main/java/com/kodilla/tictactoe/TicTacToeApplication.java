package com.kodilla.tictactoe;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TicTacToeApplication extends Application {

    private static Image back = new Image("file:src/main/resources/back.png");

    private static Parent createScene() {

        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(back, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane root = new GridPane();
        root.setPrefSize(600, 600);
        root.setBackground(background);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++ ) {
                Tile tile = new Tile();
                tile.setTranslateX(i * 200);
                tile.setTranslateY(j * 200);

                root.getChildren().addAll(tile);
            }
        }

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

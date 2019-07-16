package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    private Image cross = new Image("file:src/main/resources/cross.png");
    private Image circle = new Image("file:src/main/resources/circle.png");

    public Tile() {
        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);

        setOnMouseClicked(action -> {
            if (action.getButton() == MouseButton.PRIMARY) {
                border.setFill(new ImagePattern(cross));
            } else if (action.getButton() == MouseButton.SECONDARY) {
                border.setFill(new ImagePattern(circle));
            }
        });
    }

    private void drawX() {

    }

    private void drawO() {

    }
}

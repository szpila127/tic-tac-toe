package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    public static Image cross = new Image("file:src/main/resources/cross.png");
    public static Image circle = new Image("file:src/main/resources/circle.png");
    public FieldValue fieldValue;

    public Tile(FieldValue fieldValue) {
        this.fieldValue = fieldValue;

        Rectangle border = new Rectangle(200, 200);
        border.setFill(null);
        border.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);

        if (FieldValue.CROSS == fieldValue) {
            border.setFill(new ImagePattern(cross));
        } else if (FieldValue.CIRCLE == fieldValue) {
            border.setFill(new ImagePattern(circle));
        }

        setOnMouseClicked(action -> {

            if (action.getButton() == MouseButton.PRIMARY) {
                if (!TicTacToeApplication.turnX) {
                    return;
                }
                if (this.fieldValue == FieldValue.CIRCLE) {
                    return;
                }
                this.fieldValue = FieldValue.CROSS;
                border.setFill(new ImagePattern(cross));
                TicTacToeApplication.turnX = false;

            } else if (action.getButton() == MouseButton.SECONDARY) {
                if (TicTacToeApplication.turnX) {
                    return;
                }
                if (this.fieldValue == FieldValue.CROSS) {
                    return;
                }
                this.fieldValue = FieldValue.CIRCLE;
                border.setFill(new ImagePattern(circle));
                TicTacToeApplication.turnX = true;
            }
        });
    }
}

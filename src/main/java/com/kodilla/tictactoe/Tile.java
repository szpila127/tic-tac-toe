package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

class Tile extends StackPane {

    private static Image cross = new Image("file:src/main/resources/cross.png");
    private static Image circle = new Image("file:src/main/resources/circle.png");
    private FieldValue fieldValue;

    private FieldValue getFieldValue() {
        return fieldValue;
    }

    private void setFieldValue(FieldValue fieldValue) {
        this.fieldValue = fieldValue;
    }

    Tile(FieldValue fieldValue) {
        this.fieldValue = fieldValue;

        Rectangle rectangle = new Rectangle(200, 200);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle);

//        if (fieldValue == FieldValue.CROSS) {
//            rectangle.setFill(new ImagePattern(cross));
//        } else if (fieldValue == FieldValue.CIRCLE) {
//            rectangle.setFill(new ImagePattern(circle));
//        }

        setOnMouseClicked(action -> {

            if (action.getButton() == MouseButton.PRIMARY) {
                if (!TicTacToeApplication.turnX || getFieldValue() == FieldValue.CIRCLE || getFieldValue() == FieldValue.CROSS) {
                    return;
                }
                setFieldValue(FieldValue.CROSS);
                rectangle.setFill(new ImagePattern(cross));
                TicTacToeApplication.turnX = false;

            } else if (action.getButton() == MouseButton.SECONDARY) {
                if (TicTacToeApplication.turnX || getFieldValue() == FieldValue.CROSS || getFieldValue() == FieldValue.CIRCLE) {
                    return;
                }
                setFieldValue(FieldValue.CIRCLE);
                rectangle.setFill(new ImagePattern(circle));
                TicTacToeApplication.turnX = true;
            }
        });
    }
}

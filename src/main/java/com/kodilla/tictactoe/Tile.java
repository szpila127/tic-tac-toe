package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {

    private static Image cross = new Image("file:src/main/resources/cross.png");
    private static Image circle = new Image("file:src/main/resources/circle.png");
    private FieldValue fieldValue;

    FieldValue getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(FieldValue fieldValue) {
        this.fieldValue = fieldValue;
    }

    Tile(FieldValue fieldValue) {
        this.fieldValue = fieldValue;

        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(200);
        rectangle.setWidth(200);
        rectangle.setFill(null);
        rectangle.setStroke(Color.BLACK);

        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle);

        if (getFieldValue() == FieldValue.EMPTY) {
            rectangle.setFill(null);
        }

        setOnMouseClicked(action -> {
            State.changeLabel();
            if (!TicTacToeApplication.possibleMove) {
                return;
            }

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

            Score score = State.gameResult();
            System.out.println(score);
            if (score == Score.O_WIN) {
                TicTacToeApplication.possibleMove = false;
                TicTacToeApplication.label.setText("WINNER: O");
                TicTacToeApplication.label.setTextFill(Color.BLACK);
                return;
            }

            if (score == Score.X_WIN) {
                TicTacToeApplication.possibleMove = false;
                TicTacToeApplication.label.setText("WINNER: X");
                TicTacToeApplication.label.setTextFill(Color.RED);
                return;
            }

            if (score == Score.EVEN) {
                TicTacToeApplication.label.setText("EVEN");
                TicTacToeApplication.label.setTextFill(Color.GREEN);
                TicTacToeApplication.possibleMove = false;
            }

            if (score == Score.IN_PROGRESS) {
                if (TicTacToeApplication.turnX) {
                    TicTacToeApplication.label.setText("CROSS TURN");
                    TicTacToeApplication.label.setTextFill(Color.RED);
                }
                if (!TicTacToeApplication.turnX) {
                    TicTacToeApplication.label.setText("CIRCLE TURN");
                    TicTacToeApplication.label.setTextFill(Color.BLACK);
                }
            }
        });
    }
}

package com.kodilla.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            if (!TicTacToeApplication.possibleMove) {
                return;
            }
            if (TicTacToeApplication.vsComputer) {
                if (action.getButton() == MouseButton.SECONDARY) {
                    return;
                }
                if (action.getButton() == MouseButton.PRIMARY) {
                    if (!TicTacToeApplication.turnX || getFieldValue() == FieldValue.CIRCLE || getFieldValue() == FieldValue.CROSS) {
                        return;
                    }
                    setFieldValue(FieldValue.CROSS);
                    rectangle.setFill(new ImagePattern(cross));
                    TicTacToeApplication.turnX = false;
                    checkState();
                    if (!TicTacToeApplication.possibleMove) {
                        return;
                    }
                }
                List<Tile> tileList = Arrays.stream(TicTacToeApplication.board.fields)
                        .flatMap(array -> Arrays.stream(array))
                        .filter(field -> field.getFieldValue() == FieldValue.EMPTY)
                        .collect(Collectors.toList());
                Tile tile = tileList.get(randomNumber(tileList.size()));
                Rectangle node = (Rectangle) tile.getChildren().get(0);
                tile.setFieldValue(FieldValue.CIRCLE);
                node.setFill(new ImagePattern(circle));
                TicTacToeApplication.turnX = true;
                checkState();
            }

            if (!TicTacToeApplication.vsComputer) {
                if (action.getButton() == MouseButton.PRIMARY) {
                    if (!TicTacToeApplication.turnX || getFieldValue() == FieldValue.CIRCLE || getFieldValue() == FieldValue.CROSS) {
                        return;
                    }
                    setFieldValue(FieldValue.CROSS);
                    rectangle.setFill(new ImagePattern(cross));
                    TicTacToeApplication.turnX = false;
                    checkState();
                }
                if (action.getButton() == MouseButton.SECONDARY) {
                    if (TicTacToeApplication.turnX || getFieldValue() == FieldValue.CROSS || getFieldValue() == FieldValue.CIRCLE) {
                        return;
                    }
                    setFieldValue(FieldValue.CIRCLE);
                    rectangle.setFill(new ImagePattern(circle));
                    TicTacToeApplication.turnX = true;
                    checkState();
                }
            }
        });
    }

    public int randomNumber(int limit) {
        Random generator = new Random();
        int random = generator.nextInt(limit);
        return random;
    }

    public void checkState() {
        Score score = State.gameResult();
        System.out.println(score);
        if (score == Score.O_WIN) {
            TicTacToeApplication.playerO.setCount(TicTacToeApplication.playerO.getCount() + 1);
            TicTacToeApplication.counter.setText("X  [" + TicTacToeApplication.playerX.getCount()
                    + "] : [" + TicTacToeApplication.playerO.getCount() + "] O");
            TicTacToeApplication.possibleMove = false;
            TicTacToeApplication.label.setText("WINNER: O");
            TicTacToeApplication.label.setTextFill(Color.BLACK);
            return;
        }
        if (score == Score.X_WIN) {
            TicTacToeApplication.playerX.setCount(TicTacToeApplication.playerX.getCount() + 1);
            TicTacToeApplication.counter.setText("X  [" + TicTacToeApplication.playerX.getCount()
                    + "] : [" + TicTacToeApplication.playerO.getCount() + "]  O");
            TicTacToeApplication.possibleMove = false;
            TicTacToeApplication.label.setText("WINNER: X");
            TicTacToeApplication.label.setTextFill(Color.RED);
            return;
        }
        if (score == Score.EVEN) {
            TicTacToeApplication.possibleMove = false;
            TicTacToeApplication.label.setText("EVEN");
            TicTacToeApplication.label.setTextFill(Color.GREEN);
            return;
        }
        if (score == Score.IN_PROGRESS) {
            if (TicTacToeApplication.turnX) {
                TicTacToeApplication.label.setText("X TURN");
                TicTacToeApplication.label.setTextFill(Color.RED);
            }
            if (!TicTacToeApplication.turnX) {
                TicTacToeApplication.label.setText("O TURN");
                TicTacToeApplication.label.setTextFill(Color.BLACK);
            }
        }
    }
}
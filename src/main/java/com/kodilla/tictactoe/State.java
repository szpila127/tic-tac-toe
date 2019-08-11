package com.kodilla.tictactoe;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class State {

    public static void gameReset() {
        Tile[][] fields = TicTacToeApplication.board.fields;

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                fields[x][y].setFieldValue(FieldValue.EMPTY);
                Rectangle node = (Rectangle) fields[x][y].getChildren().get(0);
                node.setFill(null);
            }
        }
        TicTacToeApplication.turnX = true;
        TicTacToeApplication.possibleMove = true;
        TicTacToeApplication.label.setText("CROSS TURN");
        TicTacToeApplication.label.setTextFill(Color.RED);
    }

    public static Score gameResult() {
        Tile[][] fields = TicTacToeApplication.board.fields;

        for (int y = 0; y < 3; y++) {
            if (fields[0][y].getFieldValue() == fields[1][y].getFieldValue()
                    && fields[0][y].getFieldValue() == fields[2][y].getFieldValue()) {
                if (fields[0][y].getFieldValue() == FieldValue.CIRCLE) {
                    return Score.O_WIN;
                } else if (fields[0][y].getFieldValue() == FieldValue.CROSS) {
                    return Score.X_WIN;
                }
            }
        }

        for (int x = 0; x < 3; x++) {
            if (fields[x][0].getFieldValue() == fields[x][1]
                    .getFieldValue()
                    && fields[x][0].getFieldValue() == fields[x][2].getFieldValue()) {
                if (fields[x][0].getFieldValue() == FieldValue.CIRCLE) {
                    return Score.O_WIN;
                } else if (fields[x][0].getFieldValue() == FieldValue.CROSS) {
                    return Score.X_WIN;
                }
            }
        }

        if (fields[0][0].getFieldValue() == fields[1][1].getFieldValue() &&
                fields[0][0].getFieldValue() == fields[2][2].getFieldValue()) {
            if (fields[0][0].getFieldValue() == FieldValue.CIRCLE) {
                return Score.O_WIN;
            } else if (fields[0][0].getFieldValue() == FieldValue.CROSS) {
                return Score.X_WIN;
            }
        }

        if (fields[2][0].getFieldValue() == fields[1][1].getFieldValue() &&
                fields[2][0].getFieldValue() == fields[0][2].getFieldValue()) {
            if (fields[2][0].getFieldValue() == FieldValue.CIRCLE) {
                return Score.O_WIN;
            } else if (fields[2][0].getFieldValue() == FieldValue.CROSS) {
                return Score.X_WIN;
            }
        }

        boolean isEmpty = false;
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (fields[x][y].getFieldValue() == FieldValue.EMPTY) {
                    isEmpty = true;
                }
            }
        }
        if (!isEmpty) {
            return Score.EVEN;
        }

        return Score.IN_PROGRESS;
    }
}

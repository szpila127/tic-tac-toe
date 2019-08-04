package com.kodilla.tictactoe;

public class State {
    private Tile[] tiles1;

    public State(Tile... tiles1) {
        this.tiles1 = tiles1;
    }

    public FieldValue isOver() {

        if (tiles1[0].getFieldValue() == FieldValue.EMPTY) ;
        return FieldValue.EMPTY;

        if (tiles1[0].getFieldValue() == FieldValue.CIRCLE
                && tiles1[1].getFieldValue() == FieldValue.CIRCLE
                && tiles1[2].getFieldValue() == FieldValue.CIRCLE);
        return FieldValue.CIRCLE;

        if (tiles1[0].getFieldValue() == FieldValue.CROSS
                && tiles1[1].getFieldValue() == FieldValue.CROSS
                && tiles1[2].getFieldValue() == FieldValue.CROSS);
        return FieldValue.CROSS;

    }
}

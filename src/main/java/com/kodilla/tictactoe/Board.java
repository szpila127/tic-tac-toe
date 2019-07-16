package com.kodilla.tictactoe;

public class Board {

    public Tile[][] fields = new Tile[3][3];

    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile(FieldValue.EMPTY);
                tile.setTranslateX(i * 200);
                tile.setTranslateY(j * 200);
                fields[i][j] = tile;
            }
        }
        fields[1][1] = new Tile(FieldValue.CROSS);
    }
}

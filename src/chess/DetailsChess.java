package chess;

import java.util.ArrayList;

import game.Details;

public class DetailsChess extends Details {

    static ArrayList<String> capturedPiecesW = new ArrayList<>();
    static ArrayList<String> capturedPiecesB = new ArrayList<>();
    String piece;

    public DetailsChess() {
    }

    public DetailsChess(int row, int col) {
        super(row, col);
    }
}

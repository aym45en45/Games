package chess;

import java.util.ArrayList;
import java.util.Arrays;

import game.Details;

public class DetailsChess extends Details {

    static ArrayList<String> capturedPiecesW = new ArrayList<>(Arrays.asList("captured : "));
    static ArrayList<String> capturedPiecesB = new ArrayList<>(Arrays.asList("captured : "));
    String piece;

    public DetailsChess() {
    }

    public DetailsChess(int row, int col) {
        super(row, col);
    }

    public DetailsChess(DetailsChess original) {
        super(original.getRow(), original.getCol());
    }
}

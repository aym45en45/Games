package chess;

public class Pieces {
    static int[][] knightMoves = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 },
            { 2, 1 } };

    static boolean checkIfValidMoveForN() {
        return true;
    }

    static boolean checkIfValidMoveForB() {
        return true;
    }

    static boolean checkIfValidMoveForR() {
        return true;
    }

    static boolean checkIfValidMoveForP() {
        return true;
    }

    static boolean checkIfValidMoveForQ() {
        return true;
    }

    static boolean checkIfValidMoveForK() {
        return Math.abs(BoardChess.squareToStart.getRow() - BoardChess.squareToEnd.getRow()) <= 1
                && Math.abs(BoardChess.squareToStart.getCol() - BoardChess.squareToEnd.getCol()) <= 1;
    }

}

package chess;

import java.util.ArrayList;

import game.Board;

public class BoardChess extends Board {
    private static DetailsChess squareToStart = new DetailsChess();
    private static DetailsChess squareToEnd = new DetailsChess();
    private static DetailsChess whiteKing = new DetailsChess(0, 4);
    private static DetailsChess blackKing = new DetailsChess(7, 4);
    int[][] knightMoves = { { -2, -1 }, { -2, 1 }, { -1, -2 }, { -1, 2 }, { 1, -2 }, { 1, 2 }, { 2, -1 },
            { 2, 1 } };

    public BoardChess() {
        initialize();
    }

    public void move(String move) {
        split(move);
        if (checkIfCanMove()) {
            if (MainChess.getPlayer().equals("white"))
                DetailsChess.capturedPiecesW.add(getPiece(squareToEnd));
            else
                DetailsChess.capturedPiecesB.add(getPiece(squareToEnd));
            board[squareToEnd.getRow()][squareToEnd.getCol()] = getPiece(squareToStart);
            board[squareToStart.getRow()][squareToStart.getCol()] = " ";
        }
    }

    public boolean checkIfCanMove() {
        if (squareToStart.equals(squareToEnd))
            return false;
        else if (getPiece(squareToStart).equals(" "))
            return false;
        else if (checkIfValidMove()) {
            if (isKingInCheck(board)) {
                if (CanGetOutOfCheck())
                    return true;
            }
        }
        return false;

    }

    private boolean CanGetOutOfCheck() {
        String[][] CheckBoard = copyBoard(board);
        return false;
    }

    private boolean isKingInCheck(String[][] CheckBoard) {
        if (MainChess.getPlayer().equals("white")) {
            if (CheckBoard[whiteKing.getRow() + 1][whiteKing.getCol() + 1].equals("p")
                    || CheckBoard[whiteKing.getRow() + 1][whiteKing.getCol() - 1].equals("p"))
                return true;
            for (int[] move : knightMoves) {
                int newRow = whiteKing.getRow() + move[0];
                int newCol = whiteKing.getCol() + move[1];

                if (isValid(newRow, newCol) && "n".equals(board[newRow][newCol])) {
                    return true;
                }
            }
            for (int i = whiteKing.getCol(); i < CheckBoard.length; i++) {
                if (CheckBoard[whiteKing.getRow()][i].equals("r") || CheckBoard[whiteKing.getRow()][i].equals("q"))
                    return true;
                else if (!CheckBoard[whiteKing.getRow()][i].equals(" "))
                    break;
            }
            for (int i = whiteKing.getCol(); i >= 0; i--) {
                if (CheckBoard[whiteKing.getRow()][i].equals("r") || CheckBoard[whiteKing.getRow()][i].equals("q"))
                    return true;
                else if (!CheckBoard[whiteKing.getRow()][i].equals(" "))
                    break;
            }
            for (int i = whiteKing.getRow(); i < CheckBoard.length; i++) {
                if (CheckBoard[i][whiteKing.getCol()].equals("r") || CheckBoard[i][whiteKing.getCol()].equals("q"))
                    return true;
                else if (!CheckBoard[i][whiteKing.getCol()].equals(" "))
                    break;
            }
            for (int i = whiteKing.getRow(); i >= 0; i--) {
                if (CheckBoard[i][whiteKing.getCol()].equals("r") || CheckBoard[i][whiteKing.getCol()].equals("q"))
                    return true;
                else if (!CheckBoard[i][whiteKing.getCol()].equals(" "))
                    break;
            }
            for (int i = whiteKing.getRow(), j = whiteKing.getCol(); i < CheckBoard.length
                    && j < CheckBoard.length; i++, j++) {
                if (CheckBoard[i][j].equals("b") || CheckBoard[i][j].equals("q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
            for (int i = whiteKing.getRow(), j = whiteKing.getCol(); i < CheckBoard.length && j >= 0; i++, j--) {
                if (CheckBoard[i][j].equals("b") || CheckBoard[i][j].equals("q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
            for (int i = whiteKing.getRow(), j = whiteKing.getCol(); i >= 0 && j < CheckBoard.length; i--, j++) {
                if (CheckBoard[i][j].equals("b") || CheckBoard[i][j].equals("q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
            for (int i = whiteKing.getRow(), j = whiteKing.getCol(); i >= 0 && j >= 0; i--, j--) {
                if (CheckBoard[i][j].equals("b") || CheckBoard[i][j].equals("q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
        } else {
            if (CheckBoard[blackKing.getRow() - 1][blackKing.getCol() + 1].equals("P")
                    || CheckBoard[blackKing.getRow() - 1][blackKing.getCol() - 1].equals("P"))
                return true;
            for (int[] move : knightMoves) {
                int newRow = blackKing.getRow() + move[0];
                int newCol = blackKing.getCol() + move[1];
                if (isValid(newRow, newCol) && "N".equals(board[newRow][newCol])) {
                    return true;
                }
            }
            for (int i = blackKing.getCol(); i < CheckBoard.length; i++) {
                if (CheckBoard[blackKing.getRow()][i].equals("R") || CheckBoard[blackKing.getRow()][i].equals("Q"))
                    return true;
                else if (!CheckBoard[blackKing.getRow()][i].equals(" "))
                    break;
            }
            for (int i = blackKing.getCol(); i >= 0; i--) {
                if (CheckBoard[blackKing.getRow()][i].equals("R") || CheckBoard[blackKing.getRow()][i].equals("Q"))
                    return true;
                else if (!CheckBoard[blackKing.getRow()][i].equals(" "))
                    break;
            }
            for (int i = blackKing.getRow(); i < CheckBoard.length; i++) {
                if (CheckBoard[i][blackKing.getCol()].equals("R") || CheckBoard[i][blackKing.getCol()].equals("Q"))
                    return true;
                else if (!CheckBoard[i][blackKing.getCol()].equals(" "))
                    break;
            }
            for (int i = blackKing.getRow(); i >= 0; i--) {
                if (CheckBoard[i][blackKing.getCol()].equals("R") || CheckBoard[i][blackKing.getCol()].equals("Q"))
                    return true;
                else if (!CheckBoard[i][blackKing.getCol()].equals(" "))
                    break;
            }
            for (int i = blackKing.getRow(), j = blackKing.getCol(); i < CheckBoard.length
                    && j < CheckBoard.length; i++, j++) {
                if (CheckBoard[i][j].equals("B") || CheckBoard[i][j].equals("Q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
            for (int i = blackKing.getRow(), j = blackKing.getCol(); i < CheckBoard.length && j >= 0; i++, j--) {
                if (CheckBoard[i][j].equals("B") || CheckBoard[i][j].equals("Q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
            for (int i = blackKing.getRow(), j = blackKing.getCol(); i >= 0 && j < CheckBoard.length; i--, j++) {
                if (CheckBoard[i][j].equals("B") || CheckBoard[i][j].equals("Q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
            for (int i = blackKing.getRow(), j = blackKing.getCol(); i >= 0 && j >= 0; i--, j--) {
                if (CheckBoard[i][j].equals("B") || CheckBoard[i][j].equals("Q"))
                    return true;
                else if (!CheckBoard[i][j].equals(" "))
                    break;
            }
        }
        return false;
    }

    private String getPiece(DetailsChess square) {
        return board[square.getRow()][square.getCol()];
    }

    public void split(String split) {
        if (split.length() == 3) {
            squareToStart.piece = "" + split.charAt(0);
            squareToStart.setRow(split.charAt(1) - 'a' + 1);
            squareToStart.setCol(split.charAt(2));

        } else {
            squareToStart.setRow(split.charAt(0) - 'a' + 1);
            squareToStart.setCol(split.charAt(1));
            squareToEnd.setRow(split.charAt(2) - 'a' + 1);
            squareToEnd.setCol(split.charAt(3));
        }
    }

    public boolean checkIfValidMove() {
        switch (board[squareToStart.getRow()][squareToStart.getCol()]) {
            case "K":
                if (checkIfValidMoveForK())
                    return true;
                break;
            case "Q":
                if (checkIfValidMoveForQ())
                    return true;
                break;
            default:
                System.out.println("yawdi golna 1 wala 2 wala 3");
                break;
        }
        return false;
    }

    private boolean checkIfValidMoveForK() {
        return Math.abs(squareToStart.getRow() - squareToEnd.getRow()) <= 1
                && Math.abs(squareToStart.getCol() - squareToEnd.getCol()) <= 1;
    }
    private boolean checkIfValidMoveForQ() {
        return Math.abs(squareToStart.getRow() - squareToEnd.getRow()) <= 1
                && Math.abs(squareToStart.getCol() - squareToEnd.getCol()) <= 1;
    }

    public boolean showValidMove(String move) {
        return false;
    }

    @Override
    public void draw() {
        for (int in = 0; in < 8; in++) {
            System.out.println("  ========================================");
            System.out.print((8 - in) + " ");
            for (int i = 0; i < 8; i++) {
                if (in % 2 == 0) {
                    if (i % 2 == 0)
                        System.out.print("| " + board[in][i] + " |");
                    else
                        System.out.print("|." + board[in][i] + ".|");
                } else {
                    if (i % 2 != 0)
                        System.out.print("| " + board[in][i] + " |");
                    else
                        System.out.print("|." + board[in][i] + ".|");
                }
            }
            System.out.println();
        }
        System.out.println("  ========================================");
        System.out.println("    a    b    c    d    e    f    g    h  ");
    }

    @Override
    public boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @Override
    public void initialize() {
        board = new String[][] {
                { "r", "n", "b", "q", "k", "b", "n", "r" },
                { "p", "p", "p", "p", "p", "p", "p", "p" },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { "P", "P", "P", "P", "P", "P", "P", "P" },
                { "R", "N", "B", "Q", "K", "B", "N", "R" },
        };
    }

}

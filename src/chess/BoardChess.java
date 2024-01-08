package chess;

import java.util.Arrays;

import game.Board;

public class BoardChess extends Board {
    static DetailsChess squareToStart = new DetailsChess();
    static DetailsChess squareToEnd = new DetailsChess();
    static DetailsChess whiteKing = new DetailsChess(0, 4);
    static DetailsChess blackKing = new DetailsChess(7, 4);
    String[][] binaryBoard;

    public BoardChess() {
        initialize();
    }

    public void move(String move) {
        split(move);
        if (checkIfCanMove()) {
            if (MainChess.getPlayer().equals("White")) {
                if (!getPiece(squareToEnd).equals(" "))
                    DetailsChess.capturedPiecesW.add(getPiece(squareToEnd));
            } else {
                if (!getPiece(squareToEnd).equals(" "))
                    DetailsChess.capturedPiecesB.add(getPiece(squareToEnd));
            }
            board[squareToEnd.getRow()][squareToEnd.getCol()] = getPiece(squareToStart);
            board[squareToStart.getRow()][squareToStart.getCol()] = " ";
        } else {
            MainChess.showMsg = true;
        }
    }

    public boolean checkIfCanMove() {
        if (squareToStart.getRow() == squareToEnd.getRow() && squareToStart.getCol() == squareToEnd.getCol())
            return false;
        else if (getPiece(squareToStart).equals(" "))
            return false;
        else if (checkIfValidMove()) {
            if (isKingInCheck(board)) {
                if (CanGetOutOfCheck())
                    return true;
            } else {
                return true;
            }
        }
        return false;

    }

    private boolean CanGetOutOfCheck() {
        String[][] CheckBoard = copyBoard(board);
        return false;
    }

    private String getPiece(DetailsChess square) {
        return board[square.getRow()][square.getCol()];
    }

    public void split(String split) {
        if (split.length() == 2) {
            squareToStart.setCol(split.charAt(1) - 'a');
            squareToStart.setRow(Character.getNumericValue(split.charAt(2)) - 1);

        } else {
            squareToStart.setCol(split.charAt(0) - 'a');
            squareToStart.setRow(Character.getNumericValue(split.charAt(1)) - 1);
            squareToEnd.setCol(split.charAt(2) - 'a');
            squareToEnd.setRow(Character.getNumericValue(split.charAt(3)) - 1);
        }
    }

    public boolean checkIfValidMove() {
        switch (board[squareToStart.getRow()][squareToStart.getCol()].toUpperCase()) {
            case "K":
                return Pieces.checkIfValidMoveForK();
            case "Q":
                return Pieces.checkIfValidMoveForQ();
            case "R":
                return Pieces.checkIfValidMoveForR();
            case "B":
                return Pieces.checkIfValidMoveForB();
            case "N":
                return Pieces.checkIfValidMoveForN();
            case "P":
                return Pieces.checkIfValidMoveForP();
            default:
                System.out.println("yawdi golna 1 wala 2 wala 3");
                break;
        }
        return false;
    }

    public boolean showValidMove(String move) {
        return false;
    }

    @Override
    public void draw() {
        System.out.println(Arrays.toString(DetailsChess.capturedPiecesW.toArray()));
        for (int in = 0; in < 8; in++) {
            System.out.println("  ========================================");
            System.out.print((8 - in) + " ");
            for (int i = 0; i < 8; i++) {
                if (in % 2 == 0) {
                    if (i % 2 == 0)
                        System.out.print("| " + board[7 - in][i] + " |");
                    else
                        System.out.print("|." + board[7 - in][i] + ".|");
                } else {
                    if (i % 2 != 0)
                        System.out.print("| " + board[7 - in][i] + " |");
                    else
                        System.out.print("|." + board[7 - in][i] + ".|");
                }
            }
            System.out.println();
        }
        System.out.println("  ========================================");
        System.out.println("    a    b    c    d    e    f    g    h  ");
        System.out.println(Arrays.toString(DetailsChess.capturedPiecesB.toArray()));
    }

    @Override
    public boolean isValid(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    @Override
    public void initialize() {
        board = new String[][] {
                { "R", "N", "B", "Q", "K", "B", "N", "R" },
                { "P", "P", "P", "P", "P", "P", "P", "P" },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { "p", "p", "p", "p", "p", "p", "p", "p" },
                { "r", "n", "b", "q", "k", "b", "n", "r" },
        };
        binaryBoard = new String[][] {
                { "0", "0", "0", "0", "0", "0", "0", "0" },
                { "0", "0", "0", "0", "0", "0", "0", "0" },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { " ", " ", " ", " ", " ", " ", " ", " " },
                { "1", "1", "1", "1", "1", "1", "1", "1" },
                { "1", "1", "1", "1", "1", "1", "1", "1" },
        };
    }

    private boolean isKingInCheck(String[][] CheckBoard) {

        DetailsChess king;
        if (MainChess.getPlayer().equals("White")) {
            king = new DetailsChess(whiteKing);
            if (CheckBoard[king.getRow() + 1][king.getCol() + 1].equals("p")
                    || CheckBoard[king.getRow() + 1][king.getCol() - 1].equals("p"))
                return true;
        } else {
            king = new DetailsChess(blackKing);
            if (CheckBoard[king.getRow() - 1][king.getCol() + 1].equals("P")
                    || CheckBoard[king.getRow() - 1][king.getCol() - 1].equals("P"))
                return true;
        }
        for (int[] move : Pieces.knightMoves) {
            int newRow = king.getRow() + move[0];
            int newCol = king.getCol() + move[1];

            if (isValid(newRow, newCol) && "n".equalsIgnoreCase(board[newRow][newCol])) {
                return true;
            }
        }
        for (int i = king.getCol(); i < CheckBoard.length; i++) {
            if (CheckBoard[king.getRow()][i].equalsIgnoreCase("r")
                    || CheckBoard[king.getRow()][i].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[king.getRow()][i].equals(" "))
                break;
        }
        for (int i = king.getCol(); i >= 0; i--) {
            if (CheckBoard[king.getRow()][i].equalsIgnoreCase("r")
                    || CheckBoard[king.getRow()][i].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[king.getRow()][i].equals(" "))
                break;
        }
        for (int i = king.getRow(); i < CheckBoard.length; i++) {
            if (CheckBoard[i][king.getCol()].equalsIgnoreCase("r")
                    || CheckBoard[i][king.getCol()].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[i][king.getCol()].equals(" "))
                break;
        }
        for (int i = king.getRow(); i >= 0; i--) {
            if (CheckBoard[i][king.getCol()].equalsIgnoreCase("r")
                    || CheckBoard[i][king.getCol()].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[i][king.getCol()].equals(" "))
                break;
        }
        for (int i = king.getRow(), j = king.getCol(); i < CheckBoard.length
                && j < CheckBoard.length; i++, j++) {
            if (CheckBoard[i][j].equalsIgnoreCase("b") || CheckBoard[i][j].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[i][j].equals(" "))
                break;
        }
        for (int i = king.getRow(), j = king.getCol(); i < CheckBoard.length && j >= 0; i++, j--) {
            if (CheckBoard[i][j].equalsIgnoreCase("b") || CheckBoard[i][j].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[i][j].equals(" "))
                break;
        }
        for (int i = king.getRow(), j = king.getCol(); i >= 0 && j < CheckBoard.length; i--, j++) {
            if (CheckBoard[i][j].equalsIgnoreCase("b") || CheckBoard[i][j].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[i][j].equals(" "))
                break;
        }
        for (int i = king.getRow(), j = king.getCol(); i >= 0 && j >= 0; i--, j--) {
            if (CheckBoard[i][j].equalsIgnoreCase("b") || CheckBoard[i][j].equalsIgnoreCase("q"))
                return true;
            else if (!CheckBoard[i][j].equals(" "))
                break;
        }
        return false;
    }

}

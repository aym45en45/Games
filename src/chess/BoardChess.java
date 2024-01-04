package chess;

import game.Board;

public class BoardChess extends Board {
    private int[] square1 = new int[2];
    private int[] square2 = new int[2];
    final String[] pieces = { "K", "Q", "R", "N", "B", "P", "k", "q", "r", "n", "b", "p" };

    public BoardChess() {
        initialize();
    }

    public void move(String move) {

    }

    public boolean check(String check) {
        split(check);
        switch (getPiece(square1)) {
            case " ":
                return false;
            case piece[2]:
                return false;
            default:
                return false;
        }

    }

    private String getPiece(int[] square) {
        return board[square[0]][square[1]];
    }

    public void split(String split) {
        square1[0] = split.charAt(0) - 'a' + 1;
        square1[1] = split.charAt(1);
        square2[0] = split.charAt(0) - 'a' + 1;
        square2[1] = split.charAt(1);
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
        return false;
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

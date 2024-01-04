package chess;

import game.Board;

public class BoardChess extends Board {
    static final String[] pieces = { "K", "Q", "R", "N", "B", "P", "k", "q", "r", "n", "b", "p" };

    public BoardChess() {
        initialize();
    }

    @Override
    public void draw() {
        for (int in = 0; in < 8; in++) {
            System.out.println("  ========================================");
            System.out.print((8-in)+" ");
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

    public boolean isValid(String move) {
        if (move.length() != 4)
            return false;
        return true;
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

    public boolean check(String check) {

        return false;
    }

}

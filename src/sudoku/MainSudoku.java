package sudoku;

import java.util.Scanner;

import game.Board;
import game.Game;

public class MainSudoku extends Game {

    private BoardSudoku board = new BoardSudoku();

    @Override
    public void play() {
        board.generateRandomBoard();
        Scanner scn = new Scanner(System.in);
        while (!gameOver()) {
            board.draw();
            String a;
            do {
                System.out.println("-if u want enter [S] to solve it and finish th game.-");
                System.out.println("do u want to add or remove number enter [A/R]");
                a = scn.next();
            } while (!"a".equalsIgnoreCase(a) && !"r".equalsIgnoreCase(a) && !"s".equalsIgnoreCase(a));

            if ("s".equalsIgnoreCase(a)) {
                board.solveBoard();
                break;
            } else {
                int x, y;
                do {
                    try {
                        System.out.println("Enter row and column (1-9):");
                        x = scn.nextInt() - 1;
                        y = scn.nextInt() - 1;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter valid row and column numbers.");
                        scn.nextLine();
                        x = y = -1;
                    }
                } while (!board.isValid(x, y));
                if ("a".equalsIgnoreCase(a))
                    addNumber(x, y, scn);
                else if ("r".equalsIgnoreCase(a))
                    removeNumber(x, y);
            }
        }
        scn.close();
        board.draw();
        System.out.println("game over!");
    }

    @Override
    public boolean gameOver() {
        for (int i = 0; i < Board.D; i++) {
            for (int j = 0; j < Board.D; j++) {
                if (board.getBoard()[i][j].equals(Board.EMPTY_CELL))
                    return false;
            }
        }
        return true;
    }

    void removeNumber(int x, int y) {
        if (board.isPositionInRandomBoardDetails(x, y)) {
            System.out.println("Cannot remove number that have a star * .");
            return;
        }

        board.getBoard()[x][y] = Board.EMPTY_CELL;
    }

    void addNumber(int x, int y, Scanner scn) {
        int z;
        do {
            System.out.println("enter number 1-9");
            z = scn.nextInt();
        } while (!board.isValid(x, y, z, true));
        board.getBoard()[x][y] = "" + z;
    }
}
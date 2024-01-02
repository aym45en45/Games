package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import game.Game;

public class MainSudoku extends Game {

    private BoardSudoku board = new BoardSudoku();
    @Override
    public void play() {
        do {
            board.generateRandomBoard();
            BoardSudoku.boardSolved = deepCopy(board);
        } while (!solve(BoardSudoku.boardSolved));
        Scanner scn = new Scanner(System.in);
        while (!gameOver()) {
            board.draw();
            String a;
            do {
                System.out.println("do u want to add or remove number [A/R]");
                System.out.println("enter s to solve it and finish th game.");
                a = scn.next();
            } while (!"a".equalsIgnoreCase(a) && !"r".equalsIgnoreCase(a) && !"s".equalsIgnoreCase(a));

            if ("s".equalsIgnoreCase(a)) {
                board = BoardSudoku.boardSolved;
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
                } while (!isValid(x, y));
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
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                if (board[i][j].equals(EMPTY_CELL))
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValid(int a, int b) {
        return 0 <= a && a < 9 && 0 <= b && b < 9;
    }

    static void removeNumber(int x, int y) {
        if (isPositionInRandomBoardDetails(x, y)) {
            System.out.println("Cannot remove number that have a star * .");
            return;
        }

        board[x][y] = EMPTY_CELL;
    }

    private static boolean isPositionInRandomBoardDetails(int x, int y) {
        for (Details details : randomBoardDetails) {
            if (details.row == x && details.col == y) {
                return true;
            }
        }
        return false;
    }

    static void addNumber(int x, int y, Scanner scn) {
        int z;
        do {
            System.out.println("enter number 1-9");
            z = scn.nextInt();
        } while (!isValid(board, x, y, z, true));
        board[x][y] = "" + z;
    }

    static void generateRandomBoard() {
        Random random = new Random();
        int r = random.nextInt(15, 20);
        randomBoardDetails = new ArrayList<>();
        for (int index = 0; index <= r; index++) {
            Details details = new Details();
            int n = 0;
            for (int i = 0; i < D; i++) {
                details.row = random.nextInt(D);
                details.col = random.nextInt(D);
                n = (random.nextInt(D) + 1);
                if (!board[details.row][details.col].equals(EMPTY_CELL)) {
                    i = -1;
                    continue;
                }
                if (!isValid(board, details.row, details.col, n, false)) {
                    i = -1;
                    continue;
                }

            }
            board[details.row][details.col] = "" + n;
            randomBoardDetails.add(details);
        }
    }

    static boolean isValid(String[][] board, int x, int y, int z, boolean var) {
        if (0 >= z || z > 9) {
            System.out.println("the number have to be between 1-9. Try again.");
            return false;
        } else {
            for (int i = 0; i < D; i++) {
                if (i != y && board[x][i].equals(String.valueOf(z))) {
                    if (var)
                        System.out.println("the number is exist in line " + (i + 1) + ". Try again.");
                    return false;
                }
                if (i != x && board[i][y].equals(String.valueOf(z))) {
                    if (var)
                        System.out.println("the number is exist in colume " + (i + 1) + ". Try again.");
                    return false;
                }
            }
            int X = x / 3;
            X *= 3;
            int Y = y / 3;
            Y *= 3;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((X + i) != x && (j + Y) != y && board[X + i][Y + j].equals(String.valueOf(z))) {
                        if (var)
                            System.out.println(
                                    "the number is exist in squir " + (x / 3) + "," + (y / 3) + ". Try again.");
                        return false;
                    }
                }
            }
            return true;
        }
    }

    static boolean solve(String[][] BoardSudoku.boardSolved) {
        for (int row = 0; row < D; row++) {
            for (int col = 0; col < D; col++) {
                if (BoardSudoku.boardSolved[row][col].equals(EMPTY_CELL)) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(BoardSudoku.boardSolved, row, col, num, false)) {
                            BoardSudoku.boardSolved[row][col] = "" + num;

                            if (solve(BoardSudoku.boardSolved)) {
                                return true;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    static String[][] deepCopy(String[][] original) {
        if (original == null) {
            return null;
        }

        String[][] copy = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    static String[][] getBoard() {
        return board;
    }

}
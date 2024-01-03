package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import game.Board;
import game.Details;

public class BoardSudoku extends Board {
    private static ArrayList<Details> randomBoardDetails;
    private String[][] solvedBoard;

    @Override
    public void draw() {
        System.out.println("   1   2   3 || 1   2   3 || 1   2   3 ");
        System.out.println("  =====================================");
        for (int i = 0; i < D; i++) {
            System.out.print((i + 1) + EMPTY_CELL);
            for (int j = 0; j < D; j++) {
                if (isPositionInRandomBoardDetails(i, j)) {
                    System.out.print("*" + board[i][j] + "*");
                } else {
                    System.out.print(" " + board[i][j] + " ");
                }
                if ((j + 1) % 3 != 0)
                    System.out.print("|");
                else if (j == 2 || j == 5)
                    System.out.print("||");
                else
                    System.out.println();
            }
            if ((i + 1) % 3 != 0)
                System.out.println("  -----------  -----------  -----------");
            else
                System.out.println("  =====================================");
        }

    }

    @Override
    public boolean isValid(int a, int b) {
        return 0 <= a && a < 9 && 0 <= b && b < 9;
    }

    boolean isValid(int x, int y, int z, boolean var) {
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

    boolean solve() {
        for (int row = 0; row < D; row++) {
            for (int col = 0; col < D; col++) {
                if (board[row][col].equals(EMPTY_CELL)) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(row, col, num, false)) {
                            board[row][col] = "" + num;

                            if (solve()) {
                                return true;
                            }
                        }
                    }

                    return false;
                }
            }
        }
        String[][] Box = copyBoard(board);
        board = copyBoard(solvedBoard);
        solvedBoard = copyBoard(Box);
        return true;
    }

    void generateRandomBoard() {
        do {
            initialize();
            randomBoardDetails = new ArrayList<>();

            Random random = new Random();
            int r = random.nextInt(15, 20);
            for (int index = 0; index <= r; index++) {
                Details details = new Details();
                int n = 0;
                for (int i = 0; i < D; i++) {
                    details.setRow(random.nextInt(D));
                    details.setCol(random.nextInt(D));
                    n = random.nextInt(D) + 1;
                    if (!board[details.getRow()][details.getCol()].equals(EMPTY_CELL)) {
                        i = -1;
                        continue;
                    }
                    if (!isValid(details.getRow(), details.getCol(), n, false)) {
                        i = -1;
                        continue;
                    }

                }
                board[details.getRow()][details.getCol()] = "" + n;
                randomBoardDetails.add(details);
            }

            solvedBoard = copyBoard(board);
        } while (!solve());
    }

    private static String[][] copyBoard(String[][] original) {
        if (original == null) {
            return null;
        }

        String[][] copy = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    void solveBoard() {
        board = solvedBoard;
    }

    static ArrayList<Details> getRandomBoardDetails() {
        return randomBoardDetails;
    }

    boolean isPositionInRandomBoardDetails(int x, int y) {
        for (Details details : randomBoardDetails) {
            if (details.getRow() == x && details.getCol() == y) {
                return true;
            }
        }
        return false;
    }

}
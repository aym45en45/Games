package sudoku;

import java.util.Random;
import java.util.Scanner;

public class MainSudoku {
    private static int D = 9;
    private static final String EMPTY_CELL = " ";
    private static String[][] board;

    public static void play() {
        initializeBoard();
        randomBoard();
        Scanner scanner = new Scanner(System.in);
        while (!gameOver()) {
            drawBoard();
            String a;
            do {
                System.out.println("do u want to add or remove number [A/R]");
                a = scanner.next();
            } while (!"a".equalsIgnoreCase(a) && !"r".equalsIgnoreCase(a));
            int x, y;
            do {
                System.out.println("enter row and culem 1-9");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } while (!isValid(x, y));
            if ("a".equalsIgnoreCase(a))
                addNumber(x, y, scanner);
            else
                removeNumber(x, y);
        }
        scanner.close();
        System.out.println("game over!");
    }

    static void initializeBoard() {
        board = new String[D][D];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                board[i][j] = EMPTY_CELL;
            }
        }
    }

    static void removeNumber(int x, int y) {
        board[x][y] = EMPTY_CELL;
    }

    static void addNumber(int x, int y, Scanner scanner) {
        int z;
        do {
            System.out.println("enter number 1-9");
            z = scanner.nextInt();
        } while (!isValid(x, y, z, true));
        board[x][y] = "" + z;
    }

    static void randomBoard() {
        Random random = new Random();
        int r = random.nextInt(15, 20);
        for (int index = 0; index <= r; index++) {
            int n = 0;
            int row = 0;
            int col = 0;
            for (int i = 0; i < D; i++) {
                row = random.nextInt(D);
                col = random.nextInt(D);
                n = (random.nextInt(D) + 1);
                if (!board[row][col].equals(EMPTY_CELL)) {
                    i = -1;
                    continue;
                }
                if (!isValid(row, col, n, false)) {
                    i = -1;
                    continue;
                }

            }
            board[row][col] = "" + n;
        }
    }

    static void drawBoard() {
        System.out.println("   1  2  3 || 4  5  6 || 7  8  9");
        System.out.println("  ===============================");
        for (int i = 0; i < D; i++) {
            System.out.print((i + 1) + EMPTY_CELL);
            for (int j = 0; j < D; j++) {
                System.out.print(board[i][j]);
                if (j != 2 && j != 5 && j != 8)
                    System.out.print(" | ");
                else if (j == 2 || j == 5)
                    System.out.print("||");
                else
                    System.out.println();
            }
            if (i != 2 && i != 5 && i != 8)
                System.out.println("  ---------  ---------  ---------");
            else
                System.out.println("  ===============================");
        }

    }

    static boolean gameOver() {
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                if (board[i][j].equals(EMPTY_CELL))
                    return false;
            }
        }
        return true;
    }

    static boolean isValid(int x, int y, int z, boolean var) {
        if (0 >= z || z > 9) {
            System.out.println("the number have to be between 1-9. Try again.");
            return false;
        } else {
            for (int i = 0; i < D; i++) {
                if (board[x][i].equals(String.valueOf(z))) {
                    if (var)
                        System.out.println("the number is exist in line " + (i + 1) + ". Try again.");
                    return false;
                }
                if (board[i][y].equals(String.valueOf(z))) {
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
                    if (board[X + i][Y + j].equals(String.valueOf(z))) {
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

    static boolean isValid(int a, int b) {
        return 0 <= a && a < 9 && 0 <= b && b < 9;
    }

    static String[][] getBoard() {
        return board;
    }
}

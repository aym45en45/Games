package sudoku;

import java.util.Random;
import java.util.Scanner;

public class MainSudoku {
    private static int D = 9;
    private static final String EMPTY_CELL = " ";
    private static String[][] boardS;

    public static void mainSudoku() {
        initializeBoard();
        randomBoard();
        while (!gameOver()) {
            drawBoard();
            String a;
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("do u want to add or remove number [A/R]");
                a = scanner.next();
                scanner.close();
            } while (!"a".equalsIgnoreCase(a) && !"r".equalsIgnoreCase(a));
            int x, y;
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println("enter row and culem 1-9");
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
                scanner.close();
            } while (!isValid(x, y));
            if ("a".equalsIgnoreCase(a))
                addNumber(x, y);
            else
                removeNumber(x, y);
        }
        System.out.println("game over!");
    }

    static void initializeBoard() {
        boardS = new String[D][D];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                boardS[i][j] = EMPTY_CELL;
            }
        }
    }

    static void removeNumber(int x, int y) {
        boardS[x][y] = EMPTY_CELL;
    }

    static void addNumber(int x, int y) {
        int z;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter number 1-9");
            z = scanner.nextInt();
            scanner.close();
        } while (!isValid(x, y, z));
        boardS[x][y] = "" + z;
    }

    private static void randomBoard() {
        Random random = new Random();
        int r = random.nextInt(15, 20);
        for (int index = 0; index <= r; index++) {
            String n = "";
            int row = 0;
            int col = 0;
            for (int i = 0; i < D; i++) {
                row = random.nextInt(D);
                col = random.nextInt(D);
                n = "" + (random.nextInt(D) + 1);
                if (!boardS[row][col].equals(EMPTY_CELL)) {
                    i = -1;
                    continue;
                }
                for (int j = 0; j < D; j++) {
                    if (boardS[i][col].equals(n)) {
                        i = -1;
                        break;
                    }
                    if (boardS[row][j].equals(n)) {
                        i = -1;
                        break;
                    }
                }
                int X = row / 3;
                X *= 3;
                int Y = col / 3;
                Y *= 3;
                for (int k = 0; k < 3 && i != -1; k++) {
                    for (int j = 0; j < 3; j++) {
                        if (boardS[X + k][Y + j].equals(String.valueOf(n))) {
                            i = -1;
                            break;
                        }
                    }
                }
            }
            boardS[row][col] = n;
        }
    }

    private static void drawBoard() {
        System.out.println("   1  2  3 || 4  5  6 || 7  8  9");
        System.out.println("  ===============================");
        for (int i = 0; i < D; i++) {
            System.out.print((i + 1) + EMPTY_CELL);
            for (int j = 0; j < D; j++) {
                System.out.print(boardS[i][j]);
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

    private static boolean gameOver() {
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                if (boardS[i][j].equals(EMPTY_CELL))
                    return false;
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y, int z) {
        if (0 > z && z >= 9) {
            System.out.println("the number have to be between 1-9. Try again.");
            return false;
        } else {
            for (int i = 0; i < D; i++) {
                if (boardS[x][i].equals(String.valueOf(z))) {
                    System.out.println("the number is exist in line " + (i + 1) + ". Try again.");
                    return false;
                }
                if (boardS[i][y].equals(String.valueOf(z))) {
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
                    if (boardS[X + i][Y + j].equals(String.valueOf(z))) {
                        System.out.println("the number is exist in squir " + (x / 3) + "," + (y / 3) + ". Try again.");
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private static boolean isValid(int a, int b) {
        return 0 <= a && a < 9 && 0 <= b && b < 9;
    }

}

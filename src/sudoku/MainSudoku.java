package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MainSudoku {
    private static int[][] boardS = new int[][] {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    public static void mainSudoku() {
        randomBoard();
        while (!gameOver()) {
            Scanner snc = new Scanner(System.in);
            drawBoard();
            String a;
            do {
                System.out.println("do u want to add or remove number [A/R]");
                a = snc.next();
            } while (!"a".equalsIgnoreCase(a) && !"r".equalsIgnoreCase(a));
            int x, y;
            do {
                System.out.println("enter row and culem 1-9");
                x = snc.nextInt();
                y = snc.nextInt();
            } while (!isValid(x, y));
            if ("a".equalsIgnoreCase(a)) {
                int z;
                do {
                    System.out.println("enter number 1-9");
                    z = snc.nextInt();
                } while (!isValid(x, y, z));
                boardS[x - 1][y - 1] = z;
            } else
                boardS[x - 1][y - 1] = 0;

        }
        System.out.println("game over!");
    }

    private static void randomBoard() {
        Random random = new Random();
        int r = random.nextInt(15, 20);
        for (int index = 0; index <= r; index++) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            int n = 0;
            outerLoop: for (int i = 0; i < 9; i++) {
                n = random.nextInt(9);
                for (int j = 0; j < 9; j++) {
                    if (boardS[i][col]==n){
                        i=-1;
                        continue outerLoop; 
                    }
                    if (boardS[row][j]==n){
                        i=-1;
                        continue outerLoop; 
                    }
                }
            }
            boardS[row][col] = n;
        }
    }

    private static void drawBoard() {
        System.out.print("   1  2  3  4  5  6  7  8  9");
        for (int i = 0; i < 9; i++) {
            System.out.print("\n" + (i + 1) + "|");
            for (int j = 0; j < 9; j++) {
                if (boardS[i][j] == 0)
                    System.out.print("  ");
                else
                    System.out.print(" " + boardS[i][j]);
                System.out.print("|");
            }
            System.out.print("\n ----------------------------");
        }
        System.out.println();
    }

    private static boolean gameOver() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (boardS[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y, int z) {
        if (0 >= z && z >= 10) {
            System.out.println("the number have to be between 1-9. Try again.");
            return false;
        } else {
            for (int i = 0; i < 9; i++) {
                if (boardS[x][i] == z) {
                    System.out.println("the number is exist in line " + (i + 1) + ". Try again.");
                    return false;
                }
                if (boardS[i][y] == z) {
                    System.out.println("the number is exist in colume " + (i + 1) + ". Try again.");
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean isValid(int a, int b) {
        return 0 < a && a < 10 && 0 < b && b < 10;
    }

}

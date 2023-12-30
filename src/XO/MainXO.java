package XO;

import java.util.Scanner;

public class MainXO {

    public static boolean x = false;

    public static void play() {

        Board[][] boardA = new Board[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardA[i][j] = new Board();
            }
        }
        drawMainBoard(boardA);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first row column to start");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (!isVRowCol(boardA, row, col)) {
            System.out.println("Invalid case. Try again.");
            scanner.close();
            return;
        }
        while (!isGameOver(boardA)) {
            changePlayer();
            while (!isVRowCol(boardA, row, col)) {
                try {
                    System.out.println("Enter another row column to countine");
                    row = scanner.nextInt();
                    col = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter valid row and column numbers.");
                    scanner.nextLine();
                    row = col = -1;
                }
                if (!isVRowCol(boardA, row, col)) {
                    System.out.println("Invalid case. Try again.");
                }
            }
            int subRow;
            int subCol;
            do {
                try {
                    System.out.println(
                            "Enter your move player " + getPlayer() + " in row " + row + ", column " + col + ":");
                    subRow = scanner.nextInt();
                    subCol = scanner.nextInt();
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter valid row and column numbers.");
                    scanner.nextLine();
                    subRow = subCol = -1;
                }
                if (isVSRowCol(boardA, row, col, subRow, subCol)) {
                    boardA[row][col].board[subRow][subCol] = getPlayer();
                    checkForWin(boardA, row, col);
                    checkForDraw(boardA, row, col);
                    row = subRow;
                    col = subCol;
                    drawMainBoard(boardA);
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } while (!isVSRowCol(boardA, row, col, subRow, subCol));
        }

        drawMainBoard(boardA);
        System.out.println("Game over!");
        scanner.close();
    }

    public static void drawMainBoard(Board[][] boardA) {
        System.out.println("   1  2  3 || 1  2  3 || 1  2  3");
        System.out.println("  ===============================");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.println(
                        (i + 1) + " " + boardA[j][0].board[i][0] + " | " + boardA[j][0].board[i][1] + " | " +
                                boardA[j][0].board[i][2] + "||" + boardA[j][1].board[i][0] + " | " +
                                boardA[j][1].board[i][1] + " | " + boardA[j][1].board[i][2] + "||" +
                                boardA[j][2].board[i][0] + " | " + boardA[j][2].board[i][1] + " | " +
                                boardA[j][2].board[i][2]);
                if (i != 2)
                    System.out.println("  ---------  ---------  ---------");
                else
                    System.out.println("  ===============================");
            }
        }
    }

    public static boolean isGameOver(Board[][] board) {
        return checkForWin(board) || checkForDraw(board);
    }

    public static boolean checkForWin(Board[][] board) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].p.equals(getPlayer()) && board[i][1].p.equals(getPlayer())
                    && board[i][2].p.equals(getPlayer()))
                    || (board[0][i].p.equals(getPlayer())
                            && board[1][i].p.equals(getPlayer()) && board[2][i].p.equals(getPlayer()))) {
                return true;
            }
        }
        if ((board[0][0].p.equals(getPlayer()) && board[1][1].p.equals(getPlayer())
                && board[2][2].p.equals(getPlayer()))
                || (board[0][2].p.equals(getPlayer())
                        && board[1][1].p.equals(getPlayer()) && board[2][0].p.equals(getPlayer()))) {
            return true;
        }
        return false;
    }

    public static boolean checkForDraw(Board[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].p.equals("S")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkForWin(Board[][] boardA, int row, int col) {
        boolean var = false;
        if ((boardA[row][col].board[0][0].equals(getPlayer())
                && boardA[row][col].board[1][1].equals(getPlayer())
                && boardA[row][col].board[2][2].equals(getPlayer()))
                || (boardA[row][col].board[0][2].equals(getPlayer())
                        && boardA[row][col].board[1][1].equals(getPlayer())
                        && boardA[row][col].board[2][0].equals(getPlayer()))) {
            var = true;
        } else {
            for (int i = 0; i < 3; i++) {
                if ((boardA[row][col].board[i][0].equals(getPlayer())
                        && boardA[row][col].board[i][1].equals(getPlayer())
                        && boardA[row][col].board[i][2].equals(getPlayer()))
                        || (boardA[row][col].board[0][i].equals(getPlayer())
                                && boardA[row][col].board[1][i].equals(getPlayer())
                                && boardA[row][col].board[2][i].equals(getPlayer()))) {
                    var = true;
                    break;
                }
            }
        }
        if (var) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    boardA[row][col].board[j][i] = getPlayer();
                    if (boardA[i][j].board[row][col].equals(" ")) {
                        boardA[i][j].board[row][col] = getPlayer();
                    }
                }
            }
            boardA[row][col].p = getPlayer();
        }
    }

    public static void checkForDraw(Board[][] boardA, int row, int col) {
        boolean var = true;
        for (int i = 0; i < 3 && var; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardA[row][col].board[i][j].equals(" ") || boardA[row][col].p.equals(getPlayer())) {
                    var = false;
                    break;
                }
            }
        }
        if (var) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    boardA[row][col].board[j][i] = "D";
                    if (boardA[i][j].board[row][col].equals(" ")) {
                        boardA[i][j].board[row][col] = "D";
                    }
                }
            }
            boardA[row][col].p = "D";
        }
    }

    public static boolean isVRowCol(Board[][] boardA, int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 &&
                boardA[row][col].p.equals("S");
    }

    public static boolean isVSRowCol(Board[][] boardA, int row, int col, int subRow, int subCol) {
        return subRow >= 0 && subRow < 3 && subCol >= 0 && subCol < 3 &&
                boardA[row][col].board[subRow][subCol].equals(" ");
    }

    public static String getPlayer() {
        return x ? "X" : "O";
    }

    public static void changePlayer() {
        x = !x;
    }
}

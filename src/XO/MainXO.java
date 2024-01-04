package XO;

import java.util.Scanner;

import game.Game;

public class MainXO extends Game {
    private static boolean x = false;
    static BoardXO board = new BoardXO();

    static DetailsXO details = new DetailsXO();

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        int row;
        int col;
        do {
            try {
                board.draw(0);// hatit 0 bah dir override brk ma3ndo hta m3na ho
                System.out.println("first which board u want to start with enter row and column");
                row = scanner.nextInt() - 1;
                col = scanner.nextInt() - 1;
                if (!board.isValid(row, col)) {
                    System.out.println("Invalid case. Try again.");
                } else {
                    DetailsXO.setNoValidCases(board.getBoard(), row, col);
                    board.draw();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter valid row and column numbers.");
                scanner.nextLine();
                row = col = -1;
            }
        } while (!board.isValid(row, col));
        while (!gameOver()) {
            changePlayer();
            while (!board.isValid(row, col)) {
                try {
                    board.draw(0);
                    System.out.println(
                            "u cant play in board " + (row + 1) + ", " + (col + 1)
                                    + "Enter another row column to countine");
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter valid row and column numbers.");
                    scanner.nextLine();
                    row = col = -1;
                }
                if (!board.isValid(row, col)) {
                    System.out.println("Invalid case. Try again.");
                } else {
                    DetailsXO.setNoValidCases(board.getBoard(), row, col);
                    board.draw();
                }

            }
            int subRow;
            int subCol;
            boolean isValid;
            do {
                try {
                    System.out.println(
                            "Enter your move player " + getPlayer() + " in board " + (row + 1) + ", " + (col + 1)
                                    + ":");
                    subRow = scanner.nextInt() - 1;
                    subCol = scanner.nextInt() - 1;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter valid row and column numbers.");
                    scanner.nextLine();
                    subRow = subCol = -1;
                }
                isValid = board.isValid(row, col, subRow, subCol);
                if (isValid) {
                    board.getBoard()[row * 3 + subRow][col * 3 + subCol] = getPlayer();
                    details.setRow(row * 3 + subRow);
                    details.setCol(col * 3 + subCol);
                    checkForWin(row, col);
                    checkForDraw(row, col);
                    row = subRow;
                    col = subCol;
                    DetailsXO.setNoValidCases(board.getBoard(), row, col);
                    board.draw();
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } while (!isValid);
        }

        board.draw();
        System.out.println("Game over!");
        scanner.close();
    }

    @Override
    public boolean gameOver() {
        return checkForWin() || checkForDraw();
    }

    public static boolean checkForWin() {
        return checkRowCol() || checkDig();
    }

    private static boolean checkDig() {
        if ((board.boardStatus[0][0].equals(getPlayer()) && board.boardStatus[1][1].equals(getPlayer())
                && board.boardStatus[2][2].equals(getPlayer()))
                || (board.boardStatus[0][2].equals(getPlayer())
                        && board.boardStatus[1][1].equals(getPlayer())
                        && board.boardStatus[2][0].equals(getPlayer()))) {
            return true;
        }
        return false;
    }

    private static boolean checkRowCol() {
        for (int i = 0; i < 3; i++) {
            if ((board.boardStatus[i][0].equals(getPlayer()) && board.boardStatus[i][1].equals(getPlayer())
                    && board.boardStatus[i][2].equals(getPlayer()))
                    || (board.boardStatus[0][i].equals(getPlayer())
                            && board.boardStatus[1][i].equals(getPlayer())
                            && board.boardStatus[2][i].equals(getPlayer()))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.boardStatus[i][j].equals("S")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void checkForWin(int row, int col) {
        boolean var = false;
        if ((board.getBoard()[row * 3][col * 3].equals(getPlayer())
                && board.getBoard()[row * 3 + 1][col * 3 + 1].equals(getPlayer())
                && board.getBoard()[row * 3 + 2][col * 3 + 2].equals(getPlayer()))
                || (board.getBoard()[row * 3][col * 3 + 2].equals(getPlayer())
                        && board.getBoard()[row * 3 + 1][col * 3 + 1].equals(getPlayer())
                        && board.getBoard()[row * 3 + 2][col * 3].equals(getPlayer()))) {
            var = true;
        } else {
            for (int i = 0; i < 3; i++) {
                if ((board.getBoard()[row * 3][col * 3 + i].equals(getPlayer())
                        && board.getBoard()[row * 3 + 1][col * 3 + i].equals(getPlayer())
                        && board.getBoard()[row * 3 + 2][col * 3 + i].equals(getPlayer()))
                        || (board.getBoard()[row * 3 + i][col * 3].equals(getPlayer())
                                && board.getBoard()[row * 3 + i][col * 3 + 1].equals(getPlayer())
                                && board.getBoard()[row * 3 + i][col * 3 + 2].equals(getPlayer()))) {
                    var = true;
                    break;
                }
            }
        }
        if (var) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    board.getBoard()[row * 3 + i][col * 3 + j] = getPlayer();
                    if (board.getBoard()[i * 3 + row][j * 3 + col].equals(" ") ||
                            board.getBoard()[i * 3 + row][j * 3 + col].equals(".")) {
                        board.getBoard()[i * 3 + row][j * 3 + col] = getPlayer();
                        checkForWin(i, j);
                        checkForDraw(i, j);
                    }
                }
            }
            board.boardStatus[row][col] = getPlayer();
        }
    }

    public static void checkForDraw(int row, int col) {
        boolean var = true;
        for (int i = 0; i < 3 && var; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getBoard()[row * 3 + i][col * 3 + j].equals(" ")
                        || board.getBoard()[row * 3 + i][col * 3 + j].equals(".")
                        || board.boardStatus[row][col].equals(getPlayer())) {
                    var = false;
                    break;
                }
            }
        }
        if (var) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 3; i++) {
                    board.boardStatus[row][col] = "D";
                    if (board.getBoard()[i * 3 + row][j * 3 + col].equals(" ")
                            || board.getBoard()[i * 3 + row][j * 3 + col].equals(".")) {
                        board.getBoard()[i * 3 + row][j * 3 + col] = "D";
                        checkForDraw(i, j);
                    }
                }
            }
            board.boardStatus[row][col] = "D";
        }
    }

    static void changePlayer() {
        x = !x;
    }

    static String getPlayer() {
        return x ? "X" : "O";
    }

}

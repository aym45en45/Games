
import java.util.Scanner;

public class Main {
    private static boolean x = false;
    private Board[][] boardA;
    private Scanner scanner;

    public static String getPlayer() {
        return x ? "X" : "O";
    }

    private void changePlayer() {
        x = !x;
    }

    public Main() {
        boardA = new Board[3][3];
        scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardA[i][j] = new Board();
            }
        }
    }

    public void drawMainBoard() {
        System.out.println("-----------------------------------");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                System.out.println(
                    boardA[j][0].board[i][0] + " | " + boardA[j][0].board[i][1] + " | " + boardA[j][0].board[i][2] +
                    "    " + boardA[j][1].board[i][0] + " | " + boardA[j][1].board[i][1] + " | " +
                    boardA[j][1].board[i][2] + "    " + boardA[j][2].board[i][0] + " | " +
                    boardA[j][2].board[i][1] + " | " + boardA[j][2].board[i][2]);
                if (i != 2 && i != 5 && i != 8)
                    System.out.println("---------    ---------    ---------");
                else
                    System.out.println("\n");
            }
        }
    }

    public boolean isGameOver(Board[][] board) {
        return checkForWin(board) || checkForDraw(board);
    }

    private static boolean checkForWin(Board[][] board) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0].p.equals(getPlayer()) && board[i][1].p.equals(getPlayer()) && board[i][2].p.equals(getPlayer())) ||
                (board[0][i].p.equals(getPlayer()) && board[1][i].p.equals(getPlayer()) && board[2][i].p.equals(getPlayer()))) {
                return true;
            }
        }
        if ((board[0][0].p.equals(getPlayer()) && board[1][1].equals(getPlayer()) && board[2][2].p.equals(getPlayer())) ||
            (board[0][2].p.equals(getPlayer()) && board[1][1].equals(getPlayer()) && board[2][0].p.equals(getPlayer()))) {
            return true;
        }
        return false;
    }

    private boolean checkForDraw(Board[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].p.equals("S")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void checkForSWin(Board[][] boardA, int row, int col) {
        boolean var = false;
        if ((boardA[row][col].board[0][0].equals(getPlayer()) && boardA[row][col].board[1][1].equals(getPlayer()) && boardA[row][col].board[2][2].equals(getPlayer())) ||
            (boardA[row][col].board[0][2].equals(getPlayer()) && boardA[row][col].board[1][1].equals(getPlayer()) && boardA[row][col].board[2][0].equals(getPlayer()))) {
            var = true;
        } else {
            for (int i = 0; i < 3; i++) {
                if ((boardA[row][col].board[i][0].equals(getPlayer()) && boardA[row][col].board[i][1].equals(getPlayer()) && boardA[row][col].board[i][2].equals(getPlayer())) ||
                    (boardA[row][col].board[0][i].equals(getPlayer()) && boardA[row][col].board[1][i].equals(getPlayer()) && boardA[row][col].board[2][i].equals(getPlayer()))) {
                    var = true;break;
                }
            }
        }
        if(var){
          for (int j = 0; j < 3; j++) {
              for (int i = 0; i < 3; i++) {
                  boardA[row][col].board[j][i] = getPlayer();
                  if (boardA[row][col].board[row][col].equals(" ")) {
                      boardA[row][col].board[row][col] = getPlayer();
                  }
              }
          }
          boardA[row][col].p = getPlayer();
        }
    }
    private void checkForSDraw(Board[][] board,int row,int col) {
        boolean var = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardA[row][col].board[i][j].equals(" ")||boardA[row][col].p.equals(getPlayer())){
                    var= false;
                }
            }
        }
        if(var){
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                boardA[row][col].board[j][i] = "D";
                if (boardA[row][col].board[row][col].equals(" ")) {
                    board[row][col] = "D";
                }
            }
        }
        boardA[row][col].p = "D";}
    }
    public void play() {
        drawMainBoard();
        System.out.println("Enter first row column to start");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        if (!isVRowCol(row, col)) {
            System.out.println("Invalid case. Try again.");
            return;
        }
        while (!isGameOver(boardA)) {
            changePlayer();
            while (!isVRowCol(row, col)) {
                System.out.println("Enter another row column to countine");
                row = scanner.nextInt();
                col = scanner.nextInt();
                if (!isVRowCol(row, col)) {
                    System.out.println("Invalid case. Try again.");
                }
            }
            System.out.println("Enter your move player " + getPlayer() + " in row " + row + ", column " + col + ":");
            int subRow = scanner.nextInt();
            int subCol = scanner.nextInt();
            if (isVSRowCol(row, col, subRow, subCol)) {
                boardA[row][col].board[subRow][subCol] = getPlayer();
                this.checkForSWin(boardA[row][col].board,row,col);
                this.checkForSDraw(boardA[row][col].board,row,col);
                row = subRow;
                col = subCol;
                drawMainBoard();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }

        drawMainBoard();
        System.out.println("Game over!");
    }

    private boolean isVRowCol(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 &&
            boardA[row][col].p.equals("S");
    }

    private boolean isVSRowCol(int row, int col, int subRow, int subCol) {
        return subRow >= 0 && subRow < 3 && subCol >= 0 && subCol < 3 &&
            boardA[row][col].board[subRow][subCol].equals(" ");
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }

    private static class Board {
        String[][] board;
        String p = "S";

        public Board() {
            board = new String[][] {
                {
                    " ",
                    " ",
                    " "
                }, {
                    " ",
                    " ",
                    " "
                }, {
                    " ",
                    " ",
                    " "
                }
            };
        }
    }
}

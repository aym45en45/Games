package XO;

import game.Details;

public class DetailsXO extends Details {
    static void setNoValidCases(String[][] noValidBoard, int row, int col) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (noValidBoard[i][j].equals("."))
                    noValidBoard[i][j] = " ";
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (noValidBoard[i][j].equals(" ") && (i / 3 != row || j / 3 != col))
                    noValidBoard[i][j] = ".";
                else if (noValidBoard[i][j].equals("."))
                    noValidBoard[i][j] = " ";
            }
        }
    }
}

package sudoku;

import java.util.ArrayList;

import game.Board;

public class BoardSudoku extends Board{
    static String[][] boardSolved;
    private static ArrayList<Details> randomBoardDetails;
    public BoardSudoku(){
        initialize();
    }

    @Override
    public void draw() {
        System.out.println("   1  2  3|| 4  5  6|| 7  8  9");
        System.out.println("  ============================");
        for (int i = 0; i < D; i++) {
            System.out.print((i + 1) + super.EMPTY_CELL);
            for (int j = 0; j < D; j++) {
                if (isPositionInRandomBoardDetails(i, j)) {
                    System.out.print("*" + board[i][j]);
                } else {
                    System.out.print(" " + board[i][j]);
                }
                if ((j+1)%3!=0)
                    System.out.print("|");
                else if (j == 2 || j == 5)
                    System.out.print("||");
                else
                    System.out.println();
            }
            if ((i+1)%3!=0)
                System.out.println("  --------  --------  --------");
            else
                System.out.println("  ============================");
        }

    }

    @Override
    public boolean isValid(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValid'");
    }


    static ArrayList<Details> getRandomBoardDetails() {
        return randomBoardDetails;
    }
    
}
class Details {
    int row;
    int col;
}
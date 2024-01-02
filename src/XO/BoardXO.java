package XO;

import game.Board;

class BoardXO extends Board {
    String[][] boardStatus;

    public BoardXO() {
        super.initialize();
        boardStatus = new String[][] {
                { "S", "S", "S" },
                { "S", "S", "S" },
                { "S", "S", "S" }
        };
    }

    @Override
    public void draw() {
        System.out.println("   1  2  3 || 1  2  3 || 1  2  3");
        System.out.println("  ===============================");
        for (int i = 0; i < D; i++) {
            System.out.println((i / 3 + 1) + " ");
            for (int j = 0; j < D; j++) {
                System.out.print(" " + board[i][j]);
                if ((j + 1) % 3 != 0)
                    System.out.print("|");
                else if (j == 2 || j == 5)
                    System.out.print("||");
                else
                    System.out.println();
            }
            if ((i + 1) % 3 != 0)
                System.out.println("  ---------  ---------  ---------");
            else
                System.out.println("  ===============================");
        }
    }

    @Override
    public boolean isValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 &&
                boardStatus[row][col].equals("S");
    }

}

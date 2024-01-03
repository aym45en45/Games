package XO;

import game.Board;

class BoardXO extends Board {
    String[][] boardStatus;

    public BoardXO() {
        initialize();
        boardStatus = new String[][] {
                { "S", "S", "S" },
                { "S", "S", "S" },
                { "S", "S", "S" }
        };
    }

    @Override
    public void draw() {
        System.out.println("   1   2   3 || 1   2   3 || 1   2   3 ");
        System.out.println("  =====================================");
        for (int i = 0; i < D; i++) {
            System.out.print((i % 3 + 1) + " ");
            for (int j = 0; j < D; j++) {
                if (MainXO.details.getRow() == i && MainXO.details.getCol() == j) {
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

    public void draw(int i) {
        System.out.println("       1     ||     2     ||     3     ");
        System.out.println("  =====================================");
        for (i = 0; i < D; i++) {
            if (i == 1 || i == 4 || i == 7)
                System.out.print((i / 3 + 1) + " ");
            else
                System.out.print("  ");
            for (int j = 0; j < D; j++) {
                System.out.print(" " + board[i][j] + " ");
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
    public boolean isValid(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 &&
                boardStatus[row][col].equals("S");
    }

    public boolean isValid(int row, int col, int subRow, int subCol) {
        return subRow >= 0 && subRow < 3 && subCol >= 0 && subCol < 3 &&
                board[row * 3 + subRow][col * 3 + subCol].equals(" ");
    }

}

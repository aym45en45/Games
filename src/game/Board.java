package game;

public abstract class Board {
    protected static final int D = 9;
    private static final String EMPTY_CELL = " ";
    protected String[][] board;

    
    public abstract void draw();

    public abstract boolean isValid(int x, int y);

    public void initialize() {
        this.board = new String[D][D];
        for (int i = 0; i < D; i++) {
            for (int j = 0; j < D; j++) {
                this.board[i][j] = EMPTY_CELL;
            }
        }
    }
    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }
}


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testCheckForWin() {
        Board[][] boardA = new Board[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardA[i][j] = new Board();
            }
        }

        // Set up a winning condition
        for (int j = 0; j < 3; j++) {
            boardA[j][j].p = Main.getPlayer();
        }

        assertTrue(Main.checkForWin(boardA));

        for (int j = 0; j < 3; j++) {
            boardA[j][j].p = " ";
        }

        for (int j = 0; j < 3; j++) {
            boardA[2 - j][j].p = Main.getPlayer();
        }

        assertTrue(Main.checkForWin(boardA));

        for (int j = 0; j < 3; j++) {
            boardA[2 - j][j].p = " ";
        }
        for (int j = 0; j < 3; j++) {
            boardA[2][j].p = Main.getPlayer();
        }

        assertTrue(Main.checkForWin(boardA));
        for (int j = 0; j < 3; j++) {
            boardA[2][j].p = " ";
        }

        for (int j = 0; j < 3; j++) {
            boardA[j][1].p = Main.getPlayer();
        }

        assertTrue(Main.checkForWin(boardA));

    }

    @Test
    void testCheckForDraw() {
        Board[][] board = new Board[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Board();
            }
        }
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k++;
                if (k % 2 == 0)
                    board[i][j].p = "X";
                else
                    board[i][j].p = "O";
            }
        }

        assertTrue(Main.checkForDraw(board));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].p = "S";
            }
        }
    }
}

package src.XO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainXO_Test {
    Board[][] boardA = new Board[3][3];
    @BeforeEach
    void createBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardA[i][j] = new Board();
            }
        }
    }

    @Test
    void testCheckForWin() {
        
        for (int j = 0; j < 3; j++) {
            boardA[j][j].p = MainXO.getPlayer();
        }

        assertTrue(MainXO.checkForWin(boardA));

        for (int j = 0; j < 3; j++) {
            boardA[j][j].p = " ";
        }

        for (int j = 0; j < 3; j++) {
            boardA[2 - j][j].p = MainXO.getPlayer();
        }

        assertTrue(MainXO.checkForWin(boardA));

        for (int j = 0; j < 3; j++) {
            boardA[2 - j][j].p = " ";
        }
        for (int j = 0; j < 3; j++) {
            boardA[2][j].p = MainXO.getPlayer();
        }

        assertTrue(MainXO.checkForWin(boardA));
        for (int j = 0; j < 3; j++) {
            boardA[2][j].p = " ";
        }

        for (int j = 0; j < 3; j++) {
            boardA[j][1].p = MainXO.getPlayer();
        }

        assertTrue(MainXO.checkForWin(boardA));

    }

    @Test
    void testCheckForDraw() {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k++;
                if (k % 2 == 0)
                    boardA[i][j].p = "X";
                else
                    boardA[i][j].p = "O";
            }
        }

        assertTrue(MainXO.checkForDraw(boardA));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardA[i][j].p = "S";
            }
        }
    }

    @Test
    public void testCheckForSWin() {
        boardA[0][0].board[0][0]=MainXO.getPlayer();
        boardA[0][0].board[0][1]=MainXO.getPlayer();
        boardA[0][0].board[0][2]=MainXO.getPlayer();
        MainXO.changePlayer();
        boardA[2][0].board[0][0]=MainXO.getPlayer();
        MainXO.changePlayer();

        // Call the method to be tested
        MainXO.checkForWin(boardA, 0,0);

        // Assert the expected result
        assertEquals(MainXO.getPlayer(), boardA[0][0].p);
        assertEquals(MainXO.getPlayer(), boardA[0][0].board[0][0]);
        assertEquals(MainXO.getPlayer(), boardA[1][1].board[0][0]);
        assertEquals(MainXO.getPlayer(), boardA[2][2].board[0][0]);
        MainXO.changePlayer();
        assertEquals(MainXO.getPlayer(), boardA[2][0].board[0][0]);
    }

    @Test
    public void testCheckForSDraw() {

        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                k++;
                if (k % 2 == 0)
                    boardA[0][0].board[i][j] = "X";
                else
                    boardA[0][0].board[i][j] = "O";
            }
        }

        // Call the method to be tested
        MainXO.checkForDraw(boardA, 0,0);

        // Assert the expected result
        assertEquals("D", boardA[0][0].p);
        assertEquals("D", boardA[0][0].board[0][0]);
        assertEquals("D", boardA[1][1].board[0][0]);
        assertEquals("D", boardA[2][2].board[0][0]);
        assertEquals(" ", boardA[2][0].board[1][0]);
    }
}

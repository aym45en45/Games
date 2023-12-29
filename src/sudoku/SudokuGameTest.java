package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainSudokuTest {

    @Test
    void testAddNumber() {
        MainSudoku.initializeBoard();
        MainSudoku.addNumber(0, 0, new java.util.Scanner("1"));
        assertEquals("1", MainSudoku.getBoard()[0][0]);
    }

    @Test
    void testRemoveNumber() {
        MainSudoku.initializeBoard();
        MainSudoku.addNumber(0, 0, new java.util.Scanner("1"));
        assertEquals("1", MainSudoku.getBoard()[0][0]);
        MainSudoku.removeNumber(0, 0);
        assertEquals(" ", MainSudoku.getBoard()[0][0]);
    }

    @Test
    void testIsValid() {
        MainSudoku.initializeBoard();
        assertFalse(MainSudoku.isValid(0, 9));
        assertFalse(MainSudoku.isValid(9, 0));
        assertFalse(MainSudoku.isValid(9, 6));
        assertFalse(MainSudoku.isValid(9, 6));

        assertTrue(MainSudoku.isValid(0, 0));

        assertFalse(MainSudoku.isValid(0, 0, 0,true));
        assertFalse(MainSudoku.isValid(0, 0, 10,true));

        assertTrue(MainSudoku.isValid(0, 0, 3,true));

        MainSudoku.addNumber(0, 0, new java.util.Scanner("2"));
        assertFalse(MainSudoku.isValid(0, 8, 2,true));
        assertFalse(MainSudoku.isValid(8, 0, 2,true));
        assertFalse(MainSudoku.isValid(1, 1, 2,true));
        assertTrue(MainSudoku.isValid(3, 1, 2,true));
    }

    @Test
    void testGameOver() {
        MainSudoku.initializeBoard();
        assertFalse(MainSudoku.gameOver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                MainSudoku.getBoard()[i][j] = "1";
            }
        }
        assertTrue(MainSudoku.gameOver());
        MainSudoku.getBoard()[0][0] = " ";
        assertFalse(MainSudoku.gameOver());
    }
    @Test
    void randomBoard(){

    }
}

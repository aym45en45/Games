package sudoku;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class BoardSudokuTest {
    BoardSudoku sudoku = new BoardSudoku();
    @Test
    void testAddNumber() {
        
        MainSudoku.addNumber(0, 0, new java.util.Scanner("1"));
        assertEquals("1", MainSudoku.getBoard()[0][0]);
    }

    @Test
    void testRemoveNumber() {
        
        MainSudoku.addNumber(0, 0, new java.util.Scanner("1"));
        assertEquals("1", MainSudoku.getBoard()[0][0]);
        MainSudoku.removeNumber(0, 0);
        assertEquals(" ", MainSudoku.getBoard()[0][0]);
    }

    @Test
    void testIsValid() {
        
        assertFalse(MainSudoku.isValid(0, 9));
        assertFalse(MainSudoku.isValid(9, 0));
        assertFalse(MainSudoku.isValid(9, 6));
        assertFalse(MainSudoku.isValid(9, 6));

        assertTrue(MainSudoku.isValid(0, 0));

        assertFalse(MainSudoku.isValid(MainSudoku.getBoard(),0, 0, 0, true));
        assertFalse(MainSudoku.isValid(MainSudoku.getBoard(),0, 0, 10, true));

        assertTrue(MainSudoku.isValid(MainSudoku.getBoard(),0, 0, 3, true));

        MainSudoku.addNumber(0, 0, new java.util.Scanner("2"));
        assertFalse(MainSudoku.isValid(MainSudoku.getBoard(),0, 8, 2, true));
        assertFalse(MainSudoku.isValid(MainSudoku.getBoard(),8, 0, 2, true));
        assertFalse(MainSudoku.isValid(MainSudoku.getBoard(),1, 1, 2, true));
        assertTrue(MainSudoku.isValid(MainSudoku.getBoard(),3, 1, 2, true));
    }

    @Test
    void testGameOver() {
        
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
    void randomBoard() {
        
        MainSudoku.generateRandomBoard();
        for (Details details : MainSudoku.getRandomBoardDetails()) {
            int n = Integer.parseInt(MainSudoku.getBoard()[details.row][details.col]);
            assertTrue(MainSudoku.isValid(MainSudoku.getBoard(),details.row, details.col, n, true));
        }
        assertTrue(MainSudoku.solve(MainSudoku.getBoard()));
    }

    @Test
    void testSolve() {
        String[][] board = new String[][] {
            { " ", "7", "9", "8", " ", "2", " ", "6" ,"3"},
            { "6", " ", " ", "9", " ", " ", " ", "1" ," "},
            { "8", " ", "3", " ", "7", " ", " ", " " ,"2"},
            { " ", "9", " ", " ", " ", " ", "3", "7" ,"1"},
            { " ", "6", "8", "7", " ", " ", " ", "9" ," "},
            { " ", "3", "1", " ", "2", " ", "5", "8" ," "},
            { "2", "8", "6", "5", " ", " ", "1", "3" ," "},
            { " ", " ", " ", " ", " ", " ", " ", " " ," "},
            { "9", " ", "4", "3", " ", " ", "8", "2" ,"7"},
        }; 
        assertTrue(BoardSudoku.solve(board));
    }
}

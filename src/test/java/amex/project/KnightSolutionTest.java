package amex.project;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KnightSolutionTest {
    private KnightSolution knightSolution;

    @Before
    void setUp() throws Exception {
        knightSolution = new KnightSolution();
    }

    @Test
    void testIsValidMove() {

        int[][] board = {
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1},
                {-1, -1, -1, -1}
        };
        int boardSize = 4;

        assertTrue(knightSolution.isValidMove(board, 2, 2, boardSize));
        assertFalse(knightSolution.isValidMove(board, -1, 2, boardSize));
        assertFalse(knightSolution.isValidMove(board, 2, 4, boardSize));
        assertFalse(knightSolution.isValidMove(board, 1, 1, boardSize));
    }
}
package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Knight;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class Knight.
 */
public class KnightTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testKnight;

    /**
     * testKnight unit test the constructor for correct initial conditions.
     */
    public void testKnight() {
        Point testPosition = new Point(0, 0);
        testKnight = new Knight(Color.BLACK, testPosition);
        assertNotNull(testKnight);
        assertEquals(Color.BLACK, testKnight.color);
        assertEquals(testPosition, testKnight.position);
        assertNotNull(testKnight.validMoveSquares);
        assertNotNull(testKnight.validAttackSquares);
        assertTrue(testKnight.validMoveSquares.isEmpty());
        assertTrue(testKnight.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a Knight piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, false);
        testKnight = testBoard.pieces[1][0];
        testBoard.movePiece(testKnight, new Point(3, 3));
        assertTrue(testKnight.validMoveSquares.isEmpty());
        assertTrue(testKnight.validAttackSquares.isEmpty());
        testKnight.populateValidSquares(testBoard);
        assertEquals(6, testKnight.validMoveSquares.size());
        assertEquals(6, testKnight.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, false);
        testKnight = testBoard.pieces[1][0];
        testBoard.movePiece(testKnight, new Point(3, 3));
        testKnight.populateValidSquares(testBoard);
        assertFalse(testKnight.validMoveSquares.contains(new Point(2, 4)));
        assertFalse(testKnight.validAttackSquares.contains(new Point(2, 4)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, false);

        testKnight = testBoard.pieces[1][7];
        assertEquals("N", testKnight.toString());

        testKnight = testBoard.pieces[1][0];
        assertEquals("n", testKnight.toString());
    }
}
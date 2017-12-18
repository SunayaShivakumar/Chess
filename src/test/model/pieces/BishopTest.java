package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Bishop;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class Bishop.
 */
public class BishopTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testBishop;

    /**
     * testBishop unit test the constructor for correct initial conditions.
     */
    public void testBishop() {
        Point testPosition = new Point(0, 0);
        testBishop = new Bishop(Color.BLACK, testPosition);
        assertNotNull(testBishop);
        assertEquals(Color.BLACK, testBishop.color);
        assertEquals(testPosition, testBishop.position);
        assertNotNull(testBishop.validMoveSquares);
        assertNotNull(testBishop.validAttackSquares);
        assertTrue(testBishop.validMoveSquares.isEmpty());
        assertTrue(testBishop.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a Bishop piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, false);
        testBishop = testBoard.pieces[2][0];
        testBoard.movePiece(testBishop, new Point(3, 3));
        assertTrue(testBishop.validMoveSquares.isEmpty());
        assertTrue(testBishop.validAttackSquares.isEmpty());
        testBishop.populateValidSquares(testBoard);
        assertEquals(8, testBishop.validMoveSquares.size());
        assertEquals(8, testBishop.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, false);
        testBishop = testBoard.pieces[2][0];
        testBoard.movePiece(testBishop, new Point(3, 3));
        testBishop.populateValidSquares(testBoard);
        assertFalse(testBishop.validMoveSquares.contains(new Point(2, 3)));
        assertFalse(testBishop.validAttackSquares.contains(new Point(2, 3)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, false);

        testBishop = testBoard.pieces[2][7];
        assertEquals("B", testBishop.toString());

        testBishop = testBoard.pieces[2][0];
        assertEquals("b", testBishop.toString());
    }
}
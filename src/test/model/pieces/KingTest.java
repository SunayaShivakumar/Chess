package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.King;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class King.
 */
public class KingTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testKing;

    /**
     * testKing unit test the constructor for correct initial conditions.
     */
    public void testKing() {
        Point testPosition = new Point(0, 0);
        testKing = new King(Color.BLACK, testPosition);
        assertNotNull(testKing);
        assertEquals(Color.BLACK, testKing.color);
        assertEquals(testPosition, testKing.position);
        assertNotNull(testKing.validMoveSquares);
        assertNotNull(testKing.validAttackSquares);
        assertTrue(testKing.validMoveSquares.isEmpty());
        assertTrue(testKing.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a King piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, false);
        testKing = testBoard.pieces[4][0];
        testBoard.movePiece(testKing, new Point(3, 3));
        assertTrue(testKing.validMoveSquares.isEmpty());
        assertTrue(testKing.validAttackSquares.isEmpty());
        testKing.populateValidSquares(testBoard);
        assertEquals(8, testKing.validMoveSquares.size());
        assertEquals(8, testKing.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, false);
        testKing = testBoard.pieces[4][0];
        testBoard.movePiece(testKing, new Point(3, 3));
        testKing.populateValidSquares(testBoard);
        assertFalse(testKing.validMoveSquares.contains(new Point(1, 5)));
        assertFalse(testKing.validAttackSquares.contains(new Point(1, 5)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, false);

        testKing = testBoard.pieces[4][7];
        assertEquals("K", testKing.toString());

        testKing = testBoard.pieces[4][0];
        assertEquals("k", testKing.toString());
    }
}
package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Rook;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class Rook.
 */
public class RookTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testRook;

    /**
     * testRook unit test the constructor for correct initial conditions.
     */
    public void testRook() {
        Point testPosition = new Point(0, 0);
        testRook = new Rook(main.model.properties.Color.BLACK, testPosition);
        assertNotNull(testRook);
        assertEquals(Color.BLACK, testRook.color);
        assertEquals(testPosition, testRook.position);
        assertNotNull(testRook.validMoveSquares);
        assertNotNull(testRook.validAttackSquares);
        assertTrue(testRook.validMoveSquares.isEmpty());
        assertTrue(testRook.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a Rook piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, false);
        testRook = testBoard.pieces[0][0];
        testBoard.movePiece(testRook, new Point(3, 3));
        assertTrue(testRook.validMoveSquares.isEmpty());
        assertTrue(testRook.validAttackSquares.isEmpty());
        testRook.populateValidSquares(testBoard);
        assertEquals(11, testRook.validMoveSquares.size());
        assertEquals(11, testRook.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, false);
        testRook = testBoard.pieces[0][0];
        testBoard.movePiece(testRook, new Point(3, 3));
        testRook.populateValidSquares(testBoard);
        assertFalse(testRook.validMoveSquares.contains(new Point(0, 7)));
        assertFalse(testRook.validAttackSquares.contains(new Point(0, 7)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, false);

        testRook = testBoard.pieces[0][7];
        assertEquals("R", testRook.toString());

        testRook = testBoard.pieces[0][0];
        assertEquals("r", testRook.toString());
    }
}
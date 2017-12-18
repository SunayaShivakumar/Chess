package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Queen;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class Queen.
 */
public class QueenTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testQueen;

    /**
     * testQueen unit test the constructor for correct initial conditions.
     */
    public void testQueen() {
        Point testPosition = new Point(0, 0);
        testQueen = new Queen(main.model.properties.Color.BLACK, testPosition);
        assertNotNull(testQueen);
        assertEquals(Color.BLACK, testQueen.color);
        assertEquals(testPosition, testQueen.position);
        assertNotNull(testQueen.validMoveSquares);
        assertNotNull(testQueen.validAttackSquares);
        assertTrue(testQueen.validMoveSquares.isEmpty());
        assertTrue(testQueen.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a Queen piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, false);
        testQueen = testBoard.pieces[3][0];
        testBoard.movePiece(testQueen, new Point(3, 3));
        assertTrue(testQueen.validMoveSquares.isEmpty());
        assertTrue(testQueen.validAttackSquares.isEmpty());
        testQueen.populateValidSquares(testBoard);
        assertEquals(19, testQueen.validMoveSquares.size());
        assertEquals(19, testQueen.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, false);
        testQueen = testBoard.pieces[3][0];
        testBoard.movePiece(testQueen, new Point(3, 3));
        testQueen.populateValidSquares(testBoard);
        assertFalse(testQueen.validMoveSquares.contains(new Point(7, 7)));
        assertFalse(testQueen.validAttackSquares.contains(new Point(7, 7)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, false);

        testQueen = testBoard.pieces[3][7];
        assertEquals("Q", testQueen.toString());

        testQueen = testBoard.pieces[3][0];
        assertEquals("q", testQueen.toString());
    }
}
package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Pawn;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class Pawn.
 */
public class PawnTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testPawn;

    /**
     * testPawn unit test the constructor for correct initial conditions.
     */
    public void testPawn() {
        Point testPosition = new Point(0, 0);
        testPawn = new Pawn(main.model.properties.Color.BLACK, testPosition);
        assertNotNull(testPawn);
        assertEquals(Color.BLACK, testPawn.color);
        assertEquals(testPosition, testPawn.position);
        assertNotNull(testPawn.validMoveSquares);
        assertNotNull(testPawn.validAttackSquares);
        assertTrue(testPawn.validMoveSquares.isEmpty());
        assertTrue(testPawn.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a Pawn piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, false);
        testPawn = testBoard.pieces[3][1];
        assertTrue(testPawn.validMoveSquares.isEmpty());
        assertTrue(testPawn.validAttackSquares.isEmpty());
        testPawn.populateValidSquares(testBoard);
        assertEquals(2, testPawn.validMoveSquares.size());
        assertEquals(0, testPawn.validAttackSquares.size());

        testBoard.movePiece(testPawn, new Point(3, 3));
        assertFalse(testPawn.validMoveSquares.isEmpty());
        assertTrue(testPawn.validAttackSquares.isEmpty());
        testPawn.populateValidSquares(testBoard);
        assertEquals(1, testPawn.validMoveSquares.size());
        assertEquals(0, testPawn.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, false);
        testPawn = testBoard.pieces[3][1];
        testBoard.movePiece(testPawn, new Point(3, 3));
        testPawn.populateValidSquares(testBoard);
        assertFalse(testPawn.validMoveSquares.contains(new Point(3, 5)));
        assertFalse(testPawn.validAttackSquares.contains(new Point(3, 5)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, false);

        testPawn = testBoard.pieces[4][6];
        assertEquals("P", testPawn.toString());

        testPawn = testBoard.pieces[4][1];
        assertEquals("p", testPawn.toString());
    }
}
package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Coyote;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Class Coyote.
 */
public class CoyoteTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testCoyote;

    /**
     * testCoyote unit test the constructor for correct initial conditions.
     */
    public void testCoyote() {
        Point testPosition = new Point(0, 0);
        testCoyote = new Coyote(main.model.properties.Color.BLACK, testPosition);
        assertNotNull(testCoyote);
        assertEquals(Color.BLACK, testCoyote.color);
        assertEquals(testPosition, testCoyote.position);
        assertNotNull(testCoyote.validMoveSquares);
        assertNotNull(testCoyote.validAttackSquares);
        assertTrue(testCoyote.validMoveSquares.isEmpty());
        assertTrue(testCoyote.validAttackSquares.isEmpty());
    }

    /**
     * testSwitchColorAndImage unit test to check if the Coyote
     * Chess piece changes color and image.
     */
    public void testSwitchColorAndImage() {
        testBoard = new Board(8, 8, true);
        testCoyote = testBoard.pieces[7][0];
        assertEquals(Color.WHITE, testCoyote.color);
        assertEquals("images/w_coyote.png", testCoyote.image.toString());
        ((Coyote) testCoyote).switchColorAndImage();
        assertEquals(Color.BLACK, testCoyote.color);
        assertEquals("images/b_coyote.png", testCoyote.image.toString());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for a Coyote piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, true);
        testCoyote = testBoard.pieces[7][0];
        testBoard.movePiece(testCoyote, new Point(3, 3));
        assertTrue(testCoyote.validMoveSquares.isEmpty());
        assertTrue(testCoyote.validAttackSquares.isEmpty());
        assertEquals(Color.BLACK, testCoyote.color);
        testCoyote.populateValidSquares(testBoard);
        assertEquals(19, testCoyote.validMoveSquares.size());
        assertEquals(19, testCoyote.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, true);
        testCoyote = testBoard.pieces[7][0];
        testBoard.movePiece(testCoyote, new Point(3, 3));
        testCoyote.populateValidSquares(testBoard);
        assertFalse(testCoyote.validMoveSquares.contains(new Point(1, 4)));
        assertFalse(testCoyote.validAttackSquares.contains(new Point(1, 4)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, true);

        testCoyote = testBoard.pieces[7][7];
        assertEquals("C", testCoyote.toString());

        testCoyote = testBoard.pieces[7][0];
        assertEquals("c", testCoyote.toString());
    }
}
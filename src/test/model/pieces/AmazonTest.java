/**
 * test.model.pieces is a collection of unit test
 * for all the Chess piece classes in main.model.pieces.
 */
package test.model.pieces;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.Amazon;
import main.model.properties.Color;

import java.awt.*;

/**
 * unit test for Class Amazon.
 */
public class AmazonTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testAmazon;

    /**
     * testAmazon unit test the constructor for correct initial conditions.
     */
    public void testAmazon() {
        Point testPosition = new Point(0, 0);
        testAmazon = new Amazon(main.model.properties.Color.BLACK, testPosition);
        assertNotNull(testAmazon);
        assertEquals(Color.BLACK, testAmazon.color);
        assertEquals(testPosition, testAmazon.position);
        assertNotNull(testAmazon.validMoveSquares);
        assertNotNull(testAmazon.validAttackSquares);
        assertTrue(testAmazon.validMoveSquares.isEmpty());
        assertTrue(testAmazon.validAttackSquares.isEmpty());
    }

    /**
     * testPopulateValidSquares unit test for the correct number
     * of possible moves and attacks for an Amazon piece.
     */
    public void testPopulateValidSquares() {
        testBoard = new Board(8, 8, true);
        testAmazon = testBoard.pieces[0][0];
        testBoard.movePiece(testAmazon, new Point(3, 3));
        assertTrue(testAmazon.validMoveSquares.isEmpty());
        assertTrue(testAmazon.validAttackSquares.isEmpty());
        testAmazon.populateValidSquares(testBoard);
        assertEquals(25, testAmazon.validMoveSquares.size());
        assertEquals(25, testAmazon.validAttackSquares.size());
    }

    /**
     * testPopulateValidSquaresForInvalidSquares unit test to see if
     * an invalid square is not part of possible moves and attacks.
     */
    public void testPopulateValidSquaresForInvalidSquares() {
        testBoard = new Board(8, 8, true);
        testAmazon = testBoard.pieces[0][0];
        testBoard.movePiece(testAmazon, new Point(3, 3));
        testAmazon.populateValidSquares(testBoard);
        assertFalse(testAmazon.validMoveSquares.contains(new Point(0, 4)));
        assertFalse(testAmazon.validAttackSquares.contains(new Point(0, 4)));
    }

    /**
     * testToString test for the right output of the overridden toString method.
     */
    public void testToString() {
        testBoard = new Board(8, 8, true);

        testAmazon = testBoard.pieces[0][7];
        assertEquals("A", testAmazon.toString());

        testAmazon = testBoard.pieces[0][0];
        assertEquals("a", testAmazon.toString());
    }
}
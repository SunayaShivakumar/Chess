package test.view;

import junit.framework.TestCase;
import main.model.Board;
import main.view.Square;

import java.awt.Point;

/**
 * unit test for Square class.
 */
public class SquareTest extends TestCase {
    /**
     * testSquare unit test to the Square constructor.
     */
    public void testSquare() {
        Square testSquare = new Square(5, 5);
        assertEquals(5, testSquare.x);
        assertEquals(5, testSquare.y);
    }

    /**
     * testGetPieceOnSquareNull unit test for the getPieceOnSquare method.
     */
    public void testGetPieceOnSquareNull() {
        Square testSquare = new Square(5, 5);
        Board testBoard = new Board(8, 8, false);
        assertNull(testSquare.getPieceOnSquare(testBoard));
    }

    /**
     * testGetPieceOnSquare second unit test for the getPieceOnSquare method.
     */
    public void testGetPieceOnSquare() {
        Square testSquare = new Square(7, 7);
        Board testBoard = new Board(8, 8, false);
        assertNotNull(testSquare.getPieceOnSquare(testBoard));
        assertEquals("R", testSquare.getPieceOnSquare(testBoard).toString());
    }

    /**
     * testGetPositionOfSquare unit test to check getPositionOfSquare method.
     */
    public void testGetPositionOfSquare() {
        Square testSquare = new Square(5, 5);
        assertEquals(new Point(5, 5), testSquare.getPositionOfSquare());
    }
}
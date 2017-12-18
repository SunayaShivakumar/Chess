package test.model.properties;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.properties.Move;

import java.awt.Point;

/**
 * unit test for Class Move.
 */
public class MoveTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testPiece;

    /**
     * testNorth unit test Move.north using a white Queen.
     */
    public void testNorth() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.north(testBoard, testPiece);
        assertEquals(3, testPiece.validMoveSquares.size());
        assertEquals(3, testPiece.validAttackSquares.size());
    }

    /**
     * testSouth unit test Move.south using a white Queen.
     */
    public void testSouth() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.south(testBoard, testPiece);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(1, testPiece.validAttackSquares.size());
    }

    /**
     * testEast unit test Move.east using a white Queen.
     */
    public void testEast() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.east(testBoard, testPiece);
        assertEquals(4, testPiece.validMoveSquares.size());
        assertEquals(4, testPiece.validAttackSquares.size());
    }

    /**
     * testWest unit test Move.west using a white Queen.
     */
    public void testWest() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.west(testBoard, testPiece);
        assertEquals(3, testPiece.validMoveSquares.size());
        assertEquals(3, testPiece.validAttackSquares.size());
    }

    /**
     * testNorthEast unit test Move.northEast using a white Queen.
     */
    public void testNorthEast() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.northEast(testBoard, testPiece);
        assertEquals(3, testPiece.validMoveSquares.size());
        assertEquals(3, testPiece.validAttackSquares.size());
    }

    /**
     * testSouthEast unit test Move.southEast using a white Queen.
     */
    public void testSouthEast() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.southEast(testBoard, testPiece);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(1, testPiece.validAttackSquares.size());
    }

    /**
     * testNorthWest unit test Move.northWest using a white Queen.
     */
    public void testNorthWest() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.northWest(testBoard, testPiece);
        assertEquals(3, testPiece.validMoveSquares.size());
        assertEquals(3, testPiece.validAttackSquares.size());
    }

    /**
     * testSouthWest unit test Move.southWest using a white Queen.
     */
    public void testSouthWest() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        testBoard.movePiece(testPiece, new Point(3, 3));
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        Move.southWest(testBoard, testPiece);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(1, testPiece.validAttackSquares.size());
    }
}
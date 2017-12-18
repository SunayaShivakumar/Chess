package test.model;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for abstract Piece class.
 */
public class PieceTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testPiece;

    /**
     * testNewPosition unit test checks if newPosition
     */
    public void testNewPosition() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[1][1];
        testPiece.position = testPiece.newPosition(0, 1);
        assertEquals(1, testPiece.position.x);
        assertEquals(2, testPiece.position.y);
    }

    /**
     * testUpdateValidSquareForEmptySquare unit test if the right
     * number of valid pieces are calculated by updateValidSquare.
     * This unit test is specific for when a given position is unoccupied.
     */
    public void testUpdateValidSquareForEmptySquare() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][1];

        Point testPosition = new Point(3, 3);
        Color testColor = testPiece.updateValidSquare(testBoard, testPosition);
        assertEquals(null, testColor);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(1, testPiece.validAttackSquares.size());
    }

    /**
     * testUpdateValidSquareForEmptySquare unit test if the right
     * number of valid pieces are calculated by updateValidSquare.
     * This unit test is specific for when a given position is occupied.
     */
    public void testUpdateValidSquareForTakenSquare() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][0];

        Point testPosition = new Point(3, 1);
        Color testColor = testPiece.updateValidSquare(testBoard, testPosition);
        assertEquals(Color.WHITE, testColor);
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());
    }

    /**
     * testUpdateValidPawnSquareForMoves unit test if the right
     * number of valid pieces are calculated by updateValidPawnSquare.
     * This unit test is specific for when a Pawn is considering moving.
     */
    public void testUpdateValidPawnSquareForMoves() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[0][1];

        testPiece.updateValidPawnSquare(testBoard, 0, 1);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        testPiece.validMoveSquares.clear();
        testPiece.validAttackSquares.clear();

        testBoard.movePiece(testBoard.pieces[0][1], new Point(0, 2));
        testPiece.updateValidPawnSquare(testBoard, 0, 2);
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        testPiece.validMoveSquares.clear();
        testPiece.validAttackSquares.clear();

        testPiece = testBoard.pieces[7][1];

        testPiece.updateValidPawnSquare(testBoard, 0, 2);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());

        testPiece.validMoveSquares.clear();
        testPiece.validAttackSquares.clear();

        testBoard.movePiece(testBoard.pieces[6][0], new Point(7, 2));
        testPiece.updateValidPawnSquare(testBoard, 0, 2);
        assertEquals(0, testPiece.validMoveSquares.size());
        assertEquals(0, testPiece.validAttackSquares.size());
    }

    /**
     * testUpdateValidPawnSquareForAttacks unit test if the right
     * number of valid pieces are calculated by updateValidPawnSquare.
     * This unit test is specific for when a Pawn is considering attacking.
     */
    public void testUpdateValidPawnSquareForAttacks() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[0][1];

        testBoard.movePiece(testBoard.pieces[1][6], new Point(1, 2));
        testPiece.updateValidPawnSquare(testBoard, 1, 1);
        assertEquals(1, testPiece.validMoveSquares.size());
        assertEquals(1, testPiece.validAttackSquares.size());
    }
}
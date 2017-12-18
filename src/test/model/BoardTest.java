/**
 * test is a collection of all the unit test
 * for all the Chess component classes in main.
 */
package test.model;

import junit.framework.TestCase;
import main.model.Board;
import main.model.Piece;
import main.model.pieces.*;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for the Board class.
 */
public class BoardTest extends TestCase {
    /** A Chess board to test with. */
    private Board testBoard;

    /** A Chess piece to test with. */
    private Piece testPiece;

    /**
     * testBoard unit test the Board constructor to check for
     * correct initial Chess board conditions.
     */
    public void testBoard() {
        testBoard = new Board(8, 8, false);
        assertEquals(8, testBoard.rows);
        assertEquals(8, testBoard.columns);
        assertNotNull(testBoard.pieces);
        assertTrue(testBoard.blackKing instanceof King);
        assertEquals(Color.BLACK, testBoard.blackKing.color);
        assertTrue(testBoard.whiteKing instanceof King);
        assertEquals(Color.WHITE, testBoard.whiteKing.color);
    }

    /**
     * testInitializeSquares unit test if all the Chess pieces
     * are initialized correctly on a newly constructed board.
     */
    public void testInitializeSquares() {
        testBoard = new Board(8, 8, false);

        /* test Chess pieces in the outer rows */
        assertTrue(testBoard.pieces[0][0] instanceof Rook);
        assertTrue(testBoard.pieces[1][0] instanceof Knight);
        assertTrue(testBoard.pieces[2][0] instanceof Bishop);
        assertTrue(testBoard.pieces[3][0] instanceof Queen);
        assertTrue(testBoard.pieces[4][0] instanceof King);
        assertTrue(testBoard.pieces[5][0] instanceof Bishop);
        assertTrue(testBoard.pieces[6][0] instanceof Knight);
        assertTrue(testBoard.pieces[7][0] instanceof Rook);
        assertTrue(testBoard.pieces[0][7] instanceof Rook);
        assertTrue(testBoard.pieces[1][7] instanceof Knight);
        assertTrue(testBoard.pieces[2][7] instanceof Bishop);
        assertTrue(testBoard.pieces[3][7] instanceof Queen);
        assertTrue(testBoard.pieces[4][7] instanceof King);
        assertTrue(testBoard.pieces[5][7] instanceof Bishop);
        assertTrue(testBoard.pieces[6][7] instanceof Knight);
        assertTrue(testBoard.pieces[7][7] instanceof Rook);

        /* test Pawn pieces. */
        for (int testColumn = 0; testColumn < testBoard.columns; testColumn++) {
            assertTrue(testBoard.pieces[testColumn][1] instanceof Pawn);
            assertTrue(testBoard.pieces[testColumn][6] instanceof Pawn);
        }

        /* test empty pieces. */
        for (int testColumn = 0; testColumn < testBoard.columns; testColumn++) {
            assertNull(testBoard.pieces[testColumn][2]);
            assertNull(testBoard.pieces[testColumn][3]);
            assertNull(testBoard.pieces[testColumn][4]);
            assertNull(testBoard.pieces[testColumn][5]);
        }

        /* test white Chess pieces. */
        for (int testRow = 0; testRow < 2; testRow++) {
            for (int testColumn = 0; testColumn < testBoard.columns; testColumn++) {
                assertEquals(Color.WHITE, testBoard.pieces[testColumn][testRow].color);
            }
        }

        /* test black Chess pieces. */
        for (int testRow = 7; testRow > 5; testRow--) {
            for (int testColumn = 0; testColumn < testBoard.columns; testColumn++) {
                assertEquals(Color.BLACK, testBoard.pieces[testColumn][testRow].color);
            }
        }
    }

    /**
     * testInitializeSquaresCustom unit test if all the custom Chess
     * pieces are initialized correctly on a newly constructed board.
     */
    public void testInitializeSquaresCustom() {
        testBoard = new Board(8, 8, true);

        /* test custom Chess pieces in the outer rows */
        assertTrue(testBoard.pieces[0][0] instanceof Amazon);
        assertTrue(testBoard.pieces[7][0] instanceof Coyote);
        assertTrue(testBoard.pieces[0][7] instanceof Amazon);
        assertTrue(testBoard.pieces[7][7] instanceof Coyote);

        /* test white Chess pieces. */
        assertEquals(Color.WHITE, testBoard.pieces[0][0].color); // white Amazon.
        assertEquals(Color.WHITE, testBoard.pieces[7][0].color); // white Coyote.

        /* test black Chess pieces. */
        assertEquals(Color.BLACK, testBoard.pieces[0][7].color); // white Amazon.
        assertEquals(Color.BLACK, testBoard.pieces[7][7].color); // black Coyote.
    }

    /**
     * testMovePiece unit test if a given Chess piece
     * is correctly moved to a new position.
     */
    public void testMovePiece() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][1];
        Point testPosition = new Point(3, 3);
        testBoard.movePiece(testPiece, testPosition);
        assertEquals(3, testPiece.position.x);
        assertEquals(3, testPiece.position.y);
    }

    /**
     * testMoveCoyotePiece unit test to check if the Coyote
     * Chess piece changes color and image after a move.
     */
    public void testMoveCoyotePiece() {
        testBoard = new Board(8, 8, true);
        testPiece = testBoard.pieces[7][0];
        Point testPosition = new Point(3, 3);
        assertEquals(Color.WHITE, testPiece.color);
        assertEquals("images/w_coyote.png", testPiece.image.toString());
        testBoard.movePiece(testPiece, testPosition);
        assertEquals(Color.BLACK, testPiece.color);
        assertEquals("images/b_coyote.png", testPiece.image.toString());
    }

    /**
     * testMoveCoyotePiece unit test to check if the Coyote
     * Chess piece changes color and image after a move.
     */
    public void testMoveNotCoyotePiece() {
        testBoard = new Board(8, 8, true);
        testPiece = testBoard.pieces[5][0];
        Point testPosition = new Point(3, 3);
        assertEquals(Color.WHITE, testPiece.color);
        assertEquals("images/w_bishop.png", testPiece.image.toString());
        testBoard.movePiece(testPiece, testPosition);
        assertEquals(Color.WHITE, testPiece.color);
        assertEquals("images/w_bishop.png", testPiece.image.toString());
    }

    /**
     * testMovePieceToInvalidPosition unit test to check that pieces
     * can not move to invalid squares.
     */
    public void testMovePieceToInvalidPosition() {
        testBoard = new Board(8, 8, false);
        testPiece = testBoard.pieces[3][1];
        Point testPosition = new Point(-1, 0);
        assertFalse(testBoard.movePiece(testPiece, testPosition));
    }

    /**
     * testValidPosition unit test for valid board positions.
     */
    public void testValidPosition() {
        testBoard = new Board(8, 8, false);
        assertFalse(testBoard.validPosition(new Point(-1, 0)));
        assertFalse(testBoard.validPosition(new Point(0, -3)));
        assertFalse(testBoard.validPosition(new Point(-1, -1)));
        assertFalse(testBoard.validPosition(new Point(8, 8)));
        assertFalse(testBoard.validPosition(new Point(5, 9)));
        assertFalse(testBoard.validPosition(new Point(9, 2)));
        assertTrue(testBoard.validPosition(new Point(5, 5)));
        assertTrue(testBoard.validPosition(new Point(7, 7)));
        assertTrue(testBoard.validPosition(new Point(0, 0)));
    }

    /**
     * testPiecePresent unit test if a Chess pieces is present a given position.
     */
    public void testPiecePresent() {
        testBoard = new Board(8, 8, false);
        assertFalse(testBoard.piecePresent(new Point(4, 4)));
        assertTrue(testBoard.piecePresent(new Point(0,0)));
        assertTrue(testBoard.piecePresent(new Point(7, 7)));
    }

    /**
     * testPieceColor unit test for the right color of piece at a given position.
     */
    public void testPieceColor() {
        testBoard = new Board(8, 8, false);
        assertEquals(Color.BLACK, testBoard.pieceColor(new Point(7, 7)));
        assertEquals(Color.WHITE, testBoard.pieceColor(new Point(0, 0)));
    }

    /**
     * testCreateStandardBoard unit test to check if the right board was created.
     */
    public void testCreateStandardBoard() {
        testBoard = Board.createStandardBoard();
        assertFalse(testBoard.customize);
    }

    /**
     * testCreateCustomBoard unit test to check if the right board was created.
     */
    public void testCreateCustomBoard() {
        testBoard = Board.createCustomBoard();
        assertTrue(testBoard.customize);
    }
}
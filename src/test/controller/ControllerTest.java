package test.controller;

import junit.framework.TestCase;
import main.controller.Controller;
import main.model.Board;
import main.model.properties.Color;

import java.awt.Point;

/**
 * unit test for Controller class.
 */
public class ControllerTest extends TestCase {
    /**
     * testInitializeGame checks for correct initial game conditions,
     * after initializing a new Controller of Chess.
     */
    public void testInitializeGame() {
        Controller.gameBoard = new Board(8, 8, false);
        Controller.initializeGame(Controller.gameBoard);
        assertTrue(Controller.gameBoard != null);
        assertEquals(Color.WHITE, Controller.currentPlayer);
        assertNull(Controller.winner);
        assertNull(Controller.checkmate);
        assertFalse(Controller.stalemate);
    }

    /**
     * testMovesRemain unit test if moves remain or not for
     * all pieces of a given color.
     */
    public void testMovesRemain() {
        Controller.gameBoard = new Board(8, 8, false);
        Controller.gameBoard.populateValidSquaresForPlayer(Color.BLACK);
        assertTrue(Controller.gameBoard.movesRemain(Color.BLACK));
        Controller.gameBoard.populateValidSquaresForPlayer(Color.WHITE);
        assertTrue(Controller.gameBoard.movesRemain(Color.WHITE));
    }

    /**
     * testPopulateValidSquaresForAllPieces unit test to see
     * if populateValidSquaresForPlayer updates valid moves
     * and valid attacks pieces correctly.
     */
    public void testPopulateValidSquaresForAllPieces() {
        Controller.gameBoard = new Board(8, 8, false);
        Controller.gameBoard.populateValidSquaresForPlayer(Color.BLACK);
        Controller.gameBoard.populateValidSquaresForPlayer(Color.WHITE);
        assertEquals(0, Controller.gameBoard.pieces[7][7].validMoveSquares.size());
        assertEquals(0, Controller.gameBoard.pieces[7][7].validAttackSquares.size());
        assertEquals(2, Controller.gameBoard.pieces[1][1].validMoveSquares.size());
        assertEquals(0, Controller.gameBoard.pieces[1][1].validAttackSquares.size());
    }

    /**
     * testBlackKingInCheck unit test to see if kingInCheck
     * returns true when the Black King is in check.
     */
    public void testBlackKingInCheck() {
        Controller.gameBoard = new Board(8, 8, false);
        Controller.gameBoard.movePiece(Controller.gameBoard.blackKing, new Point(4, 2));
        Controller.gameBoard.populateValidSquaresForPlayer(Color.BLACK);
        Controller.gameBoard.populateValidSquaresForPlayer(Color.WHITE);
        assertTrue(Controller.gameBoard.kingInCheck(Color.BLACK));
    }

    /**
     * testWhiteKingInCheck unit test to see if kingInCheck
     * returns true when the White King is in check.
     */
    public void testWhiteKingInCheck() {
        Controller.gameBoard = new Board(8, 8, false);
        Controller.gameBoard.movePiece(Controller.gameBoard.whiteKing, new Point(4, 5));
        Controller.gameBoard.populateValidSquaresForPlayer(Color.BLACK);
        Controller.gameBoard.populateValidSquaresForPlayer(Color.WHITE);
        assertTrue(Controller.gameBoard.kingInCheck(Color.WHITE));
    }

    /**
     * testUpdateGame unit test to see if a game is
     * updated correctly.
     */
    public void testUpdateGame() {
        Controller.gameBoard = new Board(8, 8, false);
        Controller.gameBoard.movePiece(Controller.gameBoard.pieces[5][1], new Point(5, 2));
        Controller.currentPlayer = Color.BLACK;
        Controller.updateBoard();
        assertEquals(0, Controller.blackScoreCount);
        assertEquals(0, Controller.whiteScoreCount);
        assertEquals(null, Controller.winner);
        assertEquals(null, Controller.checkmate);
        assertFalse(Controller.stalemate);
    }
}
package main.model.properties;

import main.model.Board;
import main.model.Piece;

import java.awt.Point;

/**
 * Move class that defines common Chess piece moves.
 *
 * @author Sunaya Shivakumar
 */
public class Move {

    /**
     * north method checks if moving north is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move north.
     */
    public static void north(Board board, Piece piece) {
        Color newColor = null;
        for (int offset = 1; newColor == null; offset++) {
            Point newPosition = piece.newPosition(0, offset);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * south method checks if moving south is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move south.
     */
    public static void south(Board board, Piece piece) {
        Color newColor = null;
        for (int offset = -1; newColor == null; offset--) {
            Point newPosition = piece.newPosition(0, offset);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * east method checks if moving east is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move east.
     */
    public static void east(Board board, Piece piece) {
        Color newColor = null;
        for (int offset = 1; newColor == null; offset++) {
            Point newPosition = piece.newPosition(offset, 0);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * west method checks if moving west is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move west.
     */
    public static void west(Board board, Piece piece) {
        Color newColor = null;
        for (int offset = -1; newColor == null; offset--) {
            Point newPosition = piece.newPosition(offset, 0);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * northEast method checks if moving NE is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move NE.
     */
    public static void northEast(Board board, Piece piece) {
        Color newColor = null;
        for (int xOffset = 1, yOffset = 1; newColor == null; xOffset++, yOffset++) {
            Point newPosition = piece.newPosition(xOffset, yOffset);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * southEast method checks if moving SE is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move SE.
     */
    public static void southEast(Board board, Piece piece) {
        Color newColor = null;
        for (int xOffset = 1, yOffset = -1; newColor == null; xOffset++, yOffset--) {
            Point newPosition = piece.newPosition(xOffset, yOffset);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * northWest method checks if moving NW is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move NW.
     */
    public static void northWest(Board board, Piece piece) {
        Color newColor = null;
        for (int xOffset = -1, yOffset = 1; newColor == null; xOffset--, yOffset++) {
            Point newPosition = piece.newPosition(xOffset, yOffset);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * southWest method checks if moving SW is possible,
     * and if so updates the validMoveSquares list.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move SW.
     */
    public static void southWest(Board board, Piece piece) {
        Color newColor = null;
        for (int xOffset = -1, yOffset = -1; newColor == null; xOffset--, yOffset--) {
            Point newPosition = piece.newPosition(xOffset, yOffset);
            if (!board.validPosition(newPosition)) {
                break;
            }
            newColor = piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * likeBishop method configures the way a Bishop moves.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move like a Bishop.
     */
    public static void likeBishop(Board board, Piece piece) {
        Move.northEast(board, piece);
        Move.southEast(board, piece);
        Move.northWest(board, piece);
        Move.southWest(board, piece);
    }

    /**
     * likeKing method configures the way a King moves.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move like a King.
     */
    public static void likeKing(Board board, Piece piece, Color color) {
        Point newPosition;
        newPosition = piece.newPosition(0, 1);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(0, -1);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(1, 0);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-1, 0);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(1, 1);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(1, -1);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-1, 1);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-1, -1);
        if (board.validPosition(newPosition) && !board.underAttack(newPosition, color)) {
            piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * likeKnight method configures the way a Knight moves.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move like a Knight.
     */
    public static void likeKnight(Board board, Piece piece) {
        Point newPosition;
        newPosition = piece.newPosition(1, 2);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-1, 2);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(1, -2);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-1, -2);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(2, 1);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-2, 1);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(2, -1);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
        newPosition = piece.newPosition(-2, -1);
        if (board.validPosition(newPosition)) {
            piece.updateValidSquare(board, newPosition);
        }
    }

    /**
     * likePawn method configures the way a Pawn moves.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move like a Pawn.
     */
    public static void likePawn(Board board, Piece piece) {
        if (piece.color == Color.BLACK) {
            piece.updateValidPawnSquare(board, 0, -1);
            piece.updateValidPawnSquare(board, 0, -2);
            piece.updateValidPawnSquare(board, 1, -1);
            piece.updateValidPawnSquare(board, -1, -1);
        } else {
            piece.updateValidPawnSquare(board, 0, 1);
            piece.updateValidPawnSquare(board, 0, 2);
            piece.updateValidPawnSquare(board, 1, 1);
            piece.updateValidPawnSquare(board, -1, 1);
        }
    }

    /**
     * likeRook method configures the way a Rook moves.
     *
     * @param board the board in it's current state.
     * @param piece the piece that needs to move like a Rook.
     */
    public static void likeRook(Board board, Piece piece) {
        Move.north(board, piece);
        Move.south(board, piece);
        Move.east(board, piece);
        Move.west(board, piece);
    }
}

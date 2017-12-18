package main.model;

import main.model.properties.Color;

import javax.swing.ImageIcon;
import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Abstract Piece class used as a template to create new Chess pieces.
 *
 * @author Sunaya Shivakumar
 */
public abstract class Piece {
    /** The color of a Chess piece - either BLACK or WHITE. */
    public Color color;

    /** The xy position or square on which a Chess piece is. */
    public Point position;

    /**
     * A list of all the xy positions or pieces that a Chess piece
     * can move to on a given Chess board.
     */
    public ArrayList<Point> validMoveSquares;

    /**
     * A list of all the xy positions or pieces that a Chess piece
     * can attack or capture opponent Chess pieces.
     */
    public ArrayList<Point> validAttackSquares;

    /**
     * The image representation of a Chess piece. All images were sourced from here:
     * https://commons.wikimedia.org/wiki/Category:PNG_chess_pieces/Standard_transparent
     */
    public ImageIcon image;

    /**
     * Piece class constructor that initializes a piece's color and position.
     *
     * @param pieceColor the color of the Chess piece.
     * @param piecePosition the position of the Chess piece.
     */
    public Piece(Color pieceColor, Point piecePosition) {
        color = pieceColor;
        position = piecePosition;
        validMoveSquares = new ArrayList<>();
        validAttackSquares = new ArrayList<>();
        image = new ImageIcon();
    }

    /**
     * populateValidSquares is an abstract method that populates the validMoveSquares list
     * with all the positions that the Chess piece could move to.
     *
     * @param board the board in its current state.
     */
    public abstract void populateValidSquares(Board board);

    /**
     * toString is an overridden abstract method that gets the notation for a Chess piece.
     *
     * @return the notation for a Chess piece.
     */
    @Override
    public abstract String toString();

    /**
     * newPosition method calculates the new xy position for a given piece
     * and the x and y offsets.
     *
     * @param xOffset the x offset or difference to be moved.
     * @param yOffset the y offset or difference to be moved.
     * @return the new position of a given Chess piece.
     */
    public Point newPosition(int xOffset, int yOffset) {
        int possibleX = position.x + xOffset;
        int possibleY = position.y + yOffset;
        return (new Point(possibleX, possibleY));
    }

    /**
     * updateValidSquare method checks if a possible valid new position for a Chess piece
     * is a valid position on the board, and also checks if the new position has
     * another Chess piece - if it can be attacked based on it's color. Based on
     * these checks, the validMoveSquares and validAttackSquares lists are updated.
     *
     * @param board the Chess board in it's current state.
     * @param possiblePosition the possible new position for the movePiece.
     * @return the Color of current piece on the possiblePosition if applicable.
     */
    public Color updateValidSquare(Board board, Point possiblePosition) {
        if (board.piecePresent(possiblePosition)) {
            if (board.pieceColor(possiblePosition) == color) {
                return board.pieceColor(possiblePosition);
            } else {
                validAttackSquares.add(possiblePosition);
                validMoveSquares.add(possiblePosition);
                return board.pieceColor(possiblePosition);
            }
        } else {
            validAttackSquares.add(possiblePosition);
            validMoveSquares.add(possiblePosition);
            return null;
        }
    }

    /**
     * updateValidPawnSquare method checks if a possible new position for a Pawn piece
     * is a valid position to move to on the board. It also covers if the Pawn can
     * move 1 or 2 pieces, in addition to checking the diagonal attack pieces. Based on
     * these checks, the validMoveSquares and validAttackSquares lists are updated.
     *
     * @param board the Chess board in its current state.
     * @param xOffset the x offset between the current and new positions.
     * @param yOffset the y offset between the current and new positions.
     */
    public void updateValidPawnSquare(Board board, int xOffset, int yOffset) {
        Point possiblePosition = newPosition(xOffset, yOffset);

        if (board.validPosition(possiblePosition)) {
            if (xOffset == 0 && Math.abs(yOffset) == 1 && !board.piecePresent(possiblePosition)) {
                validMoveSquares.add(possiblePosition);
            } else if (xOffset == 0 && color == Color.BLACK && position.y == (board.rows - 2) && yOffset == -2) {
                Point firstSquare = newPosition(xOffset, (yOffset + 1));
                if (!board.piecePresent(possiblePosition) && !board.piecePresent(firstSquare)) {
                    validMoveSquares.add(possiblePosition);
                }
            } else if (xOffset == 0 && color == Color.WHITE && position.y == 1 && yOffset == 2) {
                Point firstSquare = newPosition(xOffset, (yOffset - 1));
                if (!board.piecePresent(possiblePosition) && !board.piecePresent(firstSquare)) {
                    validMoveSquares.add(possiblePosition);
                }
            } else if (xOffset != 0 && board.piecePresent(possiblePosition) && color != board.pieceColor(possiblePosition)) {
                validAttackSquares.add(possiblePosition);
                validMoveSquares.add(possiblePosition);
            }
        }
    }
}

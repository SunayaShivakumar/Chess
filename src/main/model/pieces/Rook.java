package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Rook class that inherits from Piece class,
 * and is specific to the Rook Chess piece.
 *
 * @author Sunaya Shivakumar
 */
public class Rook extends Piece {
    /**
     * Rook class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the Rook.
     * @param piecePosition the position of the Rook.
     */
    public Rook(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_rook.png");
        } else {
            image = new ImageIcon("images/w_rook.png");
        }
    }

    /**
     * populateValidSquares method populates the validMoveSquares list
     * with all the positions that the Rook could move to.
     *
     * @param board the board in it's current state.
     */
    @Override
    public void populateValidSquares(Board board) {
        validMoveSquares.clear();
        validAttackSquares.clear();

        Move.likeRook(board, this);
    }

    /**
     * toString is an overridden method that gets the notation for a Chess piece.
     *
     * @return the notation for a Rook piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "R";
        } else {
            return "r";
        }
    }
}

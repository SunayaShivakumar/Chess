package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * King class that inherits from Piece class,
 * and is specific to the King Chess piece.
 *
 * @author Sunaya Shivakumar
 */
public class King extends Piece {
    /**
     * King class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the King.
     * @param piecePosition the position of the King.
     */
    public King(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_king.png");
        } else {
            image = new ImageIcon("images/w_king.png");
        }
    }

    /**
     * populateValidSquares method populates the validMoveSquares list
     * with all the positions that the King could move to.
     *
     * @param board the board in it's current state.
     */
    @Override
    public void populateValidSquares(Board board) {
        validMoveSquares.clear();
        validAttackSquares.clear();

        Move.likeKing(board, this, color);
    }

    /**
     * toString is an overridden method that gets the notation for a Chess piece.
     *
     * @return the notation for a King piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "K";
        } else {
            return "k";
        }
    }
}

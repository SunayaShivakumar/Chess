/**
 * main.model.pieces is a collection of Chess pieces.
 */
package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Amazon class that inherits from Piece class,
 * and is specific to the Amazon Chess piece.
 *
 * The Amazon is a custom Chess piece variant that
 * moves like a Standard Queen and a Knight.
 *
 * @author Sunaya Shivakumar
 */
public class Amazon extends Piece {
    /**
     * Amazon class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the Amazon.
     * @param piecePosition the position of the Amazon.
     */
    public Amazon(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_amazon.png");
        } else {
            image = new ImageIcon("images/w_amazon.png");
        }
    }

    /**
     * populateValidSquares method populates the validMoveSquares list
     * with all the positions that the Amazon could move to.
     *
     * @param board the board in it's current state.
     */
    @Override
    public void populateValidSquares(Board board) {
        validMoveSquares.clear();
        validAttackSquares.clear();

        Move.likeBishop(board, this);
        Move.likeRook(board, this);
        Move.likeKnight(board, this);
    }

    /**
     * toString is an overridden method that gets the notation for a Chess piece.
     *
     * @return the notation for an Amazon piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "A";
        } else {
            return "a";
        }
    }
}

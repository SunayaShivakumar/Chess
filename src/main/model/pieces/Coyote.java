package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Coyote class that inherits from Piece class,
 * and is specific to the Coyote Chess piece.
 *
 * The Coyote is a custom Chess piece variant that
 * moves like a Standard Queen. It also changes color
 * after each move.
 *
 * @author Sunaya Shivakumar
 */
public class Coyote extends Piece {
    /**
     * Coyote class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the Coyote.
     * @param piecePosition the position of the Coyote.
     */
    public Coyote(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_coyote.png");
        } else {
            image = new ImageIcon("images/w_coyote.png");
        }
    }

    /**
     * switchColorAndImage method switches the color and image of
     * the Coyote piece.
     */
    public void switchColorAndImage() {
        if (color == Color.BLACK) {
            image = new ImageIcon("images/w_coyote.png");
        } else {
            image = new ImageIcon("images/b_coyote.png");
        }
        color = color.opposite();
    }

    /**
     * populateValidSquares method populates the validMoveSquares list
     * with all the positions that the Coyote could move to.
     *
     * @param board the board in it's current state.
     */
    @Override
    public void populateValidSquares(Board board) {
        validMoveSquares.clear();
        validAttackSquares.clear();

        Move.likeBishop(board, this);
        Move.likeRook(board, this);
    }

    /**
     * toString is an overridden method that gets the notation for a Chess piece.
     *
     * @return the notation for a Coyote piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "C";
        } else {
            return "c";
        }
    }
}

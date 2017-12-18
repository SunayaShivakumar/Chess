package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Pawn class that inherits from Piece class,
 * and is specific to the Pawn Chess piece.
 *
 * @author Sunaya Shivakumar
 */
public class Pawn extends Piece {
    /**
     * Pawn class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the Pawn.
     * @param piecePosition the position of the Pawn.
     */
    public Pawn(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_pawn.png");
        } else {
            image = new ImageIcon("images/w_pawn.png");
        }
    }

    /**
     * populateValidSquares method populates the validMoveSquares and validAttackSquares list
     * with all the positions that the Pawn could move to.
     *
     * @param board the board in it's current state.
     */
    public void populateValidSquares(Board board) {
        validMoveSquares.clear();
        validAttackSquares.clear();

        Move.likePawn(board, this);
    }

    /**
     * toString is an overridden method that gets the notation for a Chess piece.
     *
     * @return the notation for a Pawn piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "P";
        } else {
            return "p";
        }
    }
}

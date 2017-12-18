package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Queen class that inherits from Piece class,
 * and is specific to the Queen Chess piece.
 *
 * @author Sunaya Shivakumar
 */
public class Queen extends Piece {
    /**
     * Queen class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the Queen.
     * @param piecePosition the position of the Queen.
     */
    public Queen(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_queen.png");
        } else {
            image = new ImageIcon("images/w_queen.png");
        }
    }

    /**
     * populateValidSquares method populates the validMoveSquares list
     * with all the positions that the Queen could move to.
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
     * @return the notation for a Queen piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "Q";
        } else {
            return "q";
        }
    }
}

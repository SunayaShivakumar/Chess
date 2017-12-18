package main.model.pieces;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.model.properties.Move;

import javax.swing.ImageIcon;
import java.awt.Point;

/**
 * Knight class that inherits from Piece class,
 * and is specific to the Knight Chess piece.
 *
 * @author Sunaya Shivakumar
 */
public class Knight extends Piece {
    /**
     * Knight class constructor that initializes it's color and position.
     *
     * @param pieceColor the color of the Knight.
     * @param piecePosition the position of the Knight.
     */
    public Knight(Color pieceColor, Point piecePosition) {
        super(pieceColor, piecePosition);

        if (pieceColor == Color.BLACK) {
            image = new ImageIcon("images/b_knight.png");
        } else {
            image = new ImageIcon("images/w_knight.png");
        }
    }

    /**
     * populateValidSquares method populates the validMoveSquares list
     * with all the positions that the Knight could move to.
     *
     * @param board the board in it's current state.
     */
    @Override
    public void populateValidSquares(Board board) {
        validMoveSquares.clear();
        validAttackSquares.clear();

        Move.likeKnight(board, this);
    }

    /**
     * toString is an overridden method that gets the notation for a Chess piece.
     *
     * @return the notation for a Knight piece.
     */
    @Override
    public String toString() {
        if (color == Color.BLACK) {
            return "N";
        } else {
            return "n";
        }
    }
}

/**
 * main.view is a collection of all view components for the Chess GUI.
 */
package main.view;

import main.model.Board;
import main.model.Piece;

import javax.swing.*;
import java.awt.Color;
import java.awt.Point;

/**
 * Square extends JButton to have a xy position.
 *
 * @author Sunaya Shivakumar
 */
public class Square extends JButton {
    /** x position of the Square. */
    public int x;

    /** y position of the Square. */
    public int y;

    /**
     * Square constructor that sets the xy position.
     *
     * @param positionX the x position of the Square.
     * @param positionY the y position of the Square.
     */
    public Square(int positionX, int positionY) {
        x = positionX;
        y = positionY;
    }

    /**
     * setDarkSquare sets the color for dark pieces on a Chess board.
     */
    public void setDarkSquare() {
        setBackground(new Color(208, 140, 71));
        setOpaque(true);
        setBorderPainted(false);
    }

    /**
     * setLightSquare sets the color for light pieces on a Chess board.
     */
    public void setLightSquare() {
        setBackground(new Color(254, 206, 158));
        setOpaque(true);
        setBorderPainted(false);
    }

    /**
     * setGreen sets the color for squares that a Chess piece can move to.
     */
    public void setGreen() {
        setBackground(new Color(49, 186, 27));
        setOpaque(true);
        setBorderPainted(false);
    }

    /**
     * setYellow sets the color for squares with a King piece when
     * the King is check.
     */
    public void setYellow() {
        setBackground(new Color(251, 255, 50));
        setOpaque(true);
        setBorderPainted(false);
    }

    /**
     * setRed sets the color for squares with a King piece when
     * the King has been checkmated.
     */
    public void setRed() {
        setBackground(new Color(232, 77, 27));
        setOpaque(true);
        setBorderPainted(false);
    }

    /**
     * setImageIcon sets an imageIcon of a Chess piece for a given square.
     *
     * @param image the imageIcon of the Chess piece.
     */
    public void setImageIcon(ImageIcon image) {
        setIcon(image);
    }

    /**
     * getPieceOnSquare method gets the Chess piece on a square.
     *
     * @param board the board in it's current state.
     * @return the Chess piece on the square.
     */
    public Piece getPieceOnSquare(Board board) {
        return board.pieces[x][y];
    }

    /**
     * getPositionOfSquare method gets the position of a square.
     *
     * @return the position of a square.
     */
    public Point getPositionOfSquare() {
        return (new Point(x, y));
    }
}

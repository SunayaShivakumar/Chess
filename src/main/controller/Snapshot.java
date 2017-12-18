package main.controller;

import main.model.Piece;

import java.awt.Point;

/**
 * A simple Snapshot class. Groups together all the variables
 * that are needed to take a snapshot of the game state.
 *
 * @author Sunaya Shivakumar
 */
public class Snapshot {
    /** The Chess piece that was moved. */
    public static Piece movedPiece;

    /** The Chess piece that was killed or attacked. */
    public static Piece killedPiece;

    /** The previous position of movedPiece. */
    public static Point prevPosition;
}

/**
 * main.model is a collection of all important components used in game of Chess.
 */
package main.model;

import main.model.pieces.*;
import main.model.properties.Color;

import java.awt.Point;

/**
 * Board class used create Chess boards of varying rows and columns.
 * The game is represented by a 2D Array of Chess pieces.
 *
 * @author Sunaya Shivakumar
 */
public class Board {
    /** The number of rows in a Chess board. */
    public final int rows;

    /** The number of columns in a Chess board. */
    public final int columns;

    /** A 2D array that represents the Chess pieces on a board of pieces. */
    public Piece[][] pieces;

    /** Easy access to the black King piece */
    public Piece blackKing;

    /** Easy access to the white King piece */
    public Piece whiteKing;

    /** Specify if the game is to be played with custom pieces or not */
    public boolean customize;

    /**
     * Board class constructor that initializes a Chess board.
     *
     * @param numColumns the number of columns required on a Chess board.
     * @param numRows the number of rows required on a Chess board.
     */
    public Board(int numColumns, int numRows, boolean customizeBoard) {
        columns = numColumns;
        rows = numRows;

        pieces = new Piece[columns][rows];

        customize = customizeBoard;
        initializeSquares();

        blackKing = pieces[4][7];
        whiteKing = pieces[4][0];
    }

    /**
     * movePiece method updates the square at the new position with the piece
     * to be moved, and sets the old position to null.
     *
     * @param pieceToMove the Chess piece to be moved.
     * @param newPosition the new position to which the movePiece has to be moved.
     * @return true if piece was moved successfully, false if not.
     */
    public boolean movePiece(Piece pieceToMove, Point newPosition) {
        if (validPosition(newPosition)) {
            Point oldPosition = pieceToMove.position;
            pieces[newPosition.x][newPosition.y] = pieces[oldPosition.x][oldPosition.y];
            pieces[oldPosition.x][oldPosition.y] = null;
            pieceToMove.position = newPosition;

            /* switch the image and color of the Coyote on each move. */
            if (pieceToMove instanceof Coyote) {
                ((Coyote) pieceToMove).switchColorAndImage();
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * populateValidSquaresForPlayer method updates the valid pieces for
     * all Chess pieces on the gameBoard.
     */
    public void populateValidSquaresForPlayer(Color color) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                Piece pieceToCheck = pieces[column][row];
                if (pieceToCheck != null && pieceToCheck.color == color) {
                    pieceToCheck.populateValidSquares(this);
                }
            }
        }
    }

    /**
     * kingInCheck method checks if the King for a given color is in check.
     *
     * @param colorInCheck the color of the King to check.
     * @return true if the King in question is in check, false if not.
     */
    public boolean kingInCheck(Color colorInCheck) {
        Piece king = (colorInCheck == Color.BLACK) ? blackKing : whiteKing;
        return underAttack(king.position, king.color);
    }

    /**
     * underAttack method checks if a piece of a color is under attack
     * by the opponent pieces at a given position.
     *
     * @param position the position to be checked if under attack.
     * @param color the color of the piece on the given position.
     * @return true if the given position is under attack by an opponent piece.
     */
    public boolean underAttack(Point position, Color color) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                Piece pieceToCheck = pieces[column][row];
                if (pieceToCheck != null && pieceToCheck.color == color.opposite()) {
                    if (pieceToCheck.validAttackSquares.contains(position)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * movesRemain checks if there are any possible moves left for
     * all Chess pieces for a given color.
     *
     * @param colorToCheck the color of the pieces to check if moves remain.
     * @return true if moves remain, false if not.
     */
    public boolean movesRemain(Color colorToCheck) {
        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                Piece pieceToCheck = pieces[column][row];
                if (pieceToCheck != null && pieceToCheck.color == colorToCheck) {
                    if (!pieceToCheck.validMoveSquares.isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * validPosition method that checks if a square on a Chess board exists or not.
     *
     * @param square Chess board square that needs to checked.
     * @return true if square is valid, and false if not.
     */
    public boolean validPosition(Point square) {
        return (square.x >= 0 && square.x < rows && square.y >= 0 && square.y < columns);
    }

    /**
     * piecePresent method that checks if a square on a Chess board contains a Chess piece or not.
     *
     * @param square Chess board square to check if it contains a Chess piece.
     * @return true if square contains a Chess piece, and false if not.
     */
    public boolean piecePresent(Point square) {
        return (pieces[square.x][square.y] != null);
    }

    /**
     * pieceColor method that returns the color of a Chess piece on a Chess board square.
     *
     * @param square Chess board square's piece to determine the color of.
     * @return color of the Chess piece on a given square.
     */
    public Color pieceColor(Point square) {
        return pieces[square.x][square.y].color;
    }

    /**
     * initializeSquares method that initializes an empty Chess board with all the pieces
     * required for a game of 2-player Chess.
     */
    private void initializeSquares() {
        initializePieces(Color.BLACK, (rows - 1));
        initializePieces(Color.WHITE, 0);
        initializePawns(Color.BLACK, (rows - 2));
        initializePawns(Color.WHITE, 1);
        initializeEmptySquares();

        if (customize) {
            initializeCustomPieces(Color.BLACK, (rows - 1));
            initializeCustomPieces(Color.WHITE, 0);
        }
    }

    /**
     * initializePieces method initializes all the Chess pieces on the back rows of
     * a Chess board. This method does not initialize the Pawn pieces.
     *
     * @param pieceColor color with which to initialize the pieces with.
     * @param row Chess board row number on which to initialize the pieces.
     */
    private void initializePieces(Color pieceColor, int row) {
        Point piecePosition;

        /* initialize King */
        piecePosition = new Point(4, row);
        pieces[4][row] = new King(pieceColor, piecePosition);

        /* initialize Queen */
        piecePosition = new Point(3, row);
        pieces[3][row] = new Queen(pieceColor, piecePosition);

        /* initialize Bishops */
        piecePosition = new Point(2, row);
        pieces[2][row] = new Bishop(pieceColor, piecePosition);
        piecePosition = new Point(5, row);
        pieces[5][row] = new Bishop(pieceColor, piecePosition);

        /* initialize Knights */
        piecePosition = new Point(1, row);
        pieces[1][row] = new Knight(pieceColor, piecePosition);
        piecePosition = new Point(6, row);
        pieces[6][row] = new Knight(pieceColor, piecePosition);

        /* initialize Rooks */
        piecePosition = new Point(0, row);
        pieces[0][row] = new Rook(pieceColor, piecePosition);
        piecePosition = new Point(7, row);
        pieces[7][row] = new Rook(pieceColor, piecePosition);
    }

    /**
     * initializeCustomPieces method initializes the custom Chess pieces on a Chess board.
     *
     * @param pieceColor color with which to initialize the pieces with.
     * @param row Chess board row number on which to initialize the custom pieces.
     */
    private void initializeCustomPieces(Color pieceColor, int row) {
        Point piecePosition;

        /* initialize Amazons and Coyotes */
        if (pieceColor == Color.BLACK) {
            piecePosition = new Point(0, row);
            pieces[0][row] = new Amazon(pieceColor, piecePosition);
            piecePosition = new Point(7, row);
            pieces[7][row] = new Coyote(pieceColor, piecePosition);
        } else {
            piecePosition = new Point(0, row);
            pieces[0][row] = new Amazon(pieceColor, piecePosition);
            piecePosition = new Point(7, row);
            pieces[7][row] = new Coyote(pieceColor, piecePosition);
        }
    }

    /**
     * initializePawns method that initializes all the Chess pawn pieces.
     *
     * @param pieceColor color with which to initialize the pawns pieces with.
     * @param row Chess board row number on which to initialize the pawns.
     */
    private void initializePawns(Color pieceColor, int row) {
        Point piecePosition;

        for (int column = 0; column < columns; column++) {
            piecePosition = new Point(column, row);
            pieces[column][row] = new Pawn(pieceColor, piecePosition);
        }
    }

    /**
     * initializeEmptySquares method sets all the empty pieces on a Chess board
     * to null, to distinguish them in the absence of a Chess piece.
     */
    private void initializeEmptySquares() {
        for (int column = 0; column < columns; column++) {
            for (int row = 2; row < (rows - 2); row++) {
                pieces[column][row] = null;
            }
        }
    }

    /**
     * toString is an overridden method that gets the board in a simple visual format.
     *
     * @return a simple visual for a Chess board (for easy debugging).
     */
    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();

        for (int row = rows - 1; row >= 0; row--) {
            for (int column = 0; column < columns; column++) {
                Piece piece = pieces[column][row];
                if (piece == null) {
                    board.append("| - ");
                } else {
                    board.append("| " + piece + " ");
                }
            }
            board.append("|\n");
        }

        return board.toString();
    }

    /**
     * createStandardBoard method makes a new standard Chess board.
     *
     * @return a new standard Chess board.
     */
    public static Board createStandardBoard() {
        return new Board(8, 8, false);
    }

    /**
     * createCustomBoard method makes a new custom Chess board.
     *
     * @return a new custom Chess board.
     */
    public static Board createCustomBoard() {
        return new Board(8, 8, true);
    }
}

/**
 * main.controller is where all the components needed for
 * the controller of this application lives.
 */
package main.controller;

import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.view.View;
import main.view.eventlisteners.*;

/**
 * A simple Controller class. The main method to play Chess is called here.
 *
 * @author Sunaya Shivakumar
 */
public class Controller {
    /** A Chess game view. */
    public static View gameView;

    /** A Chess board to play the game on. */
    public static Board gameBoard;

    /** Color to keep track of players' turn. */
    public static Color currentPlayer;

    /** Integer to keep track of black player's score. */
    public static int blackScoreCount;

    /** Integer to keep track of the white player's score. */
    public static int whiteScoreCount;

    /** Color of the winning player. */
    public static Color winner;

    /** Color of the King that's checkmated. */
    public static Color checkmate;

    /** Boolean to check if the game ended in a draw. */
    public static boolean stalemate;

    /**
     * main method that initializes a game and updates the board.
     * It also prints out a board to visualize how pieces move.
     *
     * @param args arguments passed in from terminal.
     */
    public static void main(String[] args) {
        blackScoreCount = 0;
        whiteScoreCount = 0;
        initializeGame(new Board(8,8, false));
        gameView = new View(gameBoard);
        addEventListenersToButtons();
    }

    /**
     * initializeGame method initializes all the parameters required
     * to new game conditions and states.
     */
    public static void initializeGame(Board board) {
        gameBoard = board;
        currentPlayer = Color.WHITE;
        winner = null;
        checkmate = null;
        stalemate = false;
        gameBoard.populateValidSquaresForPlayer(currentPlayer);
        gameBoard.populateValidSquaresForPlayer(currentPlayer.opposite());
    }

    /**
     * updateBoard method updates game states and player turns.
     */
    public static void updateBoard() {
        gameBoard.populateValidSquaresForPlayer(currentPlayer);
        gameBoard.populateValidSquaresForPlayer(currentPlayer.opposite());
        currentPlayer = currentPlayer.opposite();
        updateGame();
    }

    /**
     * updateGame checks for whether a King is in check, and if so assigns
     * the winner. It also updates if the game ended in a draw.
     */
    private static void updateGame() {
        Piece king = (currentPlayer == Color.BLACK) ? gameBoard.blackKing : gameBoard.whiteKing;

        if (gameBoard.kingInCheck(currentPlayer)) {
            if (king.validMoveSquares.isEmpty()) {
                checkmate = currentPlayer;
                winner = currentPlayer.opposite();
                if (winner == Color.BLACK) {
                    blackScoreCount++;
                } else {
                    whiteScoreCount++;
                }
                gameView.resetBoard(gameBoard);
                Controller.addEventListenersToButtons();
            }
        }

        if (!gameBoard.movesRemain(Color.BLACK) && !gameBoard.movesRemain(Color.WHITE)) {
            stalemate = true;
            blackScoreCount++;
            whiteScoreCount++;
        }
    }

    /**
     * addEventListenersToButtons adds mouse listeners to all the buttons on the
     * Chess GUI, including the squares on the Chess board.
     */
    public static void addEventListenersToButtons() {
        gameView.standardGame.addMouseListener(new StandardGame());
        gameView.customGame.addMouseListener(new CustomGame());
        gameView.restartGame.addMouseListener(new RestartGame());
        gameView.blackForfeit.addMouseListener(new BlackForfeit());
        gameView.whiteForfeit.addMouseListener(new WhiteForfeit());
        gameView.undoMove.addMouseListener(new UndoMove());

        for (int column = 0; column < gameBoard.columns; column++) {
            for (int row = 0; row < gameBoard.rows; row++) {
                gameView.squares[column][row].addMouseListener(new SquareListener());
            }
        }
    }
}

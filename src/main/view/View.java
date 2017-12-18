package main.view;

import main.controller.Controller;
import main.model.Board;
import main.model.properties.Color;

import javax.swing.*;
import java.awt.*;

/**
 * View class to create a GUI for a game of Chess.
 *
 * @author Sunaya Shivakumar
 */
public class View {
    /** Frame to add the panel to. */
    public JFrame frame;

    /** Panel to add the buttons to. */
    public JPanel panel;

    /** Easy access to the game board. */
    public Board board;

    /** A 2D array of buttons or Chess board Squares. */
    public Square[][] squares;

    /** Button to start a new standard game of Chess. */
    public JButton standardGame;

    /** Button to start a new custom game of Chess. */
    public JButton customGame;

    /** Button to start a restart current game of Chess. */
    public JButton restartGame;

    /** Button to undo moves in current game of Chess. */
    public JButton undoMove;

    /** Text field to prompt for name. */
    public JTextField blackPlayer;

    /** Text field for black player's name. */
    public JTextField blackPlayerName;

    /** Text field for black player's score. */
    public JTextField blackScore;

    /** Button to forfeit current game by black player. */
    public JButton blackForfeit;

    /** Text field to prompt for name. */
    public JTextField whitePlayer;

    /** Text field for white player's name. */
    public JTextField whitePlayerName;

    /** Text field for white player's score. */
    public JTextField whiteScore;

    /** Button to forfeit current game by white player. */
    public JButton whiteForfeit;

    /** Text field to show announcements about player turns and game statuses. */
    public JTextField announcement;

    /** The xy dimensions for each Square. */
    private final int squareDimension = 60;

    /** The xy dimensions for a Chess board. */
    private final int boardDimension = squareDimension * 8;

    /** The dimensions for the frame. */
    private final Dimension frameDimension = new Dimension(2 * boardDimension, boardDimension + 90);

    /**
     * View constructor to create a Chess game view or GUI.
     *
     * @param gameBoard the Chess board to display.
     */
    public View(Board gameBoard) {
        frame = new JFrame("Chess Game");
        frame.setSize(frameDimension);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        resetBoard(gameBoard);
    }

    /**
     * resetBoard method creates buttons or Squares for each Chess board square. It also sets
     * the bounds, the Color, and the Chess piece image (if there's a piece present) for each Square.
     */
    public void resetBoard(Board gameBoard) {
        panel = new JPanel(null);
        board = gameBoard;
        squares = new Square[board.columns][board.rows];
        for (int row = 0; row < board.rows; row++) {
            for (int column = 0; column < board.columns; column++) {
                squares[column][row] = new Square(column, row);
                Square square = squares[column][row];

                int xOffset = column * squareDimension;
                int yOffset = boardDimension - ((row + 1) * squareDimension);
                square.setBounds(xOffset, yOffset, squareDimension, squareDimension);

                if ((row + column) % 2 == 0) {
                    square.setDarkSquare();
                } else {
                    square.setLightSquare();
                }

                if (board.pieces[column][row] != null) {
                    square.setImageIcon(board.pieces[column][row].image);
                }

                panel.add(square);
            }
        }

        addButtonsToPanel(panel);
        updateTextAndButtons();

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    /**
     * updateTextAndButtons updates the announcement text
     * and also the status of the different buttons on the GUI.
     */
    public void updateTextAndButtons() {
        if (Controller.winner == Color.BLACK) {
            announcement.setText("Black wins this game");
        }

        if (Controller.winner == Color.WHITE) {
            announcement.setText("White wins this game");
        }

        if (Controller.winner == null) {
            if (Controller.currentPlayer == Color.BLACK) {
                announcement.setText("Black player's turn");
                blackForfeit.setEnabled(true);
                whiteForfeit.setEnabled(false);
            } else {
                announcement.setText("White player's turn");
                blackForfeit.setEnabled(false);
                whiteForfeit.setEnabled(true);
            }
        }

        if (Controller.stalemate) {
            announcement.setText("Game ended in stalemate");
        }
    }

    /**
     * addButtonsToPanel adds all the UI buttons to the panel.
     *
     * @param panel the panel to add the buttons to.
     */
    private void addButtonsToPanel(JPanel panel) {
        gameButtons();
        panel.add(standardGame);
        panel.add(customGame);
        panel.add(restartGame);

        undoButtons();
        panel.add(undoMove);

        forfeitButtons();
        panel.add(blackForfeit);
        panel.add(whiteForfeit);

        setPlayers();
        panel.add(blackPlayer);
        panel.add(whitePlayer);
        panel.add(blackPlayerName);
        panel.add(whitePlayerName);

        setScores();
        panel.add(blackScore);
        panel.add(whiteScore);

        setAnnouncement();
        panel.add(announcement);

        setGameStatus(panel);
    }

    /**
     * gameButtons method creates and sets bounds for the game buttons.
     */
    private void gameButtons() {
        standardGame = new JButton("Start new standard game");
        standardGame.setBounds(700, 155, 200, 50);
        customGame = new JButton("Start new custom game");
        customGame.setBounds(700, 215, 200, 50);
        restartGame = new JButton("Restart current game");
        restartGame.setBounds(700, 275, 200, 50);
    }

    /**
     * undoButtons method creates and sets bounds for undo button.
     */
    private void undoButtons() {
        undoMove = new JButton("UNDO");
        undoMove.setBounds(540, 215, 100, 50);
    }

    /**
     * forfeitButtons method creates and sets bounds for the forfeit buttons.
     */
    private void forfeitButtons() {
        blackForfeit = new JButton("Forfeit game - Black");
        blackForfeit.setBounds(700, 60, 200, 40);
        whiteForfeit = new JButton("Forfeit game - White");
        whiteForfeit.setBounds(700, 380, 200, 40);
    }

    /**
     * setPlayers method creates text fields for player names.
     */
    private void setPlayers() {
        blackPlayer = new JTextField("Black player - ");
        blackPlayer.setBounds(545, 10, 100, 40);
        blackPlayer.setBackground(null);
        blackPlayer.setBorder(null);
        blackPlayer.setEditable(false);

        blackPlayerName = new JTextField("");
        blackPlayerName.setBounds(700, 10, 200, 40);
        blackPlayerName.setBackground(null);
        blackPlayerName.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        whitePlayer = new JTextField("White player - ");
        whitePlayer.setBounds(545, 430, 100, 40);
        whitePlayer.setBackground(null);
        whitePlayer.setBorder(null);
        whitePlayer.setEditable(false);

        whitePlayerName = new JTextField("");
        whitePlayerName.setBounds(700, 430, 200, 40);
        whitePlayerName.setBackground(null);
        whitePlayerName.setHorizontalAlignment(SwingConstants.HORIZONTAL);
    }

    /**
     * setScores method creates text fields for player scores.
     * These text fields are not editable by the players.
     */
    private void setScores() {
        blackScore = new JTextField("Black score - " + Controller.blackScoreCount);
        blackScore.setBounds(545, 60, 200, 40);
        blackScore.setEditable(false);
        blackScore.setBackground(null);
        blackScore.setBorder(null);

        whiteScore = new JTextField("White score - " + Controller.whiteScoreCount);
        whiteScore.setBounds(545, 380, 200, 40);
        whiteScore.setBackground(null);
        whiteScore.setBorder(null);
        blackScore.setEditable(false);
    }

    /**
     * setAnnouncement creates and sets bounds for the announcement box.
     */
    private void setAnnouncement() {
        announcement = new JTextField("Welcome");
        announcement.setBounds(545, 500, 350, 30);
        announcement.setEditable(false);
        announcement.setBorder(null);
        announcement.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));
    }

    /**
     * setGameStatus creates and sets bounds for the legend of colors
     * at the bottom of the Chess board.
     *
     * @param panel the panel to which we have add the keys.
     */
    private void setGameStatus(JPanel panel) {
        Square greenSquare = new Square(500, 510);
        greenSquare.setBounds(15, 500, 30, 30);
        greenSquare.setGreen();
        panel.add(greenSquare);
        JTextField greenKey = new JTextField(" - valid move");
        greenKey.setBounds(55, 500, 100, 30);
        greenKey.setEditable(false);
        greenKey.setBackground(null);
        greenKey.setBorder(null);
        panel.add(greenKey);

        Square yellowSquare = new Square(660, 510);
        yellowSquare.setBounds(175, 500, 30, 30);
        yellowSquare.setYellow();
        panel.add(yellowSquare);
        JTextField yellowKey = new JTextField(" - in check");
        yellowKey.setBounds(215, 500, 100, 30);
        yellowKey.setEditable(false);
        yellowKey.setBackground(null);
        yellowKey.setBorder(null);
        panel.add(yellowKey);

        Square redSquare = new Square(660, 510);
        redSquare.setBounds(335, 500, 30, 30);
        redSquare.setRed();
        panel.add(redSquare);
        JTextField redKey = new JTextField(" - checkmate");
        redKey.setBounds(375, 500, 100, 30);
        redKey.setEditable(false);
        redKey.setBackground(null);
        redKey.setBorder(null);
        panel.add(redKey);
    }

    /**
     * redrawBoard redraws a board after a move has been made.
     *
     * @param currentBoard the board in it's current state.
     */
    public void redrawBoard(Board currentBoard) {
        for (int row = 0; row < currentBoard.rows; row++) {
            for (int column = 0; column < currentBoard.columns; column++) {
                Square square = squares[column][row];

                if ((row + column) % 2 == 0) {
                    square.setDarkSquare();
                } else {
                    square.setLightSquare();
                }
            }
        }
    }

    /**
     * redrawPieces redraws all the pieces onto the board
     * after a move has been made.
     *
     * @param currentBoard the board in it's current state.
     */
    public void redrawPieces(Board currentBoard) {
        for (int row = 0; row < currentBoard.rows; row++) {
            for (int column = 0; column < currentBoard.columns; column++) {
                Square square = squares[column][row];
                if (currentBoard.pieces[column][row] != null) {
                    square.setImageIcon(currentBoard.pieces[column][row].image);
                } else {
                    square.setImageIcon(null);
                }
            }
        }
    }
}

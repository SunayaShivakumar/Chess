package main.view.eventlisteners;

import main.controller.Controller;
import main.model.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * WhiteForfeit extends MouseAdapter for the white player's forfeit button.
 *
 * @author Sunaya Shivakumar
 */
public class WhiteForfeit extends MouseAdapter {
    /** An instance of the Chess board. */
    public Board newBoard;

    /**
     * WhiteForfeit makes a new board after a game has been forfeited.
     */
    public WhiteForfeit() {
        if (Controller.gameBoard.customize) {
            newBoard = Board.createCustomBoard();
        } else {
            newBoard = Board.createStandardBoard();
        }
    }

    /**
     * mouseClicked is overridden to define what should happen
     * when the white player's forfeit button has been clicked.
     *
     * @param event the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        /* update model */
        Controller.initializeGame(newBoard);
        Controller.blackScoreCount++;

        /* update view */
        Controller.gameView.resetBoard(newBoard);
        Controller.addEventListenersToButtons();

        javax.swing.JOptionPane.showMessageDialog(null, "White forfeits game. Play again");
    }
}

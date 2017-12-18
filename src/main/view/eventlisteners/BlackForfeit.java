/**
 * main.view.eventlisteners contains all the listeners
 * for all the buttons and squares in the Chess GUI.
 */
package main.view.eventlisteners;

import main.controller.Controller;
import main.model.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * BlackForfeit extends MouseAdapter for the black player's forfeit button.
 *
 * @author Sunaya Shivakumar
 */
public class BlackForfeit extends MouseAdapter {
    /** An instance of the Chess board. */
    public Board newBoard;

    /**
     * BlackForfeit makes a new board after a game has been forfeited.
     */
    public BlackForfeit() {
        if (Controller.gameBoard.customize) {
            newBoard = Board.createCustomBoard();
        } else {
            newBoard = Board.createStandardBoard();
        }
    }

    /**
     * mouseClicked is overridden to define what should happen
     * when the black player's forfeit button has been clicked.
     *
     * @param event the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        /* update model */
        Controller.initializeGame(newBoard);
        Controller.whiteScoreCount++;

        /* update view */
        Controller.gameView.resetBoard(newBoard);
        Controller.addEventListenersToButtons();

        javax.swing.JOptionPane.showMessageDialog(null, "Black forfeits game. Play again");
    }
}

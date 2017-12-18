package main.view.eventlisteners;

import main.controller.Controller;
import main.model.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * RestartGame extends MouseAdapter for the restart game button.
 *
 * @author Sunaya Shivakumar
 */
public class RestartGame extends MouseAdapter {
    /** An instance of the Chess board. */
    public Board newBoard;

    /**
     * RestartGame makes a new board after a game has been restarted.
     */
    public RestartGame() {
        if (Controller.gameBoard.customize) {
            newBoard = Board.createCustomBoard();
        } else {
            newBoard = Board.createStandardBoard();
        }
    }

    /**
     * mouseClicked is overridden to define what should happen
     * when the restart game button has been clicked.
     *
     * @param event the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        /* update model */
        Controller.initializeGame(newBoard);

        /* update view */
        Controller.gameView.resetBoard(newBoard);
        Controller.addEventListenersToButtons();

        javax.swing.JOptionPane.showMessageDialog(null, "Game restarted");
    }
}

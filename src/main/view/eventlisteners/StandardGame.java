package main.view.eventlisteners;

import main.controller.Controller;
import main.model.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * StandardGame extends MouseAdapter for the new standard game button.
 *
 * @author Sunaya Shivakumar
 */
public class StandardGame extends MouseAdapter {
    /** An instance of the Chess board. */
    public Board newBoard;

    /**
     * StandardGame makes a new standard board.
     */
    public StandardGame() {
        newBoard = Board.createStandardBoard();
    }

    /**
     * mouseClicked is overridden to define what should happen
     * when the new standard game button has been clicked.
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

        javax.swing.JOptionPane.showMessageDialog(null, "New standard game of Chess");
    }
}

package main.view.eventlisteners;

import main.controller.Controller;
import main.model.Board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * CustomGame extends MouseAdapter for the new custom game button.
 *
 * @author Sunaya Shivakumar
 */
public class CustomGame extends MouseAdapter{
    /** An instance of the Chess board. */
    public Board newBoard;

    /**
     * CustomGame makes a new custom board.
     */
    public CustomGame() {
        newBoard = Board.createCustomBoard();
    }

    /**
     * mouseClicked is overridden to define what should happen
     * when the new custom game button has been clicked.
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

        javax.swing.JOptionPane.showMessageDialog(null, "New custom game of Chess");
    }
}

package main.view.eventlisteners;

import main.controller.Controller;
import main.controller.Snapshot;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SquareListener extends MouseAdapter for all the squares on a Chess board.
 *
 * @author Sunaya Shivakumar
 */
public class UndoMove extends MouseAdapter {
    /**
     * mouseClicked is overridden to define what should happen
     * when the undo button is clicked, and restores the game to
     * the previous state using the snapshot taken.
     *
     * @param event the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        /* update model */
        Point newPosition = Snapshot.movedPiece.position;
        Controller.gameBoard.movePiece(Snapshot.movedPiece, Snapshot.prevPosition);
        if (Snapshot.killedPiece != null) {
            Controller.gameBoard.pieces[newPosition.x][newPosition.y] = Snapshot.killedPiece;
        }
        Controller.updateBoard();

        /* update view */
        Controller.gameView.resetBoard(Controller.gameBoard);
        Controller.addEventListenersToButtons();

        javax.swing.JOptionPane.showMessageDialog(null, "Last move undone");
    }
}

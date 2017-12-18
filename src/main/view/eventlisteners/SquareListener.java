package main.view.eventlisteners;

import main.controller.Controller;
import main.controller.Snapshot;
import main.model.Board;
import main.model.Piece;
import main.model.properties.Color;
import main.view.Square;
import main.view.View;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * SquareListener extends MouseAdapter for all the squares on a Chess board.
 *
 * @author Sunaya Shivakumar
 */
public class SquareListener extends MouseAdapter {
    /** An instance of the Chess board. */
    public Board newBoard;

    /** An instance of the Chess view. */
    public View newView;

    /** boolean to check if a piece is being moved. */
    public static boolean movingPiece;

    /** An instance of the piece that has to be moved. */
    public static Piece pieceToMove;

    /**
     * SquareListener constructor sets all the initial conditions.
     */
    public SquareListener() {
        newBoard = Controller.gameBoard;
        newView = Controller.gameView;
        movingPiece = false;
        pieceToMove = null;
    }

    /**
     * mouseClicked is overridden to define what should happen
     * when a square button has been clicked.
     *
     * @param event the mouse click event.
     */
    @Override
    public void mouseClicked(MouseEvent event) {
        Square squareClicked = (Square) event.getSource();
        if (movingPiece) {
            if (pieceToMove.validMoveSquares.contains(squareClicked.getPositionOfSquare())) {
                moveToSquare(squareClicked);
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Illegal move! Try again");
                newView.redrawBoard(newBoard);
                newView.redrawPieces(newBoard);
                movingPiece = false;
                pieceToMove = null;
            }
        } else {
            Piece piece = squareClicked.getPieceOnSquare(newBoard);
            if (piece != null && piece.color == Controller.currentPlayer) {
                highlightSquares(piece);
                pieceToMove = piece;
            }
        }
    }

    /**
     * moveToSquare moves a piece to a given square, after taking a snapshot of
     * the current state of the game. It updates both the model and the view.
     *
     * @param square the square to be moved to.
     */
    public void moveToSquare(Square square) {
        Snapshot.movedPiece = pieceToMove;
        Snapshot.killedPiece = square.getPieceOnSquare(newBoard);
        Snapshot.prevPosition = pieceToMove.position;

        /* Update model */
        newBoard.movePiece(pieceToMove, square.getPositionOfSquare());
        Controller.updateBoard();

        /* Update view */
        newView.redrawBoard(newBoard);
        newView.redrawPieces(newBoard);
        newView.updateTextAndButtons();
        updateCheckSquare(Controller.currentPlayer);
        movingPiece = !movingPiece;
    }

    /**
     * highlightSquares highlights all the squares green, indicating
     * the squares to which a given piece could move to.
     *
     * @param piece the piece to be moved.
     */
    public void highlightSquares(Piece piece) {
        for (Point movePosition : piece.validMoveSquares) {
            newView.squares[movePosition.x][movePosition.y].setGreen();
        }
        movingPiece = !movingPiece;
    }

    /**
     * updateCheckSquare highlights a King square red or yellow
     * based on if a King is in check or has been checkmated.
     *
     * @param color the color of the king to check.
     */
    public void updateCheckSquare(Color color) {
        Piece king;
        if (color == Color.BLACK) {
            king = newBoard.blackKing;
        } else {
            king = newBoard.whiteKing;
        }

        if (newBoard.kingInCheck(color)) {
            if (king.validMoveSquares.isEmpty()) {
                newView.squares[king.position.x][king.position.y].setRed();
            } else {
                newView.squares[king.position.x][king.position.y].setYellow();
            }
        }
    }
}

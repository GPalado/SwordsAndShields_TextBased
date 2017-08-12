package Actions;

import SnSGame.Board;
import SnSGame.InvalidMoveException;
import SnSGame.Player;
import Tiles.Reactables.Piece;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class provides the concrete implementation of a Rotate action
 */
public class RotateAction implements Action {

    private Set<Integer> validAmounts = new HashSet<>(Arrays.asList(new Integer[] { 0, 90, 180, 270 }));
    private Player player;
    private Piece piece;
    private int amount;

    /**
     * The constructor takes the piece to be rotated, the amount of rotation, and the player involved.
     * @param p
     * @param amt
     * @param pl
     */
    public RotateAction(Piece p, int amt, Player pl){
        if(!validAmounts.contains(amt)) throw new InvalidMoveException("Invalid rotation amount given");
        if(player.getPiecesMoved().contains(p)) throw new InvalidMoveException("Cannot move a piece that has already been moved!");
        amount = amt;
        piece=p;
        player=pl;
    }

    @Override
    public void execute(Board board) {
        piece.rotate(amount);
        player.pieceMoved(piece);
        player.addAction(this);
    }

    @Override
    public void undo() {
        piece.rotate(360-amount);
        player.pieceNotMoved(piece);
    }
}

package SnSGame;

import Actions.Action;
import Tiles.CreationSquare;
import Tiles.Reactables.Face;
import Tiles.Reactables.Piece;
import Tiles.Reactables.Reactable;
import java.awt.*;
import java.util.*;

/**
 * This class is a representation of a Player in the Swords an Shields game.
 */
public class Player {
    public final Color color;
    public final Face face;
    public final CreationSquare creationSquare;
    public final boolean isCaps;
    private Map<Character, Piece> pieces = new HashMap<>();
    private Deque<Action> actions = new ArrayDeque<>();
    private Set<Piece> piecesMoved = new HashSet<>();
    private boolean hasCreated;

    /**
     * The constructor takes the player's color, whether their piece representations will be CAPITALS or lowercase,
     * their face, and their creation square.
     * The constructor then uses this information to initialize the player's 24 pieces.
     * @param c
     * @param caps
     * @param f
     * @param cs
     */
    public Player(Color c, boolean caps, Face f, CreationSquare cs){
        color=c;
        isCaps=caps;
        hasCreated = false;
        face=f;
        creationSquare=cs;
        initializePieces();
    }

    /**
     * This method returns the pieces that the player has moved during their current turn.
     * @return
     */
    public Set<Piece> getPiecesMoved(){
        return piecesMoved;
    }

    /**
     * This method receives a piece that has been moved in this players current turn and adds it to the set of piecesMoved
     * @param p
     */
    public void pieceMoved(Piece p){
        piecesMoved.add(p);
    }

    /**
     * This method receives a piece that is to be removed from the set of piecesMoved
     * @param p
     */
    public void pieceNotMoved(Piece p){
        if(piecesMoved.contains(p)) piecesMoved.remove(p);
    }

    /**
     * This method adds the given action to the player's Deque of actions
     * @param action
     */
    public void addAction(Action action){
        actions.add(action);
    }

    /**
     * This method returns the most recent action made by the player to be undone.
     * @return
     */
    public Action undo(){
        if(actions.isEmpty()){
            throw new InvalidMoveException("Cannot undo any further");
        }
        return actions.pollLast();
    }

    /**
     * This method returns a boolean representing whether the player has created a piece/skipped creating a piece this turn or not.
     * @return
     */
    public boolean hasCreated(){
        return hasCreated;
    }

    /**
     * This method takes a boolean, which hasCreated is to be set to.
     * @param b
     */
    public void setCreated(boolean b){
        hasCreated=b;
    }

    /**
     * This method returns a boolean representing whether the player has moved a piece this turn or not.
     * @return
     */
    public boolean hasMoved(){ return !piecesMoved.isEmpty(); }

    /**
     * This method draws all the player's unused pieces to offer them choices for creation.
     */
    public void drawUnusedPieces(){
        //todo implement this
    }

    /**
     * This move passes a players turn.
     * This involves clearing the player's actions and pieces moved, and setting hasCreated to false.
     */
    public void pass(){
        actions=new ArrayDeque<>();
        hasCreated=false;
        piecesMoved.clear();
    }

    /**
     * This method returns the player's piece which corresponds to the given character.
     * @param c
     * @return
     */
    public Piece getPiece(char c){
        if(isCaps){
            c=Character.toUpperCase(c);
        } else {
            c=Character.toLowerCase(c);
        }
        if(pieces.get(c)==null) throw new IllegalArgumentException("That piece does not exist");
        return pieces.get(c);
    }

    /**
     * This method returns whether the player has any moves left or not.
     * i.e. have they moved all the pieces they are able to move?
     * or have they not moved or created anything?
     * @return
     */
    public boolean hasMovesLeft(){
        boolean pieceNotMoved=false;
        boolean hasMovedAPiece=false;
        for(Piece p : pieces.values()){
            if(!p.getStatus().equals(Reactable.Status.ON_BOARD)) continue;
            if(!piecesMoved.contains(p)){
                pieceNotMoved=true;
            } else {
                hasMovedAPiece=true;
            }
        }
        return (!hasCreated && !hasMovedAPiece) || pieceNotMoved;
    }

    /**
     * This method initializes the player's pieces depending on whether their representations are CAPITALIZED or lowercase
     */
    private void initializePieces() {
        if (isCaps) {
            char c = 'A';
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
        } else {
            char c = 'a';
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING, Piece.Symbol.SWORD_HORIZONTAL}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.NOTHING, Piece.Symbol.NOTHING}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.SWORD_VERTICAL, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
            pieces.put(c, new Piece(new Piece.Symbol[]{Piece.Symbol.NOTHING, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD, Piece.Symbol.SHIELD}, c++));
        }
    }
}

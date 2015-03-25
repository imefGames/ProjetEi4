package istia.ei4.pm.ia.ricochet;

import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.AWorld;
import istia.ei4.pm.ia.IGameMove;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Pierre Michel
 */
public class RRGameState extends AGameState {
  
  public RRGameState(AGameState parentState, IGameMove previousMove){
    super(parentState, previousMove);
    if(parentState != null){
      this.allPieces = new ArrayList<>();
      this.mainPiece = new RRPiece(((RRGameState)parentState).getMainPiece());
      this.secondaryPieces = new RRPiece[((RRGameState)parentState).secondaryPieces.length];
      for(int i=0; i<((RRGameState)parentState).secondaryPieces.length; i++){
        this.secondaryPieces[i] = new RRPiece(((RRGameState)parentState).secondaryPieces[i]);
      }
      this.allPieces.add(this.mainPiece);
      for(int i=0; i<secondaryPieces.length; i++){
        this.allPieces.add(secondaryPieces[i]);
      }
    }
  }
  
  public void setPieces(RRPiece mainPiece, RRPiece[] secondaryPieces){
    this.allPieces = new ArrayList<>();
    this.allPieces.add(mainPiece);
    for(int i=0; i<secondaryPieces.length; i++){
      this.allPieces.add(secondaryPieces[i]);
    }
    this.mainPiece = mainPiece;
    this.secondaryPieces = secondaryPieces;
  }
  
  @Override
  public ArrayList<AGameState> computeDerivedStates(AWorld world) {
    RRWorld w = ((RRWorld)world);
    for(RRPiece piece : this.allPieces){
      w.putPiece(piece, true);
    }
    RRGameState ns;
    //UP
    ns = new RRGameState(this, new RRGameMove(this.mainPiece, ERRGameMove.UP));
    w.doMove(ns.mainPiece, ns, ERRGameMove.UP);
    this.derivedStates.add(ns);
    //DOWN
    ns = new RRGameState(this, new RRGameMove(this.mainPiece, ERRGameMove.DOWN));
    w.doMove(ns.mainPiece, ns, ERRGameMove.DOWN);
    this.derivedStates.add(ns);
    //LEFT
    ns = new RRGameState(this, new RRGameMove(this.mainPiece, ERRGameMove.LEFT));
    w.doMove(ns.mainPiece, ns, ERRGameMove.LEFT);
    this.derivedStates.add(ns);
    //RIGHT
    ns = new RRGameState(this, new RRGameMove(this.mainPiece, ERRGameMove.RIGHT));
    w.doMove(ns.mainPiece, ns, ERRGameMove.RIGHT);
    this.derivedStates.add(ns);
    
    for(int i=0; i<this.secondaryPieces.length; i++){
      //UP
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.UP));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.UP);
      this.derivedStates.add(ns);
      //DOWN
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.DOWN));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.DOWN);
      this.derivedStates.add(ns);
      //LEFT
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.LEFT));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.LEFT);
      this.derivedStates.add(ns);
      //RIGHT
      ns = new RRGameState(this, new RRGameMove(this.secondaryPieces[i], ERRGameMove.RIGHT));
      w.doMove(ns.secondaryPieces[i], ns, ERRGameMove.RIGHT);
      this.derivedStates.add(ns);
    }
    for(RRPiece piece : this.allPieces){
      w.putPiece(piece, false);
    }
    return this.derivedStates;
  }
  
  @Override
  public long computeHash(AWorld world){
    long ret = 0L;
    ret |= ((long)(mainPiece.getX()));
    ret |= ((long)(mainPiece.getY())) << 8;
    Arrays.sort(this.secondaryPieces);
    for(int i=0; i<this.secondaryPieces.length; i++){
      ret |= ((long)(secondaryPieces[i].getX())) << 16 + 16*i;
      ret |= ((long)(secondaryPieces[i].getY())) << 24 + 16*i;
    }
    return ret;
  }
  
  public ArrayList<RRPiece> getPieces(){
    return this.allPieces;
  }
  
  public RRPiece getMainPiece(){
    return this.mainPiece;
  }
  
  @Override
  public String toString(){
    String str = "";
    str += this.hashCode();
    str += "\nMain Piece:\n";
    str += String.format("%d -> x:%d, y:%d, color:%d\n", this.mainPiece.hashCode(), this.mainPiece.getX(), this.mainPiece.getY(), this.mainPiece.getColor());
    str += "Secondary Pieces:\n";
    for(RRPiece p : this.secondaryPieces){
      str += String.format("%d -> x:%d, y:%d, color:%d\n", p.hashCode(), p.getX(), p.getY(), p.getColor());
    }
    
    return str;
  }
  
  private ArrayList<RRPiece> allPieces;
  private RRPiece[] secondaryPieces;
  private RRPiece mainPiece;
  
}

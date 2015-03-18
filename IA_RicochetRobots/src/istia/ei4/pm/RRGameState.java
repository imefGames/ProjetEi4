package istia.ei4.pm;

import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.AWorld;
import istia.ei4.pm.ia.IGameMove;
import java.util.ArrayList;

/**
 *
 * @author Pierre Michel
 */
public class RRGameState extends AGameState {

  public RRGameState(AGameState parentState, IGameMove previousMove){
    super(parentState, previousMove);
  }
  
  public void setPieces(ArrayList<RRPiece> mainPieces, ArrayList<RRPiece> secondaryPieces){
    this.allPieces = new ArrayList<RRPiece>();
    this.allPieces.addAll(mainPieces);
    this.allPieces.addAll(secondaryPieces);
    this.mainPieces = mainPieces;
    this.secondaryPieces = secondaryPieces;
  }
  
  @Override
  public ArrayList<AGameState> computeDerivedStates(AWorld world) {
    // TODO: compute states
    return null;
  }
  
  public ArrayList<RRPiece> getPieces(){
    return this.allPieces;
  }
  
  private ArrayList<RRPiece> allPieces, mainPieces, secondaryPieces;
  
}

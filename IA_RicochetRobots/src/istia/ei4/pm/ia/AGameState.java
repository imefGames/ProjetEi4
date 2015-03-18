package istia.ei4.pm.ia;

import java.util.ArrayList;

/**
 *
 * @author Pierre Michel
 */
public abstract class AGameState {
  
  public AGameState(AGameState parentState, IGameMove previousMove){
    this.derivedStates = new ArrayList<AGameState>();
    this.parentState = parentState;
    this.previousMove = previousMove;
    if(parentState != null){
      this.depth = parentState.depth;
    }
  }
  
  public abstract ArrayList<AGameState> computeDerivedStates(AWorld world);
  
  public int getDepth(){
    return this.depth;
  }
  
  protected ArrayList<AGameState> derivedStates;
  protected AGameState parentState;
  protected IGameMove previousMove;
  protected int depth = 0;
  
}

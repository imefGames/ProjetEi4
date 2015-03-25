package istia.ei4.pm.ia;

import java.util.ArrayList;

/**
 *
 * @author Pierre Michel
 */
public abstract class AGameState {
  
  public AGameState(AGameState parentState, IGameMove previousMove){
    this.derivedStates = new ArrayList<>();
    this.parentState = parentState;
    this.previousMove = previousMove;
  }
  
  public abstract ArrayList<AGameState> computeDerivedStates(AWorld world);
  public abstract long computeHash(AWorld world);
  
  public AGameState getParentState(){
    return this.parentState;
  }
  
  public IGameMove getPreviousMove(){
    return this.previousMove;
  }
  
  protected ArrayList<AGameState> derivedStates;
  protected AGameState parentState;
  protected IGameMove previousMove;
  
}

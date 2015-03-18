package istia.ei4.pm;

import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.AWorld;
import istia.ei4.pm.ia.IGameMove;

/**
 *
 * @author Pierre Michel
 */
public class RRGameMove implements IGameMove {

  public RRGameMove(ERRGameMove move){
    this.move = move;
  }
  
  @Override
  public AGameState apply(AGameState state, AWorld world) {
    RRGameState newState = new RRGameState(state, this);
    
    // TODO: compute new state
    
    return newState;
  }
  
  private ERRGameMove move;
  
}

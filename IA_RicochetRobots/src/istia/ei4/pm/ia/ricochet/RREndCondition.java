package istia.ei4.pm.ia.ricochet;

import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.AWorld;
import istia.ei4.pm.ia.IEndCondition;

/**
 *
 * @author Pierre Michel
 */
public class RREndCondition implements IEndCondition{

  @Override
  public boolean checkEnd(AWorld world, AGameState state) {
    if(((RRWorld)world).isOnObjective(((RRGameState)state).getMainPiece())){
      return true;
    }
    return false;
  }
  
}

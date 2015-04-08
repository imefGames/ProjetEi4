package istia.ei4.pm.ia.ricochet;

import istia.ei4.pm.ia.AGameState;
import istia.ei4.pm.ia.AWorld;
import istia.ei4.pm.ia.IEndCondition;
import istia.ei4.pm.ia.SolverBFS;

/**
 * @author Pierre Michel
 */
public class RRSolver extends SolverBFS {

  public RRSolver(int maxDepth, AWorld world, AGameState baseState, IEndCondition endCondition){
    super(maxDepth, world, baseState, endCondition);
  }
  
  @Override
  public boolean additionnalRemovalCriteria(AGameState state) {
    RRWorld w = (RRWorld)world;
    RRPiece[] s = ((RRGameState)state).getMainPieces();
    RRPiece[] ps = ((RRGameState)(state.getParentState())).getMainPieces();
    for(int i=0; i<s.length; i++){
      if(w.scoreAtPosition(s[i]) > w.scoreAtPosition(ps[i])){
        return true;
      }//*/
      if(w.scoreAtPosition(s[i]) + this.currentDepth <= this.maxDepth){
        return false;
      }
    }
    return true;
  }
  
}

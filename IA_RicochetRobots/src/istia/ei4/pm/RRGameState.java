/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
  
  @Override
  public ArrayList<AGameState> computeDerivedStates(AWorld world) {
    // TODO: compute states
    return null;
  }

  @Override
  public void computeScore(AWorld world) {
    // TODO: compute scores
  }
  
  // TODO: add attributes
  
}

package istia.ei4.pm.ia;

import java.util.ArrayList;

public class SolverDFS {

	public SolverDFS(int maxDepth, AWorld world, AGameState baseState, IEndCondition endCondition){
    this.maxDepth = maxDepth;
    this.currentDepth = 0;
    this.world = world;
    this.baseState = baseState;
    this.nextLayer = new ArrayList<AGameState>();
    this.nextLayer.add(baseState);
    this.endCondition = endCondition;
	}
	
	public GameSolution solve(){
		boolean hasASolution = false;
    while(this.currentDepth < this.maxDepth && !hasASolution){
      ArrayList<AGameState> currentLayer = (ArrayList<AGameState>)(this.nextLayer.clone());
      this.nextLayer.clear();
      for(AGameState state : currentLayer){
        if(endCondition.checkEnd(this.world, state)){
          // TODO: get path to solution
        }
        ArrayList<AGameState> derivedStates = state.computeDerivedStates(this.world);
        // TODO: check if new states don't already exist
        this.nextLayer.addAll(derivedStates);
      }
    }
    if(hasASolution){
      // TODO: find that solution
    }
    return null;
	}
  
  private int maxDepth;
  private int currentDepth;
  private ArrayList<AGameState> nextLayer;
  
  private AWorld world;
  private AGameState baseState;
  private IEndCondition endCondition;
	
}

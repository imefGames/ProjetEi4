package istia.ei4.pm.ia;

import java.util.ArrayList;

public class SolverDFS {

	public SolverDFS(int maxDepth, AWorld world, AGameState baseState){
    this.maxDepth = maxDepth;
    this.currentDepth = 0;
    this.world = world;
    this.baseState = baseState;
    this.nextLayer = new ArrayList<AGameState>();
    this.nextLayer.add(baseState);
	}
	
	public GameSolution solve(){
		boolean hasASolution = false;
    while(this.currentDepth < this.maxDepth && !hasASolution){
      ArrayList<AGameState> currentLayer = (ArrayList<AGameState>)(this.nextLayer.clone());
      this.nextLayer.clear();
      for(AGameState state : currentLayer){
        // TODO: check if this state is the end
        this.nextLayer.addAll(state.computeDerivedStates(this.world));
        // TODO: check if new states don't already exist
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
	
}

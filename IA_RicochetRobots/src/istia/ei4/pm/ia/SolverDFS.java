package istia.ei4.pm.ia;

import java.util.ArrayList;

public class SolverDFS {

	public SolverDFS(int maxDepth, AWorld world, AGameState baseState, IEndCondition endCondition){
    this.maxDepth = maxDepth;
    this.currentDepth = 0;
    this.world = world;
    this.baseState = baseState;
    this.nextLayer = new ArrayList<>();
    this.nextLayer.add(baseState);
    this.endCondition = endCondition;
	}
	
	public GameSolution solve(){
		boolean hasASolution = false;
    ArrayList<Long> hashes = new ArrayList<>();
    hashes.add(this.baseState.computeHash(world));
    
    while(this.currentDepth < this.maxDepth && !hasASolution){
      System.out.println(this.currentDepth + " -> " + this.nextLayer.size());
      this.currentDepth++;
      ArrayList<AGameState> currentLayer = (ArrayList<AGameState>)(this.nextLayer.clone());
      this.nextLayer.clear();
      for(AGameState state : currentLayer){
        ArrayList<AGameState> derivedStates = state.computeDerivedStates(this.world);
        ///
        for(AGameState s : derivedStates){
          //check if solution
          if(endCondition.checkEnd(this.world, s)){
            AGameState upState = s;
            GameSolution solution = new GameSolution();
            while(upState.getParentState() != null){
              solution.insertMove(upState.getPreviousMove());
              upState = upState.getParentState();
            }
            return solution;
          }
          //check if hash already exists
          boolean hashFound = false;
          Long stateHash = s.computeHash(world);
          for(Long h : hashes){
            if(stateHash.equals(h)){
              hashFound = true;
            }
          }
          if(!hashFound){
            hashes.add(stateHash);
            this.nextLayer.add(s);
          }
        }
        ///
      }
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

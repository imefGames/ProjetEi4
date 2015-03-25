package istia.ei4.pm.ia.ricochet;

import istia.ei4.pm.ia.*;

public class MainIA {

	public static void main(String[] args) {
    
    RRGameState baseState = new RRGameState(null, null);
    
    RRPiece p = new RRPiece(1, 1, 1, 0);
    RRPiece[] mainL = new RRPiece[]{
      new RRPiece(9, 1, 2, 1),
      new RRPiece(9, 2, 3, 2),
      new RRPiece(9, 3, 4, 3)};
    baseState.setPieces(p, mainL);
    
    RRWorld world = new RRWorld();
    world.init();
    world.setObjective(12, 0, 1);
    
    world.show(baseState);
    
    
    RREndCondition endCondition = new RREndCondition();
    
		SolverDFS solver = new SolverDFS(10, world, baseState, endCondition);
		
    long t0 = System.currentTimeMillis();
		GameSolution solution = solver.solve();
    System.out.println("----\n" + (System.currentTimeMillis()-t0) + "\n----");
    
    if(solution == null){
      System.out.println("No Solution!");
    }else{
      for(IGameMove m : solution.getMoves()){
        System.out.println(m);
      }
    }
	}
}

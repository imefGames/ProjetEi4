package istia.ei4.pm;

import istia.ei4.pm.ia.*;

public class MainIA {

	public static void main(String[] args) {
    
    RRWorld world = new RRWorld();
    
    world.init();
    
    
    RRGameState baseState = new RRGameState(null, null);
    
		SolverDFS solver = new SolverDFS(10, world, baseState);
		
		GameSolution solution = solver.solve();
    
	}
}

package istia.ei4.pm.ia.ricochet;

import istia.ei4.ProjetISTIA.FileReadWrite;
import istia.ei4.ProjetISTIA.GridElement;
import istia.ei4.ProjetISTIA.MapObjects;
import istia.ei4.pm.ia.*;
import java.util.ArrayList;


public class MainIA {

	public static void main(String[] args) {
    
    /*RRGameState baseState = new RRGameState(null, null);
    baseState.setDepth(1);
    
    RRPiece[] mainL = new RRPiece[]{
      new RRPiece(1, 1, 1, 0)};
    RRPiece[] secL = new RRPiece[]{
      new RRPiece(9, 1, 2, 1),
      new RRPiece(9, 2, 3, 2),
      new RRPiece(9, 3, 4, 3)};
    baseState.setPieces(mainL, secL);
    
    RRWorld world = new RRWorld();
    world.init();
    world.setObjective(12, 0, 1);*/
    
    RRWorld world = null;
    String text = null;
    RRGameState baseState = new RRGameState(null, null);
    FileReadWrite myFile = new FileReadWrite();
    text = myFile.read("generatedMap_153.txt");
    ArrayList<GridElement> elements = MapObjects.extractDataFromString(text);
    world = RRGetMap.createWorld(elements, baseState);
    
    world.show(baseState);
    
    RREndCondition endCondition = new RREndCondition();
    world.precomputeGrid();
    
		RRSolver solver = new RRSolver(10, world, baseState, endCondition);
		
		GameSolution solution = solver.solve();
    
    if(solution == null){
      System.out.println("No Solution!");
    }else{
      for(IGameMove m : solution.getMoves()){
        System.out.println(m);
      }
    }
	}
}

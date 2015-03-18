package istia.ei4.pm;

import istia.ei4.pm.ia.*;
import java.util.ArrayList;


import istia.ei4.ProjetISTIA.*;

public class MainIA {

	public static void main(String[] args) {
    
    RRGameState baseState = new RRGameState(null, null);
    /*
    ArrayList<RRPiece> mainL = new ArrayList<RRPiece>();
    RRPiece p = new RRPiece(1, 1, 0);
    mainL.add(p);
    mainL.add(new RRPiece(9, 1, 0));
    baseState.setPieces(mainL, new ArrayList<RRPiece>());
    */
    RRWorld world = null;
    String text = null;
    
    FileReadWrite myFile = new FileReadWrite();
    
    text = myFile.read("generatedMap.txt");
    
    ArrayList<GridElement> elements = MapObjects.extractDataFromString(text);
    
    world = RRGetMap.createWorld(elements, baseState);
    
    
    
    
    //world.init();
    //world.setObjective(8, 6);
    
    //world.doMove(p, baseState, ERRGameMove.RIGHT);
    //world.doMove(p, baseState, ERRGameMove.DOWN);
    
    world.show(baseState);
    
    /*
    RREndCondition endCondition = new RREndCondition();
    
		SolverDFS solver = new SolverDFS(10, world, baseState, endCondition);
		
		GameSolution solution = solver.solve();*/
    
	}
}

package istia.ei4.pm.ia.ricochet;

import driftingdroids.model.Board;
import driftingdroids.model.Solution;
import driftingdroids.model.Solver;
import istia.ei4.ProjetISTIA.FileReadWrite;
import istia.ei4.ProjetISTIA.GridElement;
import istia.ei4.ProjetISTIA.MapObjects;
import istia.ei4.pm.ia.GameSolution;
import istia.ei4.pm.ia.IGameMove;
import java.util.ArrayList;
import java.util.List;


public class MainIA {

    public static void main(String[] args) {
      exec();
    }
    
    static void exec(){
        RRGameState baseState = new RRGameState(null, null);
        FileReadWrite myFile = new FileReadWrite();
        String text = myFile.read("generatedMap_154.txt");
        ArrayList<GridElement> elements = MapObjects.extractDataFromString(text);
        RRWorld world = RRGetMap.createWorld(elements, baseState);

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
    
    static void execDD(){
      FileReadWrite myFile = new FileReadWrite();
      String text = myFile.read("generatedMap_154.txt");
      ArrayList<GridElement> elements = MapObjects.extractDataFromString(text);

      Board b = RRGetMap.createWorld(elements);
      System.out.println("**********");
      System.out.println(b.toString());
      System.out.println("**********");


      Solver s = Solver.createInstance(b);
      try{
          List<Solution> sol = s.execute();
          for(Solution solution : sol){
              System.out.println(solution.toMovelistString());
          }
      }catch(InterruptedException e){
          System.err.println(e.getMessage());
      }
    }
}

package istia.ei4.pm.ia;

import java.util.ArrayList;

/**
 *
 * @author usrlocal
 */
public class GameSolution {
  
  public GameSolution(){
    this.moves = new ArrayList<IGameMove>();
  }
  
  public void addMove(IGameMove move){
    this.moves.add(move);
  }
  
  public ArrayList<IGameMove> getMoves(){
    return this.moves;
  }
  
  private ArrayList<IGameMove> moves;
  
}

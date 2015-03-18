package istia.ei4.pm;

/**
 *
 * @author Pierre Michel
 */
public class RRGridCell {
  
  public RRGridCell(){
    this.up = true;
    this.down = true;
    this.left = true;
    this.right = true;
  }
  
  public void setWall(ERRGameMove move, boolean state){
    switch(move.getDirection()){
      case 0: //UP
        this.up = state;
        break;
      case 1: //RIGHT
        this.right = state;
        break;
      case 2: //DOWN
        this.down = state;
        break;
      case 3: //LEFT
        this.left = state;
        break;
    }
  }
  
  public boolean getWall(ERRGameMove move){
    switch(move.getDirection()){
      case 0: //UP
        return this.up;
      case 1: //RIGHT
        return this.right;
      case 2: //DOWN
        return this.down;
      case 3: //LEFT
        return this.left;
      default:
        return false;
    }
  }
  
  public boolean doMove(RRPiece pos, ERRGameMove move){
    boolean didMove = this.getWall(move);
    if(didMove){
      switch(move.getDirection()){
        case 0: //UP
          pos.decY();
          break;
        case 1: //RIGHT
          pos.incX();
          break;
        case 2: //DOWN
          pos.incY();
          break;
        case 3: //LEFT
          pos.decX();
          break;
      }
    }
    return didMove;
  }
  
  private boolean up, down, left, right;
  
}

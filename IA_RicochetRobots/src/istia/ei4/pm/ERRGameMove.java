package istia.ei4.pm;

/**
 *
 * @author Pierre Michel
 */
public enum ERRGameMove {
  
  NOMOVE(-1), UP(0), RIGHT(1), DOWN(2), LEFT(3);
  
  private final int direction;
  
  public int getDirection(){
    return this.direction;
  }
  
  private ERRGameMove(int direction){
    this.direction = direction;
  }
  
}

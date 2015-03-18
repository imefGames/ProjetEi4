package istia.ei4.pm;

import istia.ei4.pm.ia.AWorld;

/**
 *
 * @author Pierre Michel
 */
public class RRWorld extends AWorld {
  
  public RRWorld(){
    this.grid = new RRGridCell[16][16];
    for(int i=0; i<16; i++){
      for(int j=0; j<16; j++){
        RRGridCell nc = new RRGridCell();
        if(i==0){ nc.setWall(ERRGameMove.LEFT, false); }
        if(j==0){ nc.setWall(ERRGameMove.UP, false); }
        if(i==15){ nc.setWall(ERRGameMove.RIGHT, false); }
        if(j==15){ nc.setWall(ERRGameMove.DOWN, false); }
        this.grid[i][j] = nc;
      }
    }
  }
  
  public void init(){
    // TODO: init with all walls
  }
  
  public RRPiece doMove(RRPiece pos, ERRGameMove move){
    RRPiece nPos = new RRPiece(pos);
    while(this.grid[nPos.getX()][nPos.getY()].doMove(nPos, move)){}
    return nPos;
  }
  
  private RRGridCell[][] grid;
  
}

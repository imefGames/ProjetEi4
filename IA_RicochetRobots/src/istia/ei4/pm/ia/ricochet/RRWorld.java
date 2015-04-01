package istia.ei4.pm.ia.ricochet;

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
    this.grid[7][6].setWall(ERRGameMove.DOWN, false);
    this.grid[8][6].setWall(ERRGameMove.DOWN, false);
    this.grid[7][9].setWall(ERRGameMove.UP, false);
    this.grid[8][9].setWall(ERRGameMove.UP, false);
    
    this.grid[6][7].setWall(ERRGameMove.RIGHT, false);
    this.grid[6][8].setWall(ERRGameMove.RIGHT, false);
    this.grid[9][7].setWall(ERRGameMove.LEFT, false);
    this.grid[9][8].setWall(ERRGameMove.LEFT, false);
  }
  
  public void setVerticalWall(int x, int y){
    if(y < 16)
    {
      if(x < 16)
        this.grid[x][y].setWall(ERRGameMove.LEFT, false);
      if((x-1)>0)
        this.grid[x-1][y].setWall(ERRGameMove.RIGHT, false);
    }
  }
  
  public void setHorizontalWall(int x, int y){
    if(x < 16)
    {
      if(y < 16)
        this.grid[x][y].setWall(ERRGameMove.UP, false);
      if((y-1)>0)
        this.grid[x][y-1].setWall(ERRGameMove.DOWN, false);
    }
  }
  
  public void setObjective(int x, int y, int color){
    this.xObj = x;
    this.yObj = y;
    this.objColor = color;
  }
  
  public boolean isOnObjective(RRPiece p){
    return this.xObj == p.getX() && this.yObj == p.getY() && (this.objColor==0 || this.objColor==p.getColor());
  }
  
  public void doMove(RRPiece pos, RRGameState state, ERRGameMove move){
    boolean keepMoving = true;
    while(keepMoving){
      int x, y;
      x = pos.getX();
      y = pos.getY();
      switch(move.getDirection()){
        case 1: //UP
          if(y!=0 && this.grid[x][y-1].hasPiece()){
            keepMoving = false;
          }
          break;
        case 2: //RIGHT
          if(x!=15 && this.grid[x+1][y].hasPiece()){
            keepMoving = false;
          }
          break;
        case 4: //DOWN
          if(y!=15 && this.grid[x][y+1].hasPiece()){
            keepMoving = false;
          }
          break;
        case 8: //LEFT
          if(x!=0 && this.grid[x-1][y].hasPiece()){
            keepMoving = false;
          }
          break;
      }
      if(keepMoving){
        keepMoving = this.grid[pos.getX()][pos.getY()].doMove(pos, move);
      }
    }
  }
  
  public void putPiece(RRPiece piece, boolean state){
    this.grid[piece.getX()][piece.getY()].putPiece(state);
  }
  
  public void show(RRGameState state){
  
    for(int j=0; j<this.grid[0].length; j++){
      for(int i=0; i<this.grid.length; i++){
        if(grid[i][j].getWall(ERRGameMove.UP)){
          System.out.print("   ");
        }else{
          System.out.print("___");
        }
      }
      System.out.print('\n');
      for(int i=0; i<this.grid.length; i++){
        if(grid[i][j].getWall(ERRGameMove.LEFT)){
          System.out.print(" ");
        }else{
          System.out.print("|");
        }
        
        boolean noPiece = true;
        int idPiece = 0;
        for(RRPiece p : state.getPieces()){
          if(p.getX()==i && p.getY()==j){
            noPiece = false;
            idPiece = p.getId();
          }
        }
        if(noPiece){
          if(i==xObj && j==yObj){
            System.out.print("X");
          }else{
            System.out.print(" ");
          }
        }else{
          System.out.print(idPiece);
        }
        
        if(grid[i][j].getWall(ERRGameMove.RIGHT)){
          System.out.print(" ");
        }else{
          System.out.print("|");
        }
      }
      System.out.print('\n');
      for(int i=0; i<this.grid.length; i++){
        if(grid[i][j].getWall(ERRGameMove.DOWN)){
          System.out.print("   ");
        }else{
          System.out.print("---");
        }
      }
      System.out.print('\n');
    }
    
  }
  
  public void precomputeGrid(RRGameState state){
    grid[xObj][yObj].setPrecomutedNumber(0);
    //TODO
  }
  
  private RRGridCell[][] grid;
  private int xObj = 0, yObj = 0, objColor = 0;
  
}

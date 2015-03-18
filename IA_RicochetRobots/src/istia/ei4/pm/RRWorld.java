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
    this.color = color;
  }
  
  public boolean isOnObjective(RRPiece p){
    return this.xObj == p.getX() && this.yObj == p.getY();
  }
  
  public void doMove(RRPiece pos, RRGameState state, ERRGameMove move){
    boolean keepMoving = true;
    while(keepMoving){
      switch(move.getDirection()){
        case 0: //UP
          for(RRPiece p : state.getPieces()){
            if(p.getX() == pos.getX() && p.getY() == pos.getY()-1){
              keepMoving = false;
            }
          }
          break;
        case 1: //RIGHT
          for(RRPiece p : state.getPieces()){
            if(p.getX() == pos.getX()+1 && p.getY() == pos.getY()){
              keepMoving = false;
            }
          }
          break;
        case 2: //DOWN
          for(RRPiece p : state.getPieces()){
            if(p.getX() == pos.getX() && p.getY() == pos.getY()+1){
              keepMoving = false;
            }
          }
          break;
        case 3: //LEFT
          for(RRPiece p : state.getPieces()){
            if(p.getX() == pos.getX()-1 && p.getY() == pos.getY()){
              keepMoving = false;
            }
          }
          break;
      }
      if(keepMoving){
        keepMoving = this.grid[pos.getX()][pos.getY()].doMove(pos, move);
      }
    }
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
        for(RRPiece p : state.getPieces()){
          if(p.getX()==i && p.getY()==j){
            noPiece = false;
          }
        }
        if(noPiece){
          if(i==xObj && j==yObj){
            System.out.print("X");
          }else{
            System.out.print(" ");
          }
        }else{
          System.out.print("O");
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
  
  private RRGridCell[][] grid;
  private int xObj = 0, yObj = 0;
  private int color = 0;
  
}

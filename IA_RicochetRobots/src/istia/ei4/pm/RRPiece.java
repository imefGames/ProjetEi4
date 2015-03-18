package istia.ei4.pm;

/**
 *
 * @author Pierre Michel
 */
public class RRPiece{
  
  public RRPiece(){
    this.x = 0;
    this.y = 0;
    this.color = 0;
  }
  
  public RRPiece(RRPiece p){
    this.x = p.x;
    this.y = p.y;
    this.color = p.color;
  }
  
  public RRPiece(int x, int y, int color){
    this.x = x;
    this.y = y;
    this.color = color;
  }
  
  public int getColor(){
    return this.color;
  }
  
  public void setColor(int color){
    this.color = color;
  }
  
  public int getX(){
    return this.x;
  }
  
  public void setX(int x){
    this.x = x;
  }
  
  public void incX(){
    this.x++;
  }
  
  public void decX(){
    this.x--;
  }
  
  public int getY(){
    return this.y;
  }
  
  public void setY(int y){
    this.y = y;
  }
  
  public void incY(){
    this.y++;
  }
  
  public void decY(){
    this.y--;
  }
  
  private int x, y, color;
  
}

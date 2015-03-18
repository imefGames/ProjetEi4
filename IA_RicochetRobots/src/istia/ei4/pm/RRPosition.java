package istia.ei4.pm;

/**
 *
 * @author Pierre Michel
 */
public class RRPosition {
  
  public RRPosition(){
    this.x = 0;
    this.y = 0;
  }
  
  public RRPosition(RRPosition p){
    this.x = p.x;
    this.y = p.y;
  }
  
  public RRPosition(int x, int y){
    this.x = x;
    this.y = y;
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
  
  protected int x, y;
  
}

package istia.ei4.pm.ia.ricochet;

import java.util.Comparator;

/**
 *
 * @author Pierre Michel
 */
public class RRPiece implements Comparable<RRPiece>{
  
  public RRPiece(){
    this.x = 0;
    this.y = 0;
    this.color = 0;
    this.id = 0;
  }
  
  public RRPiece(RRPiece p){
    this.x = p.x;
    this.y = p.y;
    this.color = p.color;
    this.id = p.id;
  }
  
  public RRPiece(int x, int y, int color, int id){
    this.x = x;
    this.y = y;
    this.color = color;
    this.id = id;
  }
  
  public int getId(){
    return this.id;
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

  @Override
  public int compareTo(RRPiece o) {
    int a = this.getX()+this.getY();
      int b = o.getX()+o.getY();
      if(a==b){
        return 0;
      }else if(a>b){
        return 1;
      }else{
        return -1;
      }
  }
  
  private int x, y, color, id;
  
}

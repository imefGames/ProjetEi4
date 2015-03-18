/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istia.ei4.pm;

/**
 *
 * @author usrlocal
 */
public class RRPiece extends RRPosition {
  
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
  
  private int color;
  
}

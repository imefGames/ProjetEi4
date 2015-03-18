/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istia.ei4.pm;

import istia.ei4.ProjetISTIA.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author usrlocal
 */
public class RRGetMap {
  
  public static RRWorld createWorld(ArrayList<GridElement> gridElements, RRGameState baseState)
  {
    RRWorld currentWorld = new RRWorld();
    
    ArrayList<GridElement> robots = new ArrayList<GridElement>();
    GridElement cible = null;
    
    Map<String, Integer> colors = new HashMap<String, Integer>();
    
        /*
    colors.put("rr", Color.RED);
    colors.put("rb", Color.BLUE);
    colors.put("rv", Color.GREEN);
    colors.put("rj", Color.YELLOW);
    colors.put("cr", Color.RED);
    colors.put("cb", Color.BLUE);
    colors.put("cv", Color.GREEN);
    colors.put("cj", Color.YELLOW);
     */
    
    //Todo : Remplacer par les lignes précédentes
    colors.put("rr", 1);
    colors.put("rb", 2);
    colors.put("rv", 3);
    colors.put("rj", 4);
    colors.put("cr", 5);
    colors.put("cb", 6);
    colors.put("cv", 7);
    colors.put("cj", 8);
    colors.put("cm", 0);
    
    
    for (Object element : gridElements) {
      GridElement myp = (GridElement) element;

      if (myp.getType().equals("mh")) {
        currentWorld.setHorizontalWall(myp.getX(), myp.getY());
      }
      if (myp.getType().equals("mv")) {
          currentWorld.setVerticalWall(myp.getX(), myp.getY());    
      }
      if (myp.getType().equals("cr") || myp.getType().equals("cv") ||myp.getType().equals("cb") ||myp.getType().equals("cj") ||myp.getType().equals("cm")) {
          cible = myp;  
      }
      if (myp.getType().equals("rr") || myp.getType().equals("rv") ||myp.getType().equals("rb") ||myp.getType().equals("rj")) {
          robots.add(myp);
      }    
    }
    
    ArrayList<RRPiece> mainL = new ArrayList<RRPiece>();
    ArrayList<RRPiece> secondL = new ArrayList<RRPiece>();
    
    String types[] = {"cr","cb","cv","cj"};
    
    if(cible.getType().equals("cm"))
    {
      for(GridElement robot : robots)
      {
        mainL.add(new RRPiece(robot.getX(), robot.getY(), colors.get(robot.getType())));
      }
    }
    else{          
      for(String type : types)
      {
        if(cible.getType().equals(type))
        {
          for(GridElement robot : robots)
          {
            if(robot.getType().equals(cible.getType()))
            {
              mainL.add(new RRPiece(robot.getX(), robot.getY(), colors.get(robot.getType())));
            }
            else
            {
              secondL.add(new RRPiece(robot.getX(), robot.getY(), colors.get(robot.getType())));
            }
          }
        }
      }
    }
    
    System.out.println("Cible y :");
    System.out.println(cible.getType());
    currentWorld.setObjective(cible.getX(), cible.getY(), colors.get(cible.getType()));
    
    baseState.setPieces(mainL, secondL);       
    
    
    return currentWorld;
  }
  
}

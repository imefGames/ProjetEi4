package com.example.alain.t7;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Object;

/**
 * Created by Alain on 04/02/2015.
 */
public class MapGenerator {

    Random rand;
    public MapGenerator(){
        rand = new Random();
    }

    public ArrayList<Point> translateArraysToMap(int[][] horizontalWalls, int[][] verticalWalls)
    {
        int i = 0;
        int j = 0;
        ArrayList<Point> data = new ArrayList<Point>();

        for(i=0; i<17; i++)
            for(j=0; j < 17; j++)
            {
                if(horizontalWalls[i][j]== 1)
                {
                    data.add(new Point(i,j,"mh"));
                }
                if(verticalWalls[i][j]== 1)
                {
                    data.add(new Point(i,j,"mv"));
                }
            }
        return data;

    }

    public int getRandom(int min, int max)
    {
        return rand.nextInt((max - min) + 1) + min;
    }

    public ArrayList<Point> get16DimensionalMap()
    {
        int[][] horizontalWalls = new int[17][17];
        int[][] verticalWalls = new int[17][17];

        int i =0;
        int j =0;
        int temp = 0;


        for(i=0; i < 16; i++)
        {
            horizontalWalls[i][0] = 1;
            horizontalWalls[i][16] = 1;
            verticalWalls[0][i] = 1;
            verticalWalls[16][i] = 1;
        }

        //Murs près de la bordure gauche
        horizontalWalls[0][getRandom(2,14)] = 1;
        do{
            temp = getRandom(2,15);
            System.out.println(temp);
        }while(horizontalWalls[0][temp-1] == 1 || horizontalWalls[0][temp] == 1 || horizontalWalls[0][temp+1] == 1);
        horizontalWalls[0][temp] = 1;

        //Murs près de la bordure droite
        horizontalWalls[15][getRandom(2,14)] = 1;
        do{
            temp = getRandom(2,15);
            System.out.println(temp);
        }while(horizontalWalls[15][temp-1] == 1 || horizontalWalls[15][temp] == 1 || horizontalWalls[15][temp+1] == 1);
        horizontalWalls[15][temp] = 1;


        //Murs près de la bordure haut
        verticalWalls[getRandom(2,14)][0] = 1;
        do{
            temp = getRandom(2,15);
            System.out.println(temp);
        }while(verticalWalls[temp-1][0] == 1 || verticalWalls[temp][0] == 1 || verticalWalls[temp+1][0] == 1);
        verticalWalls[temp][0] = 1;

        //Murs près de la bordure bas
        verticalWalls[getRandom(2,14)][15] = 1;
        do{
            temp = getRandom(2,15);
            System.out.println(temp);
        }while(verticalWalls[temp-1][15] == 1 || verticalWalls[temp][15] == 1 || verticalWalls[temp+1][15] == 1);
        verticalWalls[temp][15] = 1;

        horizontalWalls[7][7] = horizontalWalls[8][7] = horizontalWalls[7][9] = horizontalWalls[8][9] = 1;
        verticalWalls[7][7] = verticalWalls[7][8] = verticalWalls[9][7] = verticalWalls[9][8] = 1;

        for(int k = 0; k < 17; k++) {
            Boolean flag = false;
            int tempX = 0;
            int tempY = 0;
            int tempXv = 0;
            int tempYv = 0;

            do {
                flag = false;
                tempX = getRandom(1, 15);
                tempY = getRandom(1, 15);

                if(horizontalWalls[tempX][tempY] == 1 || horizontalWalls[tempX-1][tempY] == 1 || horizontalWalls[tempX+1][tempY] == 1)
                    flag = true;
                if(horizontalWalls[tempX][tempY-1] == 1 || horizontalWalls[tempX][tempY+1] == 1)
                    flag = true;

                if(verticalWalls[tempX][tempY] == 1 || verticalWalls[tempX+1][tempY] == 1)
                    flag = true;
                if(verticalWalls[tempX][tempY-1] == 1 || verticalWalls[tempX+1][tempY-1] == 1)
                    flag = true;

                if(!flag)
                {
                    tempXv = tempX+getRandom(0,1);
                    tempYv = tempY-getRandom(0,1);

                    if(verticalWalls[tempXv][tempYv] == 1 || verticalWalls[tempXv-1][tempYv] == 1 || verticalWalls[tempXv+1][tempYv] == 1)
                        flag = true;
                    if(verticalWalls[tempXv][tempYv-1] == 1 || verticalWalls[tempXv][tempYv+1] == 1)
                        flag = true;

                    if(horizontalWalls[tempXv][tempYv] == 1 || horizontalWalls[tempXv+1][tempYv] == 1)
                        flag = true;
                    if(horizontalWalls[tempXv][tempYv-1] == 1 || horizontalWalls[tempXv+1][tempYv-1] == 1)
                        flag = true;

                    if(horizontalWalls[tempXv][tempYv] == 1 && horizontalWalls[tempXv-1][tempYv+1] == 1)
                        flag = true;
                    if(horizontalWalls[tempXv][tempYv] == 1 && horizontalWalls[tempXv+1][tempYv-1] == 1)
                        flag = true;
                    if(horizontalWalls[tempXv][tempYv+1] == 1 && horizontalWalls[tempXv-1][tempYv] == 1)
                        flag = true;
                    if(horizontalWalls[tempXv-1][tempYv] == 1 && horizontalWalls[tempXv][tempYv+1] == 1)
                        flag = true;
                }

            } while (flag);
            horizontalWalls[tempX][tempY] = 1;
            verticalWalls[tempXv][tempYv] = 1;

        }

        Boolean flag;
        int cX = 0;
        int cY = 0;
        do{
            flag = false;
            cX = getRandom(0, 15);
            cY = getRandom(0, 15);

            if(horizontalWalls[cX][cY] == 0 && horizontalWalls[cX][cY+1] == 0)
                flag = true;
            if(verticalWalls[cX][cY] == 0 && verticalWalls[cX+1][cY] == 0)
                flag = true;
            if((cX == 7 && cY == 7) || (cX == 7 && cY == 8) || (cX == 8 && cY == 7) || (cX == 8 && cY == 8))
                flag = true;

        }while(flag);

        String typesOfCibles[] = {"cj","cr","cb", "cj", "cm"};


        ArrayList<Point> data = translateArraysToMap(horizontalWalls, verticalWalls);
        data.add(new Point(cX, cY, typesOfCibles[getRandom(0,4)]));

        String typesOfRobots[] = {"rr", "rb", "rj", "rv"};

        ArrayList<Point> robotsTemp = new ArrayList<Point>();

        for(String currentRobotType:typesOfRobots)
        {
            do {
                flag = false;
                cX = getRandom(0, 15);
                cY = getRandom(0, 15);

                for(Point robot:robotsTemp)
                {
                    if(robot.getX() == cX && robot.getY() == cY)
                        flag = true;

                    if((cX == 7 && cY == 7) || (cX == 7 && cY == 8) || (cX == 8 && cY == 7) || (cX == 8 && cY == 8))
                        flag = true;
                }


            }while(flag);
            robotsTemp.add(new Point(cX, cY, currentRobotType));
        }

//        for(Point p : robotsTemp)
//        {
//            data.add(p);
//        }
        data.addAll(robotsTemp);

        return data;
    }
}

package com.example.alain.t7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Alain on 21/01/2015.
 */
public class MapObjects {

    private String _data = null;
    public MapObjects(String data){
        _data = data;
    }

    public ArrayList extractData()
    {
        int x = 0;
        int y = 0;
        //String[] allLines = _data.split("[\\r\\n]+");
        ArrayList<Point> data = new ArrayList<Point>();

        List<String> objectTypes = Arrays.asList("mh", "mv", "rv", "rj", "rr", "rb", "cv", "cj", "cr", "cb", "cm");


        for(final String objectType: objectTypes) {
//            System.out.println("objectType");
//            System.out.println(objectType);



            List<String> allMatches = new ArrayList<String>();
            Matcher m = Pattern.compile(objectType+"\\d+,\\d+;").matcher(_data);
            while (m.find()) {
                allMatches.add(m.group());
            }
//            System.out.println("Longueur de la liste");
//            System.out.println(allMatches.size());


            for(final String line: allMatches) {
                String[] values = line.split(",");
                //System.out.println(values[0]);

                if(values.length>=2) {

                    String valueX = values[0].replaceAll("[^0-9]", "");
                    //System.out.println(valueX);

                    if (valueX != "") {
                        x = Integer.decode(valueX);
                    }


                    String valueY = values[1].replaceAll("[^0-9]", "");
                    //System.out.println(valueY);
                    if (valueY != "") {
                        y = Integer.decode(valueY);
                    }


                    Point p = new Point(x, y, objectType);



                    data.add(p);
                }

            }
        }
        return data;
    }



    //String.split("[\\r\\n]+")
}

package com.example.alain.t7;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class T7 extends ActionBarActivity {

    private ExampleSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FileReadWrite file = new FileReadWrite(this);

        String text = file.read("/sdcard/mymap.txt");
        ArrayList data = null;
        if (text != null) {
           // System.out.println(text);
            MapObjects allobjects = new MapObjects(text);
            data = allobjects.extractData();

            for(Object p: data){
                Point myp = (Point)p;
//                System.out.println("Point :");
//                System.out.println(myp.getX());
//                System.out.println(myp.getY());
            }

        }
        else
        {
            System.out.println("Echec lecture fichier");
        }

        MapGenerator generatedMap = new MapGenerator();

        //data = generatedMap.get16DimensionalMap();



        myView = new ExampleSurfaceView(this);
        myView.setBackgroundMap(data);

        myView.setBackgroundColor(Color.BLUE);
        setContentView(R.layout.activity_t7);
        setContentView(myView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_t7, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

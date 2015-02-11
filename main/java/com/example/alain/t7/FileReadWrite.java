package com.example.alain.t7;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import android.content.Context;

/**
 * Created by Alain on 21/01/2015.
 */
public class FileReadWrite {
    public FileReadWrite(Context context){

    }

    public String read(String fileLocation)
    {
        String txtData = null;
        String aBuffer = "";

        try {
            File myFile = new File(fileLocation);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            String aDataRow = "";
            while ((aDataRow = myReader.readLine()) != null) {
                aBuffer += aDataRow + "\n";
            }

            myReader.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;

        }
        return aBuffer;
    }


}

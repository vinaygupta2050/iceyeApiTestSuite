package com.api.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Helper {
    public static int generateRandomRoomNumber()
    {
        Random rand = new Random();
        return rand.nextInt(100);
    }

    public static Iterator<Object[]> parseCsvData(String fileName) throws IOException
    {
        BufferedReader input = null;
        File file = new File(fileName);
        input = new BufferedReader(new FileReader(file));
        String line = null;
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        int lineNumber=0;
        while ((line = input.readLine()) != null)
        {
            if(lineNumber>0)
            {
                String in = line.trim();
                String[] temp = in.split(",");
                List<Object> arrray = new ArrayList<Object>();
                for(String s : temp)
                {
                    arrray.add(s);
                }
                data.add(arrray.toArray());
            }
            else
            {
                lineNumber++;
            }
        }
        input.close();
        return data.iterator();
    }
}


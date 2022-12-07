package com.jacobconner.artifacttracker.utils;

import android.widget.Spinner;

public class FormUtils {
    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static float tryParseFloat(String value) {
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException nfe) {
            return 0.0F;
        }
    }

    public static int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return -1;
    }

}

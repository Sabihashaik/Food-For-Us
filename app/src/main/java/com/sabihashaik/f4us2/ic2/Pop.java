package com.sabihashaik.f4us2.ic2;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;


public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup);


        DisplayMetrics dem=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dem);

        int width = dem.widthPixels;
        int height= dem.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));


    }
}



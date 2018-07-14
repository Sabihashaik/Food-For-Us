package com.sabihashaik.f4us2.ic2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;


public class BusinessPop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_pop);

        DisplayMetrics dem=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dem);

        int width = dem.widthPixels;
        int height= dem.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.4));


    }

}

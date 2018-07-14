package com.sabihashaik.f4us2.ic2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        // Start home activity
        startActivity(new Intent(SplashActivity.this, Main2Activity.class));
        // close splash activity
        finish();
    }
}

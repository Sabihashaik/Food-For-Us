package com.sabihashaik.f4us2.ic2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class Onboard extends AppCompatActivity {

    ViewPager vp;
    RelativeLayout dots;
    SliderAdapter mSliderAdapter;
    TextView[] Dotz;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setExitTransition(new Explode());
        setContentView(R.layout.activity_onboard);



        vp=(ViewPager) findViewById(R.id.vp);
        dots=(RelativeLayout) findViewById(R.id.mDots);
        btn=(Button) findViewById(R.id.btski);


        mSliderAdapter= new SliderAdapter(this);
        vp.setAdapter(mSliderAdapter);
        //addDotsIndicator();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openReg();



            }
        });

    }



    private void openReg(){
        Intent intent;
        intent=new Intent(Onboard.this,RegistrationActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }
    public void addDotsIndicator(){
        Dotz=new TextView[3];
        for(int i=0;i<Dotz.length;i++){
            Dotz[i]=new TextView(this);
            Dotz[i].setText(Html.fromHtml("&#8226;"));
            Dotz[i].setTextSize(25);
            Dotz[i].setTextColor(getResources().getColor(R.color.brand_color1));

            dots.addView(Dotz[i]);
        }
    }
}

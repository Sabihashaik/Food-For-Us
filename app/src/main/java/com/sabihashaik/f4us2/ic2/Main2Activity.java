package com.sabihashaik.f4us2.ic2;

import android.app.ActivityOptions;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.Locale;

import com.microsoft.windowsazure.mobileservices.*;
public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private MobileServiceClient mClient;
    private RecyclerView mpostlist;
    private DatabaseReference mDatabaseReference;

    Button btn;
    ImageButton speech;
    TextToSpeech textToSpeech;
    TextView rest,foodt,time,tagtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        getWindow().setExitTransition(new Explode());

        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn=(Button) findViewById(R.id.More);
        speech=(ImageButton) findViewById(R.id.speech);

        rest=(TextView) findViewById(R.id.rest_title);
        foodt=(TextView) findViewById(R.id.food_title);
        time=(TextView) findViewById(R.id.time_prep);
        tagtitle=(TextView) findViewById(R.id.tag_f);




        try{
            mClient = new MobileServiceClient(
                    "https://foodforus.azurewebsites.net",
                    this
            );
        }
        catch(Exception e){
            Log.d("F4UsTrialUI",e.getMessage());
            //e.getMessage();
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   fabac();


//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentMain();
            }
        });




        speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speaknow();          }
        });



        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status== TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result== TextToSpeech.LANG_MISSING_DATA ||
                    result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(Main2Activity.this, "Language Not Supported",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        speech.setEnabled(true);
//                        textToSpeech.setPitch(0.6f);
//                        textToSpeech.setSpeechRate(1.0f);

                        speaknow();
                    }

                }
            }
        });



    }

    private void fabac() {
            Intent intent;
            intent = new Intent(Main2Activity.this, PostActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    private void speaknow(){
        String text= rest.getText().toString()+"\n"+foodt.getText().toString()+"\n"+time.getText().toString()+"\n"+tagtitle.getText().toString();

            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);

    }


    private void ContentMain(){
        Intent intent;
        intent=new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {



        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;



        if (id == R.id.nav_camera) {
            // Handle the camera action

            Intent intent;
            intent=new Intent(Main2Activity.this,UserProfile.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        } else if (id == R.id.nav_gallery) {


        } else if (id == R.id.nav_slideshow) {
            Intent intent;
            intent=new Intent(Main2Activity.this,DonationsNearMe.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        } else if (id == R.id.nav_manage) {
            Intent intent;
            intent=new Intent(Main2Activity.this,Vouchers.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        }
        else if (id == R.id.nav_setting) {
            Intent intent;
            intent=new Intent(Main2Activity.this,SettingsActivity.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());


        } else if (id == R.id.nav_share) {
            Context context = getApplicationContext();


            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            String label= "Text Copied! \n";
            CharSequence text = " Check out this app! Food4Us lets you earn vouchers everytime you donate food. REFERRAL CODE: IS082 \n https://bit.ly/1cY78RZ";

            ClipData clip = ClipData.newPlainText(label, text);
            clipboard.setPrimaryClip(clip);
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();


        } else if (id == R.id.nav_send) {
            Intent intent;
            intent=new Intent(Main2Activity.this,BusinessPop.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

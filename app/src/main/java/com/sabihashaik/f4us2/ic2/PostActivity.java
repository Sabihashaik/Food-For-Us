package com.sabihashaik.f4us2.ic2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PostActivity extends AppCompatActivity {

    private ImageButton mImageButton;
    private static final int GALLERY_REQUEST=100;
    private EditText mEditText1,mEditText2,mEditText3,Location;
    private Button mbtn,cbt;
    String strEditText;
    Uri imageURi=null;
    ImageView icv;
    Bitmap btc;
    private StorageReference mStorageReference;
     DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        mEditText1=  (EditText) findViewById(R.id.editText4);
        mEditText2=  (EditText) findViewById(R.id.editText5);
        mEditText3=  (EditText) findViewById(R.id.editText3);


        Location=  (EditText) findViewById(R.id.ltext);
        mbtn=(Button) findViewById(R.id.button2);
        cbt=(Button) findViewById(R.id.analyze);


        // Firebase storage
        mStorageReference= FirebaseStorage.getInstance().getReference();
        //Firebase database
        mDatabaseReference= FirebaseDatabase.getInstance().getReference().child("Blog");





        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post();
            }
        });
        cbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doAnalyze();
            }
        });



    }

    private void doAnalyze(){
        Intent intent;
        intent=new Intent(PostActivity.this, DescribeActivity.class);
        startActivityForResult(intent,1);

                //(intent,
              //  ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
       // receive.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));


    }



    private void Post(){
        Log.d("f4us","entered post");
        final String title=mEditText1.getText().toString();
        final String postval=mEditText2.getText().toString();

       final String Tags =mEditText3.getText().toString();
        //mEditText3.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));
        final String Loc=Location.getText().toString();
       // final String Tags=mEditText3.getText().toString();

        Log.d("f4us",title);
        Log.d("f4us",postval);


        if(!TextUtils.isEmpty(title) &&!TextUtils.isEmpty(postval) ){
            //&&imageURi!=null
            Log.d("f4us","Entered text utils");
            //StorageReference filep=mStorageReference.child("Blog_img").child(imageURi.getLastPathSegment());
            DatabaseReference newPost= mDatabaseReference.push();

            newPost.child("title").setValue(title);
            newPost.child("desc").setValue(postval);
            newPost.child("Tags").setValue(Tags);
            newPost.child("Location").setValue(Loc);


          /*  filep.putFile(imageURi).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests")
                    Uri downloadUrl =taskSnapshot.getDownloadUrl();

                    DatabaseReference newPost= mDatabaseReference.push();

                    newPost.child("title").setValue(title);
                    newPost.child("desc").setValue(postval);


                }
            });*/

        }


        //
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_REQUEST && requestCode==RESULT_OK){
            imageURi=data.getData();
            mImageButton.setImageURI(imageURi);
        }


            if (requestCode == 1) {
                if(resultCode == RESULT_OK) {
                     strEditText = data.getStringExtra("editTextValue");
                    Log.d("Sample","Entered Result");
                    mEditText3.setText(strEditText);


                }
            }

    }


}

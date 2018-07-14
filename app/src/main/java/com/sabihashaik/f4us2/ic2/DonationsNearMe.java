package com.sabihashaik.f4us2.ic2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DonationsNearMe extends AppCompatActivity {

    ImageButton btn;
    int c;
    EditText mEditText;
    RecyclerView mResultList;
    CheckBox ctitle,ctags;
    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donations_near_me);


        btn=(ImageButton) findViewById(R.id.searchBtn);
        mEditText=(EditText) findViewById(R.id.ed);
        mResultList=(RecyclerView) findViewById(R.id.post_horizontal);
        ctitle=(CheckBox)findViewById(R.id.ctitle);
        ctags=(CheckBox)findViewById(R.id.ctags);



        mResultList.setLayoutManager(new LinearLayoutManager(this));


        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctags.isChecked()) {
                    ctags.toggle();

                    String s = mEditText.getText().toString();

                    firebaseUserSearch(s);
//
               }
              else if(ctitle.isChecked()){
//                    c=2;
                    ctitle.toggle();
                 String s = mEditText.getText().toString();
                   firebaseUserSearcht(s);
//
//
        }

//                else if(ctags.isChecked() && ctitle.isChecked()){
//                    Toast.makeText(DonationsNearMe.this,"Please Check Either Tag or Title",Toast.LENGTH_SHORT).show();
//                }
            }
        });


    }

    private void firebaseUserSearcht(String s) {
        Query  firebasesqt = mDatabaseReference.orderByChild("title").startAt(s).endAt(s + '\uf8ff');


        FirebaseRecyclerAdapter<blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<blog, BlogViewHolder>(
                blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                firebasesqt

        ) {
            @Override
            public void populateViewHolder(final BlogViewHolder viewHolder, blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setTags(model.getTags());
                viewHolder.setLoc(model.getLocation());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);


    }

  /*  public void SearchQuery(int Caseno, String searcht){

        switch(Caseno){
            case 1:
                         Query firebasesq =mDatabaseReference.orderByChild("Tags").startAt(searcht).endAt(searcht+'\uf8ff');
                        break;
            case 2:       firebasesq =mDatabaseReference.orderByChild("title").startAt(searcht).endAt(searcht+'\uf8ff');

            break;

        }

    }*/


    private void firebaseUserSearch(String searcht){
        //SearchQuery(Caseno,searcht);
       /* if(Caseno==1){
            if(ctags.isChecked()) {
                if (ctitle.isChecked()) {
                    ctitle.toggle();
                    c = 1;
                    // firebasesq = mDatabaseReference.orderByChild("Tags").startAt(searcht).endAt(searcht + '\uf8ff');

                }
            }
            else if(Caseno==2) {
                if (ctitle.isChecked()) {
                    if (ctags.isChecked()) {
                        ctags.toggle();
                        c = 2;
                        // firebasesq = mDatabaseReference.orderByChild("title").startAt(searcht).endAt(searcht + '\uf8ff');
                    }
                }
            }
        }*/

        Query  firebasesq = mDatabaseReference.orderByChild("Tags").startAt(searcht).endAt(searcht + '\uf8ff');


        FirebaseRecyclerAdapter<blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<blog, BlogViewHolder>(
                blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                firebasesq

        ) {
            @Override
            public void populateViewHolder(final BlogViewHolder viewHolder, blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setTags(model.getTags());
                viewHolder.setLoc(model.getLocation());

            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    //View Holder for Recycler Card View
    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);

            mView=itemView;
        }

        public void setTitle(String title){
            TextView post_title=(TextView) mView.findViewById(R.id.title_post);
            post_title.setText(title);
        }


        public void setDesc(String desc){
            TextView post_desc=(TextView) mView.findViewById(R.id.desc);
            post_desc.setText(desc);
        }
        public void setTags(String tag){
            TextView post_desc=(TextView) mView.findViewById(R.id.Tagview);
            post_desc.setText(tag);
        }

        public void setLoc(String Location){
            TextView post_loc=(TextView) mView.findViewById(R.id.Loc);
            post_loc.setText(Location);

        }

    }

}

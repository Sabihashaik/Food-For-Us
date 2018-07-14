package com.sabihashaik.f4us2.ic2;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mpostlist;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getString(R.string.subscription_key).startsWith("Please")) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.add_subscription_key_tip_title))
                    .setMessage(getString(R.string.add_subscription_key_tip))
                    .setCancelable(false)
                    .show();
        }
        mpostlist=(RecyclerView) findViewById(R.id.postlist);
        mpostlist.setLayoutManager(new LinearLayoutManager(this));
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<blog, BlogViewHolder>(
                blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabaseReference

        ) {
            @Override
            public void populateViewHolder(final BlogViewHolder viewHolder, blog model, int position) {

                viewHolder.setTitle(model.getTitle());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setTags(model.getTags());
                viewHolder.setLoc(model.getLocation());


            }
        };

        mpostlist.setAdapter(firebaseRecyclerAdapter);

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
            TextView post_tag=(TextView) mView.findViewById(R.id.Tagview);
            post_tag.setText(tag);

        }

        public void setLoc(String loc){
            TextView post_loc=(TextView) mView.findViewById(R.id.Loc);
            post_loc.setText(loc);

        }

    }



}

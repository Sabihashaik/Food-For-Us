package com.sabihashaik.f4us2.ic2;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.R.attr.typeface;
import static com.sabihashaik.f4us2.ic2.R.id.textView;
import static com.sabihashaik.f4us2.ic2.R.layout.slide;


public class SliderAdapter extends PagerAdapter {
    Typeface fonth,fontd;



    Context mContext;
    LayoutInflater mLayoutInflater;
    Button btn;


    public SliderAdapter(Context context) {
        mContext = context;
        fonth = Typeface.createFromAsset(context.getAssets(), "fonts/Cabin-Bold.ttf");
       // fontd=Typeface.createFromAsset(context.getAssets(), "fonts/Cabin-Regular.ttf");
    }

    public int[] slideimage = {
    R.drawable.launchericon,
    R.drawable.noodles,
    R.drawable.smartphone

};
    public int[] slidedot = {
            R.drawable.un,
            R.drawable.un2,
            R.drawable.un3

    };

public String[] slide_headings={
        "Welcome \n",
        "For Everyone",
        "Just three easy steps"

    };

    public String[] desc={
                 "Food4Us connects those who can give food,\n to those who need it.",
                "Have leftovers at home?\n Own a restaurant and want to donate?\n We are open to all.",
                "Click, tag and upload! \n" + "Earn Vouchers the more you Donate\n Begin today!"

    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view== (RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        mLayoutInflater= (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view= mLayoutInflater.inflate(slide,container,false);

        ImageView slideri= (ImageView) view.findViewById(R.id.imagel);
        ImageView sliderd= (ImageView) view.findViewById(R.id.dot);

        TextView slidetext= (TextView) view.findViewById(R.id.titlel);
        TextView desc1= (TextView) view.findViewById(R.id.desc);

        slidetext.setTypeface(fonth);
        //desc1.setTypeface(fontd);

        slideri.setImageResource(slideimage[position]);
        sliderd.setImageResource(slidedot[position]);
        slidetext.setText(slide_headings[position]);
        desc1.setText(desc[position]);
        container.addView(view);



        return view;

    };

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
}

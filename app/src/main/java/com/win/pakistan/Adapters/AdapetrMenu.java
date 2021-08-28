package com.win.pakistan.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.win.pakistan.R;
import com.win.pakistan.activites.PrizeActivity;
import com.win.pakistan.activites.WalletActivity;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class AdapetrMenu extends PagerAdapter {
    private Context context;
    String[] country;
    int[] flag;
    public AdapetrMenu(Context context , String[] country,
                       int[] flag) {
        this.context = context;
        this.context = context;

        this.country = country;

        this.flag = flag;


    }
    /*
    This callback is responsible for creating a page. We inflate the layout and set the drawable
    to the ImageView based on the position. In the end we add the inflated layout to the parent
    container .This method returns an object key to identify the page view, but in this example page view
    itself acts as the object key
    */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.linear_menu, null);
        ImageView imagemenu1 = view.findViewById(R.id.imagemenu1);
        ImageView imagemenu2 = view.findViewById(R.id.imagemenu2);
        TextView txtparticepent1= view.findViewById(R.id.txtmenu1);
        TextView txtparticepent2= view.findViewById(R.id.txtmenu2);

        txtparticepent1.setText(country[0]);
        txtparticepent2.setText(country[1]);

        imagemenu1.setImageResource(flag[0]);
        imagemenu2.setImageResource(flag[1]);

        container.addView(view);



        imagemenu1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(context, "hh", Toast.LENGTH_SHORT).show();




            }
        });

        imagemenu2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent intent=new Intent(context, PrizeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);


            }
        });



        return view;
    }
    /*
    This callback is responsible for destroying a page. Since we are using view only as the
    object key we just directly remove the view from parent container
    */
    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }
    /*
    Returns the count of the total pages
    */
    @Override
    public int getCount() {
        return 3;
    }
    /*
    Used to determine whether the page view is associated with object key returned by instantiateItem.
    Since here view only is the key we return view==object
    */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }
   /* private int getImageAt(int position) {
        switch (position) {
            case 0:
                return R.mipmap.banner;
            case 1:
                return R.mipmap.banner2;
            case 2:
                return R.mipmap.banner3;
            case 3:
                return R.mipmap.banner4;
            case 4:
                return R.mipmap.banner5;

            default:
                return R.mipmap.banner;
        }*//*
    }*/
}

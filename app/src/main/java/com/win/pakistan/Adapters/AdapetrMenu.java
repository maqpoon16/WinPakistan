package com.win.pakistan.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.win.pakistan.Models.MenuDataclass;
import com.win.pakistan.R;
import com.win.pakistan.activites.PrizeActivity;
import com.win.pakistan.activites.WalletActivity;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class AdapetrMenu extends RecyclerView.Adapter<AdapetrMenu.MyViewHolder> {
    private ArrayList<MenuDataclass> dataSet;
    Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtmenuame;
        ImageView menuimg;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtmenuame = (TextView) itemView.findViewById(R.id.txtmenu);



            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.menuimg = (ImageView) itemView.findViewById(R.id.imagemenu);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {



                }
            });*/

        }
    }

    public AdapetrMenu(Context context, ArrayList<MenuDataclass> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linear_menu, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_out_top);

// Start animation


        TextView txtitemname = holder.txtmenuame;

        ImageView itemimg = holder.menuimg;

        txtitemname.setText(dataSet.get(listPosition).getMenuname());




        Picasso.get().load(dataSet.get(listPosition).getMenuImage()).into(itemimg);



        holder.menuimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listPosition==0){
                    Intent intent=new Intent(context, PrizeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("type","capital");
                    context.startActivity(intent);


                }
                else if(listPosition==1){

                    Toast.makeText(context, "Refer to friend", Toast.LENGTH_SHORT).show();


                }

            }
        });


    }
    @Override
    public int getItemCount() {
        return dataSet.size();

    }

}
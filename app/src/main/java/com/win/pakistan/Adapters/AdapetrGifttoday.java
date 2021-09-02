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

import com.squareup.picasso.Picasso;
import com.win.pakistan.Models.MenuDataclass;
import com.win.pakistan.Models.TodaygiftDataclass;
import com.win.pakistan.R;
import com.win.pakistan.activites.PrizeActivity;
import com.win.pakistan.activites.WalletActivity;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class AdapetrGifttoday extends RecyclerView.Adapter<AdapetrGifttoday.MyViewHolder> {
    private ArrayList<TodaygiftDataclass> dataSet;
    Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtprizename;
        ImageView imgviewprize;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtprizename = (TextView) itemView.findViewById(R.id.txtprizename);



            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imgviewprize = (ImageView) itemView.findViewById(R.id.imgviewprize);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {



                }
            });*/

        }
    }

    public AdapetrGifttoday(Context context, ArrayList<TodaygiftDataclass> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customlayoutprize, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_out_top);

// Start animation


        TextView txtprizename = holder.txtprizename;

        ImageView imgviewprize = holder.imgviewprize;

        txtprizename.setText(dataSet.get(listPosition).getGiftname());




        Picasso.with(context).load(dataSet.get(listPosition).getGiftImage()).into(imgviewprize);


/*

        holder.imgviewprize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
*/


    }
    @Override
    public int getItemCount() {
        return dataSet.size();

    }

}
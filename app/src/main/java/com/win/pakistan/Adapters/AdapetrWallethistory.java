package com.win.pakistan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.win.pakistan.Models.LeatherBoardDataclass;
import com.win.pakistan.Models.WalletHistoryDataclass;
import com.win.pakistan.R;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapetrWallethistory extends RecyclerView.Adapter<AdapetrWallethistory.MyViewHolder> {
    private ArrayList<WalletHistoryDataclass> dataSet;
    Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txthisorydate,txthistoryamount,txthisoryluckyname,txthisorypayedby;
        CircleImageView imaghsitory;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txthisorydate = (TextView) itemView.findViewById(R.id.txthisorydate);
            this.txthisoryluckyname = (TextView) itemView.findViewById(R.id.txthisoryluckyname);
            this.txthisorypayedby = (TextView) itemView.findViewById(R.id.txthisorypayedby);
            this.txthistoryamount = (TextView) itemView.findViewById(R.id.txthistoryamount);


            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imaghsitory = (CircleImageView) itemView.findViewById(R.id.imaghsitory);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {



                }
            });*/

        }
    }

    public AdapetrWallethistory(Context context, ArrayList<WalletHistoryDataclass> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customwallethistory, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_out_top);

// Start animation
        TextView txthisoryluckyname,txthisorypayedby,txthistoryamount,txthisorydate;

        txthisoryluckyname = holder.txthisoryluckyname;
        txthisorypayedby = holder.txthisorypayedby;
        txthistoryamount = holder.txthistoryamount;
        txthisorydate = holder.txthisorydate;

        CircleImageView imaghsitory = holder.imaghsitory;



    }
    @Override
    public int getItemCount() {
        return dataSet.size();

    }

}
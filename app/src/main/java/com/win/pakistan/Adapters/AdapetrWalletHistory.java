package com.win.pakistan.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.win.pakistan.Models.LeatherBoardDataclass;
import com.win.pakistan.Models.WalletHistoryDataclass;
import com.win.pakistan.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapetrWalletHistory extends RecyclerView.Adapter<AdapetrWalletHistory.MyViewHolder> {
    private ArrayList<WalletHistoryDataclass> dataSet;
    Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTimeDate,txtLuckdrawTitle,txtPaymentMethod,txtCashOrCoin;
        CircleImageView imageleaderimage;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtTimeDate = (TextView) itemView.findViewById(R.id.txttimedate);
            this.txtLuckdrawTitle = (TextView) itemView.findViewById(R.id.txtluckydrawtitle);
            this.txtPaymentMethod = (TextView) itemView.findViewById(R.id.txtpaymentmethod);
            this.txtCashOrCoin = (TextView) itemView.findViewById(R.id.txtpaidcashorcoins);


            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageleaderimage = (CircleImageView) itemView.findViewById(R.id.imagewallethistory);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {



                }
            });*/

        }
    }

    public AdapetrWalletHistory(Context context, ArrayList<WalletHistoryDataclass> data) {
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
        TextView txtTimeDate,txtLuckdrawTitle,txtPaymentMethod,txtCashOrCoin;

        txtTimeDate = holder.txtTimeDate;
        txtLuckdrawTitle = holder.txtLuckdrawTitle;
        txtPaymentMethod = holder.txtPaymentMethod;
        txtCashOrCoin = holder.txtCashOrCoin;

        CircleImageView itemimg = holder.imageleaderimage;

        txtTimeDate.setText(dataSet.get(listPosition).getHistoryTimedate());
        txtLuckdrawTitle.setText(dataSet.get(listPosition).getHistoryTitle());
        txtPaymentMethod.setText(dataSet.get(listPosition).getHistoryPaymentBy());
        txtCashOrCoin.setText(dataSet.get(listPosition).getHistoryCoinOrCash());
 Picasso.get().load(dataSet.get(listPosition).getHistoryimage()).into(itemimg);
/*

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
        });*/
    }
    @Override
    public int getItemCount() {
        return dataSet.size();

    }

}
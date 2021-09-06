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
import com.win.pakistan.Models.LeatherBoardDataclass;
import com.win.pakistan.Models.MenuDataclass;
import com.win.pakistan.R;
import com.win.pakistan.activites.PrizeActivity;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdapetrLeatherBoard extends RecyclerView.Adapter<AdapetrLeatherBoard.MyViewHolder> {
    private ArrayList<LeatherBoardDataclass> dataSet;
    Context context;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtleadername,txtleaderamount,txtleaderdate,txtleaderprize;
        CircleImageView imageleaderimage;
        ConstraintLayout constraintLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtleaderamount = (TextView) itemView.findViewById(R.id.txtleadercashamount);
            this.txtleadername = (TextView) itemView.findViewById(R.id.txtleadername);
            this.txtleaderprize = (TextView) itemView.findViewById(R.id.txtleaderprizename);
            this.txtleaderdate = (TextView) itemView.findViewById(R.id.txtleaderdate);


            //this.textViewVersion = (TextView) itemView.findViewById(R.id.textViewVersion);
            this.imageleaderimage = (CircleImageView) itemView.findViewById(R.id.imageleatherboard);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {



                }
            });*/

        }
    }

    public AdapetrLeatherBoard(Context context, ArrayList<LeatherBoardDataclass> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {


        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customleatherboard, parent, false);

        //view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        Animation slide_up = AnimationUtils.loadAnimation(context,
                R.anim.slide_out_top);

// Start animation
        TextView txtleadername,txtleaderamount,txtleaderdate,txtleaderprize;

        txtleadername = holder.txtleadername;
        txtleaderamount = holder.txtleaderamount;
        txtleaderdate = holder.txtleaderdate;
        txtleaderprize = holder.txtleaderprize;

        CircleImageView itemimg = holder.imageleaderimage;

        txtleadername.setText(dataSet.get(listPosition).getLeadername());
        txtleaderamount.setText(dataSet.get(listPosition).getLeaderamount());
        txtleaderdate.setText(dataSet.get(listPosition).getLeaderdate());
        txtleaderprize.setText(dataSet.get(listPosition).getLeaderprize());






        Picasso.get().load(dataSet.get(listPosition).getLeaderimage()).into(itemimg);



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
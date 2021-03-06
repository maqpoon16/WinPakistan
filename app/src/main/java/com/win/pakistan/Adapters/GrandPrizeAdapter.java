package com.win.pakistan.Adapters;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.win.pakistan.R;

import java.util.ArrayList;

public class GrandPrizeAdapter extends ArrayAdapter<PrizDataclass> {

    Context context;
    ArrayList<PrizDataclass> albhabet;
    String request;
    TextToSpeech t1;

    public GrandPrizeAdapter(Context context, ArrayList<PrizDataclass> albhabet) {

        super(context, R.layout.grandprizelayout, albhabet);
        this.albhabet = albhabet;
        this.context = context;
    }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.grandprizelayout, null);


            final ImageView imagepng = (ImageView) convertView.findViewById(R.id.imagegrandprize);



            final TextView txtcoins = (TextView) convertView.findViewById(R.id.txtcoins);
            final TextView txtprizname = (TextView) convertView.findViewById(R.id.txtprizname);

            Picasso.get().load(albhabet.get(position).getPrizeimage()).into(imagepng);


            txtcoins.setText(albhabet.get(position).getRequiredcoins()+"");
            txtprizname.setText(albhabet.get(position).getPrizename()+"");



            return convertView;


        }

    }




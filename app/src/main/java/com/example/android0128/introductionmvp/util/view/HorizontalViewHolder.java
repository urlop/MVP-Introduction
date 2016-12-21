package com.example.android0128.introductionmvp.util.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android0128.introductionmvp.R;

/**
 * Created by tk-0130 on 9/23/16.
 */
public class HorizontalViewHolder extends RecyclerView.ViewHolder{
    public ImageView image_iv;
    public TextView name_tv;
    public CardView card_view_horizontal;

    public HorizontalViewHolder(View v) {
        super(v);
        image_iv = (ImageView) v.findViewById(R.id.image_iv);
        name_tv = (TextView) v.findViewById(R.id.name_tv);
        card_view_horizontal = (CardView) v.findViewById(R.id.card_view_horizontal);
    }
}
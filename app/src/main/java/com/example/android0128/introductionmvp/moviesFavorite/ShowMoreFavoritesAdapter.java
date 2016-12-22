package com.example.android0128.introductionmvp.moviesFavorite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android0128.introductionmvp.data.source.QueryResponse;
import com.example.android0128.introductionmvp.util.view.HorizontalViewHolder;
import com.example.android0128.introductionmvp.BuildConfig;
import com.example.android0128.introductionmvp.util.Constants;
import com.example.android0128.introductionmvp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.android0128.introductionmvp.util.Constants.NO_USER;

/**
 * Created by rubymobile on 12/14/16.
 */

public class ShowMoreFavoritesAdapter extends RecyclerView.Adapter<HorizontalViewHolder>  {

    Context context;
    ArrayList<QueryResponse> query_ls;
    int type;

    public ShowMoreFavoritesAdapter(Context context, ArrayList<QueryResponse> query_ls, int type) {
        this.context = context;
        this.query_ls = query_ls;
        this.type = type;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_more_item,parent,false);
        return new HorizontalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, final int position) {
        switch (type){
            case 1:
                if(query_ls.get(position).getPoster_path() != null){
                    Picasso.with(context).load(BuildConfig.IMAGE_URL + query_ls.get(position)
                            .getPoster_path()).resize(Constants.picasso_image_width, Constants.picasso_image_height).centerInside().into(holder.image_iv);
                }else{
                    Picasso.with(context).load(NO_USER).resize(Constants.picasso_image_width-100, Constants.picasso_image_height-100).centerInside().into(holder.image_iv);
                }
                holder.name_tv.setText(query_ls.get(position).getTitle());
                break;
            case 2:
                if(query_ls.get(position).getPoster_path() != null){
                    Picasso.with(context).load(BuildConfig.IMAGE_URL + query_ls.get(position)
                            .getPoster_path()).resize(Constants.picasso_image_width, Constants.picasso_image_height).centerInside().into(holder.image_iv);
                }else{
                    Picasso.with(context).load(NO_USER).resize(Constants.picasso_image_width-100, Constants.picasso_image_height-100).centerInside().into(holder.image_iv);
                }
                holder.name_tv.setText(query_ls.get(position).getName());
                break;
            case 3:
                if(query_ls.get(position).getProfile_path() != null){
                    Picasso.with(context).load(BuildConfig.IMAGE_URL + query_ls.get(position)
                            .getProfile_path()).resize(Constants.picasso_image_width, Constants.picasso_image_height).centerInside().into(holder.image_iv);
                }else{
                    Picasso.with(context).load(NO_USER).resize(Constants.picasso_image_width-100, Constants.picasso_image_height-100).centerInside().into(holder.image_iv);
                }
                holder.name_tv.setText(query_ls.get(position).getName());
                break;
        }

        holder.image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(context, ItemDetailActivity.class);
                i.putExtra("models",query_ls.get(position));
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(i);*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return query_ls.size();
    }

    public void addMoreToList(ArrayList<QueryResponse> new_List){
        query_ls.addAll(new_List);
    }
}

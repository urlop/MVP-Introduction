package com.example.android0128.introductionmvp.movies;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android0128.introductionmvp.BuildConfig;
import com.example.android0128.introductionmvp.data.MovieModel;
import com.example.android0128.introductionmvp.util.view.HorizontalViewHolder;
import com.example.android0128.introductionmvp.util.Constants;
import com.example.android0128.introductionmvp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.android0128.introductionmvp.util.Constants.NO_USER;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by tk-0130 on 9/30/16.
 */
public class ShowMoreAdapter extends RecyclerView.Adapter<HorizontalViewHolder> {

    private MovieItemListener mItemListener;
    Context context;
    ArrayList<MovieModel> query_ls;
    int type;

    public ShowMoreAdapter(Context context, ArrayList<MovieModel> query_ls, int type, MovieItemListener mItemListener) {
        this.context = context;
        this.query_ls = query_ls;
        this.type = type;
        this.mItemListener = mItemListener;
    }

    @Override
    public HorizontalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_more_item, parent, false);
        return new HorizontalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HorizontalViewHolder holder, final int position) {
        final MovieModel item = query_ls.get(position);
        if (item.getPoster_path() != null) {
            Picasso.with(context).load(BuildConfig.IMAGE_URL + item
                    .getPoster_path()).resize(Constants.picasso_image_width, Constants.picasso_image_height).centerInside().into(holder.image_iv);
        } else {
            Picasso.with(context).load(NO_USER).resize(Constants.picasso_image_width - 100, Constants.picasso_image_height - 100).centerInside().into(holder.image_iv);
        }
        holder.name_tv.setText(item.getTitle());

        holder.image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return query_ls.size();
    }

    public void addMoreToList(ArrayList<MovieModel> new_List) {
        query_ls.addAll(new_List);
    }

    public void replaceData(ArrayList<MovieModel> tasks) {
        query_ls = checkNotNull(tasks);
        notifyDataSetChanged();
    }

    public interface MovieItemListener {
        void onItemClick(MovieModel clickedTask);
    }
}

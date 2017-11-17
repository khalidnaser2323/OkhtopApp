package com.example.toto.okhtobapp;


import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MatchListAdaptor extends RecyclerView.Adapter<MatchListAdaptor.ViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    private List<RowItems> reqList;;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView userPic;
        public TextView city, name;



        public ViewHolder(View itemView) {
            super(itemView);
            userPic = (ImageView)itemView.findViewById(R.id.reqRow_ImageView_pic);
            city = (TextView) itemView.findViewById(R.id.reqRow_textView_city);
            name = (TextView) itemView.findViewById(R.id.reqRow_textView_name);
        }
        // each data item


    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MatchListAdaptor(List<RowItems> reqList) {
        this.reqList = reqList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MatchListAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_request_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        RowItems rowItems = reqList.get(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return (null != reqList ? reqList.size() : 0);
    }

}

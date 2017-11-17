package com.example.toto.okhtobapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TabFragment extends Fragment {
    private List<RowItems> reqList;
    int position;
    private TextView textView;
    String city, name,userPic;
    RecyclerView matchReqList;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_tab_match_request, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // implement adapter and push data to recycle view
        reqList = new ArrayList<>();
        // parase the data from server to handle them in the arraylist
        RowItems item = new RowItems(city,name,userPic);
        reqList.add(item);

        textView = (TextView) view.findViewById(R.id.textView);
        matchReqList = (RecyclerView) view.findViewById(R.id.matchReq_recyclerView);
        mAdapter = new MatchListAdaptor(reqList);
        matchReqList.setAdapter(mAdapter);

        textView.setText("Fragment " + (position  + 1));

    }
}
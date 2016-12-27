package com.lqh.news.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.lqh.news.R;
import com.lqh.news.presenter.TopNewsPresenter;

@RequiresPresenter(TopNewsPresenter.class)
public class TopNewsFragment extends BeamFragment<TopNewsPresenter> {

    private EasyRecyclerView easyRecyclerView;
    public TopNewsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_top_news, container, false);
        easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.easy_recyclerview);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        return view;
    }

    public EasyRecyclerView getEasyRecyclerView(){
        return easyRecyclerView;
    }
}

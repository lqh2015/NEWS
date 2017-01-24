package com.lqh.news.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private FloatingActionButton button;
    private static int startdy;
    public TopNewsFragment(){
        Log.e("TopNewsFragment","构造方法");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TopNewsFragment","onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=View.inflate(getContext(),R.layout.fragment_top_news,null);
        easyRecyclerView = (EasyRecyclerView) view.findViewById(R.id.easy_recyclerview);
        easyRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        easyRecyclerView.setRefreshingColor(R.color.colorAccent);
        easyRecyclerView.setRefreshingColorResources(R.color.colorAccent);

        button= (FloatingActionButton) view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyRecyclerView.scrollToPosition(0);
            }
        });
        easyRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dx<startdy){
                    button.setVisibility(View.GONE);
                }else{
                    button.setVisibility(View.VISIBLE);
                }
                startdy=dy;
            }
        });
        Log.e("TopNewsFragment","onCreateView");
        button.setVisibility(View.GONE);
        return view;
    }

    public EasyRecyclerView getEasyRecyclerView(){
        return easyRecyclerView;
    }


}

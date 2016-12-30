package com.lqh.news.presenter;

import android.support.annotation.NonNull;

import com.jude.beam.expansion.BeamBasePresenter;
import com.lqh.news.model.TopNewsModel;
import com.lqh.news.presenter.adapter.MainRecycleViewAdapter;
import com.lqh.news.ui.TopNewsFragment;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class TopNewsPresenter extends BeamBasePresenter<TopNewsFragment> {
    private TopNewsFragment view;
    private TopNewsModel topNewsModel;

    public TopNewsPresenter(){
    }

    public TopNewsPresenter(TopNewsFragment view){
        this.view=view;
    }
    @Override
    protected void onCreateView(@NonNull TopNewsFragment view) {
        super.onCreateView(view);
        this.view=view;
        topNewsModel=new TopNewsModel(this);
    }

    public void setRecycleAdapter(MainRecycleViewAdapter adapter){
        view.getEasyRecyclerView().setAdapter(adapter);
    }
}

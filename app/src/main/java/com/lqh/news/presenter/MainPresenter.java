package com.lqh.news.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.lqh.news.model.TopNewsModel;
import com.lqh.news.presenter.adapter.MainViewPagerAdapter;
import com.lqh.news.ui.NewsFragment;
import com.lqh.news.ui.TopNewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqinghai on 2016/12/26.
 */
public class MainPresenter extends BeamBasePresenter<NewsFragment> {
    private NewsFragment view;
    private FragmentManager manager;

    @Override
    protected void onCreateView(@NonNull NewsFragment view) {
        super.onCreateView(view);
        this.view=view;
        manager=view.getActivity().getSupportFragmentManager();
//        manager=view.getChildFragmentManager();
//        initFragments();
        Log.e("MainPresenter",view.toString());
        TopNewsModel model=new TopNewsModel(manager,view.getTabLayout(),view.getViewPager());

    }

    public void setupWithViewPager(ViewPager viewPager){
        view.getTabLayout().setupWithViewPager(viewPager);
    }
}

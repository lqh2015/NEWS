package com.lqh.news.presenter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.jude.beam.expansion.BeamBasePresenter;
import com.lqh.news.ui.MainActivity;
import com.lqh.news.ui.TopNewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqinghai on 2016/12/26.
 */
public class MainPresenter extends BeamBasePresenter<MainActivity> {
    private MainActivity view;
    private FragmentManager manager;
    private List<Fragment> fragments;
    @Override
    protected void onCreateView(@NonNull MainActivity view) {
        super.onCreateView(view);
        this.view=view;
        manager=view.getSupportFragmentManager();
        initFragments();
        Log.e("MainPresenter",view.toString());
    }

    private void initFragments() {
        Fragment f1=new TopNewsFragment();
        Fragment f2=new TopNewsFragment();
        Fragment f3=new TopNewsFragment();
        Fragment f4=new TopNewsFragment();
        fragments=new ArrayList<>();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        MainViewPagerAdapter adapter=new MainViewPagerAdapter(manager,fragments);
        view.getViewPager().setAdapter(adapter);
        view.getTabLayout().setupWithViewPager(view.getViewPager());
    }
}

package com.lqh.news.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.lqh.news.model.Constants;
import com.lqh.news.ui.TopNewsFragment;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private static String[] tab_title= Constants.getTab_titles();
    private List<TopNewsFragment> fragments;
    FragmentManager fm;
    public MainViewPagerAdapter(FragmentManager fm,List<TopNewsFragment> fragments) {
        super(fm);
        this.fm=fm;
        this.fragments=fragments;
       // tab_title= APP.getInstance().getResources().getStringArray(R.array.tab_title);
        Log.e("MainViewPagerAdapter","MainViewPagerAdapter");
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return tab_title.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tab_title[position];
    }

}

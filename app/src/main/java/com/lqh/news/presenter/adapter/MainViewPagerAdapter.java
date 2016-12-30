package com.lqh.news.presenter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;

import com.lqh.news.APP;
import com.lqh.news.R;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private static String[] tab_title;
    private List<Fragment> fragments;
    FragmentManager fm;
    public MainViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fm=fm;
        this.fragments=fragments;
        tab_title= APP.getInstance().getResources().getStringArray(R.array.tab_title);
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

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((Fragment) obj).getView();
    }

}

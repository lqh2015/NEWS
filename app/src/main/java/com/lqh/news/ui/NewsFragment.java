package com.lqh.news.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.lqh.news.R;
import com.lqh.news.presenter.MainPresenter;

@RequiresPresenter(MainPresenter.class)
public class NewsFragment extends BeamFragment<MainPresenter> {

    private ViewPager viewPager;

    public NewsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        viewPager= (ViewPager) view.findViewById(R.id.main_viewpager);
        return view;
    }

    public ViewPager getViewPager(){
        return viewPager;
    }
    public TabLayout getTabLayout(){
        return ((MainActivity)getActivity()).getTabLayout();
    }

}

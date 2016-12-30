package com.lqh.news.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.jude.beam.expansion.BeamBasePresenter;
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
    private List<Fragment> fragments;
    @Override
    protected void onCreateView(@NonNull NewsFragment view) {
        super.onCreateView(view);
        this.view=view;
        manager=view.getActivity().getSupportFragmentManager();
//        manager=view.getChildFragmentManager();
        initFragments();
        Log.e("MainPresenter",view.toString());
    }

    private void initFragments() {
        final TopNewsFragment f1=new TopNewsFragment();
        final Bundle bundle=new Bundle();
        bundle.putString("newsId","");
        f1.setArguments(bundle);
        final TopNewsFragment f2=new TopNewsFragment();
        final Bundle bundle2=new Bundle();
        bundle.putString("newsId","T1348648517839");
        f2.setArguments(bundle2);
        final TopNewsFragment f3=new TopNewsFragment();
        final Bundle bundle3=new Bundle();
        bundle.putString("newsId","T1348648517839");
        f3.setArguments(bundle3);
        final TopNewsFragment f4=new TopNewsFragment();
        final Bundle bundle4=new Bundle();
        bundle.putString("newsId","T1348648517839");
        f4.setArguments(bundle4);
        fragments=new ArrayList<>();
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        fragments.add(f4);
        new TopNewsModel(new TopNewsPresenter(f1)).initRecAdapter();
        final MainViewPagerAdapter adapter=new MainViewPagerAdapter(manager,fragments);

        view.getViewPager().setAdapter(adapter);
        view.getViewPager().setCurrentItem(0);
        adapter.notifyDataSetChanged();
        view.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("MainPresenter","position:"+position);
                switch (position){
                    case 0 :
                        new TopNewsModel(new TopNewsPresenter(f1)).initRecAdapter();
                        break;
                    case 1 :
                        new TopNewsModel(new TopNewsPresenter(f2)).initRecAdapter2("T1348648517839");
                        break;
                    case 2 :
                        new TopNewsModel(new TopNewsPresenter(f3)).initRecAdapter2("T1348648517839");
                        break;
                    case 3 :
                        new TopNewsModel(new TopNewsPresenter(f4)).initRecAdapter2("T1348648517839");
                        break;
                }
               // adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

     //   adapter.notifyDataSetChanged();
        view.getTabLayout().setupWithViewPager(view.getViewPager());
    }
}

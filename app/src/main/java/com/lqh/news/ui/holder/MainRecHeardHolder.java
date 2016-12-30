package com.lqh.news.ui.holder;

import android.util.Log;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.rollviewpager.RollPagerView;
import com.lqh.news.R;
import com.lqh.news.model.beans.TopNews;
import com.lqh.news.presenter.adapter.MainHeardViewPagerAdapter;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class MainRecHeardHolder extends BaseViewHolder<TopNews.NewsBean> {

    private RollPagerView pagerView;

    public MainRecHeardHolder(ViewGroup parent) {
        super(parent, R.layout.main_recycleview_heard_item);
        pagerView=$(R.id.main_heard_item_rollViewPager);
    }

    @Override
    public void setData(TopNews.NewsBean data) {
        super.setData(data);
        if(data.getHasHead()==1){
            if(data.getAds()!=null&&data.getAds().size()>0){
                MainHeardViewPagerAdapter adapter=new MainHeardViewPagerAdapter(pagerView,data.getAds());
                pagerView.setAdapter(adapter);
            }
        }
        Log.e("MainRecHeardHolder",data.getHasHead()+"");
    }
}

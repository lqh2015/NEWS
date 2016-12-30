package com.lqh.news.presenter.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lqh.news.R;
import com.lqh.news.model.beans.TopNews;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class MainHeardViewPagerAdapter extends LoopPagerAdapter {

    private List<TopNews.NewsBean.AdsBean> adsBeanList;
    public MainHeardViewPagerAdapter(RollPagerView viewPager, List<TopNews.NewsBean.AdsBean> adsBeanList){
        super(viewPager);
        this.adsBeanList=adsBeanList;
    }

    @Override
    protected int getRealCount() {
        return adsBeanList.size();
    }
    
    @Override
    public View getView(ViewGroup container, int position) {
        View view=View.inflate(container.getContext(), R.layout.main_heard_viewpager_item,null);
        SimpleDraweeView draweeView = (SimpleDraweeView) view.findViewById(R.id.main_heard_item_rollViewPager_simDraView);
        TextView tv_title= (TextView) view.findViewById(R.id.main_heard_item_rollViewPager_title);
        draweeView.setImageURI(adsBeanList.get(position).getImgsrc());
        tv_title.setText(adsBeanList.get(position).getTitle());
       // container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}

package com.lqh.news.presenter.adapter;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.lqh.news.R;
import com.lqh.news.model.beans.NewsData;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class MainHeardViewPagerAdapter extends LoopPagerAdapter {

    private static final String TAG="ViewPagerAdapter";
    private List<NewsData.NewsBean.AdsBean> adsBeanList;
    private SimpleDraweeView draweeView;
    private View view;
    public MainHeardViewPagerAdapter(RollPagerView viewPager, List<NewsData.NewsBean.AdsBean> adsBeanList){
        super(viewPager);
        this.adsBeanList=adsBeanList;
    }

    @Override
    public int getRealCount() {
        return adsBeanList.size();
    }
    
    @Override
    public View getView(ViewGroup container, int position) {
        Log.e(TAG,"getView");
        view=View.inflate(container.getContext(), R.layout.main_heard_viewpager_item,null);
        draweeView = (SimpleDraweeView) view.findViewById(R.id.main_heard_item_rollViewPager_simDraView);
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

    @Override
    public Object instantiateItem(View container, int position) {
        Log.e(TAG,"instantiateItem");
     //   View view = imageViews.get(position);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.e("xl", "xl:arrive here.");
            }
        });
        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view);
        return view;
    }
}

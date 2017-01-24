package com.lqh.news.ui.holder;

import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.lqh.news.APP;
import com.lqh.news.R;
import com.lqh.news.model.beans.NewsData;
import com.lqh.news.presenter.adapter.MainHeardViewPagerAdapter;
import com.lqh.news.ui.ImgNewsDetialActivity;

import java.util.List;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class MainRecHeardHolder extends BaseViewHolder<NewsData.NewsBean> {

    private RollPagerView pagerView;
    private MainHeardViewPagerAdapter adapter;
    private static final String TAG="MainRecHeardHolder";
    private List<NewsData.NewsBean.AdsBean> ads;


    public MainRecHeardHolder(final ViewGroup parent) {
        super(parent, R.layout.main_recycleview_heard_item);
        pagerView=$(R.id.main_heard_item_rollViewPager);
        pagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG,"onItemClick:"+position);
                NewsData.NewsBean.AdsBean adsBean = ads.get(position);
                String title = adsBean.getTitle();
                String url1 = adsBean.getUrl();
                String id = url1.split("\\|")[1];

                Intent intent=new Intent(APP.getInstance(), ImgNewsDetialActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("title",title);
                intent.putExtra("id",id);
                APP.getInstance().startActivity(intent);
            }
        });
    }

    @Override
    public void setData(NewsData.NewsBean data) {
        super.setData(data);
        if(data.getHasHead()==1){
            if(data.getAds()!=null&&data.getAds().size()>0){
                ads = data.getAds();
                adapter=new MainHeardViewPagerAdapter(pagerView,ads);
                if(adapter.getCount()==1){
                    Log.e(TAG,"count==1");
                    pagerView.setHintView(null);
                }
                pagerView.setAdapter(adapter);
            }
        }
        Log.e("MainRecHeardHolder",data.getHasHead()+"");
    }
}

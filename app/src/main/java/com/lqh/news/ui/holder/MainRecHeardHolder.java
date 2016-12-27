package com.lqh.news.ui.holder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.lqh.news.R;
import com.lqh.news.model.beans.TopNews;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class MainRecHeardHolder extends BaseViewHolder<TopNews.NewsBean> {

    private TextView textView;

    public MainRecHeardHolder(ViewGroup parent) {
        super(parent, R.layout.main_recycleview_heard_item);
    //    textView=$(R.id.main_heard_item_tv);
    }

    @Override
    public void setData(TopNews.NewsBean data) {
        super.setData(data);
    //    textView.setText(data.getTitle());
        Log.e("MainRecHeardHolder",data.getTitle());
    }
}

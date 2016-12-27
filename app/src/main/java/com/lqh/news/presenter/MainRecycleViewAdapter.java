package com.lqh.news.presenter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lqh.news.model.beans.TopNews;
import com.lqh.news.ui.holder.MainRecCommonHolder;
import com.lqh.news.ui.holder.MainRecHeardHolder;

/**
 * Created by liqinghai on 2016/12/27.
 */
public class MainRecycleViewAdapter extends RecyclerArrayAdapter<TopNews.NewsBean> {
    private static final int HEARD_VIEWTYPR=0;
    private static final int COMMON_VIEWTYPE=1;
    public MainRecycleViewAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("MainRecycleViewAdapter",viewType+":viewType");
        switch (viewType){
            case HEARD_VIEWTYPR:
                return new MainRecHeardHolder(parent);
            case COMMON_VIEWTYPE:
                return new MainRecCommonHolder(parent);
            default:
                return null;

        }

    }


    @Override
    public int getViewType(int position) {
        Log.e("MainRecycleViewAdapter",position+":position");
        return position==0?HEARD_VIEWTYPR:COMMON_VIEWTYPE;
    }
}

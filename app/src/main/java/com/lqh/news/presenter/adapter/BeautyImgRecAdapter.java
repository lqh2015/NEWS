package com.lqh.news.presenter.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lqh.news.model.beans.BeautyImg;
import com.lqh.news.ui.holder.BeautyRecycleViewHolder;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class BeautyImgRecAdapter extends RecyclerArrayAdapter<BeautyImg.BeautyBean> {
    public BeautyImgRecAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new BeautyRecycleViewHolder(parent);
    }

}

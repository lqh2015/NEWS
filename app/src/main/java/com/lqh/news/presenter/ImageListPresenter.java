package com.lqh.news.presenter;

import android.support.annotation.NonNull;

import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lqh.news.model.BeautyImgModel;
import com.lqh.news.ui.BeautyImgFragment;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class ImageListPresenter extends BeamBasePresenter<BeautyImgFragment> {
    private BeautyImgFragment view;
    @Override
    protected void onCreateView(@NonNull BeautyImgFragment view) {
        super.onCreateView(view);
        this.view=view;


        BeautyImgModel model=new BeautyImgModel(this);
    }

    public void setRecAdapter(RecyclerArrayAdapter adapter) {
        view.getRecyclerView().setAdapter(adapter);
    }
}

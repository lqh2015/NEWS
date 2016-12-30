package com.lqh.news.model;

import android.util.Log;

import com.jude.beam.model.AbsModel;
import com.lqh.news.APP;
import com.lqh.news.model.beans.BeautyImg;
import com.lqh.news.net.NetSevice;
import com.lqh.news.presenter.ImageListPresenter;
import com.lqh.news.presenter.adapter.BeautyImgRecAdapter;
import com.lqh.news.utils.Utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class BeautyImgModel extends AbsModel {

    private ImageListPresenter presenter;
    private BeautyImgRecAdapter adapter;
    private NetSevice netSevice= Utils.creatRetrofit().create(NetSevice.class);
    public BeautyImgModel(ImageListPresenter presenter){
        this.presenter=presenter;
        adapter=new BeautyImgRecAdapter(APP.getInstance());
        initRecAdapter();
    }

    public void initRecAdapter(){
        Observable<BeautyImg> topNewsObserable = netSevice.getBeautyImg(0);
        topNewsObserable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BeautyImg>() {
                    @Override
                    public void onCompleted() {
                        Log.e("BeautyImgModel","beautyImg加载完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("BeautyImgModel","beautyImg加载出错");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BeautyImg beautyImg) {
                        Log.e("BeautyImgModel",beautyImg.get美女().get(0).getTitle());
                        adapter.clear();
                        adapter.addAll(beautyImg.get美女());
                        //adapter.a
                        presenter.setRecAdapter(adapter);
                    }
                });
    }
}

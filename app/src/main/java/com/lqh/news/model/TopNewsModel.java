package com.lqh.news.model;

import android.util.Log;

import com.jude.beam.model.AbsModel;
import com.lqh.news.APP;
import com.lqh.news.model.beans.TopNews;
import com.lqh.news.net.NetSevice;
import com.lqh.news.presenter.MainRecycleViewAdapter;
import com.lqh.news.presenter.TopNewsPresenter;
import com.lqh.news.utils.Utils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liqinghai on 2016/12/26.
 */
public class TopNewsModel extends AbsModel {
    private TopNewsPresenter mPresenter;
    private  MainRecycleViewAdapter adapter;
    private NetSevice netSevice= Utils.creatRetrofit().create(NetSevice.class);
    public TopNewsModel(TopNewsPresenter presenter){
        mPresenter=presenter;
        adapter= new MainRecycleViewAdapter(APP.getInstance());
//        presenter.setRecycleAdapter(adapter);
        initRecAdapter();
    }

    public void initRecAdapter(){
        Observable<TopNews> topNewsObserable = netSevice.getTopNews(0);
        topNewsObserable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TopNews>() {
            @Override
            public void onCompleted() {
                Log.e("TopNewsModel","topnews加载完成");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TopNewsModel","topnews加载出错");
                e.printStackTrace();
            }

            @Override
            public void onNext(TopNews topNews) {
                Log.e("TopNewsModel",topNews.getT1348647909107().get(0).getTitle());
                adapter.clear();
                adapter.addAll(topNews.getT1348647909107());
                //adapter.a
                mPresenter.setRecycleAdapter(adapter);
            }
        });
    }


}

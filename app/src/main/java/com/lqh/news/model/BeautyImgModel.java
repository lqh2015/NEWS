package com.lqh.news.model;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.jude.beam.model.AbsModel;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lqh.news.APP;
import com.lqh.news.R;
import com.lqh.news.model.beans.BeautyImg;
import com.lqh.news.net.NetSevice;
import com.lqh.news.presenter.ImageListPresenter;
import com.lqh.news.presenter.adapter.BeautyImgRecAdapter;
import com.lqh.news.utils.Utils;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class BeautyImgModel extends AbsModel{

    private static final String TAG="BeautyImgModel";
    private ImageListPresenter presenter;
    private BeautyImgRecAdapter adapter;
    private EasyRecyclerView recyclerView;
    private static int page=0;
    List<BeautyImg.BeautyBean> beautyBeanList;
    private NetSevice netSevice= Utils.creatRetrofit().create(NetSevice.class);
    public BeautyImgModel(ImageListPresenter presenter){
        this.presenter=presenter;
        initView();
        loadData(0);
    }

    private void initView() {
        adapter=new BeautyImgRecAdapter(APP.getInstance());
        recyclerView=presenter.getEasyRecyclerView();
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(0);
            }
        });
        adapter.setMore(R.layout.load_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                Log.e(TAG,"onLoadMore:"+page);
                loadData(page);
            }
        });

        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG,"onItemClick");
            }
        });
        presenter.setRecAdapter(adapter);

    }


    public void loadData(final int page){
        Observable<BeautyImg> topNewsObserable = netSevice.getBeautyImg(page);
        topNewsObserable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BeautyImg>() {
                    @Override
                    public void onCompleted() {
                        Log.e("BeautyImgModel","beautyImg加载完成");
                       // recyclerView.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("BeautyImgModel","beautyImg加载出错");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(BeautyImg beautyImg) {
                        Log.e("BeautyImgModel",beautyImg.get美女().get(0).getTitle());
                        if(page==0){
                            adapter.clear();
                        }
                      //  beautyBeanList = beautyImg.get美女();
                        adapter.addAll(beautyImg.get美女());
                    }
                });
    }
}

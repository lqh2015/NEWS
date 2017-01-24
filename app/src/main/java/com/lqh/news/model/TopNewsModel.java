package com.lqh.news.model;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jude.beam.model.AbsModel;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lqh.news.APP;
import com.lqh.news.R;
import com.lqh.news.model.beans.NewsData;
import com.lqh.news.net.NetSevice;
import com.lqh.news.presenter.adapter.MainRecycleViewAdapter;
import com.lqh.news.presenter.adapter.MainViewPagerAdapter;
import com.lqh.news.ui.NewsDetialActivity;
import com.lqh.news.ui.TopNewsFragment;
import com.lqh.news.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liqinghai on 2016/12/26.
 */
public class TopNewsModel extends AbsModel implements SwipeRefreshLayout.OnRefreshListener{
//    private TopNewsPresenter mPresenter;
    private static final String TAG="TopNewsModel";
    private  MainRecycleViewAdapter recycleViewAdapter;
    private FragmentManager manager;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<TopNewsFragment> fragments=new ArrayList<>();
    private MainViewPagerAdapter adapter;
    private static int viewPagerCurPosition=0;//当前处于哪个条目
    private static int page=0;//新闻列表显示的是第几页的数据
    RequestQueue requestQueue= Volley.newRequestQueue(APP.getInstance());//这里的this指的是Context

    private NetSevice netSevice= Utils.creatRetrofit().create(NetSevice.class);
    public TopNewsModel(FragmentManager manager, TabLayout tabLayout,ViewPager viewPager){
        this.tabLayout=tabLayout;
        this.manager=manager;
        this.viewPager=viewPager;
        recycleViewAdapter= new MainRecycleViewAdapter(APP.getInstance());
        recycleViewAdapter.setMore(R.layout.load_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                page++;
                Log.e(TAG,"onLoadMore:"+page);
                switch (viewPagerCurPosition){
                    case 0 :
//                        loadHomeNewsData();
                        loadMoreTopNewsData();
                        break;
                    default:
                        loadCommonNewsData(fragments.get(viewPagerCurPosition),Constants.getNewsId(viewPagerCurPosition-1));
                        break;

                }
            }
        });
        recycleViewAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.e(TAG,"onItemClick:"+position);
                Intent intent=new Intent(APP.getInstance(), NewsDetialActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("postid",recycleViewAdapter.getItem(position).getPostid());
                intent.putExtra("title",recycleViewAdapter.getItem(position).getTitle());
                APP.getInstance().startActivity(intent);
            }
        });
        initFragments();
    }
    private void initFragments() {
        for(int i=0;i<Constants.getTab_titles().length;i++){
            TopNewsFragment fragment=new TopNewsFragment();
            fragments.add(fragment);
        }
        loadHomeNewsData();

        adapter=new MainViewPagerAdapter(manager,fragments);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);

//        adapter.notifyDataSetChanged();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e("MainPresenter","position:"+position);
//                recycleViewAdapter.clear();
                recycleViewAdapter= new MainRecycleViewAdapter(APP.getInstance());
                recycleViewAdapter.setMore(R.layout.load_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        page=3;
                        Log.e(TAG,"onLoadMore:"+page);
                        switch (viewPagerCurPosition){
                            case 0 :
                                loadMoreTopNewsData();
                                break;
                            default:
//                                loadCommonNewsData(fragments.get(viewPagerCurPosition),Constants.getNewsId(viewPagerCurPosition-1));
                                loadMoreCommonNewsData(fragments.get(viewPagerCurPosition),Constants.getNewsId(viewPagerCurPosition-1));
                                break;

                        }
                    }
                });

                recycleViewAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Log.e(TAG,"onItemClick:"+position);
                    }
                });

                viewPagerCurPosition=position;
                page=0;//切换新闻条目时，重置显示的页数
                switch (position){
                    case 0 :
                        loadHomeNewsData();
                        break;
                    default:
                        loadCommonNewsData( fragments.get(viewPagerCurPosition),Constants.getNewsId(viewPagerCurPosition-1));
                        break;

                }
                // adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //   adapter.notifyDataSetChanged();
        tabLayout.setupWithViewPager(viewPager);
    }


    public void loadHomeNewsData(){
        Log.e("loadHomeNewsData","loadHomeNewsData");
        Observable<NewsData> topNewsObserable = netSevice.getTopNews(page);
        topNewsObserable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsData>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TopNewsModel", "topnews加载完成");
//                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TopNewsModel", "topnews加载出错");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(NewsData topNews) {
                        Log.e("TopNewsModel", topNews.getT1348647909107().get(0).getTitle());
                        if (page==0){
                            recycleViewAdapter.clear();
                        }

                        recycleViewAdapter.addAll(topNews.getT1348647909107());
                        //adapter.a
                        fragments.get(0).getEasyRecyclerView().setAdapterWithProgress(recycleViewAdapter);
                        fragments.get(0).getEasyRecyclerView().setRefreshListener(TopNewsModel.this);
//                        adapter.notifyDataSetChanged();
                    }
                });
    }


    public void loadCommonNewsData(final TopNewsFragment fragment, String newsId){
        Log.e(TAG,"loadCommonNewsData");
        if(!TextUtils.isEmpty(newsId)){
            Observable<NewsData> topNewsObserable = netSevice.getCommenNews(newsId,page);
            topNewsObserable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<NewsData>() {
                        @Override
                        public void onCompleted() {
                            Log.e("TopNewsModel","CommenNews加载完成");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("TopNewsModel","CommenNews加载出错");
                            e.printStackTrace();
                        }

                        @Override
                        public void onNext(NewsData topNews) {
//                            Log.e("TopNewsModel",topNews.getT1348648517839().get(0).getTitle());
                            if (page==0){
                                recycleViewAdapter.clear();
                            }
                            Log.e("TopNewsModel","page:"+page);
//                            recycleViewAdapter.addAll(topNews.getT1348648517839());
                            switch (viewPagerCurPosition){
                                case 1:
                                    recycleViewAdapter.addAll(topNews.getT1348648517839());
                                    break;
                                case 2:
                                    recycleViewAdapter.addAll(topNews.getT1348649079062());
                                    break;
                                case 3:
                                    recycleViewAdapter.addAll(topNews.getT1348648756099());
                                    break;
                                case 4:
                                    recycleViewAdapter.addAll(topNews.getT1348649580692());
                                    break;
                                case 5:
                                    recycleViewAdapter.addAll(topNews.getT1348648650048());
                                    break;
                                case 6:
                                    recycleViewAdapter.addAll(topNews.getT1348654060988());
                                    break;
                                case 7:
                                    recycleViewAdapter.addAll(topNews.getT1350383429665());
                                    break;
                                case 8:
                                    recycleViewAdapter.addAll(topNews.getT1348654151579());
                                    break;
                                case 9:
                                    recycleViewAdapter.addAll(topNews.getT1348650593803());
                                    break;
                                case 10:
                                    recycleViewAdapter.addAll(topNews.getT1348650839000());
                                    break;
                                case 11:
                                    recycleViewAdapter.addAll(topNews.getT1370583240249());
                                    break;
                                case 12:
                                    recycleViewAdapter.addAll(topNews.getT1379038288239());
                                    break;
                                case 13:
                                    recycleViewAdapter.addAll(topNews.getT1348649145984());
                                    break;
                                case 14:
                                    recycleViewAdapter.addAll(topNews.getT1348649776727());
                                    break;
                                case 15:
                                    recycleViewAdapter.addAll(topNews.getT1351233117091());
                                    break;
                                case 16:
                                    recycleViewAdapter.addAll(topNews.getT1356600029035());
                                    break;
                                case 17:
                                    recycleViewAdapter.addAll(topNews.getT1348654225495());
                                    break;
                                case 18:
                                    recycleViewAdapter.addAll(topNews.getT1349837670307());
                                    break;
                                case 19:
                                    recycleViewAdapter.addAll(topNews.getT1348654204705());
                                    break;
                                case 20:
                                    recycleViewAdapter.addAll(topNews.getT1348649654285());
                                    break;
                                case 21:
                                    recycleViewAdapter.addAll(topNews.getT1349837698345());
                                    break;
                                case 22:
                                    recycleViewAdapter.addAll(topNews.getT1348648037603());
                                    break;
                                case 23:
                                    recycleViewAdapter.addAll(topNews.getT1348654105308());
                                    break;
//                                case 24:
//                                    recycleViewAdapter.addAll(topNews.getT1348648141035());
//                                    break;
                            }
                            fragment.getEasyRecyclerView().setRefreshListener(TopNewsModel.this);
                            fragment.getEasyRecyclerView().setAdapterWithProgress(recycleViewAdapter);
                        }
                    });

        }

    }

    @Override
    public void onRefresh() {
        Log.e(TAG,"onRefresh");
//        loadCommonNewsData(f2,"T1348648517839");
//        loadHomeNewsData();
//        recycleViewAdapter.clear();
        page=0;//刷新的时候把page设置为0
        switch (viewPagerCurPosition){
            case 0:
                loadHomeNewsData();
                break;
            default:
                loadCommonNewsData(fragments.get(viewPagerCurPosition),Constants.getNewsId(viewPagerCurPosition-1));
                break;
        }
    }

    private void loadMoreTopNewsData(){
        if(page%5!=0){
            page=page+2;
        }else {
            page=page+3;
        }
        String url="http://c.m.163.com/nc/article/headline/T1348647909107/"+page+"-20.html";
        Log.e(TAG,page+"");
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG,response.toString());
                String data=response.toString();
                Gson gson=new Gson();
                NewsData newsData = gson.fromJson(data, NewsData.class);
                List<NewsData.NewsBean> newsBeen = newsData.getT1348647909107();

                if(newsBeen!=null&&newsBeen.size()>0){
                    recycleViewAdapter.addAll(newsBeen);
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });
        requestQueue.add(jr);
    }

    private void loadMoreCommonNewsData(final TopNewsFragment fragment, String newsId){
        if(page%5!=0){
            page=page+2;
        }else {
            page=page+3;
        }
        String url="http://c.m.163.com/nc/article/list/"+newsId+"/"+page+"-20.html";
        Log.e(TAG,page+":"+url);
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG,response.toString());
                String data=response.toString();
                Gson gson=new Gson();
                NewsData topNews = gson.fromJson(data, NewsData.class);
//                List<NewsData.NewsBean> newsBeen = newsData.getT1348647909107();
//                if(newsBeen!=null&&newsBeen.size()>0){
//                    recycleViewAdapter.addAll(newsBeen);
//                }

                switch (viewPagerCurPosition){
                    case 1:
                        recycleViewAdapter.addAll(topNews.getT1348648517839());
                        break;
                    case 2:
                        recycleViewAdapter.addAll(topNews.getT1348649079062());
                        break;
                    case 3:
                        recycleViewAdapter.addAll(topNews.getT1348648756099());
                        break;
                    case 4:
                        recycleViewAdapter.addAll(topNews.getT1348649580692());
                        break;
                    case 5:
                        recycleViewAdapter.addAll(topNews.getT1348648650048());
                        break;
                    case 6:
                        recycleViewAdapter.addAll(topNews.getT1348654060988());
                        break;
                    case 7:
                        recycleViewAdapter.addAll(topNews.getT1350383429665());
                        break;
                    case 8:
                        recycleViewAdapter.addAll(topNews.getT1348654151579());
                        break;
                    case 9:
                        recycleViewAdapter.addAll(topNews.getT1348650593803());
                        break;
                    case 10:
                        recycleViewAdapter.addAll(topNews.getT1348650839000());
                        break;
                    case 11:
                        recycleViewAdapter.addAll(topNews.getT1370583240249());
                        break;
                    case 12:
                        recycleViewAdapter.addAll(topNews.getT1379038288239());
                        break;
                    case 13:
                        recycleViewAdapter.addAll(topNews.getT1348649145984());
                        break;
                    case 14:
                        recycleViewAdapter.addAll(topNews.getT1348649776727());
                        break;
                    case 15:
                        recycleViewAdapter.addAll(topNews.getT1351233117091());
                        break;
                    case 16:
                        recycleViewAdapter.addAll(topNews.getT1356600029035());
                        break;
                    case 17:
                        recycleViewAdapter.addAll(topNews.getT1348654225495());
                        break;
                    case 18:
                        recycleViewAdapter.addAll(topNews.getT1349837670307());
                        break;
                    case 19:
                        recycleViewAdapter.addAll(topNews.getT1348654204705());
                        break;
                    case 20:
                        recycleViewAdapter.addAll(topNews.getT1348649654285());
                        break;
                    case 21:
                        recycleViewAdapter.addAll(topNews.getT1349837698345());
                        break;
                    case 22:
                        recycleViewAdapter.addAll(topNews.getT1348648037603());
                        break;
                    case 23:
                        recycleViewAdapter.addAll(topNews.getT1348654105308());
                        break;
//                                case 24:
//                                    recycleViewAdapter.addAll(topNews.getT1348648141035());
//                                    break;
                }

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,error.getMessage());
            }
        });
        requestQueue.add(jr);
    }
}

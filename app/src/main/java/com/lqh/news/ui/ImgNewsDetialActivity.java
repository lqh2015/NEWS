package com.lqh.news.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.lqh.news.APP;
import com.lqh.news.R;
import com.lqh.news.model.beans.ImageNews;
import com.lqh.news.presenter.adapter.ImgNewsViewPagerAdapter;
import com.lqh.news.utils.Utils;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImgNewsDetialActivity extends AppCompatActivity {

    @BindView(R.id.imgnews_title)
    public TextView title;
    @BindView(R.id.imgnews_page)
    public TextView page;
    @BindView(R.id.imgnews_content)
    public TextView content;
    @BindView(R.id.imgnews_viewpager)
    public RollPagerView pagerView;
    @BindView(R.id.img_page)
    public TextView img_page;

    private String url="http://c.m.163.com/photo/api/set/0001/"+"id"+".json";
    private RequestQueue requestQueue= Volley.newRequestQueue(APP.getInstance());
    private static final String TAG="ImgNewsDetialActivity";
    private List<ImageNews.PhotosBean> photos;
    private String imgsum;
    private ActionBar actionBar;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_img_news_detial);
        ButterKnife.bind(this);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        actionBar.setShowHideAnimationEnabled(true);
        actionBar.setTitle("");
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        loadNewsDetialData();//加载图片新闻详情数据
    }

    private void initView() {
        title.setText(getIntent().getStringExtra("title"));
        relativeLayout= (RelativeLayout) findViewById(R.id.line2);
        relativeLayout.setVisibility(View.INVISIBLE);
        pagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ActionBar actionBar = getSupportActionBar();
                actionBar.setShowHideAnimationEnabled(true);
                ScrollView scrollView= (ScrollView) findViewById(R.id.scrollView);
                relativeLayout= (RelativeLayout) findViewById(R.id.line2);
                if(actionBar.isShowing()){
                    actionBar.hide();

                    scrollView.setVisibility(View.INVISIBLE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }else {
                    actionBar.show();
                    scrollView.setVisibility(View.VISIBLE);
                    relativeLayout.setVisibility(View.INVISIBLE);
                }
            }
        });
        pagerView.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                
                page.setText(position+1+"/"+imgsum);
                content.setText(photos.get(position).getNote());
                img_page.setText(position+1+"/"+imgsum);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void loadNewsDetialData() {
        String id = getIntent().getStringExtra("id");
        url = url.replace("id", id);
        JsonObjectRequest jr=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson=new Gson();
                ImageNews imageNews = gson.fromJson(jsonObject.toString(), ImageNews.class);
                imgsum = imageNews.getImgsum();
                photos = imageNews.getPhotos();
                ImgNewsViewPagerAdapter adapter = new ImgNewsViewPagerAdapter(photos);
                pagerView.setAdapter(adapter);
                pagerView.setHintView(null);
                ViewGroup.LayoutParams layoutParams = pagerView.getLayoutParams();
                layoutParams.height=Utils.getDeviceWidth()*9/16+200;
                page.setText("1/"+imgsum);
                content.setText(photos.get(0).getNote());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,"加载图片新闻出错");
            }
        });
        requestQueue.add(jr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case  R.id.overflow:
//                int height = getSupportActionBar().getHeight();
                Utils.popUpMyOverflow(this,0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

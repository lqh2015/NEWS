package com.lqh.news.ui;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.lqh.news.APP;
import com.lqh.news.R;
import com.lqh.news.model.beans.NewsDetialData;
import com.lqh.news.utils.Utils;

import java.net.URL;


public class DetailNewsActivity extends AppCompatActivity {
    private static final String TAG="DetailNewsActivity";

    private static final boolean AUTO_HIDE = true;

    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private TextView mContentView;
    private RequestQueue requestQueue= Volley.newRequestQueue(APP.getInstance());
    private String url="http://c.m.163.com/nc/article/postId/full.html";

    private Handler handler;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(getIntent().getStringExtra("title"));
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_news);
//        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getIntent().getStringExtra("title"));
            actionBar.setShowHideAnimationEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        }
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView =(TextView) findViewById(R.id.fullscreen_content);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
        final String html1="<html><head><title>TextView使用HTML</title></head><body><p><strong>强调</strong></p><p><em>斜体</em></p>"
                +"<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1"
                +"</p><p><font color=\"#00bbaa\">颜色2</p><h1>标题1</h1><h3>标题2</h3><h6>标题3</h6><p>大于>小于<</p><p>" +
                "下面是网络图片</p><img src=\"http://cms-bucket.nosdn.127.net/fcd525f109c342868cf37bdbdc82eb9c20170111170930.jpeg\"/>" +
                "</body></html>";

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Spanned spanned = (Spanned) msg.obj;

                mContentView.setText(spanned);
            }
        };



        StringRequest sr=new StringRequest(url.replace("postId",getIntent().getStringExtra("postid")),
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                s=s.replace(getIntent().getStringExtra("postid"),"bean");
                Gson gson=new Gson();
                NewsDetialData newsDetialData = gson.fromJson(s, NewsDetialData.class);
                final NewsDetialData.Bean bean=newsDetialData.getBean();
                String content=bean.getBody();
                for(int i=0;i<bean.getImg().size();i++){
                    String src = bean.getImg().get(i).getSrc();
                    String imgSrc="<img src=\""+src+"\"/>";
                    content=content.replace("<!--IMG#"+i+"-->",imgSrc);
                }
                String html=getString(R.string.news_content);
                final String news_content =html
                        .replace("ep-title","<h1>"+bean.getTitle()+"</h1>")
                        .replace("ep-time",bean.getPtime())
                        .replace("ep-source",bean.getSource())
                        .replace("ep-content",content)
                        .replace("ep-editor",bean.getEc());
                Log.e(TAG,news_content);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final int count=0;

                        Spanned spanned=Html.fromHtml(news_content, new Html.ImageGetter() {
                            @Override
                            public Drawable getDrawable(String s) {
                                try {
                                    String pixel = bean.getImg().get(count).getPixel();
                                    String[] pixels=pixel.split("\\*");
                                    int img_width=Integer.parseInt(pixels[0]);
                                    int img_higth=Integer.parseInt(pixels[1]);
                                    int width= Utils.getDeviceWidth()-10;
                                    int higth=width*img_higth/img_width;
                                    URL url=new URL(s);
                                    Drawable drawable=Drawable.createFromStream(url.openStream(),"");
                                    drawable.setBounds(0, 0,width,higth);
                                    return drawable;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    return null;
                                }
                              //  count++;
                            }
                        },null);
                        Message message=Message.obtain();
                        message.obj=spanned;
                        handler.sendMessage(message);
                    }
                }).start();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        requestQueue.add(sr);

        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedHide(100);
    }

    private void toggle() {
        Log.e("mVisible",mVisible+"");
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        Log.e("hide","hide");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        Log.e("show","show");
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}

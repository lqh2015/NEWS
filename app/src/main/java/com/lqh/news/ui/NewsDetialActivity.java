package com.lqh.news.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetialActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    public TextView tv_content;
    @BindView(R.id.scrollView)
    public ScrollView scrollView;
    private static final String TAG="NewsDetialActivity";
    private Handler handler;
    private RequestQueue requestQueue= Volley.newRequestQueue(APP.getInstance());
    private String url="http://c.m.163.com/nc/article/postId/full.html";
    private static int startdy;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detial);
        ButterKnife.bind(this);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getIntent().getStringExtra("title"));
            actionBar.setShowHideAnimationEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.show();
        }
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Spanned spanned = (Spanned) msg.obj;
                tv_content.setText(spanned);
            }
        };
//        getOverflowMenu();
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_MOVE:
                        int dx= (int) motionEvent.getRawX();
                        int dy= (int) motionEvent.getRawY();
                        if(dy<startdy){
                            actionBar = getSupportActionBar();
                            if (actionBar != null) {
                                actionBar.hide();
                            }
                        }else{
                            actionBar = getSupportActionBar();
                            if (actionBar != null) {
                                actionBar.setTitle(getIntent().getStringExtra("title"));
                                actionBar.setShowHideAnimationEnabled(true);
                                actionBar.setDisplayHomeAsUpEnabled(true);
                                actionBar.show();
                            }
                        }
                        startdy=dy;
                }
                return false;
            }
        });
        loadNewsDetialData();
    }

    private void loadNewsDetialData() {
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
                            String imgSrc="<p><img src=\""+src+"\"/></p>";
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

                                Spanned spanned= Html.fromHtml(news_content, new Html.ImageGetter() {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail, menu);
        Log.e(TAG,"onCreateOptionsMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.overflow) {
            int height = getSupportActionBar().getHeight();
            Utils.popUpMyOverflow(this,height);
            return true;
        }else if(id==android.R.id.home){
            this.finish();
        }
        Log.e(TAG,"onOptionsItemSelected");
        return super.onOptionsItemSelected(item);
    }

//    //在actionbar上强制显示OverflowMenu
//    private void getOverflowMenu() {
//        try {
//            ViewConfiguration config = ViewConfiguration.get(this);
//            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
//            if(menuKeyField != null) {
//                menuKeyField.setAccessible(true);
//                menuKeyField.setBoolean(config, false);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    // OverflowShow显示图标
//    @Override
//    public boolean onMenuOpened(int featureId, Menu menu) {
//        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
//            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
//                try {
//                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//                    method.setAccessible(true);
//                    method.invoke(menu, true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return super.onMenuOpened(featureId, menu);
//    }
}

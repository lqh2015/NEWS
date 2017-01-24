package com.lqh.news.utils;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jude.beam.expansion.list.ListConfig;
import com.lqh.news.APP;
import com.lqh.news.R;
import com.lqh.news.model.beans.APIStores;

import java.io.File;
import java.lang.reflect.Field;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lqh on 2016/12/11.
 */
public class Utils {
    private static CompositeSubscription mCompositeSubscription;
    public static Retrofit creatRetrofit(){
        //创建interceptor，用于打印网络请求的信息，如请求行，请求体等信息
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //用于网络请求
        OkHttpClient client= new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(APIStores.NETEAST_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    public static void registersubscribe(Observable observable, Subscriber subscriber){

        if(mCompositeSubscription==null){
            mCompositeSubscription=new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    public static File getDiskLruCachedir(Context context){
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            return context.getExternalCacheDir();
        }
        return context.getCacheDir();
    }

    public static int getAPPVersion(Context context){
            PackageInfo info = null;
            try {
                info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                return info.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        return 1;
    }

    public static int getDeviceWidth(){
        WindowManager manager= (WindowManager) APP.getInstance().getSystemService(Context.WINDOW_SERVICE);
        int deviceWidth=manager.getDefaultDisplay().getWidth();
        return deviceWidth;
    }

    public static int getDeviceHight(){
        WindowManager manager= (WindowManager) APP.getInstance().getSystemService(Context.WINDOW_SERVICE);
        int deviceHigth=manager.getDefaultDisplay().getHeight();
        return deviceHigth;
    }

    public static PipelineDraweeController getPipelineDraweeController(SimpleDraweeView draweeView,Uri uri, int width, int higth){
        ImageRequest request =
                ImageRequestBuilder.newBuilderWithSource(uri)
                        .setResizeOptions(new ResizeOptions(width,higth))
                        //缩放,在解码前修改内存中的图片大小, 配合Downsampling可以处理所有图片,否则只能处理jpg,
                        // 开启Downsampling:在初始化时设置.setDownsampleEnabled(true)
                        .setProgressiveRenderingEnabled(true)//支持图片渐进式加载
                        .setAutoRotateEnabled(true) //如果图片是侧着,可以自动旋转
                        .build();

        PipelineDraweeController controller =
                (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(draweeView.getController())
                        .setAutoPlayAnimations(true) //自动播放gif动画
                        .build();

        //  draweeView.setLayoutParams(params);

        return controller;
    }

    public static ListConfig getBaseConfig() {
        ListConfig listConfig = new ListConfig();
         listConfig.setRefreshAble(true)
                .setNoMoreAble(false)
                .setLoadmoreAble(false)
                .setErrorAble(true)
                .setContainerEmptyAble(true)
                .setContainerErrorAble(true)
                .setContainerErrorRes(R.layout.view_net_error)
                .setContainerProgressRes(R.layout.page_progress)
                .setContainerEmptyRes(R.layout.view_empty);
                //.setLoadMoreRes(R.layout.page_loadmore);
        return listConfig;
    }

    public static void popUpMyOverflow(Activity context, int height) {
        /**
         * 定位PopupWindow，让它恰好显示在Action Bar的下方。 通过设置Gravity，确定PopupWindow的大致位置。
         * 首先获得状态栏的高度，再获取Action bar的高度，这两者相加设置y方向的offset样PopupWindow就显示在action
         * bar的下方了。 通过dp计算出px，就可以在不同密度屏幕统一X方向的offset.但是要注意不要让背景阴影大于所设置的offset，
         * 否则阴影的宽度为offset.
         */
//         获取状态栏高度
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        状态栏高度：frame.top
//        if(frame==null){
//            Log.e("frame","111");
//        }
        int statusBarHeight = getStatusBarHeight(context);
        int xOffset = frame.top+height+5;//减去阴影宽度，适配UI.
        int yOffset = Dp2Px(context, 5f); //设置x方向offset为5dp
        View parentView = context.getLayoutInflater().inflate(R.layout.activity_main,
                null);
        View popView = context.getLayoutInflater().inflate(
                R.layout.action_overflow_popwindow, null);
        PopupWindow popWind = new PopupWindow(popView,
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, true);//popView即popupWindow的布局，ture设置focusAble.

        //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效。这里在XML中定义背景，所以这里设置为null;
        popWind.setBackgroundDrawable(new BitmapDrawable(context.getResources(),
                (Bitmap) null));
        popWind.setOutsideTouchable(true); //点击外部关闭。
        popWind.setAnimationStyle(android.R.style.Animation_Dialog);    //设置一个动画。
        //设置Gravity，让它显示在右上角。
        popWind.showAtLocation(parentView, Gravity.RIGHT | Gravity.TOP,
                yOffset, xOffset);
    }
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    //获取手机状态栏高度

    public static int getStatusBarHeight(Context context){

        Class<?> c = null;

        Object obj = null;

        Field field = null;

        int x = 0, statusBarHeight = 38;//通常这个值会是38

        try {

            c = Class.forName("com.android.internal.R$dimen");

            obj = c.newInstance();

            field = c.getField("status_bar_height");

            x = Integer.parseInt(field.get(obj).toString());

            statusBarHeight = context.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {

            e1.printStackTrace();

        }

        return statusBarHeight;

    }

}

package com.lqh.news.utils;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.lqh.news.APP;
import com.lqh.news.model.beans.APIStores;

import java.io.File;

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
}

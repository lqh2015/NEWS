package com.lqh.news;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.beam.Beam;

/**
 * Created by liqinghai on 2016/12/26.
 */
public class APP extends Application {
    private static APP instance;

    public static APP getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Beam.init(this);
        Fresco.initialize(this);
    }
}

package com.lqh.news.net;

import com.lqh.news.model.beans.CommonNews;
import com.lqh.news.model.beans.HouseNews;
import com.lqh.news.model.beans.TopNews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by liqinghai on 2016/12/27.
 */
public interface NetSevice {

    //获取头条新闻
    @GET("nc/article/headline/T1348647909107/{startPage}-20.html")
    Observable<TopNews> getTopNews(@Path("startPage") int startPage );

    //获取房产新闻
    @GET("nc/article/house/5YyX5Lqs/{startPage}-20.html")
    Observable<HouseNews> getHouseNews(@Path("startPage") int startPage );

    //获取普通新闻
    @GET("nc/article/list/{id}/{startPage}-20.html")
    Observable<CommonNews> getCommenNews(@Path("id") String id, @Path("startPage") int startPage );
}

package com.lqh.news.net;

import com.lqh.news.model.beans.BeautyImg;
import com.lqh.news.model.beans.HouseNews;
import com.lqh.news.model.beans.TopNews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

//    //获取普通新闻
//    @GET("nc/article/list/{id}/{startPage}-20.html")
//    Observable<CommonNews> getCommenNews(@Path("id") String id, @Path("startPage") int startPage );

    //获取普通新闻
    @GET("nc/article/list/{id}/{startPage}-20.html")
    Observable<TopNews> getCommenNews(@Path("id") String id, @Path("startPage") int startPage );

    //获取美女图片 http://c.m.163.com/recommend/getChanListNews?channel=T1456112189138&size=20&offset=0&fn=1
    @GET("recommend/getChanListNews?channel=T1456112189138&size=20")
    Observable<BeautyImg> getBeautyImg( @Query("offset") int offset );
}

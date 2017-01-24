package com.lqh.news.model;

import com.lqh.news.APP;
import com.lqh.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liqinghai on 2017/1/6.
 */
public class Constants {
    private static List<String> newsIds;
    private static String[] tab_titles;
    public static String getNewsId(int pos){
        if(newsIds==null){
            newsIds=new ArrayList<>();
            newsIds.add("T1348648517839");
            newsIds.add("T1348649079062");
            newsIds.add("T1348648756099");
            newsIds.add("T1348649580692");
            newsIds.add("T1348648650048");
            newsIds.add("T1348654060988");
            newsIds.add("T1350383429665");
            newsIds.add("T1348654151579");
            newsIds.add("T1348650593803");
            newsIds.add("T1348650839000");
            newsIds.add("T1370583240249");
            newsIds.add("T1379038288239");
            newsIds.add("T1348649145984");
            newsIds.add("T1348649776727");
            newsIds.add("T1351233117091");

            newsIds.add("T1356600029035");
            newsIds.add("T1348654225495");
            newsIds.add("T1349837670307");
            newsIds.add("T1348654204705");
            newsIds.add("T1348649654285");
            newsIds.add("T1349837698345");

            newsIds.add("T1348648037603");
            newsIds.add("T1348654105308");
            newsIds.add("T1397016069906");
            newsIds.add("T1397116135282");
            newsIds.add("T1348649475931");
            newsIds.add("T1371543208049");
            newsIds.add("T1348648141035");
        }
        return newsIds.get(pos);
    }

    public static String[] getTab_titles(){
        if (tab_titles==null){
            tab_titles = APP.getInstance().getResources().getStringArray(R.array.tab_title);
        }
        return tab_titles;
    }
}

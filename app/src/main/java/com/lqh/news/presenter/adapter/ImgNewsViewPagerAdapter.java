package com.lqh.news.presenter.adapter;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.lqh.news.R;
import com.lqh.news.model.beans.ImageNews;
import com.lqh.news.utils.Utils;

import java.util.List;

/**
 * Created by liqinghai on 2017/1/23.
 */
public class ImgNewsViewPagerAdapter extends StaticPagerAdapter {

    private SimpleDraweeView draweeView;
    private View view;
    private List<ImageNews.PhotosBean> photos;


    public ImgNewsViewPagerAdapter( List<ImageNews.PhotosBean> photos){

        this.photos=photos;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        view=View.inflate(container.getContext(), R.layout.img_news_viewpager_item,null);
        draweeView= (SimpleDraweeView) view.findViewById(R.id.imgnews_draweeview);
        ImageNews.PhotosBean photosBean = photos.get(position);
        String imgurl = photosBean.getImgurl();
        int width= Utils.getDeviceWidth();
        int higth=width*9/16+200;
        PipelineDraweeController controller = Utils.getPipelineDraweeController(draweeView, Uri.parse(imgurl), width, higth);
        ViewGroup.LayoutParams layoutParams = draweeView.getLayoutParams();
        layoutParams.height=higth;
        layoutParams.width=width;
        //  draweeView.setLayoutParams(params);
        draweeView.setController(controller);
        return view;
    }

    @Override
    public int getCount() {
        return photos.size();
    }
}

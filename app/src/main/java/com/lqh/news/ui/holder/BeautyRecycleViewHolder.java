package com.lqh.news.ui.holder;

import android.net.Uri;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.lqh.news.R;
import com.lqh.news.model.beans.BeautyImg;
import com.lqh.news.utils.Utils;

/**
 * Created by liqinghai on 2016/12/28.
 */
public class BeautyRecycleViewHolder extends BaseViewHolder<BeautyImg.BeautyBean> {
    private SimpleDraweeView draweeView;

    public BeautyRecycleViewHolder(ViewGroup parent) {
        super(parent, R.layout.beauty_img_recycleview_item);
        draweeView=$(R.id.beauty_img_recycle_item_sipDraView);

    }

    @Override
    public void setData(BeautyImg.BeautyBean data) {
        super.setData(data);
        String pixel = data.getPixel();
        String[] pixels=pixel.split("\\*");
        int img_width=Integer.parseInt(pixels[0]);
        int img_higth=Integer.parseInt(pixels[1]);
        int width= Utils.getDeviceWidth()/2-10;
        int higth=width*img_higth/img_width;

        PipelineDraweeController controller = Utils.getPipelineDraweeController(draweeView, Uri.parse(data.getImgsrc()), width, higth);
        ViewGroup.LayoutParams layoutParams = draweeView.getLayoutParams();
        layoutParams.height=higth;
        layoutParams.width=width;
      //  draweeView.setLayoutParams(params);
        draweeView.setController(controller);
    }
}

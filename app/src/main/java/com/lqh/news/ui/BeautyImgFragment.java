package com.lqh.news.ui;

import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.lqh.news.R;
import com.lqh.news.presenter.ImageListPresenter;


@RequiresPresenter(ImageListPresenter.class)
public class BeautyImgFragment extends BeamFragment<ImageListPresenter> {

    private EasyRecyclerView recyclerView;

    public BeautyImgFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        recyclerView= (EasyRecyclerView) view.findViewById(R.id.easy_recyclerview);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        SpaceDecoration itemDecoration = new SpaceDecoration(8);//参数是距离宽度
        itemDecoration.setPaddingEdgeSide(false);
        recyclerView.addItemDecoration(itemDecoration);
        return view;
    }

    public EasyRecyclerView getRecyclerView(){
        return recyclerView;
    }

}

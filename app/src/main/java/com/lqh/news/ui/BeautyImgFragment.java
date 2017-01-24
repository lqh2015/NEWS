package com.lqh.news.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
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
    private FloatingActionButton button;
    private static int startdy;

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
        recyclerView.setRefreshingColor(R.color.colorAccent);
        recyclerView.setRefreshingColorResources(R.color.colorAccent);
        button= (FloatingActionButton) view.findViewById(R.id.fab);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.scrollToPosition(0);
            }
        });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dx<startdy){
                    button.setVisibility(View.GONE);
                }else{
                    button.setVisibility(View.VISIBLE);
                }
                startdy=dy;
            }
        });
        button.setVisibility(View.GONE);
        return view;
    }

    public EasyRecyclerView getRecyclerView(){
        return recyclerView;
    }

}

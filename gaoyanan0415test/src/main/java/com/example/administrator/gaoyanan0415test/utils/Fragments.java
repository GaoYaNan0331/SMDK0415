package com.example.administrator.gaoyanan0415test.utils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.xlistviewlibrary.XListView;
import com.example.administrator.gaoyanan0415test.R;
import com.example.administrator.gaoyanan0415test.beans.JsonsBean;
import com.example.administrator.gaoyanan0415test.beans.UrlBean;

import java.util.List;
/**
 * data:2017/4/15
 * author:高亚男(Administrator)
 * function:
 */

public class Fragments extends Fragment implements MyAsyncTask.GetList{
    private XListView xlv;
    private String url;
    private MyBase base;

    public Fragments(String url) {
        this.url = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragments,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        xlv= (XListView) getView().findViewById(R.id.xlv);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        initData();
    }

    private void initData() {
        MyAsyncTask task = new MyAsyncTask(getActivity(), xlv, 2);

        task.execute(UrlUtils.PATH+url);
        task.getData(this);

    }

    @Override
    public void getTitle(List<UrlBean.ResultBean.DateBean> analysis) {

    }

    @Override
    public void getCent(List<JsonsBean.ResultBean.DataBean> analysis) {
        base=new MyBase(getActivity(),analysis);
        xlv.setAdapter(base);
    }
}

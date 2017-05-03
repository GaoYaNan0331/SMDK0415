package com.example.administrator.gaoyanan0415test.utils;
import com.example.administrator.gaoyanan0415test.beans.UrlBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
/**
 * data:2017/4/15
 * author:高亚男(Administrator)
 * function:
 */

public class AnalysisData  {

    private List<UrlBean.ResultBean.DateBean> list=new ArrayList<>();
    public List<UrlBean.ResultBean.DateBean> analysis(String json){
        Gson gson=new Gson();
        UrlBean urlBean = gson.fromJson(json, UrlBean.class);
        List<UrlBean.ResultBean.DateBean> dates = urlBean.getResult().getDate();
        for (int i=0;i<dates.size();i++){
            UrlBean.ResultBean.DateBean date = new UrlBean.ResultBean.DateBean();
            date.setTitle(dates.get(i).getTitle());
            date.setUri(dates.get(i).getUri());
            list.add(date);
        }
        return list;
    }
}

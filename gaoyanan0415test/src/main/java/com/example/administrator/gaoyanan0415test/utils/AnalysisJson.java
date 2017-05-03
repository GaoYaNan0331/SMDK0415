package com.example.administrator.gaoyanan0415test.utils;

import com.example.administrator.gaoyanan0415test.beans.JsonsBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
/**
 * data:2017/4/15
 * author:高亚男(Administrator)
 * function:解析json串的功能
 */

public class AnalysisJson {
    private List<JsonsBean.ResultBean.DataBean> list=new ArrayList<>();
    public List<JsonsBean.ResultBean.DataBean> analysisData(String json){
        Gson gson=new Gson();
        JsonsBean bean = gson.fromJson(json, JsonsBean.class);

        List<JsonsBean.ResultBean.DataBean> datas = bean.getResult().getData();
        if (datas!=null) {
            for (int i = 0; i < datas.size(); i++) {
                JsonsBean.ResultBean.DataBean data = new JsonsBean.ResultBean.DataBean();
               //得到所需内容，标题，
                data.setAuthor_name(datas.get(i).getAuthor_name());
                data.setTitle(datas.get(i).getTitle());
                data.setThumbnail_pic_s(datas.get(i).getThumbnail_pic_s());
                list.add(data);
            }
        }
            return list;

    }
}


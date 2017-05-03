package com.example.administrator.gaoyanan0415test.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.bawei.xlistviewlibrary.XListView;
import com.example.administrator.gaoyanan0415test.beans.JsonsBean;
import com.example.administrator.gaoyanan0415test.beans.UrlBean;

import java.util.ArrayList;
import java.util.List;
/**
 * data:2017/4/15
 * author:高亚男(Administrator)
 * function:设置 MyAsyncTask的操作
 */

public class MyAsyncTask extends AsyncTask<String,Integer,String> {
    private Context context;
    private ViewPager vp;
    private TabLayout tab;
    private int num;
    private MypagerAdapter adapter;
    public  GetList getList;
    private XListView xlv;

    /**
     *
     * @param context
     * @param xlv
     * @param num
     * 设置构造方法，用来传值
     */
    public MyAsyncTask(Context context,XListView xlv,int num){
        this.context = context;
        this.xlv=xlv;
        this.num=num;
    }
    public MyAsyncTask(Context context, ViewPager vp,TabLayout tab,int num) {
        this.context = context;
        this.vp = vp;
        this.tab=tab;
        this.num=num;
    }

    /**
     *
     * @param params
     * @return
     * 重写4个方法
     *
     * 在onPreExcuse执行完后，立即执行，用于较为耗时的操作，
     * 此方法将接受输入参数和返回结果，在执行过程当中，
     * 可以调用publishProgress用来更新进度条的进度，
     */
    @Override
    protected String doInBackground(String... params) {
        //设置
        String path=params[0];
        //获取url的地址
        String json = new HttpUrlConUtils().doget(path);
        return json;
    }

    /**
     *
     * @param s
     *
     * 在调用publishProgress（Progress...values）时，
     * 此方法被执行,直接将进度条更新到UI的组件上；
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s!=null){
            if (num==1){
                //解析的json串，用来更新数据做一些耗时的操作
            List<UrlBean.ResultBean.DateBean> analysis = new AnalysisData().analysis(s);
            getList.getTitle(analysis);
        }else{

                List<JsonsBean.ResultBean.DataBean> dataBeen = new AnalysisJson().analysisData(s);
                getList.getCent(dataBeen);
            }

        }
    }

    public interface GetList{
       public void getTitle(List<UrlBean.ResultBean.DateBean> analysis);
        public void getCent(List<JsonsBean.ResultBean.DataBean> analysis);
    }
    public void getData(GetList getList){
        this.getList=getList;
    }
}

package com.example.administrator.gaoyanan0415test;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.gaoyanan0415test.beans.JsonsBean;
import com.example.administrator.gaoyanan0415test.beans.UrlBean;
import com.example.administrator.gaoyanan0415test.utils.Fragments;
import com.example.administrator.gaoyanan0415test.utils.MyAsyncTask;
import com.example.administrator.gaoyanan0415test.utils.MypagerAdapter;
import com.example.administrator.gaoyanan0415test.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAsyncTask.GetList {
    private TabLayout tab;
    private ViewPager vp;
    private List<String> mTile=new ArrayList<>();
    private List<Fragment> mlist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
//实例化自定义的MyAsyncTask
        MyAsyncTask task = new MyAsyncTask(this, vp, tab,1);
        //执行一个异步任务，需要我们在代码中代码中调用此方法，触发异步任务的执行；
        task.execute(UrlUtils.PATH2);
        task.getData(this);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tab= (TabLayout) findViewById(R.id.tab);
        vp= (ViewPager) findViewById(R.id.vp);
    }

    /**
     *
     * @param analysis
     * 设置标题和内容的操作
     */
    @Override
    public void getTitle(List<UrlBean.ResultBean.DateBean> analysis) {
        for (int i=0;i<analysis.size();i++){
            mTile.add(analysis.get(i).getTitle());
            vp.setCurrentItem(i);
        }
        /**
         *
         */
        for (int y=0;y<analysis.size();y++){
            if (!analysis.get(y).getUri().equals("bg")) {
                mlist.add(new Fragments(analysis.get(y).getUri()));
            }
        }
        vp.setAdapter(new MypagerAdapter(getSupportFragmentManager(),this,mlist,mTile));
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        tab.setupWithViewPager(vp);

    }

    @Override
    public void getCent(List<JsonsBean.ResultBean.DataBean> analysis) {

    }

}


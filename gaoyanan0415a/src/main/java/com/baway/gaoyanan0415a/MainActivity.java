package com.baway.gaoyanan0415a;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.baway.gaoyanan0415a.Adpter.MyCBaseAdpter;
import com.baway.gaoyanan0415a.Beans.Beans;
import com.baway.gaoyanan0415a.Utils.UrlUtils;
import com.baway.gaoyanan0415a.myfragment.MyFragment;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView chbutton;
    private List<Beans.DataBean> clist;
    private List<MyFragment> fragments;
    private List<String> urls;
    private ListView clv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        chbutton = (ImageView) findViewById(R.id.cehua);
        clist = new ArrayList<Beans.DataBean>();
        urls = new ArrayList<String>();
        //解析json串
        jxcdata();
        //添加地址
        urls.add(UrlUtils.irl2);
        urls.add(UrlUtils.url3);
        urls.add(UrlUtils.url4);
        //定义一个存放fragment的集合
        fragments = new ArrayList<>();
        //循环添加数据
        for (int i =0;i<urls.size();i++){
             fragments.add(new MyFragment(urls.get(i)));
        }
    }

    /**
     * 利用   Handler解析json传入的数据
     */
    public void jxcdata(){
        Handler mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    String messges = (String) msg.obj;
                    Gson gson = new Gson();
                    Beans beans = gson.fromJson(messges, Beans.class);
                    List<Beans.DataBean> data = beans.getData();
                    //循环添加数据
                    for (int i = 0; i < data.size(); i++) {
                        Beans.DataBean bd = new Beans.DataBean();
                        String name = data.get(i).getName();
                        bd.setName(name);
                        clist.add(bd);
                    }
                }
                //设置适配器
                clv.setAdapter(new MyCBaseAdpter(MainActivity.this, clist));
            }

        };
        //设置HttpConnUtils获取地址
        new HttpConnUtils(UrlUtils.url,mhandler).start();

    }

    /**
     * 初始化数据并对SlidingMenu设置一些属性
     */
    private void initView() {
        //实例化 SlidingMenu对象
        final SlidingMenu slidingMenu = new SlidingMenu(this);
        //设置slidingMenu的位置
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置slidingMenu触摸的方向
        slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);
        //侧滑设置方法
        int width = getPing();
        //设置宽度的占比
        slidingMenu.setBehindOffset(width/4);
        //设置slidingMenu所能包含的内容
        slidingMenu.attachToActivity(MainActivity.this,SlidingMenu.SLIDING_CONTENT);
        //设置布局
        slidingMenu.setMenu(R.layout.cehua_activity);
        //调用slidingMenu设置监听事件的方法
        chbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenu.toggle();
            }
        });
        //找到控件
        clv = (ListView) slidingMenu.findViewById(R.id.clv);
        //设置监听事件

        clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //利用管理类添加fragment
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                FragmentTransaction replace = transaction.replace(R.id.frame, fragments.get(i));
                transaction.commit();
            }
        });
    }
    //侧滑设置方法
    public int getPing(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }
}

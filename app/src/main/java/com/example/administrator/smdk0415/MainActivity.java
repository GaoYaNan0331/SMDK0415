package com.example.administrator.smdk0415;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

import static android.R.attr.country;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bu = (Button) findViewById(R.id.bu);
        Button mSettingBtn = (Button) findViewById(R.id.btn_setting);
        bu.setOnClickListener(this);
        mSettingBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.bu:

                //初始化SMDK的sdk
                SMSSDK.initSDK(this, "1cf74badd33ac", "4a3a2074d4dc98b9c2f8608f9de6e7ba");
                //创建默认界面
                RegisterPage registerPage = new RegisterPage();
                //注册绑定 回调函数
                registerPage.setRegisterCallback(new EventHandler(){
                    //重写4个方法
                    //注册监听是触发
                    @Override
                    public void onRegister() {
                        super.onRegister();

                    }
                    //操作执行之前的触发
                    @Override
                    public void beforeEvent(int i, Object o) {
                        super.beforeEvent(i, o);


                    }
                    //操作结束时触发（即相当于输入了手机号并进行啦点击的事件后）
                    @Override
                    public void afterEvent(int i, int i1, Object o) {
                        super.afterEvent(i, i1, o);
                        //当验证成功的时候对其结果集进行判断
                        if(i1==SMSSDK.RESULT_COMPLETE){
                            //获取结果集设置为hashmap
                            HashMap<String,Object> phonehash= (HashMap<String, Object>) o;
                            //通过hashmap获取结果集当中的ke和yvalues值
                            String count= (String) phonehash.get("count");
                            String phone= (String) phonehash.get("phone");

                            //提交用户信息
                            registerUser(country, phone);


                        }else if(i1==SMSSDK.RESULT_ERROR){


                        }

                    }

                    private void registerUser(int country, String phone) {



                    }

                    @Override
                    public void onUnregister() {
                        super.onUnregister();


                    }

                });

                //跳转到默认的界面
                registerPage.show(MainActivity.this);

                break;
            case R.id.btn_setting:
                MainActivity.this.startActivity(new Intent(MainActivity.this, ChangeTheme.class));

                break;

        }


    }
    }


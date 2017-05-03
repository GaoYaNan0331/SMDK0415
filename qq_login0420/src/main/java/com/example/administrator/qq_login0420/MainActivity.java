package com.example.administrator.qq_login0420;

import android.app.Notification;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEdit_deng;
    private EditText mEdit_mima;
    private EditText mEdit_pfwd;
    private EditText mEdit_yofu;
    private Button mBu_zhu;
    private String mStr_deng;
    private String mStr_mfima;
    private String mStr_pwd;
    private String mStr_you;
    private Button mBu_deng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
      }
    private void initView() {
        mEdit_deng = (EditText) findViewById(R.id.edit_deng);
        mEdit_mima = (EditText) findViewById(R.id.edit_mima);
        mEdit_pfwd = (EditText) findViewById(R.id.edit_pwd);
        mEdit_yofu = (EditText) findViewById(R.id.edit_you);
        mBu_zhu = (Button) findViewById(R.id.bu_zhu);
        mBu_deng = (Button) findViewById(R.id.bu_deng);
        mBu_zhu.setOnClickListener(this);
        mBu_deng.setOnClickListener(this);

    }

    /**
         * 邮箱判断方法
         * @param url
         * @return
         */
        public static boolean isEmailAddress(String url) {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(url);
            return matcher.matches();
        }
//    password=fanyanlong&client=android&username=18611352750
//    password=fan1991&password_confirm=fan1991&client=android&email=fan111@qq.com&username=17600050562
    public Object httpDatas(String sd,String sm,String sp,String sy){
        RequestParams params=new RequestParams(Url.LINK_MOBILE_REG);
        params.addQueryStringParameter("userName",sd);
        params.addQueryStringParameter("userPwd1",sm);
        params.addQueryStringParameter("userPwd2",sp);
        params.addQueryStringParameter("userYou",sy);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(MainActivity.this, "返回的结果"+result, Toast.LENGTH_SHORT).show();
                Log.d("TAG","onSuccess "+result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });



        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bu_zhu:
                mStr_deng = mEdit_deng.getText().toString().trim();
                mStr_mfima = mEdit_mima.getText().toString().trim();
                mStr_pwd = mEdit_pfwd.getText().toString().trim();
                mStr_you = mEdit_yofu.getText().toString().trim();
                if(TextUtils.isEmpty( mStr_deng)){
                    Toast.makeText(this, "账号为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mStr_mfima)){
                    Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mStr_pwd)){
                    Toast.makeText(this, "请再次确认密码是否为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mStr_you)){
                    Toast.makeText(this, "邮箱密码为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
//            if(!isEmailAddress(mStr_you)){
//                Toast.makeText(MainActivity.this, "邮箱格式错误", Toast.LENGTH_SHORT).show();
//                return;
//            }
                }

                getDataToVolley(mStr_deng,mStr_mfima, mStr_pwd,mStr_you);
            break;
            case R.id.bu_deng:


                break;
        }

    }
    public void getDataToVolley(String zh,String mm,String mm2,String yx){
        HashMap<String, String> map = new HashMap<>();

        map.put("username",zh);
        map.put("password",mm);
        map.put("password_confirm",mm2);
        map.put("email",yx);
        map.put("client","android");

        volleyUtils.Send(this,Url.LINK_MOBILE_REG,map);

    }

    }



package com.example.administrator.threedemo0417;

import android.content.Context;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Tencent mTencent;

    /** 你自己应用的app id */
    private String mAppid = "1105602574";

    /** 回调接口 */
    private IUiListener loginListener = new BaseUiListener();

    /** 获取用户信息的辅助类 */
    private UserInfo mInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_user_info).setOnClickListener(this);

        // 初始化实例
        if (mTencent == null) {
            mTencent = Tencent.createInstance(mAppid, this);
        }
    }

    /**
     * 为了正常回调，获取结果，必须写上这段代码
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_user_info:
                getUserInfo();
                break;

            default:
                break;
        }
    }

    /**
     * TODO
     * 获取用户个人信息
     */
    private void getUserInfo() {
        if (ready(this)) {
            mInfo = new UserInfo(this, mTencent.getQQToken());
            mInfo.getUserInfo(new BaseUiListener(){
                @Override
                protected void doComplete(Object values) {
                    JSONObject response = (JSONObject) values;
                    Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    /**
     * 条件是否允许获取用户信息
     * @param context
     * @return
     */
    private boolean ready(Context context) {
        if (mTencent == null) {
            return false;
        }
        boolean ready = mTencent.isSessionValid() && mTencent.getQQToken().getOpenId() != null;
        if (!ready) {
            Toast.makeText(context, "请先进行授权",Toast.LENGTH_SHORT).show();
        }
        return ready;
    }

    /**
     * 登录
     */
    private void login() {
        mTencent = Tencent.createInstance(mAppid, this);
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, "all", loginListener);
        }
    }

    private class BaseUiListener implements IUiListener {
        @Override
        public void onComplete(Object response) {
            Log.e("QQLogin", "onComplete:" + response.toString());
            doComplete(response);
        }

        protected void doComplete(Object values) {
            initOpenidAndToken(values);
        }

        @Override
        public void onError(UiError e) {
            Log.e("QQLogin", "onError:" + "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);
        }

        @Override
        public void onCancel() {
            Log.e("QQLogin", "onCancel");
        }
    }

    private void initOpenidAndToken(Object values) {
        JSONObject jsonObject = (JSONObject) values;
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                // 设置Token和OpenId
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }

            Toast.makeText(getApplicationContext(), "token=" + token + ", openId=" + openId + ", expires=" + expires, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

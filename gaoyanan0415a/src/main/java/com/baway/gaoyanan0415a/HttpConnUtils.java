package com.baway.gaoyanan0415a;

import android.os.Handler;
import android.os.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * author: Administrator
 * date: 2017/4/15.
 * 获取网络数据
 */

public class HttpConnUtils extends Thread {
    private String result = "";
    private String url;
    private Handler myhandler;

    public HttpConnUtils(String url, Handler myhandler) {
        this.url = url;
        this.myhandler = myhandler;
    }

    @Override
    public void run() {
        super.run();
        try {
            String doget = Doget(url);
            Message msg = new Message();
            msg.what=1;
            msg.obj=doget;
            myhandler.sendMessage(msg);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public String Doget(String url) throws MalformedURLException {
        HttpURLConnection connection = null;
        URL myurl = new URL(url);
        try {
            connection = (HttpURLConnection) myurl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while((line=reader.readLine())!=null){
                result+=line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                connection.disconnect();
            }
        }
        return result;
    }
}

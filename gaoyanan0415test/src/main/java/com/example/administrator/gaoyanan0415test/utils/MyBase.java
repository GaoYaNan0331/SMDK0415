package com.example.administrator.gaoyanan0415test.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.gaoyanan0415test.R;
import com.example.administrator.gaoyanan0415test.beans.JsonsBean;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
/**
 * data:2017/4/15
 * author:高亚男(Administrator)
 * function:设置适配器的功能
 */

public class MyBase extends BaseAdapter {
    private Context context;
    private List<JsonsBean.ResultBean.DataBean> list;

    public MyBase(Context context, List<JsonsBean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mybase,null);
            holder=new ViewHolder();
            holder.text= (TextView) convertView.findViewById(R.id.text1);
            holder.text2= (TextView) convertView.findViewById(R.id.text2);
            holder.imageView= (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.text.setText(list.get(position).getTitle());
        holder.text2.setText(list.get(position).getAuthor_name());
        ImageLoader.getInstance().displayImage(list.get(position).getThumbnail_pic_s(),holder.imageView);
        return convertView;
    }
    class ViewHolder{
        private ImageView imageView;
        private TextView text,text2;
    }
}

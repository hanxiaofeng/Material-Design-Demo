package com.wangkeke.mdsimple;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wangkeke on 2017/2/13.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.Myholder> {

    private Context mContext;
    private String[] strs = new String[100];
    public MyRecyclerAdapter(Context context) {
        this.mContext = context;
        //为测试给Recycler添加数据
        for (int i = 0; i < 50; i++) {
            strs[i] = "item "+i;
        }
    }
    //这里返回一个ViewHolder
    @Override
    public MyRecyclerAdapter.Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item, null);
        Myholder myholder = new Myholder(view);
        return myholder;
    }
    //为ViewHolder中的布局绑定数据
    @Override
    public void onBindViewHolder(Myholder holder, int position) {
        holder.textView.setText(strs[position]);
    }
    @Override
    public int getItemCount() {
        return strs.length;
    }
    static class Myholder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_text)
        TextView textView;
        public Myholder(View itemView) {
            super(itemView);
            //ButterKnife也可以用于ViewHoder中
            ButterKnife.bind(this, itemView);
        }
    }
}

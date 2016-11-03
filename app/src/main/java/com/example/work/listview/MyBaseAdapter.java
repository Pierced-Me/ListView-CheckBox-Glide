package com.example.work.listview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by qxc on 2016/11/1.
 */
public class MyBaseAdapter extends BaseAdapter {

    private MainActivity mainActivity;
    private ArrayList<MyItem> dataList;
    private boolean isHide;
    private ArrayList<MyItem> dataCopyList =new ArrayList();

    public MyBaseAdapter(MainActivity mainActivity, ArrayList<MyItem> dataList, boolean isHide) {
        this.mainActivity = mainActivity;
        this.dataList = dataList;
        dataCopyList.addAll(dataList);
        this.isHide = isHide;
    }

    public void check() {
        dataCopyList.clear();
        dataCopyList.addAll(dataList);
        if (isHide){
            ArrayList<MyItem> delList = new ArrayList<>();
            for (MyItem myItem:dataList){
                if (myItem.isChecked){
                    delList.add(myItem);
                }
            }
            for (MyItem myItem:delList){
                    dataCopyList.remove(myItem);
            }

        }
        notifyDataSetChanged();
    }

    public void setHide(boolean isHide) {
        this.isHide = isHide;
    }

    @Override
    public int getCount() {
        return dataCopyList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataCopyList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(mainActivity, R.layout.lv_item, null);
            holder.imageView = (ImageView) view.findViewById(R.id.item_iv);
            holder.checkBox = (CheckBox) view.findViewById(R.id.item_cb);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final MyItem myItem = dataCopyList.get(i);
        Glide.with(mainActivity).load(myItem.name).asGif().placeholder(R.color.colorPrimary).into(holder.imageView);

//        holder.checkBox.setOnCheckedChangeListener(null);
//        holder.checkBox.setChecked(myItem.isChecked);
        /*holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myItem.isChecked = !myItem.isChecked;
                check();
            }
        });*/

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                myItem.isChecked = b;
                check();
            }
        });
        holder.checkBox.setChecked(myItem.isChecked);

        return view;
    }

    class ViewHolder {
        ImageView imageView;
        CheckBox checkBox;
    }

}

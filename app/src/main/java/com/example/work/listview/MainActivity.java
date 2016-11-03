package com.example.work.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isHide = false;
    private MyBaseAdapter adapter;
    private ArrayList<MyItem> dataList;
    private Button hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button all = (Button) findViewById(R.id.main_bt_all);
        Button clear = (Button) findViewById(R.id.main_bt_clear);
        hide = (Button) findViewById(R.id.main_bt_hide);

        all.setOnClickListener(this);
        clear.setOnClickListener(this);
        hide.setOnClickListener(this);

        String[] images = {
                "http://images.17173.com/2013/news/2013/12/16/d1216t07.gif",
                "http://imgsrc.baidu.com/forum/w%3D580/sign=2fb84a33eb50352ab16125006342fb1a/b9389b504fc2d562907e9569e51190ef76c66c3b.jpg",
                "http://s1.dwstatic.com/group1/M00/D2/E9/d5b71fea6a8c903c3333f14b16d284be.gif",
                "http://images.17173.com/2014/news/2014/02/24/g0224if14.gif",
                "http://s1.dwstatic.com/group1/M00/93/7C/a8d4d483bdc26890adeac9186c7fd960.gif",
                "http://images.17173.com/2013/news/2013/12/23/9.gif",
                "http://images.17173.com/2014/news/2014/03/10/g0310if12.gif",
                "http://imgsrc.baidu.com/forum/w%3D580/sign=a429b58c7ed98d1076d40c39113eb807/bf7ece0735fae6cdf17d88110db30f2443a70f4d.jpg",
                "http://s1.dwstatic.com/group1/M00/7F/B6/323ee7150dcd1ee394a470d3c3c4a27b.gif",
                "http://att.bbs.duowan.com/forum/201408/05/1557168h00r0vkxf33jq13.gif",
        };

        dataList = new ArrayList<MyItem>();
        for (int i = 0; i < images.length; i++){
            dataList.add(new MyItem(images[i], false));
        }

        ListView main_lv = (ListView) findViewById(R.id.main_lv);
        adapter = new MyBaseAdapter(this, dataList, isHide);
        main_lv.setAdapter(adapter);
        main_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                adapter.check();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_bt_all:
                check(true);
                adapter.check();
                break;
            case R.id.main_bt_clear:
                check(false);
                adapter.check();
                break;
            case R.id.main_bt_hide:
                if (isHide){
                    hide.setText("隐藏已选");
                }else {
                    hide.setText("取消已选");
                }
                isHide = !isHide;
                adapter.setHide(isHide);
                adapter.check();
                break;
        }
    }

    private void check(boolean flag) {
        for (MyItem myItem:dataList){
            myItem.isChecked = flag;
        }
        adapter.check();
    }
}

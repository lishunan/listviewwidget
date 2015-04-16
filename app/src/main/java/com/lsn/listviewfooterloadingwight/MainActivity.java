package com.lsn.listviewfooterloadingwight;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;

import com.lsn.listviewfooterloadingwight.widget.ListViewFooterLoading;

import java.util.ArrayList;
import java.util.logging.Handler;


public class MainActivity extends ActionBarActivity {

    private ListViewFooterLoading mlitView;
    private ArrayList<String> date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        //为方便，使用ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, date);

        mlitView.setAdapter(adapter);

        //实现自定义接口方法
        mlitView.setmOnLoadingListener(new ListViewFooterLoading.OnLoadingListener() {
            @Override
            public void onLoading() {

                //加载数据
                date.add("aaaaaa");

                //数据加载完成后，调用此方法。勿忘！！！！！！！！！！！！
                mlitView.setLoadFinish();


            }
        });
    }

    /**
     * 初始化孩儿们
     */
    private void init() {

        //初始化自定义ListView
        mlitView = (ListViewFooterLoading) findViewById(R.id.listview);

        //添加数据
        date = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            date.add(i + "");
        }


    }


}

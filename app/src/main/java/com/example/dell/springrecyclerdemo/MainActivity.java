package com.example.dell.springrecyclerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MyAdapter.OnItemClickListener{

    private SpringView springView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private int start = 0;
    private List<User> list = new ArrayList<>();
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData(start);

        springView = (SpringView) findViewById(R.id.spring_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        if (list != null && list.size() > 0) {
            adapter = new MyAdapter(this, list);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(this);
        }

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        initData(start);
                        adapter.notifyDataSetChanged();
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (linearLayoutManager.findLastVisibleItemPosition() == list.size() - 1){
                            start = start + 20;
                            initData(start);
                            adapter.notifyDataSetChanged();
                        }
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));
    }

    private void initData(int number) {
        for (int i = number; i < number + 20; i++) {
            User user = new User();
            user.setName("张" + i);
            list.add(user);
        }
    }

    @Override
    public void onItemClickListener(int position, View view) {
        Toast.makeText(this, "" + list.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClickListener(int position, View view) {

    }
}

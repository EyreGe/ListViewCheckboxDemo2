package com.example.listviewcheckboxdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;
    GroupAdapter adapter;
    private ArrayList<String> groups;
    private ArrayList<Boolean> checkList = new ArrayList(); // 判断listview单选位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) this.findViewById(R.id.list);
        groups = new ArrayList<String>();
        groups.add("11");
        groups.add("22");
        groups.add("33");
        groups.add("44");
        groups.add("55");
        groups.add("66");
        groups.add("77");

        for (int i = 0; i < groups.size(); i++) {
            checkList.add(false); // 均为未选
        }

        adapter = new GroupAdapter(this, groups, checkList);
        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(listItemClickListener);

        adapter.setTaskItemClickListener(new GroupAdapter.OnTaskItemClickListener() {
            @Override
            public void onTaskItemClickListener(int position) {

            }
        });
    }

//    //设置选中的位置，将其他位置设置为未选
//    public void checkPosition(int position) {
//        for (int i = 0; i < checkList.size(); i++) {
//            if (position == i) {// 设置已选位置
//                checkList.set(i, true);
//            } else {
//                checkList.set(i, false);
//            }
//        }
//        adapter.notifyDataSetChanged();
//    }

    AdapterView.OnItemClickListener listItemClickListener=new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
//            //取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
//            GroupAdapter.ViewHolder viewHolder=(GroupAdapter.ViewHolder)view.getTag();
//            viewHolder.cb.toggle();// 把CheckBox的选中状态改为当前状态的反,gridview确保是单一选中
        }
    };
}
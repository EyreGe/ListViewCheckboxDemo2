package com.example.listviewcheckboxdemo;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class GroupAdapter extends BaseAdapter {
    private Activity activity;//上下文
    private ArrayList<String> list;
    private ArrayList<Boolean> checkList;

    private LayoutInflater inflater=null;//导入布局
    private int temp=-1;

    public GroupAdapter(Activity context, ArrayList<String> list, ArrayList<Boolean> checkList) {
        this.activity = context;
        this.list = list;
        this.checkList = checkList;
        inflater = LayoutInflater.from(context);

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
        // TODO Auto-generated method stub
        return position;
    }

    //listview每显示一行数据,该函数就执行一次
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null) {//当第一次加载ListView控件时  convertView为空
            convertView = inflater.inflate(R.layout.listview_item, null);//所以当ListView控件没有滑动时都会执行这条语句
            holder=new ViewHolder();
            holder.tv=(TextView)convertView.findViewById(R.id.item_tv);
            holder.cb=(CheckBox)convertView.findViewById(R.id.item_cb);
            convertView.setTag(holder);//为view设置标签

        }
        else{//取出holder
            holder=(ViewHolder) convertView.getTag();//the Object stored in this view as a tag
        }
//        //设置list的textview显示
//        holder.tv.setTextColor(Color.WHITE);
//        holder.tv.setText(list.get(position));
//        // 根据isSelected来设置checkbox的选中状况
//
//        holder.cb.setId(position);//对checkbox的id进行重新设置为当前的position
//        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            //把上次被选中的checkbox设为false
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){//实现checkbox的单选功能,同样适用于radiobutton
//                    if(temp!=-1){
//                        //找到上次点击的checkbox,并把它设置为false,对重新选择时可以将以前的关掉
//                        CheckBox tempCheckBox=(CheckBox)activity.findViewById(temp);
//                        if(tempCheckBox!=null)
//                            tempCheckBox.setChecked(false);
//                    }
//                    temp=buttonView.getId();//保存当前选中的checkbox的id值
//                }
//            }
//        });
//        //System.out.println("temp:"+temp);
//        //System.out.println("position:"+position);
//        if(position==temp)//比对position和当前的temp是否一致
//            holder.cb.setChecked(true);
//        else
//            holder.cb.setChecked(false);

        holder.cb.setChecked(checkList.get(position));
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {//单击checkbox实现单选，根据状态变换进行单选设置
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        Log.e("设置选中的位置", "onCheckedChanged: " + position);
                        // TODO Auto-generated method stub
                        if (isChecked) {
                            checkPosition(position);
                        } else {
                            checkList.set(position, false);//将已选择的位置设为选择
                        }
                    }
                });

        convertView.setOnClickListener(new View.OnClickListener() {//item单击进行单选设置
            @Override
            public void onClick(View v) {
                Log.e("设置选中的位置", "onClick: " + position);
                // TODO Auto-generated method stub
//                checkPosition(position);
            }
        });

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("设置选中的位置", "cb onClick: " + position + " , " + checkList.get(position));
//                checkPosition(position);

                if (!checkList.get(position)) { //false
                    ViewHolder view = (ViewHolder) v.getTag();
                    Log.e("设置选中的位置", "view: " + view);
                    if (view != null) {
                        view.cb.toggle();
                    }
                    checkList.set(position, false);
                    notifyDataSetChanged();
                } else {
                    checkPosition(position);
                }
            }
        });

        return convertView;
    }

    //设置选中的位置，将其他位置设置为未选
    public void checkPosition(int position) {
        for (int i = 0; i < checkList.size(); i++) {
            if (position == i) {// 设置已选位置
                checkList.set(i, true);
            } else {
                checkList.set(i, false);
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        TextView tv;
        CheckBox cb;
    }

    /**
     * 点击回调
     */
    public interface OnTaskItemClickListener {
        void onTaskItemClickListener(int position);
    }

    public OnTaskItemClickListener mOnTaskItemClickListener;

    public void setTaskItemClickListener(OnTaskItemClickListener listener) {
        mOnTaskItemClickListener = listener;
    }
}

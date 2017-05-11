package com.example.suny.ecard.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.suny.ecard.R;
import com.example.suny.ecard.database.WeekData;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by suny on 2017/4/29.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MyViewHolder> {

    private List<WeekData> mWeekDatas;
    private LayoutInflater mLayoutInflater;

    public RecyAdapter(List<WeekData> weekDatas, Context context) {

        this.mWeekDatas = weekDatas;
        mLayoutInflater = LayoutInflater.from(context);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(mLayoutInflater.inflate(R.layout.item_card, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_cost.setText(mWeekDatas.get(position).getCost());
        holder.tv_data.setText(mWeekDatas.get(position).getData());
        holder.tv_loca.setText(mWeekDatas.get(position).getLocation());

    }

    @Override
    public int getItemCount() {
        return mWeekDatas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cost;
        TextView tv_loca;
        TextView tv_data;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_cost = (TextView) itemView.findViewById(R.id.tv_cost);
            tv_loca = (TextView) itemView.findViewById(R.id.tv_location);
            tv_data = (TextView) itemView.findViewById(R.id.tv_data);


        }
    }
}

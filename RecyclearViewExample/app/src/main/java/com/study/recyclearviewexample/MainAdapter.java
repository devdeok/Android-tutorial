package com.study.recyclearviewexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolde> {

    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // listView 메뉴가 처음으로 생성될 때 lifecycle
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        CustomViewHolde holder = new CustomViewHolde(view);

        return holer;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolde holder, int position) {
        holder.iv_profile.setImageResource(arrayList.get(position).getTv_name());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_content.setText(arrayList.get(position).getTv_content());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // curName의 값이 현재 recyclerView의 item 중 Click한 data을 가져옴
                String curName = holder.tv_name.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CustomViewHolde extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;

        public CustomViewHolde(View itemView) {
            super(itemView);
            this.iv_profile = (ImageView)itemView.findViewById(R.id.iv_profile);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

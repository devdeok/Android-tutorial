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

/* Adapter란?
 * Data table을 list 형태로 보여주기 위해 사용되는 것으로 data를 다양한 형식의 list형식으로 보여주기 위해서
 * data와 listView 사이에 존재하는 Object이다. 즉, data와 listView 사이의 bridge 역할을 한다.
 * Android에서 data를 customized listView로 표현하는데 있어서 매우 좋은 방식이다.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    // dynamic하게 data를 받아줄 arrayList
    private ArrayList<MainData> arrayList;

    // constructor
    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // listView 메뉴가 처음으로 생성될 때 lifecycle
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {
        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
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

        // 길게 클릭할 경우 삭제되는 기능
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // Adapter에서 LongClick을 눌렀을 때 ListView를 삭제
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    // arrayList size를 반환하는 method
    @Override
    public int getItemCount() {
        // arrayList의 size를 return해주는 method
        // arrayList가 null이 아니면 arrayList의 size를 return하고 맞다면 0을 return
        // 쉽게 설명하면 arrayList에 data가 있으면 arrayList size를 return해주고 data가 없으면 0을 return해줌
        return (null != arrayList ? arrayList.size() : 0);
    }

    // 삭제해주는 method
    public void remove(int position){
        try{
            // position에 있는 값을 remove
            arrayList.remove(position);
            // notifyItemRemoved를 통해 새로고침됨, 새로고침을 해주어야 Add, Remove, Modify가 이루어짐
            notifyItemRemoved(position);
        }catch(IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }
    
    // CustomViewHolder class 정의
    // ViewHolder : ListView/RecyclerView
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // 3개의 argument가 있음
        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;
        // constructor (각 변수에 id를 연결해줌)
        public CustomViewHolder(View itemView) {
            super(itemView);
            this.iv_profile = (ImageView)itemView.findViewById(R.id.iv_profile);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

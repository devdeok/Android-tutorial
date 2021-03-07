package com.study.listexample01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        // this는 현재 activity에 해당된다는 것을 의미, android 기본 UI, List data
        list.setAdapter(adapter); // adapter와 연결됨

        data.add("AJOU");
        data.add("University");
        data.add("ICT");
        adapter.notifyDataSetChanged(); // 이걸 해줘야 위 상황이 저장됨
    }
}
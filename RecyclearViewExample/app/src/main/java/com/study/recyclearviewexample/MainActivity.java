package com.study.recyclearviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/* RecyclerView란?
 * 대량의 data set을 효율적으로 표시할 수 있다. 개발자가 data를 제공하고 각 항목의 모양을 정의하면
 * RecyclerView library가 필요할 때 요소를 dynamic으로 생성한다.
 * item이 scroll되어 display에서 벗어나더라도 RecyclerView는 View를 Remove하지 않는다.
 * 대신 RecyclerView는 display에서 scroll된 new item의 view를 recycle한다.
 */

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList; // MainData는 사용자 정의 class
    private MainAdapter mainAdapter; // MainAdapter는 사용자정의 class
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 처음 앱 실행의 lifecycle
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        // 위에서 만든 linearLayoutManager를 recyclerView에 setting
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        //ArrayList<MainData>를 MainAdapter에 담는다.
        mainAdapter = new MainAdapter(arrayList);
        // recyclerView에 mainAdapter를 set한다.
        recyclerView.setAdapter(mainAdapter);

        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData mainData = new MainData(R.mipmap.ic_launcher,"Ajou","ManCity");
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged(); // modified action이 이루어졌을 때 꼭 써주자!!(새로고침)
            }
        });

    }
}
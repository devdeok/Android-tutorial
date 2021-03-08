package com.study.threadexam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_start,btn_stop;
    Thread thread;
    boolean isThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        
        //Thread 시작
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = true;
                thread = new Thread(){
                    public void run(){
                        while(isThread){
                            try { //sleep가 error가 나게되므로 Alt+Enter를 통해 try-catch 추가
                                sleep(5000); // 5초동안 쉬어라
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        // handler가 isThread동안에는 5초동안 쉬었다가 handler를 실행
                        // handleMessage안에 있는 message를 5초마다 실행하게 됨
                        handler.sendEmptyMessage(0);
                    }
                };
                thread.start(); // button을 눌렀을 때 Thread 실행
            }
        }); //setOnClickListener 끝
        
        // Thread 종료
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isThread = false;
            }
        });//setOnClickListener 끝
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Toast.makeText(getApplicationContext(),"Mancity",Toast.LENGTH_SHORT).show();
        }
    };
}
package com.study.debugexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String str = "ajou";

        Log.e("MainActivity : ",str);

        int a = 10;

        // 두번째 인자는 String type으로 사용
        Log.e("MainActivity",String.valueOf(a));
    }
}
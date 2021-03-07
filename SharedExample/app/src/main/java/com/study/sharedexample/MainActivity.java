package com.study.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_save;
    private String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save = (EditText)findViewById(R.id.et_save);
        
        // SharedPreferences는 앱을 삭제할 경우 data가 사라짐 즉, 임시저장 용도
        // 사라지지 않게 하기 위해서는 database를 배워야함
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        // 꺼내오는 것이기 때문에 값은 없음
        String value = sharedPreferences.getString("ajou","");
        // value에 있는 String값을 et_save에 넣음
        et_save.setText(value);
        
    }
    
    // onCreate에서 lifecyle을 끝내고 onDestroy 실행
    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = et_save.getText().toString();
        editor.putString("ajou",value); // ajou라는 nicknmae으로 value를 불러온다
        editor.commit(); // save를 완료해라

    }
}
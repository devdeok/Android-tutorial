package com.study.imageview_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_test;
    private Button btn_test;
    private String str;
    private ImageView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_test = (EditText)findViewById(R.id.et_test);

        btn_test = (Button)findViewById(R.id.btn_test);
        btn_test.setOnClickListener((v)->{
            str = et_test.getText().toString();
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            intent.putExtra("str",str);
            startActivity(intent); // Activity 이동
        });

        test = (ImageView)findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Ajou",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
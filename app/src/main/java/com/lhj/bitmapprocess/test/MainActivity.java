package com.lhj.bitmapprocess.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button2)
    public void turnCompress(){
        Intent intent = new Intent(this,CompressActivity.class);
        startActivity(intent);
    }


}

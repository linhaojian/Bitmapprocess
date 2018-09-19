package com.lhj.bitmapprocess.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import butterknife.BindViews;
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
        ActivityUtils.startActivity(this,CompressActivity.class);
    }


}

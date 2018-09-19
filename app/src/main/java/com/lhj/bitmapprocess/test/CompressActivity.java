package com.lhj.bitmapprocess.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhj.bitmapprocess.BitmapCompressUtil;
import com.lhj.bitmapprocess.BitmapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompressActivity extends AppCompatActivity {
    @BindView(R.id.tv)
    public TextView tv;
    @BindView(R.id.imageView)
    public ImageView imageView;
    public Bitmap bitmap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprocess);
        //绑定初始化ButterKnife
        ButterKnife.bind(this);

        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.e983f93bfb);
        print();
    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.e983f93bfb);
                print();
                break;
            case R.id.btn2:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.e983f93bfb);
                bitmap = BitmapCompressUtil.quality(bitmap,20);
                print();
                break;
            case R.id.btn3:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.e983f93bfb);
                bitmap = BitmapCompressUtil.samplingRate(BitmapUtil.getBytesByBitmap(bitmap),200,400);
                print();
                break;
            case R.id.btn4:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.e983f93bfb);
                bitmap = BitmapCompressUtil.martix(bitmap,0.5f);
                print();
                break;
            case R.id.btn5:
                bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.e983f93bfb);
                bitmap = BitmapCompressUtil.createScaledBitmap(bitmap,100,100);
                print();
                break;
        }
    }

    private void print(){
        imageView.setImageBitmap(bitmap);
        tv.setText("图片大小："+ (bitmap.getByteCount() / 1024)+"KB"+
                "  图片宽度："+bitmap.getWidth()+
                "  图片高度："+bitmap.getHeight());
    }


}

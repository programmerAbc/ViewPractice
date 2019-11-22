package com.practice.viewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View button1;
    View button2;
    View button3;
    View button4;
    View button5;
    View contaienr;
    View scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        contaienr = findViewById(R.id.container);
        scrollView = findViewById(R.id.scrollView);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        showDetail(v);
    }

    void showDetail(View v) {
        int resId;
        RoundCornerImageView roundCornerImageView = (RoundCornerImageView) v;
        switch (v.getId()) {
            case R.id.button1:
                resId = R.drawable.image1;
                break;
            case R.id.button2:
                resId = R.drawable.image2;
                break;
            case R.id.button3:
                resId = R.drawable.image3;
                break;
            case R.id.button4:
                resId = R.drawable.image4;
                break;

            case R.id.button5:
                resId = R.drawable.image5;
                break;
            default:
                resId = R.drawable.image1;
                break;
        }
        Point point = UIHelper.calculateDeepChildOffset((ViewGroup) contaienr, v);
        Intent intent = new Intent(this, DetailPage.class);
        intent.putExtra("viewConfig", ViewConfig.newBuilder()
                .x(point.x - scrollView.getScrollX())
                .y(point.y - scrollView.getScrollY())
                .w(v.getWidth())
                .h(v.getHeight())
                .src(resId)
                .radius(roundCornerImageView.getRadius())
                .build());
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}

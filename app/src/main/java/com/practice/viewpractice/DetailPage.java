package com.practice.viewpractice;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class DetailPage extends AppCompatActivity {
    RoundCornerImageView imageView;
    Intent intent;
    LayoutMonitor layoutMonitor;
    ViewConfig viewConfig;
    View container;
    View panel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);
        container = findViewById(R.id.container);
        imageView = findViewById(R.id.imageView);
        panel = findViewById(R.id.panel);
        viewConfig = getIntent().getParcelableExtra("viewConfig");
        imageView.setImageResource(viewConfig.getSrc());
        layoutMonitor = LayoutMonitor.newBuilder().callback(new LayoutMonitor.Callback() {
            @Override
            public void onLayoutFinished() {
                startAnimation();
            }
        })
                .once(true)
                .view(imageView)
                .build()
                .install();
    }

    void startAnimation() {
        final int targetW = container.getWidth();
        final int targetH = UIHelper.dp2px(200);
        final int panelTargetW = container.getWidth();
        final int panelTargetH = container.getHeight() - targetH;
        final float targetRadius = 0;
        final int targetX = 0;
        final int targetY = 0;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float progress = (float) animation.getAnimatedValue();
                float radius = UIHelper.lerp(viewConfig.getRadius(), targetRadius, progress);
                int x = UIHelper.lerp(viewConfig.getX(), targetX, progress);
                int y = UIHelper.lerp(viewConfig.getY(), targetY, progress);
                int width = UIHelper.lerp(viewConfig.getW(), targetW, progress);
                int height = UIHelper.lerp(viewConfig.getH(), targetH, progress);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
                layoutParams.leftMargin = x;
                layoutParams.topMargin = y;
                layoutParams.width = width;
                layoutParams.height = height;
                imageView.setLayoutParams(layoutParams);
                imageView.setRadius(radius);
                imageView.setVisibility(View.VISIBLE);
                panel.setPivotY(0);
                panel.setX(x);
                panel.setY(y+height);
                panel.setScaleX((float)width/panelTargetW);
                panel.setScaleY((float) Math.pow(progress,5));
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setDuration(800);
        valueAnimator.start();


    }

}

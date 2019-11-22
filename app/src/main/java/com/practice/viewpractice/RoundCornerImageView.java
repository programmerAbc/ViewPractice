package com.practice.viewpractice;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by zhuyakun on 2017/10/18.
 */

public class RoundCornerImageView extends AppCompatImageView {
    float radius = 15;

    public RoundCornerImageView(Context context) {
        super(context);
        init(null);
    }

    public RoundCornerImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RoundCornerImageView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RoundCornerImageView);
            radius = a.getDimension(R.styleable.RoundCornerImageView_corner_radius, 15);
            a.recycle();
        }
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}

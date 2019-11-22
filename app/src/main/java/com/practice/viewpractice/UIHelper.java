package com.practice.viewpractice;

import android.content.res.Resources;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class UIHelper {
    private static void getDeepChildOffset(final ViewGroup mainParent, final ViewParent parent, final View child, final Point accumulatedOffset) {
        ViewGroup parentGroup = parent == null ? null : (ViewGroup) parent;
        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();
        if (parentGroup == null || parentGroup.equals(mainParent)) {
            return;
        }
        getDeepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }

    public static Point calculateDeepChildOffset(ViewGroup parent, View child) {
        Point point = new Point(0, 0);
        getDeepChildOffset(parent, child.getParent(), child, point);
        return point;
    }

    public static float lerp(float start, float end, float progress) {
        float range = end - start;
        if (progress >= 1) {
            return end;
        }
        if (progress <= 0) {
            return start;
        }
        return start + range * progress;
    }

     public static int lerp(int start, int end, float progress) {
        float range = end - start;
        if (progress >= 1) {
            return end;
        }
        if (progress <= 0) {
            return start;
        }
        return (int) (start + range * progress);
    }
    public static int dp2px(double dp) {
        return (int) Math.ceil(Resources.getSystem().getDisplayMetrics().density * dp);
    }

}

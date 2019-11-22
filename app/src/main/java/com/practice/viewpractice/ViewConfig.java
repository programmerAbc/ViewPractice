package com.practice.viewpractice;

import android.os.Parcel;
import android.os.Parcelable;

public class ViewConfig implements Parcelable {
    private int x;
    private int y;
    private int w;
    private int h;
    private int src;
    private float radius;

    public ViewConfig() {
    }

    private ViewConfig(Builder builder) {
        setX(builder.x);
        setY(builder.y);
        setW(builder.w);
        setH(builder.h);
        setSrc(builder.src);
        setRadius(builder.radius);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public static final class Builder {
        private int x;
        private int y;
        private int w;
        private int h;
        private int src;
        private float radius;

        private Builder() {
        }

        public Builder x(int val) {
            x = val;
            return this;
        }

        public Builder y(int val) {
            y = val;
            return this;
        }

        public Builder w(int val) {
            w = val;
            return this;
        }

        public Builder h(int val) {
            h = val;
            return this;
        }

        public Builder src(int val) {
            src = val;
            return this;
        }

        public Builder radius(float val) {
            radius = val;
            return this;
        }

        public ViewConfig build() {
            return new ViewConfig(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeInt(this.w);
        dest.writeInt(this.h);
        dest.writeInt(this.src);
        dest.writeFloat(this.radius);
    }

    protected ViewConfig(Parcel in) {
        this.x = in.readInt();
        this.y = in.readInt();
        this.w = in.readInt();
        this.h = in.readInt();
        this.src = in.readInt();
        this.radius = in.readFloat();
    }

    public static final Creator<ViewConfig> CREATOR = new Creator<ViewConfig>() {
        @Override
        public ViewConfig createFromParcel(Parcel source) {
            return new ViewConfig(source);
        }

        @Override
        public ViewConfig[] newArray(int size) {
            return new ViewConfig[size];
        }
    };
}

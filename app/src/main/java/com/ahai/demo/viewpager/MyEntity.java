package com.ahai.demo.viewpager;

import java.io.Serializable;

/**
 * Created by zhenhai.fzh on 17/3/15.
 */

public class MyEntity implements Serializable {

    private int imgResId;
    private String text;

    public MyEntity(int imgResId, String text) {
        this.imgResId = imgResId;
        this.text = text;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

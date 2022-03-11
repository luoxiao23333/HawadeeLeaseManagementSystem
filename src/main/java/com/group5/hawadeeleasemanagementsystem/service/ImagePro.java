package com.group5.hawadeeleasemanagementsystem.service;

public class ImagePro {
    private float x; //x 坐标
    private float y; //y 坐标
    private float scalePercent;  //缩放百分比
    private String imgPath; //路径

    public ImagePro() {
    }

    public ImagePro(float x, float y, float scalePercent, String imgPath) {
        this.x = x;
        this.y = y;
        this.scalePercent = scalePercent;
        this.imgPath = imgPath;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getScalePercent() {
        return scalePercent;
    }

    public void setScalePercent(float scalePercent) {
        this.scalePercent = scalePercent;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

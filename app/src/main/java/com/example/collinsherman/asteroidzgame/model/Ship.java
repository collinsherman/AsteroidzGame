package com.example.collinsherman.asteroidzgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Ship {

    private Bitmap bitmap;
    private int x;
    private int y;

    public Ship(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitmap() { return bitmap; }

    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, (canvas.getWidth() / 2), (canvas.getHeight()), null);
    }

    public void update() {
        x += (getX());
    }
}

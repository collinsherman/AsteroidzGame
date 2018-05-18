package com.example.collinsherman.asteroidzgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.collinsherman.asteroidzgame.model.components.Speed;

public class Ship {

    private Bitmap bitmap;
    private int x;
    private int y;
    private Speed speed;

    public Ship(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        speed = new Speed(0, 0);
    }

    public Speed getSpeed() { return speed; }

    public Bitmap getBitmap() { return bitmap; }

    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public int getLeft() { return x - bitmap.getWidth() / 2; }

    public int getRight() { return (x + bitmap.getWidth() / 2); }

    public int getTop() { return (y - bitmap.getHeight() / 2); }

    public int getBottom() { return (y + bitmap.getHeight() / 2);}

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (canvas.getWidth() / 2), y - (canvas.getHeight() / 2), null);
    }

    public void update() {
        x += (speed.getXVel());
        y += (speed.getYVel() * speed.getYDir());
    }
}
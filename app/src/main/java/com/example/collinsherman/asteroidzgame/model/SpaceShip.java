package com.example.collinsherman.asteroidzgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

//import com.example.collinsherman.asteroidzgame.model.components.Speed;

public class SpaceShip {

    private Bitmap bitmap;
    private int x;
    private int y;
    //private Speed speed;

    public SpaceShip(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        //speed = new Speed();
    }

    //public Speed getSpeed() { return speed; }

    public Bitmap getBitmap() { return bitmap; }

    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight()/2), null);
    }

    //public void update() {
    //    x += (speed.getXVel());
    //    y += (speed.getYVel() * speed.getYDir());
    //}
}


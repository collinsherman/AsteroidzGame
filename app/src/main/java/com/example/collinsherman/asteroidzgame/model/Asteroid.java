package com.example.collinsherman.asteroidzgame.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.collinsherman.asteroidzgame.model.components.Speed;

import java.util.concurrent.ThreadLocalRandom;

public class Asteroid {

    private Bitmap bitmap;
    private int x;
    private int y;
    private Speed speed;

    public Asteroid(Bitmap bitmap, int x, int y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        speed = new Speed();
    }

    public Asteroid(Bitmap bitmap, int y) {
        this.bitmap = bitmap;
        int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
        this.x = randomX;
        this.y = y;
        speed = new Speed();
    }

    public Speed getSpeed() { return speed; }

    public Bitmap getBitmap() { return bitmap; }

    public void setBitmap(Bitmap bitmap) { this.bitmap = bitmap; }

    public int getX() { return x; }

    public void setX(int x) { this.x = x; }

    public int getY() { return y; }

    public void setY(int y) { this.y = y; }

    public int getLeft() { return (x - bitmap.getWidth() / 2);}

    public int getRight() { return (x + bitmap.getWidth() / 2); }

    public int getTop() { return (y - bitmap.getHeight() / 2); }

    public int getBottom() { return (y + bitmap.getHeight() / 2);}

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
    }

    public void update() {
        x += (speed.getXVel());
        y += (speed.getYVel() * speed.getYDir());
    }


}

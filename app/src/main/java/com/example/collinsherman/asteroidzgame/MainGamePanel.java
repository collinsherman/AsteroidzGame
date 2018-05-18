package com.example.collinsherman.asteroidzgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.collinsherman.asteroidzgame.model.Asteroid;
import com.example.collinsherman.asteroidzgame.model.Ship;
import com.example.collinsherman.asteroidzgame.model.Star;
import com.example.collinsherman.asteroidzgame.model.components.Speed;

import java.util.concurrent.ThreadLocalRandom;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "MainGamePanel";

    private MainThread thread;
    private Star star1;
    private Star star2;
    private Star star3;
    private Star star4;
    private Star star5;
    private Star star6;
    private Star star7;
    private Star star8;
    private Asteroid asteroid1;
    private Asteroid asteroid2;
    private Asteroid asteroid3;
    private Speed speed;
    private Ship ship;

    public MainGamePanel(Context context) {
        super(context);
        // Callback intercepts events
        getHolder().addCallback(this);
        // Creates asteroid and loads bitmap
        star1 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), 84, 600);
        star2 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), 200, 200);
        star3 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), 300, 300);
        star4 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), 400, 1500);
        star5 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), 500, 900);
        star6 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), 600, 1900);
        star7 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), 700, 1100);
        star8 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), 800, 400);
        asteroid1 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), 84, 84);
        asteroid2 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid2), 467, 84);
        asteroid3 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid3), 800, 84);
        ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.ss_red), 980, 2200);
        // Create game loop thread
        thread = new MainThread(getHolder(), this);
        // Allows handling of events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed...");
        // Shuts down thread and waits for finish
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // Try shutting down again
            }
        }
        Log.d(TAG, "Thread was shut down cleanly.");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        star1.draw(canvas);
        star2.draw(canvas);
        star3.draw(canvas);
        star4.draw(canvas);
        star5.draw(canvas);
        star6.draw(canvas);
        star7.draw(canvas);
        star8.draw(canvas);
        asteroid1.draw(canvas);
        asteroid2.draw(canvas);
        asteroid3.draw(canvas);
        ship.draw(canvas);
    }

    public void update() {
//        if ((asteroid1.getLeft() >= ship.getLeft() && asteroid1.getLeft() <= ship.getRight())
//                || (asteroid1.getRight() >= ship.getLeft() && asteroid1.getRight() <= ship.getRight())
//                && asteroid1.getBottom() >= ship.getTop()) {
//            Log.d(TAG, "Collision  1 . . .");
//            asteroid1.setX(5000);
//        }
        if (asteroid1.getTop() >= getHeight() || asteroid1.getLeft() >= getWidth()
                || asteroid1.getBottom() <= 0 || asteroid1.getRight() <= 0) {
            asteroid1 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), 84);
        }

//        if ((asteroid2.getLeft() >= ship.getLeft() && asteroid2.getLeft() <= ship.getRight())
//                || (asteroid2.getRight() >= ship.getLeft() && asteroid2.getRight() <= ship.getRight())
//                && asteroid2.getBottom() >= ship.getTop()) {
//            Log.d(TAG, "Collision  2 . . .");
//            asteroid2.setX(5000);
//        }
        if (asteroid2.getTop() >= getHeight() || asteroid2.getLeft() >= getWidth()
                || asteroid2.getBottom() <= 0 || asteroid2.getRight() <= 0) {
            asteroid2 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid2), 84);
        }

//        if ((asteroid3.getLeft() >= ship.getLeft() && asteroid3.getLeft() <= ship.getRight())
//                || (asteroid3.getRight() >= ship.getLeft() && asteroid3.getRight() <= ship.getRight())
//                && asteroid3.getBottom() >= ship.getTop()) {
//            Log.d(TAG, "Collision  3 . . .");
//            asteroid3.setX(5000);
//        }
        if (asteroid3.getTop() >= getHeight() || asteroid3.getLeft() >= getWidth()
                || asteroid3.getBottom() <= 0 || asteroid3.getRight() <= 0) {
            asteroid3 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid3),84);
        }

        if (star1.getTop() >= getHeight() || star1.getLeft() >= getWidth()
                || star1.getBottom() <= 0 || star1.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star1 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), randomX, 16);
        }

        if (star2.getTop() >= getHeight() || star2.getLeft() >= getWidth()
                || star2.getBottom() <= 0 || star2.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star2 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), randomX, 16);
        }

        if (star3.getTop() >= getHeight() || star3.getLeft() >= getWidth()
                || star3.getBottom() <= 0 || star3.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star3 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), randomX, 16);
        }

        if (star4.getTop() >= getHeight() || star4.getLeft() >= getWidth()
                || star4.getBottom() <= 0 || star4.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star4 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), randomX, 16);
        }

        if (star5.getTop() >= getHeight() || star5.getLeft() >= getWidth()
                || star5.getBottom() <= 0 || star5.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star5 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), randomX, 16);
        }

        if (star6.getTop() >= getHeight() || star6.getLeft() >= getWidth()
                || star6.getBottom() <= 0 || star6.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star6 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), randomX, 16);
        }

        if (star7.getTop() >= getHeight() || star7.getLeft() >= getWidth()
                || star7.getBottom() <= 0 || star7.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star7 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), randomX, 16);
        }

        if (star8.getTop() >= getHeight() || star8.getLeft() >= getWidth()
                || star8.getBottom() <= 0 || star8.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star8 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), randomX, 16);
        }

        // Check collision with bottom
        star1.update();
        star2.update();
        star3.update();
        star4.update();
        star5.update();
        star6.update();
        star7.update();
        star8.update();
        asteroid1.update();
        asteroid2.update();
        asteroid3.update();
        ship.update();
    }
}

package com.example.collinsherman.asteroidzgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.collinsherman.asteroidzgame.model.Asteroid;
import com.example.collinsherman.asteroidzgame.model.Ship;
import com.example.collinsherman.asteroidzgame.model.components.Speed;

import java.util.Random;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "MainGamePanel";

    private MainThread thread;
    private Asteroid asteroid1;
    private Asteroid asteroid2;
    private Asteroid asteroid3;
    private Speed speed;
    private Ship ship;

    Random rand = new Random();


    public MainGamePanel(Context context) {
        super(context);
        // Callback intercepts events
        getHolder().addCallback(this);
        // Creates asteroid and loads bitmap
        asteroid1 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), rand.nextInt(getWidth() + 86), 85);
        asteroid2 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid2), rand.nextInt(getWidth() + 86), 85);
        asteroid3 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid3), rand.nextInt(getWidth() + 86), 85);
        ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), 970, 2200);
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
        asteroid1.draw(canvas);
        asteroid2.draw(canvas);
        asteroid3.draw(canvas);
        ship.draw(canvas);
    }

    public void update() {
        // Check collision with bottom
        if (asteroid1.getSpeed().getYDir() == Speed.DIRECTION_DOWN && asteroid1.getY() + asteroid1.getBitmap().getHeight() / 2 >= getHeight()) {
            int randInt = rand.nextInt(getWidth() + 1);
            asteroid1 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), randInt, 85);
        }

        if (asteroid2.getSpeed().getYDir() == Speed.DIRECTION_DOWN && asteroid2.getY() + asteroid2.getBitmap().getHeight() / 2 >= getHeight()) {
            int randInt = rand.nextInt(getWidth() + 1);
            asteroid2 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid2), randInt, 85);
        }

        if (asteroid3.getSpeed().getYDir() == Speed.DIRECTION_DOWN && asteroid3.getY() + asteroid3.getBitmap().getHeight() / 2 >= getHeight()) {
            int randInt = rand.nextInt(getWidth() + 1);
            asteroid3 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid3), randInt, 85);
        }

        asteroid1.update();
        asteroid2.update();
        asteroid3.update();
        ship.update();
    }
}

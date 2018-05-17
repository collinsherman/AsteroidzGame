package com.example.collinsherman.asteroidzgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;
import android.widget.Toast;

import com.example.collinsherman.asteroidzgame.model.Asteroid;
import com.example.collinsherman.asteroidzgame.model.SpaceShip;
import com.example.collinsherman.asteroidzgame.model.components.Speed;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "MainGamePanel";
    private static int WIDTH = 0;
    private static int HEIGHT = 0;

    private int xClick = 0;
    private int yClick = 0;

    private int shipX = 0;
    private int shipY = 0;

    private int shipPosX = 0;

    private MainThread thread;
    private Asteroid asteroid;
    private SpaceShip spaceship;
    private Speed speed;

    public MainGamePanel(Context context) {
        super(context);
        // Callback intercepts events
        getHolder().addCallback(this);
        // Creates droid and loads bitmap
        asteroid = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), 50, 50);
        spaceship = new SpaceShip(BitmapFactory.decodeResource(getResources(), R.drawable.ss_blue),  400, 1000);
        spaceship.setYSpeed(0);
        Log.d(TAG, "ShipY: "+ shipY);
        Log.d(TAG, "ShipX: "+ shipX);
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
        getViewSize(canvas);
        canvas.drawColor(Color.BLACK);
        asteroid.draw(canvas);
        spaceship.draw(canvas);
    }

    public void update() {
        int shipY = HEIGHT - (spaceship.getBitmap().getHeight()/2);
        spaceship.setY(shipY);
        // Check collision with bottom
        if (asteroid.getSpeed().getYDir() == Speed.DIRECTION_DOWN && asteroid.getY() + asteroid.getBitmap().getHeight() / 2 <= 0) {
            //TODO: remove from screen
        }

        if (spaceship.getSpeed().getXDir() == 1 && spaceship.getX() + spaceship.getBitmap().getWidth() / 2 >= WIDTH) {
            spaceship.setXSpeed(0);
        }

        if (spaceship.getSpeed().getXDir() == -1 && spaceship.getX() - spaceship.getBitmap().getWidth() / 2 <= 0) {
            spaceship.setXSpeed(0);
        }

        asteroid.update();
        spaceship.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xClick = (int)event.getX();
            yClick = (int)event.getY();
            Log.d(TAG, "Ship x: "+spaceship.getX());
            Log.d(TAG, "Ship y: "+spaceship.getY());

            if (xClick < spaceship.getX()+(spaceship.getBitmap().getWidth()/2) && xClick > spaceship.getX()-(spaceship.getBitmap().getWidth()/2)){
                if (yClick < spaceship.getY()+(spaceship.getBitmap().getHeight()/2) && yClick > spaceship.getY()-(spaceship.getBitmap().getHeight()/2)){
                    Log.d(TAG, "Settings clicked!");
                }
            }
            if (xClick > WIDTH/2) {
                Log.d(TAG, "Right side clicked!");
                spaceship.setXDirection(1);
                spaceship.setXSpeed(10);
            } else if (xClick < WIDTH/2) {
                Log.d(TAG, "Left side clicked!");
                spaceship.setXDirection(-1);
                spaceship.setXSpeed(10);
            } else if (xClick == spaceship.getX() && yClick == spaceship.getY()) {
                Log.d(TAG, "Ship clicked!");
            } else {
                Log.d(TAG, "Coords: x=" + xClick + ",y=" + yClick);
            }
            }

        if (event.getAction() == MotionEvent.ACTION_UP) {
           spaceship.setXSpeed(0);
        }
        return true;
    }

//    public getCanvasCoords(int bitmapDimensions, int xPos){
//        int result = 0;
//        int w = bitmapDimensions/2;
//        int rightPoint = 0;
//        int leftPoint = 0;
//        rightPoint = xPos + w;
//        leftPoint = xPos - w;
//        return rightPoint, leftPoint;
//    }
//
    public void getViewSize(Canvas canvas){
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
    }
}

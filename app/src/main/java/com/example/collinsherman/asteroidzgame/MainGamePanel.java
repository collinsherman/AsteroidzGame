package com.example.collinsherman.asteroidzgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.collinsherman.asteroidzgame.model.Asteroid;
import com.example.collinsherman.asteroidzgame.model.components.Speed;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "MainGamePanel";

    private MainThread thread;
    private Asteroid asteroid;
    private Speed speed;

    public MainGamePanel(Context context) {
        super(context);
        // Callback intercepts events
        getHolder().addCallback(this);
        // Creates droid and loads bitmap
        asteroid = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), 50, 50);
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
}

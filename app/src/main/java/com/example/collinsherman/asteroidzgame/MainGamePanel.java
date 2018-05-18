package com.example.collinsherman.asteroidzgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.collinsherman.asteroidzgame.model.Asteroid;
import com.example.collinsherman.asteroidzgame.model.Ship;
import com.example.collinsherman.asteroidzgame.model.Star;
import com.example.collinsherman.asteroidzgame.model.components.Speed;

import java.util.concurrent.ThreadLocalRandom;

public class MainGamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = "MainGamePanel";
    private static int WIDTH = 0;
    private static int HEIGHT = 0;

    private int xClick = 0;
    private int yClick = 0;

    private int shipX = 0;
    private int shipY = 0;

    private int colorIndex = 1;

    private MainThread thread;
    private Star star1;
    private Star star2;
    private Star star3;
    private Star star4;
    private Star star5;
    private Star star6;
    private Star star7;
    private Star star8;
    private Star star9;
    private Star star10;
    private Star star11;
    private Star star12;
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
        star4 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star4), 400, 1500);
        star5 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), 500, 900);
        star6 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), 600, 1900);
        star7 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), 700, 1100);
        star8 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star4), 800, 700);
        star9 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), 900, 100);
        star10 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), 1000, 2100);
        star11 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), 1100, 2000);
        star12 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star4), 1200, 1500);
        asteroid1 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid1), 84, 84);
        asteroid2 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid2), 467, 84);
        asteroid3 = new Asteroid(BitmapFactory.decodeResource(getResources(), R.drawable.asteroid3), 800, 84);
        ship = new Ship(BitmapFactory.decodeResource(getResources(), R.drawable.ss_red), 300, 1500);
        ship.setYSpeed(0);
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
        star1.draw(canvas);
        star2.draw(canvas);
        star3.draw(canvas);
        star4.draw(canvas);
        star5.draw(canvas);
        star6.draw(canvas);
        star7.draw(canvas);
        star8.draw(canvas);
        star9.draw(canvas);
        star10.draw(canvas);
        star11.draw(canvas);
        star12.draw(canvas);
        asteroid1.draw(canvas);
        asteroid2.draw(canvas);
        asteroid3.draw(canvas);
        ship.draw(canvas);
    }

    public void update() {
        int shipY = HEIGHT - (ship.getBitmap().getHeight()/2) - 25;
        ship.setY(shipY);

        if (ship.getSpeed().getXDir() == 1 && ship.getX() + ship.getBitmap().getWidth() / 2 >= WIDTH) {
            ship.setXSpeed(0);
        }

        if (ship.getSpeed().getXDir() == -1 && ship.getX() - ship.getBitmap().getWidth() / 2 <= 0) {
            ship.setXSpeed(0);
        }
//                if ((asteroid1.getLeft() >= ship.getLeft() && asteroid1.getLeft() <= ship.getRight())
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
            star4 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star4), randomX, 16);
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
            star8 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star4), randomX, 16);
        }

        if (star9.getTop() >= getHeight() || star9.getLeft() >= getWidth()
                || star9.getBottom() <= 0 || star9.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star9 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star1), randomX, 16);
        }

        if (star10.getTop() >= getHeight() || star10.getLeft() >= getWidth()
                || star10.getBottom() <= 0 || star10.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star10 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star2), randomX, 16);
        }

        if (star11.getTop() >= getHeight() || star11.getLeft() >= getWidth()
                || star11.getBottom() <= 0 || star11.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star11 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star3), randomX, 16);
        }

        if (star12.getTop() >= getHeight() || star12.getLeft() >= getWidth()
                || star12.getBottom() <= 0 || star12.getRight() <= 0) {
            int randomX = ThreadLocalRandom.current().nextInt(84, 1000);
            star12 = new Star(BitmapFactory.decodeResource(getResources(), R.drawable.star4), randomX, 16);
        }

        star1.update();
        star2.update();
        star3.update();
        star4.update();
        star5.update();
        star6.update();
        star7.update();
        star8.update();
        star9.update();
        star10.update();
        star11.update();
        star12.update();
        asteroid1.update();
        asteroid2.update();
        asteroid3.update();
        ship.update();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            xClick = (int)event.getX();
            yClick = (int)event.getY();

            if (xClick < ship.getX()+(ship.getBitmap().getWidth()/2) && xClick > ship.getX()-(ship.getBitmap().getWidth()/2)){
                if (yClick < ship.getY()+(ship.getBitmap().getHeight()/2) && yClick > ship.getY()-(ship.getBitmap().getHeight()/2)){
                    Log.d(TAG, "Color index: "+colorIndex);
                    ship.setBitmap(changeColor(colorIndex));
                    if (colorIndex == 5) {
                        colorIndex = 1;
                    }
                    else {
                        colorIndex ++;
                    }
                }
            }
            else if (xClick > WIDTH/2) {
                ship.setXDirection(1);
                ship.setXSpeed(20);
            } else if (xClick < WIDTH/2) {
                ship.setXDirection(-1);
                ship.setXSpeed(20);
            }
            }
        if (event.getAction() == MotionEvent.ACTION_UP) {
           ship.setXSpeed(0);
        }
        return true;
    }

    public void getViewSize(Canvas canvas){
        WIDTH = canvas.getWidth();
        HEIGHT = canvas.getHeight();
    }

    private Bitmap changeColor(int colorIndex) {
        Bitmap result = null;
        if (colorIndex == 1) {
            return result = BitmapFactory.decodeResource(getResources(), R.drawable.ss_red);
        } else if (colorIndex == 2) {
            return result = BitmapFactory.decodeResource(getResources(), R.drawable.ss_green);
        } else if (colorIndex == 3) {
            return result = BitmapFactory.decodeResource(getResources(), R.drawable.ss_orange);
        } else if (colorIndex == 4) {
            return result = BitmapFactory.decodeResource(getResources(), R.drawable.ss_purple);
        } else if (colorIndex == 5) {
            return result = BitmapFactory.decodeResource(getResources(), R.drawable.ss_blue);
        }
        return result;
    }
}

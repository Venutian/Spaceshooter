package com.venutian.spaceshooter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class Game extends SurfaceView implements Runnable {
    public static final String TAG = "Game";
    static final int STAGE_WIDTH = 1280;
    static final int STAGE_HEIGHT = 720;
    static final int STAR_COUNT = 40;

    private Thread _gameThread;
    private volatile boolean _isRunning = false;
    private SurfaceHolder _holder;
    private Paint _paint;
    private Canvas _canvas;
private ArrayList<Entity> _entities = new ArrayList<>();
    Random _rand = new Random();

    public Game(Context context) {
        super(context);
        Entity._game = this;
        _holder = getHolder();
        _holder.setFixedSize(STAGE_WIDTH, STAGE_HEIGHT);
        _paint = new Paint();

        for (int i = 0; i < STAR_COUNT; i++){
            _entities.add(new Star());
        }
    }


    public void run() {
        while (_isRunning) {
            input();
            update();
            render();
        }
    }

    private void input() {

    }

    private void update() {
        for (Entity e : _entities){
            e.update();
        }
    }


    private void render() {

        if (!acquireAndLockCanvas()) {
            return;
        }
        Resources res = getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.background_ingame);
        _canvas.drawBitmap(bitmap, 0, 0, _paint);
        for (Entity e : _entities){
            e.render(_canvas,_paint);
        }
        _holder.unlockCanvasAndPost(_canvas);

    }

    private boolean acquireAndLockCanvas() {
        if (!_holder.getSurface().isValid()) {
            return false;
        }
        _canvas = _holder.lockCanvas();

        return (_canvas != null);
    }

    protected void onResume() {
        Log.d(TAG, "onResume");
        _isRunning = true;
        _gameThread = new Thread(this);
        _gameThread.start();
    }

    protected void onPause() {
        _isRunning = false;
        try {
            _gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d(TAG, Log.getStackTraceString(e.getCause()));
        }
    }

    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        _gameThread = null;
        Entity._game = null;
    }
}

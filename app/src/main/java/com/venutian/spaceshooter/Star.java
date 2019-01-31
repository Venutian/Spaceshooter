package com.venutian.spaceshooter;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Star extends Entity {
    private int _color = 0xFFD4B7FF;
    private float _radius = 40;

    Star() {
        _x = _game._rand.nextInt(Game.STAGE_WIDTH);
        _y = _game._rand.nextInt(Game.STAGE_HEIGHT);
        _radius = _game._rand.nextInt(4) + 1;
        _width = _radius * 2;
        _height = _radius * 2;
        _velX = -4f;
    }

    @Override
    void update() {
        _x += _velX;
        _y += _velY;
        if (right() < 0) {
            setLeft(Game.STAGE_WIDTH);
        }
        if (bottom() < 0) {
            setTop(Game.STAGE_HEIGHT);
        }
    }

    @Override
    void render(Canvas canvas, Paint paint) {
        paint.setColor(_color);
       // canvas.drawRect(_x, _y, right(), bottom(), paint);
        canvas.drawCircle(_x + _radius, _y+ _radius, _radius, paint);
    }
}

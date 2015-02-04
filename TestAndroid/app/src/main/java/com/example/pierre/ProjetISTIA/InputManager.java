package com.example.pierre.ProjetISTIA;

/**
 * Created by Pierre on 17/01/2015.
 */
public class InputManager {

    private boolean up, move, down, eventOccurred;
    private float touchX, touchY, prevTouchX, prevTouchY;

    public InputManager(){
        this.up = false;
        this.down = false;
        this.move = false;
        this.eventOccurred = false;
        this.touchX = 0;
        this.touchY = 0;
        this.prevTouchX = 0;
        this.prevTouchX = 0;
    }

    public void updateTouchPosition(float x, float y){
        this.prevTouchX = this.touchX;
        this.prevTouchY = this.touchY;
        this.touchX = x;
        this.touchY = y;
    }

    public void startUp(float x, float y){
        this.updateTouchPosition(x, y);
        this.eventOccurred = true;
        this.up = true;
    }

    public void startDown(float x, float y){
        this.updateTouchPosition(x, y);
        this.eventOccurred = true;
        this.down = true;
    }

    public void startMove(float x, float y){
        this.updateTouchPosition(x, y);
        this.eventOccurred = true;
        this.move = true;
    }

    public void resetEvents(){
        this.eventOccurred = false;
        this.up = false;
        this.down = false;
        this.move = false;
    }

    public float getTouchX(){
        return this.touchX;
    }

    public float getTouchY(){
        return this.touchY;
    }

    public float getPrevTouchX(){
        return this.prevTouchX;
    }

    public float getPrevTouchY(){
        return this.prevTouchY;
    }

    public boolean eventHasOccurred(){
        return this.eventOccurred;
    }

    public boolean downOccurred(){
        return this.down;
    }

    public boolean moveOccurred(){
        return this.move;
    }

    public boolean upOccurred(){
        return this.up;
    }


}

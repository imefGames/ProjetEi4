package com.example.pierre.ProjetISTIA;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Game extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder = null;
    private DrawingThread mThread = null;
    private int sWidth, sHeight;
    private InputManager inputManager;
    private RenderManager renderManager;
    private GameManager gameManager;


    public Game(Context context, int sWidth, int sHeight){
        super(context);
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        init(context);
    }

    public Game(Context context, AttributeSet attrs){
        super(context, attrs);
        this.sWidth = 0;
        this.sHeight = 0;
        init(context);
    }

    public Game(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.sWidth = 0;
        this.sHeight = 0;
        init(context);
    }

    public void init(Context context){
        this.inputManager = new InputManager();
        this.renderManager = new RenderManager(getResources());
        this.gameManager = new GameManager(this.inputManager, this.renderManager, this.sWidth, this.sHeight);
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    public int getScreenWidth(){
        return this.sWidth;
    }

    public int getScreenHeight(){
        return this.sHeight;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
                this.inputManager.startDown(e.getX(), e.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                this.inputManager.startMove(e.getX(), e.getY());
                break;
            case MotionEvent.ACTION_UP:
                this.inputManager.startUp(e.getX(), e.getY());
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas pCanvas){
        this.renderManager.setTarget(pCanvas);
        this.gameManager.draw();
        postInvalidate();
    }

    public void tick(Canvas pCanvas){
        this.draw(pCanvas); //draw all items
        this.gameManager.update();//update all items
        this.inputManager.resetEvents(); //reset events
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        if(mThread == null){
            mThread = new DrawingThread();
        }
        mThread.keepDrawing = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        mThread.keepDrawing = false;
        boolean joined = false;
        while(!joined){
            try{
                mThread.join();
                joined = true;
            }catch(InterruptedException e){}
        }
        mThread = null;
        this.gameManager.destroy();
    }

    private class DrawingThread extends Thread{
        boolean keepDrawing = true;

        @Override
        public void run(){
            while(keepDrawing){
                Canvas canvas = null;
                try{
                    canvas = mHolder.lockCanvas(null);
                    synchronized(mHolder){
                        tick(canvas);
                    }
                }catch(Exception e){
                }finally{
                    if(canvas != null){
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try{
                    Thread.sleep(50);
                }catch(InterruptedException e){}
            }
        }
    }
}

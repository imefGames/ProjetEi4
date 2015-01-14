package com.example.pierre.ProjetISTIA;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Jeu extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder = null;
    private DrawingThread mThread = null;
    private Paint p;
    private int sWidth, sHeight, xTouch, yTouch;
    private boolean t[][];

    public Jeu(Context context, int sWidth, int sHeight){
        super(context);
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        init();
    }

    public Jeu(Context context, AttributeSet attrs){
        super(context, attrs);
        this.sWidth = 0;
        this.sHeight = 0;
        init();
    }

    public Jeu(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.sWidth = 0;
        this.sHeight = 0;
        init();
    }

    public void init(){
        p = new Paint();
        p.setColor(Color.RED);
        mHolder = getHolder();
        mHolder.addCallback(this);
        xTouch = 0;
        yTouch = 0;
        this.t = new boolean[5][5];
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        switch(e.getAction()){
            case MotionEvent.ACTION_DOWN:
            //case MotionEvent.ACTION_MOVE:
            //case MotionEvent.ACTION_UP:
                xTouch = (int)e.getX();
                yTouch = (int)e.getY();
                t[xTouch*5/sWidth][yTouch*5/sHeight] ^= true;
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas pCanvas){
        pCanvas.drawColor(Color.WHITE);

        int w = sWidth/5;
        int h = sHeight/5;
        for(int j=0; j<5; j++){
            for(int i=0; i<5; i++){
                if(this.t[i][j])
                    pCanvas.drawRect(i*w+1, j*h+1, (i+1)*w-1, (j+1)*h-1, p);
            }
        }

        int x, y, ws2;
        x = ((xTouch*5/sWidth)*2+1)*w/2;
        y = ((yTouch*5/sHeight)*2+1)*h/2;
        ws2 = w/2;
        Drawable d = getResources().getDrawable(R.drawable.test);
        d.setBounds(x-ws2, y-ws2, x+ws2, y+ws2);
        d.draw(pCanvas);

        postInvalidate();
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
                        draw(canvas);
                    }
                }catch(Exception e){

                }finally{
                    if(canvas != null){
                        mHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try{
                    Thread.sleep(33);
                }catch(InterruptedException e){}
            }
        }
    }
}

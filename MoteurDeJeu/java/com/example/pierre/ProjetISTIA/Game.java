package com.example.pierre.ProjetISTIA;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder = null;
    private DrawingThread mThread = null;
    private int sWidth, sHeight;
    private InputManager inputManager;
    private RenderManager renderManager;
    private GameManager gameManager;


    public Game(Context context, int sWidth, int sHeight) {
        super(context);
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        init();
    }

    public Game(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.sWidth = 0;
        this.sHeight = 0;
        init();
    }

    public Game(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.sWidth = 0;
        this.sHeight = 0;
        init();
    }

    public void init() {
        this.inputManager = new InputManager();
        this.renderManager = new RenderManager(getResources());
        this.gameManager = new GameManager(this.inputManager, this.renderManager, this.sWidth, this.sHeight);
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        synchronized (this.inputManager) {
            switch (e.getAction()) {
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
        }
        return true;
    }

    @Override
    public void draw(Canvas pCanvas) {
        synchronized (this.renderManager) {
            this.renderManager.setMainTarget(pCanvas);
        }
        synchronized (this.gameManager) {
            this.gameManager.draw();
        }
        synchronized (this) {
            postInvalidate();
        }
    }

    public void tick(Canvas pCanvas) {
        try {
            this.draw(pCanvas); //draw all items
            this.gameManager.update(); //update all items
            synchronized(this.inputManager) {
                this.inputManager.resetEvents(); //reset events
            }
        }catch(Exception e){
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (mThread == null) {
            mThread = new DrawingThread();
        }
        mThread.keepDrawing = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.keepDrawing = false;
        boolean joined = false;
        while (!joined) {
            try {
                mThread.join();
                joined = true;
            } catch (InterruptedException e) {
            }
        }
        mThread = null;
        this.gameManager.destroy();
    }

    private class DrawingThread extends Thread {
        // Utilisé pour arrêter le dessin quand il le faut
        boolean keepDrawing = true;

        @Override
        public void run() {
            while (keepDrawing) {
                Canvas canvas = null;
                try {
                    // On récupère le canvas pour dessiner dessus
                    canvas = mHolder.lockCanvas();
                    // On s'assure qu'aucun autre thread n'accède au holder
                    synchronized (mHolder) {
                        // Et on dessine
                        if(canvas != null){ tick(canvas); }

                    }
                } catch(Exception e){
                    synchronized(System.out){
                        System.out.println(e.getMessage());
                    }
                }finally {
                    // Notre dessin fini, on relâche le Canvas pour que le dessin s'affiche
                    if (canvas != null)
                        mHolder.unlockCanvasAndPost(canvas);
                }
                // Pour dessiner à 30 fps
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
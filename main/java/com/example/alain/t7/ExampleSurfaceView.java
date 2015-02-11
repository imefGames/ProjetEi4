package com.example.alain.t7;

/**
 * Created by Alain on 21/01/2015.
 */


        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.Bitmap.Config;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Path;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.util.AttributeSet;
        import android.view.SurfaceHolder;
        import android.view.SurfaceView;

        import java.util.ArrayList;
        import java.util.Map;
        import java.util.HashMap;




public class ExampleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    // Le holder
    SurfaceHolder mSurfaceHolder;
    // Le thread dans lequel le dessin se fera
    DrawingThread mThread;

    Bitmap gameBackground = null;
//    Bitmap d = null;
//    Bitmap d2 = null;

    Bitmap saveBitmap = null;

    Canvas canvasBackup= null;

    Drawable d = null;
    Drawable dMH = null;
    Drawable dMV = null;
    ArrayList map = null;

    Map<String, Drawable> drawables = new HashMap<String, Drawable>();

    public ExampleSurfaceView (Context context) {
        super(context);
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        mThread = new DrawingThread();

//        d = BitmapFactory.decodeResource(
//                getResources(),
//                R.drawable.grid);
//
//
//
//        d2 = BitmapFactory.decodeResource(
//                getResources(),
//                R.drawable.grid);

        d = getResources().getDrawable(R.drawable.grid);
        dMH = getResources().getDrawable(R.drawable.mh);
        dMV = getResources().getDrawable(R.drawable.mv);

        drawables.put("rv", getResources().getDrawable(R.drawable.rv));
        drawables.put("rr", getResources().getDrawable(R.drawable.rr));
        drawables.put("rj", getResources().getDrawable(R.drawable.rj));
        drawables.put("rb", getResources().getDrawable(R.drawable.rb));

        drawables.put("cv", getResources().getDrawable(R.drawable.cv));
        drawables.put("cr", getResources().getDrawable(R.drawable.cr));
        drawables.put("cj", getResources().getDrawable(R.drawable.cj));
        drawables.put("cb", getResources().getDrawable(R.drawable.cb));
        drawables.put("cm", getResources().getDrawable(R.drawable.cm));
    }

    @Override
    protected void onDraw(Canvas pCanvas) {
        // Dessinez ici !
        super.onDraw(pCanvas);

        if (pCanvas != null) {
//            if (saveBitmap == null) {

//                Drawable d = getResources().getDrawable(R.drawable.grid);
            d.setBounds(2, 200, 1076, 1276);
            d.draw(pCanvas);

                if(map != null)
                {
                    for(Object p: map){
                        Point myp = (Point)p;
//                        System.out.println("View Point :");
//                        System.out.println(myp.getX());
//                        System.out.println(myp.getY());
//                        System.out.println(myp.getType());
                        if(myp.getType() == "mh") {
                            dMH.setBounds(myp.getX()*67+2, myp.getY()*67+200, (myp.getX()+1)*67+3, myp.getY()*67+203);
                            dMH.draw(pCanvas);
                        }
                        if(myp.getType() == "mv") {
                            dMV.setBounds(myp.getX()*67, myp.getY()*67+200, myp.getX()*67+3, (myp.getY()+1)*67+203);
                            dMV.draw(pCanvas);
                        }

//                        System.out.println("Type ::");
//                        System.out.println(myp.getType());

                        if(drawables.get(myp.getType()) != null) {

//                            System.out.println("Cible ou Robot");
                            drawables.get(myp.getType()).setBounds(myp.getX()*67+2, myp.getY()*67+202, (myp.getX()+1)*67+2, (myp.getY()+1)*67+202);
                            drawables.get(myp.getType()).draw(pCanvas);
                        }

                    }
                }



//                d2.setBounds(100, 300, 576, 576);
//                d2.draw(pCanvas);

//                Drawable d2 = getResources().getDrawable(R.drawable.grid);
//                d2.setBounds(100, 100, 452, 69);
//                d2.draw(pCanvas);
//                gameBackground = drawableToBitmap(d);


//                Canvas singleUseCanvas = new Canvas();
//
//                saveBitmap = Bitmap.createBitmap(pCanvas.getWidth(),pCanvas.getHeight(),Bitmap.Config.ARGB_8888);
//                singleUseCanvas.setBitmap(saveBitmap);
//
//                d.getScaledHeight(pCanvas);
//                d.getScaledWidth(pCanvas);
//                d.setHeight()
//
//                pCanvas.drawBitmap(d, 0, 0, null);
//                pCanvas.drawBitmap(d2, 100, 100, null);


//            }
//            else
//            {
////                pCanvas = canvasBackup;
////                Drawable d = new BitmapDrawable(getResources(),gameBackground);
//                pCanvas.setBitmap(saveBitmap);
//                Drawable d = getResources().getDrawable(R.drawable.grid);
//                d.setBounds(2, 400, 1078, 1276);
//                d.draw(pCanvas);
//
//            }


            postInvalidate();
        }


    }

    public void setBackgroundMap(ArrayList objects)
    {
        map = objects;
    }


    public void loadBitmapFromView(Canvas canvas) {
        gameBackground = Bitmap.createBitmap((int)canvas.getWidth(), (int)canvas.getHeight(), Bitmap.Config.RGB_565);
        canvas.setBitmap(gameBackground);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Que faire quand le surface change ? (L'utilisateur tourne son téléphone par exemple)
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread.keepDrawing = true;
        mThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mThread.keepDrawing = false;

        boolean joined = false;
        while (!joined) {
            try {
                mThread.join();
                joined = true;
            } catch (InterruptedException e) {}
        }
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
                    canvas = mSurfaceHolder.lockCanvas();
                    // On s'assure qu'aucun autre thread n'accède au holder
                    synchronized (mSurfaceHolder) {
                        // Et on dessine
                        onDraw(canvas);
                    }
                } finally {
                    // Notre dessin fini, on relâche le Canvas pour que le dessin s'affiche
                    if (canvas != null)
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                }

                // Pour dessiner à 50 fps
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {}
            }
        }
    }
}
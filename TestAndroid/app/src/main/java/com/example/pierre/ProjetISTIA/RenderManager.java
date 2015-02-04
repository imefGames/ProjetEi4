package com.example.pierre.ProjetISTIA;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;

/**
 * Created by Pierre on 21/01/2015.
 */
public class RenderManager {
    private Canvas target;
    private Paint brush;
    private Resources resources;
    private SparseArray<Drawable> resourceMap;

    public RenderManager(Resources resources){
        this.target = null;
        this.brush = new Paint();
        this.brush.setColor(Color.WHITE);
        this.resources = resources;
        this.resourceMap = new SparseArray<Drawable>();
    }

    public void setTarget(Canvas target){
        this.target = target;
    }

    public void setColor(int color){
        this.brush.setColor(color);
    }

    public void paintScreen(){
        this.target.drawColor(this.brush.getColor());
    }

    public void drawCircle(float x, float y, int radius){
        this.target.drawCircle(x, y, radius, this.brush);
    }

    public void drawRect(float x1, float y1, float x2, float y2){
        this.target.drawRect(x1, y1, x2, y2, this.brush);
    }

    public void drawImage(int x1, int y1, int x2, int y2, int image){
        Drawable d = this.resourceMap.get(image);
        if (d == null) {
            return;
        }
        d.setBounds(x1, y1, x2, y2);
        d.draw(this.target);
    }

    public void loadImage(int image){
        this.resourceMap.append(image, this.resources.getDrawable(image));
    }
}

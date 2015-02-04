package com.example.pierre.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 21/01/2015.
 */
public class GameButton implements IGameObject {

    private int x, y, w, h;
    private int imageUp, imageDown;
    private boolean btDown;
    private int targetScreen;

    public GameButton(int x, int y, int w, int h, int imageUp, int imageDown){
        this.create();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.imageDown = imageDown;
        this.imageUp = imageUp;
    }

    public void setTargetScreen(int target){
        this.targetScreen = target;
    }

    @Override
    public void create() {
        this.btDown = false;

    }

    @Override
    public void load(RenderManager renderManager) {
        renderManager.loadImage(this.imageDown);
        renderManager.loadImage(this.imageUp);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.WHITE);
        if(this.btDown){
            renderManager.drawImage(this.x, this.y, this.x + this.w, this.y + this.h, this.imageDown);
        }else{
            renderManager.drawImage(this.x, this.y, this.x + this.w, this.y + this.h, this.imageUp);
        }
    }

    @Override
    public void update(GameManager gameManager) {
        InputManager inputManager = gameManager.getInputManager();
        if(inputManager.eventHasOccurred()){
            float x = inputManager.getTouchX(), y = inputManager.getTouchY();
            if(inputManager.downOccurred()){
                this.btDown = (x > this.x  && x < this.x+this.w && y > this.y  && y < this.y+this.h);
            }
            if(inputManager.moveOccurred()){
                this.btDown = (x > this.x  && x < this.x+this.w && y > this.y  && y < this.y+this.h);
            }
            if(inputManager.upOccurred()){
                if(this.btDown){
                    this.btDown = false;
                    inputManager.resetEvents();
                    gameManager.setGameScreen(this.targetScreen);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}

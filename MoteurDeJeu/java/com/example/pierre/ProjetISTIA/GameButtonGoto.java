package com.example.pierre.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 21/01/2015.
 */
public class GameButtonGoto extends GameButton implements IGameObject {
    private int targetScreen;

    public GameButtonGoto(int x, int y, int w, int h, int imageUp, int imageDown){
        super(x, y, w, h, imageUp, imageDown);
    }

    public void setTargetScreen(int target){
        this.targetScreen = target;
    }

    @Override
    public void update(GameManager gameManager) {
        InputManager inputManager = gameManager.getInputManager();
        if(inputManager.eventHasOccurred() && inputManager.upOccurred() && this.btDown){
            gameManager.setGameScreen(this.targetScreen);
        }
        super.update(gameManager);
    }
}

package com.example.pierre.ProjetISTIA;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Pierre on 21/01/2015.
 */
public class MainMenuGameScreen extends GameScreen implements IGameObject {

    public MainMenuGameScreen(GameManager gameManager){
        super(gameManager);
        this.create();
    }

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;
        this.instances.add(new GameButtonGoto(ws2-128, hs2-512, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down));
        this.instances.add(new GameButtonGoto(ws2-128, hs2-128, 256, 256, R.drawable.bt_settings_up, R.drawable.bt_settings_down));
        this.instances.add(new GameButtonGoto(ws2 - 128, hs2 + 256, 256, 256, R.drawable.bt_credits_up, R.drawable.bt_credits_down));
        ((GameButtonGoto)this.instances.get(0)).setTargetScreen(1);
        ((GameButtonGoto)this.instances.get(1)).setTargetScreen(2);
        ((GameButtonGoto)this.instances.get(2)).setTargetScreen(3);
        this.load(this.gameManager.getRenderManager());
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.WHITE);
        renderManager.paintScreen();
        super.draw(renderManager);
    }
}
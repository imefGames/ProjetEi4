package com.example.pierre.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 04/02/2015.
 */
public class SettingsGameScreen extends GameScreen implements IGameObject {


    public SettingsGameScreen(GameManager gameManager){
        super(gameManager);
        this.create();
    }

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        int hs2 = this.gameManager.getScreenHeight()/2;
        this.instances.add(new GameButtonGoto(ws2-128, hs2-128, 256, 256, R.drawable.bt_back_up, R.drawable.bt_back_down));
        ((GameButtonGoto)this.instances.get(0)).setTargetScreen(0);
        super.create();
        this.load(this.gameManager.getRenderManager());
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.YELLOW);
        renderManager.paintScreen();
        super.draw(renderManager);
    }

    @Override
    public void update(GameManager gameManager) {
        super.update(gameManager);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

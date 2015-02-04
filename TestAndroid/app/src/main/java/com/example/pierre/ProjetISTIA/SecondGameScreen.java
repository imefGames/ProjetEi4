package com.example.pierre.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 21/01/2015.
 */
public class SecondGameScreen extends GameScreen implements IGameObject {


    public SecondGameScreen(GameManager gameManager){
        super(gameManager);
        this.create();
    }

    @Override
    public void create() {
        int ws2 = this.gameManager.getScreenWidth()/2;
        this.instances.add(new GameButton(ws2-128, ws2-128, 256, 256, R.drawable.bt_settings_up, R.drawable.bt_settings_down));
        ((GameButton)this.instances.get(0)).setTargetScreen(0);
        super.create();
        this.load(this.gameManager.getRenderManager());
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.GREEN);
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

package com.example.pierre.ProjetISTIA;

import android.graphics.Color;

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
        this.instances.add(new GameButton(ws2-128, hs2-128, 256, 256, R.drawable.bt_start_up, R.drawable.bt_start_down));
        ((GameButton)this.instances.get(0)).setTargetScreen(1);
        super.create();
        this.load(this.gameManager.getRenderManager());
    }

    @Override
    public void load(RenderManager renderManager) {
        super.load(renderManager);
    }

    @Override
    public void draw(RenderManager renderManager) {
        renderManager.setColor(Color.WHITE);
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

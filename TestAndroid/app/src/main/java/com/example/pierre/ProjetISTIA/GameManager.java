package com.example.pierre.ProjetISTIA;

import android.util.SparseArray;

/**
 * Created by Pierre on 04/02/2015.
 */
public class GameManager {
    private GameScreen currentScreen;
    private SparseArray<GameScreen> screens;
    private InputManager inputManager;
    private RenderManager renderManager;
    private int sWidth, sHeight;

    public GameManager(InputManager inputManager, RenderManager renderManager, int sWidth, int sHeight){
        this.inputManager = inputManager;
        this.renderManager = renderManager;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.screens = new SparseArray<GameScreen>();
        //list of all screens
        this.screens.append(0, new MainMenuGameScreen(this));
        this.screens.append(1, new SecondGameScreen(this));
        //end of list of all screens
        this.currentScreen = this.screens.get(0);
    }

    public int getScreenWidth(){
        return this.sWidth;
    }

    public int getScreenHeight(){
        return this.sHeight;
    }

    public RenderManager getRenderManager(){
        return this.renderManager;
    }

    public InputManager getInputManager(){
        return this.inputManager;
    }

    public GameScreen getCurrentScreen(){
        return this.currentScreen;
    }

    public void update(){
        this.currentScreen.update(this);
    }

    public void draw(){
        this.currentScreen.draw(this.renderManager);
    }

    public void destroy() {
        for(int i=0; i<this.screens.size(); i++){
            this.screens.get(this.screens.keyAt(i)).destroy();
        }
    }

    public void setGameScreen(int nextScreen){
        this.currentScreen = this.screens.get(nextScreen);
    }
}

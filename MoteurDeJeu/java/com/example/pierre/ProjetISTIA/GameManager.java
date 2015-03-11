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

    /*
     * Constructeur de la classe GameManager.
     * @param Référence du manager d'entrées (InputManager).
     * @param Référence du manager de l'affichage (RenderManager).
     * @param Largeur de l'écran.
     * @param Hauteur de l'écran.
     */
    public GameManager(InputManager inputManager, RenderManager renderManager, int sWidth, int sHeight){
        this.inputManager = inputManager;
        this.renderManager = renderManager;
        this.sWidth = sWidth;
        this.sHeight = sHeight;
        this.screens = new SparseArray<GameScreen>();
        //list of all screens
        this.screens.append(0, new MainMenuGameScreen(this));
        this.screens.append(1, new GameOptionsGameScreen(this));
        this.screens.append(2, new SettingsGameScreen(this));
        this.screens.append(3, new CreditsGameScreen(this));

        this.screens.append(4, new GridGameScreen(this));
        //end of list of all screens
        this.currentScreen = this.screens.get(0);
    }

    public SparseArray<GameScreen> getScreens(){
        return this.screens;
    }

    /*
     * Retourne la largeur de l'écran.
     * @return Largeur de l'écran
     */
    public int getScreenWidth(){
        return this.sWidth;
    }

    /*
     * Retourne la hauteur de l'écran.
     * @return Hauteur de l'écran
     */
    public int getScreenHeight(){
        return this.sHeight;
    }

    /*
     * Retourne la référence du manager d'affichage.
     * @return Manager d'affichage
     */
    public RenderManager getRenderManager(){
        return this.renderManager;
    }

    /*
     * Retourne la référence du manager d'entrées
     * @return Manager d'entrées
     */
    public InputManager getInputManager(){
        return this.inputManager;
    }

    /*
     * Retourne la référence de l'écran de jeu en cours d'utilisation.
     * @return Référence d'un écran de jeu
     */
    public GameScreen getCurrentScreen(){
        return this.currentScreen;
    }

    /*
     * Met à jour l'écran de jeu en cours d'utilisation.
     * C'est-à-dire que tous les Objets appartenant à cet écran seront eux aussi mis à jour.
     */
    public void update(){
        this.currentScreen.update(this);
    }

    /*
     * Affiche l'écran de jeu en cours d'utilisation.
     * C'est-à-dire que tous les Objets appartenant à cet écran seront affichés.
     */
    public void draw(){
        this.currentScreen.draw(this.renderManager);
    }

    /*
     * Détruit tous les écrans de jeu existants ainsi que tous les Objets dans ces écrans de jeu.
     */
    public void destroy() {
        for(int i=0; i<this.screens.size(); i++){
            this.screens.get(this.screens.keyAt(i)).destroy();
        }
    }

    /*
     * Modifie l'écran de jeu actuel. Permet donc de passer d'un écran de jeu à un autre.
     * @param Index du nouvel écran de jeu
     */
    public void setGameScreen(int nextScreen){
        this.currentScreen = this.screens.get(nextScreen);
    }
}

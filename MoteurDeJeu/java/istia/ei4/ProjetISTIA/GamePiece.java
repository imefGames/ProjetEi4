package istia.ei4.ProjetISTIA;

import android.graphics.Color;

/**
 * Created by Pierre on 25/02/2015.
 */
public class GamePiece implements IGameObject {

    private int x                   = 0;
    private int y                   = 0;
    private int xObjective          = 0;
    private int yObjective          = 0;
    private int xGrid               = 400;
    private int yGrid               = 500;
    private int xDraw               = 0;
    private int yDraw               = 0;
    private float widthCell           = 16;
    private float heightCell          = 16;
    private int radius              = 32;
    private int color               = Color.RED;


    public int getColor() {
        return color;
    }

    public int getxObjective() {
        return xObjective;
    }

    public int getyObjective() {
        return yObjective;
    }

    public void setyObjective(int yObjective) {
        this.yObjective = yObjective;
        this.y = this.yObjective; //Todo : enlever et mettre une animation
    }

    public void setxObjective(int xObjective) {
        this.xObjective = xObjective;

        this.x = this.xObjective; //Todo : enlever et mettre une animation
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
    public GamePiece(int x, int y, int color){
        this.x = x;
        this.y = y;
        this.xObjective = x;
        this.yObjective = y;
        this.color = color;
    }

    public void setGridDimensions(int xGrid, int yGrid, float cellSize){
        // TODO: modifier les dimensions de la grille & le rayon du pion.
        this.xGrid = xGrid;
        this.yGrid = yGrid;
        this.widthCell = this.heightCell = cellSize;
        this.radius = (int) (cellSize / 2);

    }

    @Override
    public void create(){
    }

    @Override
    public void load(RenderManager renderManager){
    }

    @Override
    public void draw(RenderManager renderManager){
        renderManager.setColor(this.color);
        //afficher le pion

        xDraw = (int)(this.xGrid+(this.x+0.5f)*this.widthCell);
        yDraw = (int)(this.yGrid+(this.y+0.5f)*this.heightCell);
        renderManager.drawCircle(xDraw, yDraw, this.radius);
    }

    @Override
    public void update(GameManager gameManager){
        //si le pion n'est pas en mouvement, ...
        if(this.x == this.xObjective && this.y == this.yObjective){
            //si il y a une entrée utilisateur, ...
            InputManager inputManager = gameManager.getInputManager();
            if(inputManager.eventHasOccurred()){
                int xTouch, yTouch, dx, dy;
                xTouch = (int)inputManager.getTouchX();
                yTouch = (int)inputManager.getTouchY();
                dx = xTouch - this.xDraw;
                dy = yTouch - this.yDraw;
                //si l'utilisateur a touché le pion, ...
                if(dx*dx + dy*dy <= this.radius*this.radius && inputManager.downOccurred()){
                    //afficher l'interface de mouvement
                    ((GridGameScreen)(gameManager.getCurrentScreen())).activateInterface(this, xDraw, yDraw);


                }
            }
        }else{ //sinon (si le pion doit bouger),
            //todo: bouger le pion

        }
    }

    @Override
    public void destroy(){
    }

}
